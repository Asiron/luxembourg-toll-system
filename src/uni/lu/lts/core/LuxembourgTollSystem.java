/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uni.lu.lts.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import uni.lu.lts.facility.Section;
import uni.lu.lts.facility.TollFacility;
import uni.lu.lts.facility.record.Record;
import uni.lu.lts.facility.record.SensorReadingError;
import uni.lu.lts.facility.record.TollSystemRecord;
import uni.lu.lts.facility.sensor.Sensor;
import uni.lu.lts.users.Account;
import uni.lu.lts.users.Account.AccountType;
import uni.lu.lts.users.AccountFactory;
import uni.lu.lts.users.Permission;
import uni.lu.lts.users.VehicleOwner;
import uni.lu.lts.util.CollectionsUtil;
import uni.lu.lts.vehicle.Vehicle;
import uni.lu.lts.vehicle.VehicleFactory;

/**
 *
 * @author asiron
 */
public class LuxembourgTollSystem {

    private Lock ltsLock;
    private Map<String, Section> sections;
    private Map<String, Account> accounts;
    private Set<Vehicle> recordedVehicles;
    private Account loggedIn = null;
    
    public LuxembourgTollSystem(Lock ltsLock) {
        this.ltsLock = ltsLock;
        this.sections = new HashMap<>();
        this.accounts = new HashMap<>();
        this.recordedVehicles = new HashSet<>();
        this.accounts.put("root", AccountFactory.createAccount(Account.AccountType.ROOT, "root", "root"));
        this.accounts.put("asiron", AccountFactory.createAccount(AccountType.REGULAR, "asiron", "bullshit"));
    }

    /**
     * Get the value of sections
     *
     * @return the value of sections
     */
    public Map<String, Section> getSections() {
        return sections;
    }

    /**
     * Set the value of sections
     *
     * @param sections new value of sections
     */
    public void setSections(Map<String, Section> sections) {
        this.sections = sections;
    }
    
    public Account login(String login, String password) {
        Account account = accounts.get(login);
        if (account != null && account.checkPassword(password)) {
            System.out.println("Successfully logged in");
            return account;
        } else {
            System.out.println("Cannot log in");
        }
        return null;
    }
    
    public void logout() {
        loggedIn = null;
        System.out.println("Successfully logged out");
    }
    
    public void createAccount(String type, String login, String password) {
        
        if (accounts.containsKey(login)) {
            System.out.println("Counldn't create account, there already exists one account with the same login");
            return;
        }
        
        AccountType accountType;
        try {  
            accountType = AccountType.valueOf(type.toUpperCase());
        } catch (IllegalArgumentException iae) {
            System.out.println("Wrong account type");
            return;
        }
        
        Account account = AccountFactory.createAccount(accountType, login, password);
        if (account == null) {
            System.out.println("Counldn't create account, there was some problem");
            return;
        }
        accounts.put(login, account);
        System.out.println("Successfully created account");
    }
    
    public List<Record> fetchRecordDataForVehicle(String selector, String dataType, String[] conditions) {
        List<Record> data = new ArrayList<>();
        ltsLock.lock();
            for (Map.Entry<String, Section> entrySection : sections.entrySet()) {
                Section currentSection = entrySection.getValue();
                for (Map.Entry<String, TollFacility> entryTF: currentSection.getTollFacilities().entrySet()) {
                    TollFacility currentTF = entryTF.getValue();
                    
                    List<Record> pulledRecords = null;
                    
                    switch (dataType) {
                        case "tolls":
                            currentTF.pullBuffersFromSensorQueues();
                            if (selector.equals("all")) {
                                pulledRecords = new ArrayList<Record>(currentTF.getAllRecords());
                            } else {
                                pulledRecords = new ArrayList<Record>(currentTF.getRecords().get(selector));
                            }
                            break;
                        case "errors":
                            pulledRecords = new ArrayList<Record>(currentTF.getErrorList());
                            break;
                    }
                    
                    if (pulledRecords != null) { 
                        Iterator<Record> iter = pulledRecords.iterator();
                        while(iter.hasNext()) {
                            Record tsr = iter.next();
                            if (tsr.checkConditions(conditions) == false) {
                                iter.remove();
                            }
                        }
                        data.addAll(pulledRecords); 
                    }
                }
            }
        ltsLock.unlock();
        
        return data;
    }
        
