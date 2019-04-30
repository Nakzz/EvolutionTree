package application;

import java.util.List;

public class UserDriverApplication {

  private boolean isLogged = false;
  private String username = null;
  private boolean isAdmin = false;
  private boolean isPopulated = false;
  private final String jsonFilePath = ""; //TODO: add a jsonFilePath
  
  public UserDriverApplication() throws UserExists{
    // registering a new user
    
    //add user, if user already exisiting, throw user Exists eception
    
    //login user
  }
  
  //loggin in with an exisiting user
  public UserDriverApplication(String username) throws InvalidUsername {
  
    //TODO: populate the internal data structure
    if(!isPopulated)
      populateDatastructureWithUsers(jsonFilePath);
    
    if(login(username)) {
      this.isLogged = true;
      //TODO: generate users from JSON file
      
    }else {
      this.isLogged = false;
      throw new InvalidUsername();
    }
    
  }
  
  public boolean login(String username) {
    //IF USER EXISTS, then do the following
      //SET USERNAME field.
      //SET isAdmin field
    
    //else return false
    return false;
    
  } 
  
  public void logout() {
    //TODO: reset the fields
    //
  }
  
  public boolean addUser(String username, List profileInfo) {
    
    return false;
  }
  
  public void populateDatastructureWithUsers(String jsonFilePath) {
    //TODO: read from JSON, for each object, add user
    //TODO: set data populated true
  }
  
  public List<User> searchUser(List profileInfo){
    
    return null;
  }
  
  public boolean editUser(String username, List profileInfo) {
    return false;
  }
  
  
  //GETTER METHODS
  
  public boolean isLoggedIn() {
    return this.isLogged;
  }
  
  public boolean isAdmin() {
    return this.isAdmin;
  }
  
  public String getUsername() {
    return this.username;
  }
  
}
