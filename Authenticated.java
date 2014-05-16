public abstract class Authenticated {
	private String username;		// username of the user which he uses to log into the system
	private String password;		// the password of the user
	
	public Authenticated(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	// TODO: define methods (probably queries)
	public getUsername() {
		return this.username;
	}
	
	public boolean checkPassword(String password) {
		return (this.password == password);
	}
}