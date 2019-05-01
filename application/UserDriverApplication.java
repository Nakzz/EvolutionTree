package application;

import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

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
  
  //login in with an exisiting user
  public UserDriverApplication(String username) throws InvalidUsername {
  
    //TODO: populate the internal data structure
    if(!isPopulated)
      try {
        populateDatastructureWithUsers(jsonFilePath);
        
      } catch (FileNotFoundException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      } catch (ParseException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    
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
  
  public void populateDatastructureWithUsers(String jsonFilePath) throws FileNotFoundException, IOException, ParseException  {
 
    JSONParser parser = new JSONParser();
    
    try {
      Object obj = parser.parse(new FileReader(jsonFilePath));

      JSONObject jsonObject = (JSONObject) obj;

      // loop array TODO: check with file
      JSONArray users = (JSONArray) jsonObject.get("users");


      for (int i = 0; i < users.size(); i++) {
        JSONObject packageItem = (JSONObject) users.get(i); 

        //TODO: GET ALL THE FIELDS. there has to be a better way of doing this. 
        String packageName = (String) packageItem.get("name"); // gets each packgename
        JSONArray dependencies = (JSONArray) packageItem.get("dependencies"); // gets each dependencies

        System.out.println("Package name: " + packageName);

       //TODO: add all the users

      }

//      System.out.println("DONE!");


    } catch (FileNotFoundException e) {
      throw new FileNotFoundException();
    } catch (IOException e) {
      throw new IOException();
    } catch (ParseException e) {
      throw new ParseException(0);
    }
    
    
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