    public List<Vehicle> fetchVehicleData(String[] conditions) {
        List<Vehicle> data = (List<Vehicle>) VehicleFactory.getAllVehicles();
        
        Iterator<Vehicle> iter = data.iterator();
        while(iter.hasNext()) {
            Vehicle vehicule = iter.next();
            if (vehicule.checkConditions(conditions) == false) {
                iter.remove();
            }
        }
        
        return data;
        
    }
 
    public List<Section> fetchSectionData(String[] conditions) {
        ltsLock.lock();
            List<Section> data = (List<Section>) sections.values();
        ltsLock.unlock();
        
        Iterator<Section> iter = data.iterator();
        while(iter.hasNext()) {
            Section section = iter.next();
            if (section.checkConditions(conditions) == false) {
                iter.remove();
            }
        }
        
        return data;
        
    }  
    
    public List<TollFacility> fetchTollFacilityData(String[] conditions) {
        ltsLock.lock();
            List<TollFacility> data =  new ArrayList<>();
            for (Map.Entry<String, Section> entrySection : sections.entrySet()) {
                Section section = entrySection.getValue(); 
                
                for (Map.Entry<String, TollFacility> entryTF : section.getTollFacilities().entrySet()) {
                    String tollFacilityID = entryTF.getKey();
                    TollFacility tf = entryTF.getValue();
                    
                    if(tf.checkConditions(conditions) == true) {
                        data.add(tf);
                    }
                }
            }
        ltsLock.unlock();
        
        return  data;
        
    }   
    
        
    public List<?> fetchData(String selector, String dataType, String sortBy, String[] conditions) {
        
        List<?> results = null;
        
        if (selector.equals("my")) {
            String myNumberPlate = ((VehicleOwner)loggedIn).getVehicle().getNumberPlate();
            results = fetchRecordDataForVehicle(myNumberPlate, dataType, conditions);
        } else if (selector.equals("all")) {
            switch (dataType) {
                case "errors": case "tolls":
                    results = fetchRecordDataForVehicle(selector, dataType, conditions);
                    break;
                case "vehicles":
                    results = fetchVehicleData(conditions);
                    break;
                case "sections":
                    results = fetchSectionData(conditions);
                    break;
                case "tfs":
                    results = fetchTollFacilityData(conditions);
                    break;
            }
        } else if (selector.length() > 0) {
            results = fetchRecordDataForVehicle(selector, dataType, conditions);
        } 
        
        return  CollectionsUtil.sortBy(results, sortBy);
        
    }
    
    public void start() {
        Scanner scanner = new Scanner(System.in);
        String line;
        while(true) {
            System.out.println("");
            System.out.print(">>");
            line = scanner.nextLine();
            String[] tokens = line.trim().split("\\s+");
            
            switch (tokens[0].toLowerCase()) {
                case "login":
                    loginCommand(tokens);
                    break;
                case "logout":
                    logoutCommand(tokens);
                    break;
                case "create":
                    createCommand(tokens);
                    break;
                case "select":
                    selectCommand(tokens);
                    break;
                case "modify":
                    modifyCommand(tokens);
                    break;
                case "register":
                    registerCommand(tokens);
                    break;
                case "vehicle":
                    showVehicleCommmand(tokens);
                    break;
                case "help":
                    helpCommand();
                    break;
                default:
                    unrecognizedCommand();
                    break;             
            }
        }
    }
    
    private void loginCommand(String[] tokens) {
        if (loggedIn == null) {
            loggedIn = login(tokens[1], tokens[2]);
        } else if (loggedIn != null) {
            System.out.println("You are already logged in!");
        }
    }
    
