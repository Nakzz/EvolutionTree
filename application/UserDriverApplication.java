package application;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

// TODO: Auto-generated Javadoc
/**
 * The Class UserDriverApplication.
 */
public class UserDriverApplication {


  private boolean isLogged = false; // if the user is logged in
  private String username = null; // the username of the user
  private boolean isAdmin = false; // if the user has admin priv
  private boolean isPopulated = false;
  private final String jsonFilePath = ""; //TODO: add a jsonFilePath
  private Map<String, Category> database =null;
  private List<String> listOfUsers = null;

  //CONSTANTS
  private final String USERS_CATEGORY = "USERS_CATEGORY";

  /**
   * Instantiates a new user driver application by just populating the database and initializing proper fields.
   * The DEV must register the user manually using register(username) method.
   *
   * @param username the username
   * @param type the type
   * @throws UserExists the user exists
   */
  public UserDriverApplication() throws UserExists{


    //populating database
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

  }

  /**
   * Instantiates a new user driver application by logging in with a user.
   *
   * @param username the username
   * @throws InvalidUsername the invalid username
   */
  //login in with an exisiting user
  public UserDriverApplication(String username) throws InvalidUsername{

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

    try{
      login(username);

    } catch (InvalidUsername e) {
      this.isLogged = false;
      throw new InvalidUsername();
    }
  }

  /**
   * Login user with current username if the user exists in the databse. If the username doesn't exist, then throw user not exists.
   *
   * @param username the username
   * @return true, if successful
   * @throws InvalidUsername the invalid username
   */
  public void login(String username) throws InvalidUsername {

    //IF USER EXISTS, then do the following
    if(this.listOfUsers.contains(username)) {
    //SET USERNAME field.

      this.username = username;
      this.isLogged = true;

      //SET isAdmin field
      //TODO: CHECK IF THE USER IS AN ADMIN

    } else {
    throw new InvalidUsername();
    }


  }
  
  /**
   * Registers user with current username if the user doesn't exists in the databse. If the username  exist, then throw UserExists.
   *
   * @param username the username
   * @throws UserExists the username existst
   */
  public void register(String username) throws UserExists {

    //IF USER doesn't EXISTS, then do the following
    if(!this.listOfUsers.contains(username)) {

    //add user to database
    try {
      addUser(username, null);
    } catch (UserExists e) {
      System.out.println("UserDriverApplication_register: THIS SHOULD NOT HAPPEN. Already checked that User exists.");
      e.printStackTrace();
    }

    //SET USERNAME field.
      //SET isAdmin field
      this.username = username;
      this.isAdmin = false;

    } else {
    throw new UserExists();
    }


  }

  /**
   * Logout the current user.
   */
  public void logout() {
    //reset the fields
    this.username = null;
    this.isAdmin = false;
    this.isPopulated= false;
    this.database = null;
    this.listOfUsers = null;
    //
  }

  /**
   * Adds the user to the database, and USERS_CATEGORY map.
   * For each profileInfo field, if corresponding Category doesn't exist, initialize a new field.
   *
   * @param username the username
   * @param profileInfo the profile info
   * @return true, if successful
   * @throws UserExists the user exists
   */
  private boolean addUser(String username, List profileInfo) throws UserExists{

    //TODO add user to each profileInfo field, if corresponding Category doesn't exist, initialize a new field.
    return false;
  }

  /**
   * Populate datastructure with users by parsing user informations from json file.
   * Also add users to Users_Category
   *
   * @param jsonFilePath the json file path
   * @throws FileNotFoundException the file not found exception
   * @throws IOException Signals that an I/O exception has occurred.
   * @throws ParseException the parse exception
   */
  public void populateDatastructureWithUsers(String jsonFilePath) throws FileNotFoundException, IOException, ParseException  {

    if(database == null) {
      database = new HashMap<String, Category>();

      Category userList = new Category(USERS_CATEGORY);

     database.put(USERS_CATEGORY, userList);

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

  /**
   * Search user in the database.
   *
   * @param profileInfo the profile info
   * @return the list
   * @throws InvalidUsername the invalid username
   */
  public List<User> searchUser(List profileInfo) throws InvalidUsername{

    return null;
  }

  /**
   * Edits the user's information if own profile or if the user is an admin.
   *
   * @param username the username
   * @param profileInfo the profile info
   * @return true, if successful
   * @throws InvalidUsername the invalid username
   */
  public boolean editUser(String username, List profileInfo) throws InvalidUsername{

    if(username == this.username || this.isAdmin) {

      //TODO: edit user information

      return true;
    }


    return false;
  }


  //GETTER METHODS

  /**
   * Checks if is logged in.
   *
   * @return true, if is logged in
   */
  public boolean isLoggedIn() {
    return this.isLogged;
  }

  /**
   * Checks if is admin.
   *
   * @return true, if is admin
   */
  public boolean isAdmin() {
    return this.isAdmin;
  }

  /**
   * Gets the username.
   *
   * @return the username
   */
  public String getUsername() {
    return this.username;
  }

}
