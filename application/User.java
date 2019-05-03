package application;
/**
 * User Class
 * 
 * @author erica, ben, aj, callan
 *
 */

//This class represents a User
public class User {
	private String name;
	private String email;
	private String type;
	private String password;
	private boolean isAdmin;
	private boolean isPublic;

	//Constructor
	//@param String name, email
	   public User(String name, String email) {
	        this.name = name;
	        this.email = email;
	        this.isPublic = true;
	    }
	

	//Constructor
	//@param String name, email, password
	public User(String name, String email, String password) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.isPublic = true;
	}
	
	//returns if the entered password is correct
	//@return true if password is a match, false if not
	public boolean auth(String password) {
		return this.password.equals(password);
	}
	
	//@return String name
	public String getName() {
		return name;
	}
	
	//@return String email
	public String getEmail() {
		return email;
	}
	
	//@return String password
	public String getPassword() {
		return password;
	}
	
	//@return boolean isAdmin
	public boolean getAdmin() {
		return isAdmin;
	}
	
	//@return boolean isPublic
	public boolean isPublic() {
		return isPublic;
	}
	
	//@param String name
	public void setName(String name) {
		this.name = name;
	}
	
	//@param String email
	public void setEmail(String email) {
		this.email = email;
	}
	
	//@param String password
	public void setPassword(String password) {
		this.password = password;
	}
	
	//@param boolean isAdmin
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	
	//@param boolean isPublic
	public void setPublic(boolean isPublic) {
		this.isPublic = isPublic;
	}
	
	public void setType(String t) {
		type = t;
	}
	
	public String getType() {
		return this.type;
	}
	
}
