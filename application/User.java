package application;

public class User {
	private String name;
	private String email;
	private String password;
	private boolean isAdmin;
	private boolean isPublic;
	
	public User(String name, String email, String password) {
		this.name = name;
		this.email = email;
		this.password = password;
		isPublic = true;
	}
	
	public boolean auth(String password) {
		return this.password.equals(password);
	}
	
	public String getName() {
		return name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public boolean getAdmin() {
		return isAdmin;
	}
	
	public boolean isPublic() {
		return isPublic;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	
	public void setPublic(boolean isPublic) {
		this.isPublic = isPublic;
	}
}
