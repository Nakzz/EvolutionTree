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
import java.lang.reflect.Array;

// TODO: Auto-generated Javadoc
/**
 * The Class UserDriverApplication.
 */

public class UserDriverApplication {


  private boolean isLogged = false; // if the user is logged in
  private String username = null; // the username of the user
  private boolean isAdmin = false; // if the user has admin priv
  private boolean isPopulated = false;
  
  private Map<String, Category> database = null;
  private int totalUsers;
  private List<String> listOfUsers = null; //TODO: maybe not going to need
  
  

  // CONSTANTS TODO: add these constants to a seperate file so Main.java can also access them 
  // TODO: match their string to what they actually are in the json file
  private final String USERS_CATEGORY = "USERS_CATEGORY";
  

  /**
   * Instantiates a new user driver application by just populating the database and initializing
   * proper fields. The DEV must register the user manually using register(username) method.
   * 
   * After registering the user, use editUser(username, profileInfo) to add information
   *
   * @param username the username
   * @param type the type
   * @throws UserExists the user exists
   */
  public UserDriverApplication() throws UserExists {


    // populating database
    if (!isPopulated)
      try {
        populateDatastructureWithUsers(Config.JSON_LOCATION);

      } catch (FileNotFoundException e) {
        System.out.println("ERROR: UserDriverApplication_constructor: ");
        System.out.println("  FileNotFoundException. Make sure JSON file path is proper");
        e.printStackTrace();
      } catch (IOException e) {
        System.out.println("ERROR: UserDriverApplication_constructor: ");
        System.out
          .println("  IOException. Make sure JSON file is not opened by another application");
        e.printStackTrace();
      } catch (ParseException e) {
        System.out.println("ERROR: UserDriverApplication_constructor: ");
        System.out.println("  ParseException. Check JSON file");
        e.printStackTrace();
      }

  }

  /**
   * Instantiates a new user driver application by logging in with a user.
   *
   * @param username the username
   * @throws InvalidUsername the invalid username
   */
  // login in with an exisiting user
  public UserDriverApplication(String username) throws InvalidUsername {

    if (!isPopulated)
      try {
        populateDatastructureWithUsers(Config.JSON_LOCATION);

      } catch (FileNotFoundException e) {
        System.out.println("ERROR: UserDriverApplication_constructor: ");
        System.out.println("  FileNotFoundException. Make sure JSON file path is proper");
        e.printStackTrace();
      } catch (IOException e) {
        System.out.println("ERROR: UserDriverApplication_constructor: ");
        System.out
          .println("  IOException. Make sure JSON file is not opened by another application");
        e.printStackTrace();
      } catch (ParseException e) {
        System.out.println("ERROR: UserDriverApplication_constructor: ");
        System.out.println("  ParseException. Check JSON file");
        e.printStackTrace();
      }

    try {
      login(username);

    } catch (InvalidUsername e) {
      this.isLogged = false;
      throw new InvalidUsername();
    }
  }

  /**
   * Login user with current username if the user exists in the databse. If the username doesn't
   * exist, then throw user not exists.
   *
   * @param username the username
   * @return true, if successful
   * @throws InvalidUsername the invalid username
   */
  public boolean login(String username) throws InvalidUsername {

    if (!this.isLogged) {// making sure a logged in user is not trying to change login
      // IF USER EXISTS, then do the following
      if (this.listOfUsers.contains(username)) {
        // SET USERNAME field.

        this.username = username;
        this.isLogged = true;

        // SET isAdmin field
        // TODO: CHECK IF THE USER IS AN ADMIN

        return true;
      } else {
        throw new InvalidUsername();
      }
    } else {
      return false; // making sure a logged in user is not trying to change login
    }
    
  }

  /**
   * Registers user with current username if the user doesn't exists in the databse. If the username
   * exist, then throw UserExists.
   *
   * @param username the username
   * @throws UserExists the username existst
   */
  public void register(String username) throws UserExists {

    // IF USER doesn't EXISTS, then do the following
    if (!this.listOfUsers.contains(username)) {

      // add user to database
      try {
        addUser(username, null);
      } catch (UserExists e) {
        System.out.println("ERROR: UserDriverApplication_register: ");
        System.out.println("  THIS SHOULD NOT HAPPEN. Already checked that User exists.");
        e.printStackTrace();
      }

      this.username = username; // SET USERNAME field.
      this.isAdmin = false; // SET isAdmin field

    } else {
      throw new UserExists();
    }


  }

