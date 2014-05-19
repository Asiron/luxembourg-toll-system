package uni.lu.lts.core;

public abstract class Authenticated {
	private String username;		// username of the user which he uses to log into the system
	private String password;		// the password of the user
	
	public Authenticated(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public String getUsername() {
		return username;
	}
	
	public boolean checkPassword(String password) {
		return password.equals(password);
	}
}