    private void logoutCommand(String[] tokens) {
        if (loggedIn != null) {
            logout();
        } else if (loggedIn == null) {
            System.out.println("You are not logged in!");
        }
    }
    
    private void createCommand(String[] tokens) {
        if (loggedIn != null &&
            loggedIn.checkPermission(Permission.MODIFYACCOUNTS))
        {
            String accountType = tokens[1];
            String login       = tokens[2];
            String password    = tokens[3];

            createAccount(accountType, login, password);
        } else if (loggedIn == null || !loggedIn.checkPermission(Permission.MODIFYACCOUNTS))
        {
            System.out.println("Cannot create accounts without permissions");
        }
    }
      
    private void registerCommand(String[] tokens) {
        if (loggedIn != null &&
            loggedIn.checkPermission(Permission.REGISTERVEHICLE))
        {
            String vehicleType = tokens[1];
            String countryCode = tokens[2];
            String numberPlate = tokens[3];
            float vehHeight   = Float.parseFloat(tokens[4]);
            
            VehicleOwner vehicleOwner = (VehicleOwner) loggedIn;
            
            if (!vehicleOwner.registerVehicle(vehicleType, numberPlate, countryCode, vehHeight)) {
                System.out.println("Couldn't register the vehicle");
            } else {
                System.out.println("Registered vehicle successfully");
            }
         
        } else if (loggedIn != null && 
            !loggedIn.checkPermission(Permission.REGISTERVEHICLE))
        {
            System.out.println("You don't have permission to do that");
        } else if (loggedIn == null) {
            System.out.println("You have to be logged in to do that");
        }     
    }
    
    private void showVehicleCommmand(String[] tokens) {
        
        if (loggedIn != null &&
            loggedIn.checkPermission(Permission.REGISTERVEHICLE))
        {
            VehicleOwner vehicleOwner = (VehicleOwner) loggedIn;
            System.out.println("Vehicle: " + vehicleOwner.getVehicle().toString());
        } else {
            System.out.println("You don't have permission or you are not logged in");
        }
    }
    
    private void selectCommand(String[] tokens) {
        
            String selector  = tokens[1];
            String object    = tokens[2];
            String wantsSort = tokens[3];
            String sortBy    = tokens[4];     
            String wantsCond = tokens[5];
            
            String[] conditions = Arrays.copyOfRange(tokens, 6, tokens.length);
            
        if (loggedIn != null &&
            loggedIn.checkPermission(Permission.READONLYSELF))
        {
            switch (selector) {
                case "my":     
                    printResults(fetchData(selector, object, sortBy, conditions));
                    break;
                case "all":
                    System.out.println("You can't select \"all\" as regular user");
                    break;
                default:
                    System.out.println("only \"my\" or \"all\" are allowed for specifing quantity of selected objects");
                    break;
            }
            
        } else if (loggedIn != null &&
                   loggedIn.checkPermission(Permission.READALL))
        {
            printResults(fetchData(selector, object, sortBy, conditions));          
        } else {
            System.out.println("You don't have permission or you are not logged in");
        }
    }
        
    private void modifyCommand(String[] tokens) {
        
    }  

    private void helpCommand() {
        System.out.printf("Usage:\n"
                        + "login <username> <password> \t\t: to log into system\n"
                        + "logout                      \t\t: to log out from system\n"
                        + "create <type> <username> <password> \t: to create new account\n"
                        + "vehicle                             \t: to display current registered vehicle\n"
                        + "select help                         \t: to display help about select from DB\n"
                        + "modify help                         \t: to display help about modifying DB\n"
                        + "help                                \t: to display this help\n");
    }

    private void unrecognizedCommand() {
        System.out.println("Unrecognized command, try \"help\" to display information");
    }

    private void printResults(List<?> results) {
        int i = 1;
        for (Object object : results) {
            System.out.println(i + " " +object);
            i++;
        }
    }
}