  /**
   * Logout the current user.
   */
  public void logout() {
    // reset the fields
    this.username = null;
    this.isAdmin = false;
    this.isPopulated = false;
    this.database = null;
    this.listOfUsers = null;
    //
  }

  /**
   * Adds the user to the database, and USERS_CATEGORY map. For each profileInfo field, if
   * corresponding Category doesn't exist, initialize a new field.
   *
   * If username is null, return false
   *
   * @param username the username
   * @param profileInfo the profile info
   * @return true, if successful
   * @throws UserExists the user exists
   */
  private boolean addUser(String username, Map<String,ArrayList<String>> profileInfo) throws UserExists {

    if (this.listOfUsers.contains(username)) {
      throw new UserExists();
    } 
    
    if(username == null)
      return false;
    
    String catName;   
   

    //get arraylist that are required for any type of User
     ArrayList<String> profileTypeField = profileInfo.get(Config.PROFILE_TYPE_FIELD);
     ArrayList<String> nameField = profileInfo.get(Config.NAME_FIELD);
     ArrayList<String> isAdminField = profileInfo.get(Config.IS_ADMIN_FIELD);
     ArrayList<String> isPublicField = profileInfo.get(Config.IS_PUBLIC_FIELD);
     
     //access the first element since they should only have one element
     String name = nameField.get(0); 
//     String email = TODO: get the username 
     String profileTypeName = profileTypeField.get(0);
     Boolean isAdminText = Boolean.parseBoolean( isAdminField.get(0));
     Boolean isPublicText = Boolean.parseBoolean( isPublicField.get(0));
     
     Category userCategory = this.database.get(USERS_CATEGORY);
     Category cat = null;
     
     
     switch(profileTypeName) {
       case "student":
         //get the fields related to the student
         ArrayList<String> majorField = profileInfo.get(Config.MAJORS_FIELD);
         ArrayList<String> certificatesField = profileInfo.get(Config.CERTIFICATES_FIELD);
         ArrayList<String> scholarshipField = profileInfo.get(Config.SCHOLARSHIPS_FIELD);
         ArrayList<String> coursesField = profileInfo.get(Config.COURSES_FIELD);
         ArrayList<String> workField = profileInfo.get(Config.WORK_EXPERIANCES_FIELD);
         ArrayList<String> clubsField =  null; //profileInfo.get(Config.CLUBS_FIELD); // TODO: add clubs field
//       ArrayList<String> yearOfGradField = profileInfo.get(Config.CLUBS_FIELD); // TODO: add year of grad
         
         int yearOfGrad = 0; //TODO: parseInt from the first element of yearOfGradField
         
         //create a student user 
         Student newUser = new Student(yearOfGrad,majorField, certificatesField, clubsField, scholarshipField, coursesField, workField, name, username, profileTypeName);
         
         userCategory.insert(newUser); // this is the master category that contains all the users
         
         //FIXME: maybe in the future, do something that would iterate instead of hardcoding  
// if the fields sting. length is 0, create a new category
         if(majorField.size() != 0) {
           if(this.database.containsKey(Config.MAJORS_FIELD)){
             //add to exisiting category 
             cat = this.database.get(Config.MAJORS_FIELD);
             
             cat.insert(newUser);
           }
             }else {
               // create new category and add user to category
               cat = new Category(Config.MAJORS_FIELD);
               cat.insert(newUser);
               
               this.database.put(Config.MAJORS_FIELD, cat);
           }
           
             
          
         
         
         
         break;
       case "faculty":
         //get the fields related to the student
         

         // create a faculty user
         
         
         userCategory.insert(newUser);
         
         break;
       default:
         System.out.println("ERROR: UserDriverApplication_addUser: ");
         System.out.println("  ProfileType is not defined");
         return false;
     }
    
//if(profileInfo != null) {
// create new user with just the username
    
    
  this.totalUsers++; // given that a new user was added to the database
  

    return false; // if user was not added, otherwise should return true
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
    
    this.totalUsers = 0;
    
   
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
  public List<User> searchUser(List profileInfo) throws InvalidUsername {

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
  public boolean editUser(String username, List profileInfo) throws InvalidUsername {

    if (username == this.username || this.isAdmin) {

      // TODO: edit user information

      return true;
    }


    return false;
  }


  // GETTER METHODS

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

  public int getTotalUser() {
    return this.totalUsers;
  }
}
