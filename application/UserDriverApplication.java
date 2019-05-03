package application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

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


  /** The is logged. */
  private boolean isLogged = false; // if the user is logged in
  
  /** The username. */
  private String username = null; // the username of the user
  
  /** The is admin. */
  private boolean isAdmin = false; // if the user has admin priv
  
  /** The is populated. */
  private boolean isPopulated = false;

  /** The database. */
  private Map<String, Category> database = null;
  
  /** The total users. */
  private int totalUsers;
  
  /** The list of users. */
  private List<String> listOfUsers = null; // TODO: maybe not going to need

  /** The users category. */
  private final String USERS_CATEGORY = "USERS_CATEGORY";


  /**
   * Instantiates a new user driver application by just populating the database and initializing
   * proper fields. The DEV must register the user manually using register(username) method.
   * 
   * After registering the user, use editUser(username, profileInfo) to add information
   */
  public UserDriverApplication() {

    this.listOfUsers = new ArrayList<String>();
    
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
      } catch (UserExists e) {
        System.out.println("ERROR: UserDriverApplication_constructor: ");
        System.out.println("  Check JSON file. JSON file contains two same users");
        e.printStackTrace();
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
        Category allUsers = this.database.get(USERS_CATEGORY);

        // User thisUser = allUsers.getUser(username); //TODO: hava Cal make a method that gives user from
        // string.
        User thisUser = new User("TESTNAME", username); // TODO: remove this line after CAL adds new method

        if (thisUser.getAdmin())
          this.isAdmin = true;
        else
          this.isAdmin = false;

        return true;
      } else {

        // User doesn't exist
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
    if (this.listOfUsers != null && !this.listOfUsers.contains(username)) {

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
   // this.isPopulated = false;
   // this.database = null;
   // this.listOfUsers = null;
    this.isLogged = false;
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
  public boolean addUser(String username, Map<String,ArrayList<String>> profileInfo) throws UserExists {

    if (this.listOfUsers != null && this.listOfUsers.contains(username)) {
      throw new UserExists();
    } 
    
    
    if(username == null )
      return false;
    
    String catName;   
   
if(profileInfo != null) {
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
     int yearOfGrad = 0000; // TODO: get this field
     
     switch(profileTypeName) {
       case "student":
         //get the fields related to the student
         ArrayList<String> majorField = profileInfo.get(Config.MAJORS_FIELD);
         ArrayList<String> certificatesField = profileInfo.get(Config.CERTIFICATES_FIELD);
         ArrayList<String> clubsField = profileInfo.get(Config.CLUBS_FIELD);
         ArrayList<String> scholarshipField = profileInfo.get(Config.SCHOLARSHIPS_FIELD);
         ArrayList<String> coursesField = profileInfo.get(Config.COURSES_FIELD);
         ArrayList<String> workField = profileInfo.get(Config.WORK_EXPERIENCES_FIELD);
         ArrayList<String> yearOfGradField = profileInfo.get(Config.YEAROFGRAD_FIELD); // TODO: add year of grad
         try{
            yearOfGrad = Integer.parseInt(yearOfGradField.get(0)); //TODO: parseInt from the first element of yearOfGradField
         } catch(NumberFormatException e) {
        	 
         }
         
         //create a student user 
         Student newUser = new Student(yearOfGrad,majorField, certificatesField, clubsField, scholarshipField, coursesField, workField, nameField.get(0), username);
         this.totalUsers++;
         
         userCategory.insert(newUser); // this is the master category that contains all the users
         System.out.println(username);
         this.listOfUsers.add(username);
         //FIXME: maybe in the future, do something that would iterate instead of hardcoding  
// if the fields sting. length is 0, create a new category
         if(majorField.size() != 0) {
        	 for(int count = 0; count < majorField.size(); count++) {
        		 if(this.database.containsKey(majorField.get(count))) {
        			 cat = this.database.get(majorField.get(count));
        			 cat.insert(newUser);
        		 }
        		 else {
        			 cat = new Category(majorField.get(count));
        			 cat.insert(newUser);
        			 this.database.put(majorField.get(count), cat);
        		 }
        	 }
        	 
         }
         
         if(certificatesField.size() != 0) {
        	 for(int count = 0; count < certificatesField.size(); count++) {
        		 if(this.database.containsKey(certificatesField.get(count))) {
        			 cat = this.database.get(certificatesField.get(count));
        			 cat.insert(newUser);
        		 }
        		 else {
        			 cat = new Category(certificatesField.get(count));
        			 cat.insert(newUser);
        			 this.database.put(certificatesField.get(count), cat);
        		 }
        	 }
        	 
         }
         
         if(clubsField.size() != 0) {
        	 for(int count = 0; count < clubsField.size(); count++) {
        		 if(this.database.containsKey(clubsField.get(count))) {
        			 cat = this.database.get(clubsField.get(count));
        			 cat.insert(newUser);
        		 }
        		 else {
        			 cat = new Category(clubsField.get(count));
        			 cat.insert(newUser);
        			 this.database.put(clubsField.get(count), cat);
        		 }
        	 }
        	 
         }
         
         if(scholarshipField.size() != 0) {
        	 for(int count = 0; count < scholarshipField.size(); count++) {
        		 if(this.database.containsKey(scholarshipField.get(count))) {
        			 cat = this.database.get(scholarshipField.get(count));
        			 cat.insert(newUser);
        		 }
        		 else {
        			 cat = new Category(scholarshipField.get(count));
        			 cat.insert(newUser);
        			 this.database.put(scholarshipField.get(count), cat);
        		 }
        	 }
        	 
         }
         
         if(coursesField.size() != 0) {
        	 for(int count = 0; count < coursesField.size(); count++) {
        		 if(this.database.containsKey(coursesField.get(count))) {
        			 cat = this.database.get(coursesField.get(count));
        			 cat.insert(newUser);
        		 }
        		 else {
        			 cat = new Category(coursesField.get(count));
        			 cat.insert(newUser);
        			 this.database.put(coursesField.get(count), cat);
        		 }
        	 }
        	 
         }
         
         
         if(workField.size() != 0) {
        	 for(int count = 0; count < workField.size(); count++) {
        		 if(this.database.containsKey(workField.get(count))) {
        			 cat = this.database.get(workField.get(count));
        			 cat.insert(newUser);
        		 }
        		 else {
        			 cat = new Category(workField.get(count));
        			 cat.insert(newUser);
        			 this.database.put(workField.get(count), cat);
        		 }
        	 }
        	 
         }
         
         if(yearOfGradField.size() != 0) {
        	 for(int count = 0; count < yearOfGradField.size(); count++) {
        		 if(this.database.containsKey(yearOfGradField.get(count))) {
        			 cat = this.database.get(yearOfGradField.get(count));
        			 cat.insert(newUser);
        		 }
        		 else {
        			 cat = new Category(yearOfGradField.get(count));
        			 cat.insert(newUser);
        			 this.database.put(yearOfGradField.get(count), cat);
        		 }
        	 }
        	 
         }
           
         break;
       case "faculty":   
           ArrayList<String> coursesTaughtField = profileInfo.get(Config.COURSESTAUGHT_FILED);
           ArrayList<String> officeHoursField = profileInfo.get(Config.OFFICEHOURS_FIELD);
           ArrayList<String> officeLocationField = profileInfo.get(Config.OFFICELOCATION_FIELD);
         //get the fields related to the student
         

         // create a faculty user
         Faculty newUser2 = new Faculty(coursesTaughtField, officeHoursField, officeLocationField, nameField.get(0), username);
         
         userCategory.insert(newUser2);
         this.listOfUsers.add(username);
         
         if(coursesTaughtField.size() != 0) {
        	 for(int count = 0; count < coursesTaughtField.size(); count++) {
        		 if(this.database.containsKey(coursesTaughtField.get(count))) {
        			 cat = this.database.get(coursesTaughtField.get(count));
        			 cat.insert(newUser2);
        		 }
        		 else {
        			 cat = new Category(coursesTaughtField.get(count));
        			 cat.insert(newUser2);
        			 this.database.put(coursesTaughtField.get(count), cat);
        		 }
        	 }
        	 
         }
         
         if(officeHoursField.size() != 0) {
        	 for(int count = 0; count < officeHoursField.size(); count++) {
        		 if(this.database.containsKey(officeHoursField.get(count))) {
        			 cat = this.database.get(officeHoursField.get(count));
        			 cat.insert(newUser2);
        		 }
        		 else {
        			 cat = new Category(officeHoursField.get(count));
        			 cat.insert(newUser2);
        			 this.database.put(officeHoursField.get(count), cat);
        		 }
        	 }
        	 
         }
         
         if(officeLocationField.size() != 0) {
        	 for(int count = 0; count < officeLocationField.size(); count++) {
        		 if(this.database.containsKey(officeLocationField.get(count))) {
        			 cat = this.database.get(officeLocationField.get(count));
        			 cat.insert(newUser2);
        		 }
        		 else {
        			 cat = new Category(officeLocationField.get(count));
        			 cat.insert(newUser2);
        			 this.database.put(officeLocationField.get(count), cat);
        		 }
        	 }
        	 
         }
         break;
         
       default:
         System.out.println("ERROR: UserDriverApplication_addUser: ");
         System.out.println("  ProfileType is not defined");
         return false;
     }
     
    

//	 User user = new User(nameField.get(0), username);
 
// create new user with just the username
} else {
    //just create a user with no info
  User user = new User(null, username);
  this.totalUsers++; // given that a new user was added to the database
  return true; 
}

    return true; // if user was not added, otherwise should return true
  }

  /**
   * Populate datastructure with users by parsing user informations from json file. Also add users to
   * Users_Category
   *
   * @param jsonFilePath the json file path
   * @throws FileNotFoundException the file not found exception
   * @throws IOException Signals that an I/O exception has occurred.
   * @throws ParseException the parse exception
   * @throws UserExists the user exists
   */
  public void populateDatastructureWithUsers(String jsonFilePath)
    throws FileNotFoundException, IOException, ParseException, UserExists {

    this.totalUsers = 0;
    
    long startTime = System.currentTimeMillis();

    if (database == null) {
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
          JSONObject profileInfo = (JSONObject) users.get(i);

          // TODO: GET ALL THE FIELDS. there has to be a better way of doing this.
          // JSONArray packageName = (JSONArray) profileInfo.get(); // gets each packgename
          // JSONArray dependencies = (JSONArray) profileInfo.get("dependencies"); // gets each dependencies

          JSONArray profileTypeField = (JSONArray) profileInfo.get(Config.PROFILE_TYPE_FIELD);
          JSONArray usernameField = (JSONArray) profileInfo.get(Config.USERNAME_FIELD);
          JSONArray nameField = (JSONArray) profileInfo.get(Config.NAME_FIELD);
          JSONArray isAdminField = (JSONArray) profileInfo.get(Config.IS_ADMIN_FIELD);
          JSONArray isPublicField = (JSONArray) profileInfo.get(Config.IS_PUBLIC_FIELD);

          // access the first element since they should only have one element
          String name = (String) nameField.get(0);
          String email = (String) usernameField.get(0);
          String profileTypeName = (String) profileTypeField.get(0);
          String isAdminText = (String) isAdminField.get(0);
          String isPublicText = (String) isPublicField.get(0);

          int k = 0;

          switch (profileTypeName) {
            case "student":


              // get the fields related to the student
              JSONArray majorField = (JSONArray) profileInfo.get(Config.MAJORS_FIELD);
              JSONArray certificatesField = (JSONArray) profileInfo.get(Config.CERTIFICATES_FIELD);
              JSONArray clubsField = (JSONArray) profileInfo.get(Config.CLUBS_FIELD);
              JSONArray scholarshipField = (JSONArray) profileInfo.get(Config.SCHOLARSHIPS_FIELD);
              JSONArray coursesField = (JSONArray) profileInfo.get(Config.COURSES_FIELD);
              JSONArray workField = (JSONArray) profileInfo.get(Config.WORK_EXPERIENCES_FIELD);
              JSONArray yearOfGradField = (JSONArray) profileInfo.get(Config.YEAROFGRAD_FIELD); // TOD



              Map<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();

              map.put(Config.USERNAME_FIELD, new ArrayList<String>(Arrays.asList(email)));
              map.put(Config.NAME_FIELD, new ArrayList<String>(Arrays.asList(name)));
              map.put(Config.PROFILE_TYPE_FIELD,
                new ArrayList<String>(Arrays.asList(profileTypeName)));
              map.put(Config.IS_ADMIN_FIELD, new ArrayList<String>(Arrays.asList(isAdminText)));
              map.put(Config.IS_PUBLIC_FIELD, new ArrayList<String>(Arrays.asList(isPublicText)));

              ArrayList<String> major = new ArrayList<String>();



              for (k = 0; k < majorField.size(); k++) {
                major.add((String) majorField.get(k));

              }
              map.put(Config.MAJORS_FIELD, major);

              ArrayList<String> certificates = new ArrayList<String>();
              for (k = 0; k < certificatesField.size(); k++) {
                certificates.add((String) certificatesField.get(k));
              }
              map.put(Config.CERTIFICATES_FIELD, certificates);

              ArrayList<String> clubs = new ArrayList<String>();
              for (k = 0; k < clubsField.size(); k++) {
                clubs.add((String) clubsField.get(k));
              }
              map.put(Config.CLUBS_FIELD, clubs);

              ArrayList<String> scholarship = new ArrayList<String>();
              for (k = 0; k < scholarshipField.size(); k++) {
                scholarship.add((String) scholarshipField.get(k));
              }
              map.put(Config.SCHOLARSHIPS_FIELD, scholarship);

              ArrayList<String> courses = new ArrayList<String>();
              for (k = 0; k < coursesField.size(); k++) {
                courses.add((String) coursesField.get(k));
              }
              map.put(Config.COURSES_FIELD, courses);

              ArrayList<String> work = new ArrayList<String>();
              for (k = 0; k < workField.size(); k++) {
                work.add((String) workField.get(k));
              }
              map.put(Config.WORK_EXPERIENCES_FIELD, work);


              map.put(Config.YEAROFGRAD_FIELD,
                new ArrayList<String>(Arrays.asList(yearOfGradField.toString())));

              addUser(email, map);

              break;

            case "faculty":
              JSONArray coursesTaughtField =
                (JSONArray) profileInfo.get(Config.COURSESTAUGHT_FILED);
              JSONArray officeHoursField = (JSONArray) profileInfo.get(Config.OFFICEHOURS_FIELD);
              JSONArray officeLocationField =
                (JSONArray) profileInfo.get(Config.OFFICELOCATION_FIELD);
              // get the fields related to the student

              
              Map<String, ArrayList<String>> mapF = new HashMap<String, ArrayList<String>>();

              mapF.put(Config.USERNAME_FIELD, new ArrayList<String>(Arrays.asList(email)));
              mapF.put(Config.NAME_FIELD, new ArrayList<String>(Arrays.asList(name)));
              mapF.put(Config.PROFILE_TYPE_FIELD,
                new ArrayList<String>(Arrays.asList(profileTypeName)));
              mapF.put(Config.IS_ADMIN_FIELD, new ArrayList<String>(Arrays.asList(isAdminText)));
              mapF.put(Config.IS_PUBLIC_FIELD, new ArrayList<String>(Arrays.asList(isPublicText)));

              ArrayList<String> coursesTaught = new ArrayList<String>();
              for (k = 0; k < coursesTaughtField.size(); k++) {
                coursesTaught.add((String) coursesTaughtField.get(k));
              }
              mapF.put(Config.COURSESTAUGHT_FILED, coursesTaught);

              ArrayList<String> officeHours = new ArrayList<String>();
              for (k = 0; k < officeHoursField.size(); k++) {
                officeHours.add((String) officeHoursField.get(k));
              }
              mapF.put(Config.OFFICEHOURS_FIELD, officeHours);

              ArrayList<String> officeLocation = new ArrayList<String>();
              for (k = 0; k < officeLocationField.size(); k++) {
                officeLocation.add((String) officeLocationField.get(k));
              }
              mapF.put(Config.OFFICELOCATION_FIELD, officeLocation);

              addUser(email, mapF);

              break;
            default:
              System.out.println("ERROR: UserDriverApplication_addUser: ");
              System.out.println("  ProfileType is not defined");
          }
          // TODO: add all the users

        }

        long endTime = System.currentTimeMillis(); 
        long timeElapsed = endTime - startTime;
        System.out.println("Execution time for populateDatastructure()"+ timeElapsed);
        
        // System.out.println("DONE!");


      } catch (FileNotFoundException e) {
        throw new FileNotFoundException();
      } catch (IOException e) {
        throw new IOException();
      } catch (ParseException e) {
        throw new ParseException(0);
      }
    }

    // TODO: set data populated true
  }

  /**
   * Search user in the database.
   *
   * @param profileInfo the profile info
   * @return the list
   */
  public List<User> searchUser(Map<String, ArrayList<String>> profileInfo) {

    long startTime = System.currentTimeMillis();
    
    List<User>searchlist = new ArrayList<User>();
    // TODO: find the complexity analysis for this algo
	  if(profileInfo != null) {
		    //get arraylist that are required for any type of User
		     ArrayList<String> profileTypeField = profileInfo.get(Config.PROFILE_TYPE_FIELD);
		     ArrayList<String> nameField = profileInfo.get(Config.NAME_FIELD);
		     ArrayList<String> isAdminField = profileInfo.get(Config.IS_ADMIN_FIELD);
		     ArrayList<String> isPublicField = profileInfo.get(Config.IS_PUBLIC_FIELD);
		     String profileTypeName = profileTypeField.get(0);
		     switch(profileTypeName) {
		       case "student":
		         //get the fields related to the student
		         ArrayList<String> majorField = profileInfo.get(Config.MAJORS_FIELD);
		         ArrayList<String> certificatesField = profileInfo.get(Config.CERTIFICATES_FIELD);
		         ArrayList<String> clubsField = profileInfo.get(Config.CLUBS_FIELD);
		         ArrayList<String> scholarshipField = profileInfo.get(Config.SCHOLARSHIPS_FIELD);
		         ArrayList<String> coursesField = profileInfo.get(Config.COURSES_FIELD);
		         ArrayList<String> workField = profileInfo.get(Config.WORK_EXPERIENCES_FIELD);
		         ArrayList<String> yearOfGradField = profileInfo.get(Config.YEAROFGRAD_FIELD);
		         
		         Set<User> majorSet = new HashSet<User>();
		         if(majorField.size() > 0 && !majorField.get(0).equalsIgnoreCase("")) {   //if string was empty, it gave nullPointer
		        	 for(int count = 0; count < majorField.size(); count++) {
		        		 if(this.database.containsKey(majorField.get(count))) {
		        			 List<User> list = this.database.get(majorField.get(count)).getAll();	
		        			 for(int count2 = 0; count2 < list.size(); count2++) {
		        				 majorSet.add(list.get(count2));
		        			 }
		        		 }
		        	 }
		         }
		         
		         Set<User> certificateSet = new HashSet<User>();


		         if(certificatesField.size() > 0 && !certificatesField.get(0).equalsIgnoreCase("")) { 


		        	 for(int count = 0; count < certificatesField.size(); count++) {
		        		 if(this.database.containsKey(certificatesField.get(count))) {
		        			 List<User> list = this.database.get(certificatesField.get(count)).getAll();	
		        			 for(int count2 = 0; count2 < list.size(); count2++) {
		        				 certificateSet.add(list.get(count2));
		        			 }
		        		 }
		        	 }
		         }
		         
		         Set<User> clubSet = new HashSet<User>();
		         if(clubsField.size() > 0 && !clubsField.get(0).equalsIgnoreCase("")) {
		        	 for(int count = 0; count < clubsField.size(); count++) {
		        		 if(this.database.containsKey(clubsField.get(count))) {
		        			 List<User> list = this.database.get(clubsField.get(count)).getAll();	
		        			 for(int count2 = 0; count2 < list.size(); count2++) {
		        				 clubSet.add(list.get(count2));
		        			 }
		        		 }
		        	 }
		         }
		         
		         Set<User> scholarshipSet = new HashSet<User>();
		         if(scholarshipField.size() > 0 && !scholarshipField.get(0).equalsIgnoreCase("")) {
		        	 for(int count = 0; count < scholarshipField.size(); count++) {
		        		 if(this.database.containsKey(scholarshipField.get(count))) {
		        			 List<User> list = this.database.get(scholarshipField.get(count)).getAll();	
		        			 for(int count2 = 0; count2 < list.size(); count2++) {
		        				 scholarshipSet.add(list.get(count2));
		        			 }
		        		 }
		        	 }
		         }
		         
		         Set<User> courseSet = new HashSet<User>();
		         if(coursesField.size() > 0 && !coursesField.get(0).equalsIgnoreCase("")) {
		        	 for(int count = 0; count < coursesField.size(); count++) {
		        		 if(this.database.containsKey(coursesField.get(count))) {
		        			 List<User> list = this.database.get(coursesField.get(count)).getAll();	
		        			 for(int count2 = 0; count2 < list.size(); count2++) {
		        				 courseSet.add(list.get(count2));
		        			 }
		        		 }
		        	 }
		         }
		         
		         Set<User> workSet = new HashSet<User>();
		         if(workField.size() > 0 && !workField.get(0).equalsIgnoreCase("")) {
		        	 for(int count = 0; count < workField.size(); count++) {
		        		 if(this.database.containsKey(workField.get(count))) {
		        			 List<User> list = this.database.get(workField.get(count)).getAll();	
		        			 for(int count2 = 0; count2 < list.size(); count2++) {
		        				 workSet.add(list.get(count2));
		        			 }
		        		 }
		        	 }
		         }
		         
		         Set<User> yearOfGradSet = new HashSet<User>();
		         if(yearOfGradField.size() > 0 && !yearOfGradField.get(0).equalsIgnoreCase("")) {
		        	 for(int count = 0; count < yearOfGradField.size(); count++) {
		        		 if(this.database.containsKey(yearOfGradField.get(count))) {
		        			 List<User> list = this.database.get(yearOfGradField.get(count)).getAll();	
		        			 for(int count2 = 0; count2 < list.size(); count2++) {
		        				 yearOfGradSet.add(list.get(count2));
		        			 }
		        		 }
		        	 }
		         }
		         
		         if(majorSet.size() != 0) {
			         majorSet.retainAll(clubSet);
			         majorSet.retainAll(certificateSet);
			         majorSet.retainAll(scholarshipSet);
			         majorSet.retainAll(courseSet);
			         majorSet.retainAll(workSet);
			         majorSet.retainAll(yearOfGradSet);
			         
			         for(User u : majorSet) {
			        	 searchlist.add(u);
			         }
		         }
		         
		         else if(clubSet.size() != 0) {
			         clubSet.retainAll(majorSet);
			         clubSet.retainAll(certificateSet);
			         clubSet.retainAll(scholarshipSet);
			         clubSet.retainAll(courseSet);
			         clubSet.retainAll(workSet);
			         clubSet.retainAll(yearOfGradSet);
			         
			         for(User u : clubSet) {
			        	 searchlist.add(u);
			         }
		         }
		         
		         else if(certificateSet.size() != 0) {
			         certificateSet.retainAll(majorSet);
			         certificateSet.retainAll(clubSet);
			         certificateSet.retainAll(scholarshipSet);
			         certificateSet.retainAll(courseSet);
			         certificateSet.retainAll(workSet);
			         certificateSet.retainAll(yearOfGradSet);
			         
			         for(User u : certificateSet) {
			        	 searchlist.add(u);
			         }
		         }
		         
		         else if(scholarshipSet.size() != 0) {
			         scholarshipSet.retainAll(majorSet);
			         scholarshipSet.retainAll(certificateSet);
			         scholarshipSet.retainAll(clubSet);
			         scholarshipSet.retainAll(courseSet);
			         scholarshipSet.retainAll(workSet);
			         scholarshipSet.retainAll(yearOfGradSet);
			         
			         for(User u : scholarshipSet) {
			        	 searchlist.add(u);
			         }
		         }
		         
		         else if(courseSet.size() != 0) {
			         courseSet.retainAll(majorSet);
			         courseSet.retainAll(certificateSet);
			         courseSet.retainAll(clubSet);
			         courseSet.retainAll(scholarshipSet);
			         courseSet.retainAll(workSet);
			         courseSet.retainAll(yearOfGradSet);
			         
			         for(User u : courseSet) {
			        	 searchlist.add(u);
			         }
		         }
		         
		         else if(workSet.size() != 0) {
			         workSet.retainAll(majorSet);
			         workSet.retainAll(certificateSet);
			         workSet.retainAll(clubSet);
			         workSet.retainAll(scholarshipSet);
			         workSet.retainAll(courseSet);
			         workSet.retainAll(yearOfGradSet);
			         
			         for(User u : workSet) {
			        	 searchlist.add(u);
			         }
		         }
		         
		         else {
			         yearOfGradSet.retainAll(majorSet);
			         yearOfGradSet.retainAll(certificateSet);
			         yearOfGradSet.retainAll(clubSet);
			         yearOfGradSet.retainAll(scholarshipSet);
			         yearOfGradSet.retainAll(courseSet);
			         yearOfGradSet.retainAll(workSet);
			         
			         for(User u : yearOfGradSet) {
			        	 searchlist.add(u);
			         }
		         }
		         
		         
		         break;
		       case("faculty"):
		         ArrayList<String> coursesTaughtField = profileInfo.get(Config.COURSESTAUGHT_FILED);
	             ArrayList<String> officeHoursField = profileInfo.get(Config.OFFICEHOURS_FIELD);
	             ArrayList<String> officeLocationField = profileInfo.get(Config.OFFICELOCATION_FIELD);
	            
	             Set<User> coursesTaughtSet = new HashSet<User>();
		         if(coursesTaughtField.size() > 0 && !coursesTaughtField.get(0).equalsIgnoreCase("")) {
		        	 for(int count = 0; count < coursesTaughtField.size(); count++) {
		        		 if(this.database.containsKey(coursesTaughtField.get(count))) {
		        			 List<User> list = this.database.get(coursesTaughtField.get(count)).getAll();	
		        			 for(int count2 = 0; count2 < list.size(); count2++) {
		        				 coursesTaughtSet.add(list.get(count2));
		        			 }
		        		 }
		        	 }
		         }
		         
	             Set<User> officeHourSet = new HashSet<User>();
		         if(officeHoursField.size() > 0 && !officeHoursField.get(0).equalsIgnoreCase("")) {
		        	 for(int count = 0; count < officeHoursField.size(); count++) {
		        		 if(this.database.containsKey(officeHoursField.get(count))) {
		        			 List<User> list = this.database.get(officeHoursField.get(count)).getAll();	
		        			 for(int count2 = 0; count2 < list.size(); count2++) {
		        				 officeHourSet.add(list.get(count2));
		        			 }
		        		 }
		        	 }
		         }
		         
	             Set<User> officeLocationSet = new HashSet<User>();
		         if(officeLocationField.size() > 0 && !officeLocationField.get(0).equalsIgnoreCase("")) {
		        	 for(int count = 0; count < officeLocationField.size(); count++) {
		        		 if(this.database.containsKey(officeLocationField.get(count))) {
		        			 List<User> list = this.database.get(officeLocationField.get(count)).getAll();	
		        			 for(int count2 = 0; count2 < list.size(); count2++) {
		        				 officeLocationSet.add(list.get(count2));
		        			 }
		        		 }
		        	 }
		         }
		         
		         if(coursesTaughtSet.size() != 0) {
			         coursesTaughtSet.retainAll(officeHourSet);
			         coursesTaughtSet.retainAll(officeLocationSet);

			         for(User u : coursesTaughtSet) {
			        	 searchlist.add(u);
			         }
		         }
		         
		         else if(officeHourSet.size() != 0) {
			         officeHourSet.retainAll(coursesTaughtSet);
			         officeHourSet.retainAll(officeLocationSet);

			         for(User u : officeHourSet) {
			        	 searchlist.add(u);
			         }
		         }
		         
		         else {
			         officeLocationSet.retainAll(coursesTaughtSet);
			         officeLocationSet.retainAll(officeHourSet);

			         for(User u : officeLocationSet) {
			        	 searchlist.add(u);
			         }
		         }
		         
		         break;
	  }
		     
  }
    // iterate through all the profileInfo fields;
    // check if the field exists
    // create set of all the fields


    // find the field with least load factor
    // search for the users in field
    // add to a list

    // if list of users doesn't contain other fields listed in the profileInfo, remove the user from the
    // list
    // and if user show if public

		     
		 long endTime = System.currentTimeMillis(); 
		 long timeElapsed = endTime - startTime;
		 System.out.println("Execution time for searchUser()"+ timeElapsed);
		 
		     

	  System.out.print(searchlist.size());

	if(searchlist.size() > 0) {
		return searchlist;
	} 
	
    return null; //no users found during the search
  }

  /**
   * Edits the user's information if own profile or if the user is an admin.
   *
   * @param profileInfo the profile info
   * @return true, if successful
   */
  public boolean editUser(Map<String, ArrayList<String>> profileInfo) {


    if (username == this.username || this.isAdmin) {
      // if own profile or if the user is an admin

      // List<User> foundUsers = searchUser(profileInfo);
      // foundUsers.contains(o)
      long startTime = System.currentTimeMillis();
      
      Category userCategory = this.database.get(USERS_CATEGORY);



      // get arraylist that are required for any type of User
      ArrayList<String> profileTypeField = profileInfo.get(Config.PROFILE_TYPE_FIELD);
      ArrayList<String> nameField = profileInfo.get(Config.NAME_FIELD);
      ArrayList<String> usernameField = profileInfo.get(Config.USERNAME_FIELD);
      ArrayList<String> isAdminField = profileInfo.get(Config.IS_ADMIN_FIELD);
      ArrayList<String> isPublicField = profileInfo.get(Config.IS_PUBLIC_FIELD);

      // access the first element since they should only have one element
      String name = nameField.get(0);
      String email = usernameField.get(0);
      String profileTypeName = profileTypeField.get(0);
      Boolean isAdminText = Boolean.parseBoolean(isAdminField.get(0));
      Boolean isPublicText = Boolean.parseBoolean(isPublicField.get(0));

      int yearOfGrad = 0;

      switch (profileTypeName) {
        case "student":
          // get the fields related to the student
          ArrayList<String> majorField = profileInfo.get(Config.MAJORS_FIELD);
          ArrayList<String> certificatesField = profileInfo.get(Config.CERTIFICATES_FIELD);
          ArrayList<String> clubsField = profileInfo.get(Config.CLUBS_FIELD);
          ArrayList<String> scholarshipField = profileInfo.get(Config.SCHOLARSHIPS_FIELD);
          ArrayList<String> coursesField = profileInfo.get(Config.COURSES_FIELD);
          ArrayList<String> workField = profileInfo.get(Config.WORK_EXPERIENCES_FIELD);
          ArrayList<String> yearOfGradField = profileInfo.get(Config.YEAROFGRAD_FIELD); // TODO: add year of grad
          try {
            yearOfGrad = Integer.parseInt(yearOfGradField.get(0)); // TODO: parseInt from the first element of yearOfGradField
          } catch (NumberFormatException e) {

          }

          // Student user = userCategory.getUser(username); // TODO: have cal implement the method
     //     Student user = new Student(yearOfGrad, majorField, majorField, majorField, majorField,
     //, majorField, profileTypeName, profileTypeName); // TODO: remove this line after CAL adds new method
          Student user = (Student)userCategory.get(username);


          user.setMajor(majorField);
          user.setCertificate(certificatesField);
          user.setClubs(clubsField);
          user.setScholership(scholarshipField);
          user.setCourses(coursesField);
          user.setWorkExperience(workField);
          user.setYearOfGrad(yearOfGrad);



          break;
        case "faculty":
          ArrayList<String> coursesTaughtField = profileInfo.get(Config.COURSESTAUGHT_FILED);
          ArrayList<String> officeHoursField = profileInfo.get(Config.OFFICEHOURS_FIELD);
          ArrayList<String> officeLocationField = profileInfo.get(Config.OFFICELOCATION_FIELD);
          // get the fields related to the student

          // Faculty userF = userCategory.getUser(username); // TODO: have cal implement the method
          majorField = null; // TODO remove this link after cal adds new method
         // Faculty userF = new Faculty(majorField, majorField, majorField, "TESTNAME", username); // TODO: remove this line after CAL adds new method
          Faculty userF = (Faculty)userCategory.get(username);

          userF.setCoursesTaught(coursesTaughtField);
          userF.setOfficeHours(officeHoursField);
          userF.setOfficeLocation(officeLocationField);

          break;
        default:
          System.out.println("ERROR: UserDriverApplication_addUser: ");
          System.out.println("  ProfileType is not defined");
          return false;
      }

      long endTime = System.currentTimeMillis(); 
      long timeElapsed = endTime - startTime;
      System.out.println("Execution time for editUser()"+ timeElapsed);
      
      
      return true; // successfully edited profile info
    }


    return false; // either you are not an admin, or the same user that you are trying to edit for;
  }
  
  public List<String> computation(Map<String, ArrayList<String>> profileInfo){
	  List<User>searchlist = new ArrayList<User>();
	    // TODO: find the complexity analysis for this algo
		  if(profileInfo != null) {
			    //get arraylist that are required for any type of User
			     ArrayList<String> profileTypeField = profileInfo.get(Config.PROFILE_TYPE_FIELD);
			     ArrayList<String> nameField = profileInfo.get(Config.NAME_FIELD);
			     ArrayList<String> isAdminField = profileInfo.get(Config.IS_ADMIN_FIELD);
			     ArrayList<String> isPublicField = profileInfo.get(Config.IS_PUBLIC_FIELD);
			     String profileTypeName = profileTypeField.get(0);
			     switch(profileTypeName) {
			       case "student":
			         //get the fields related to the student
			         ArrayList<String> majorField = profileInfo.get(Config.MAJORS_FIELD);
			         ArrayList<String> certificatesField = profileInfo.get(Config.CERTIFICATES_FIELD);
			         ArrayList<String> clubsField = profileInfo.get(Config.CLUBS_FIELD);
			         ArrayList<String> scholarshipField = profileInfo.get(Config.SCHOLARSHIPS_FIELD);
			         ArrayList<String> coursesField = profileInfo.get(Config.COURSES_FIELD);
			         ArrayList<String> workField = profileInfo.get(Config.WORK_EXPERIENCES_FIELD);
			         ArrayList<String> yearOfGradField = profileInfo.get(Config.YEAROFGRAD_FIELD);
			         
			         
					  List<User>getAll = searchUser(profileInfo);
					  for(int count = 0; count < getAll.size(); count++) {
						  
					  }
					  
					  
			         Set<User> courseSet = new HashSet<User>();
			         if(coursesField.size() > 0) {
			        	 for(int count = 0; count < coursesField.size(); count++) {
			        		 if(this.database.containsKey(coursesField.get(count))) {
			        			 List<User> list = this.database.get(coursesField.get(count)).getAll();	
			        			 for(int count2 = 0; count2 < list.size(); count2++) {
			        				 courseSet.add(list.get(count2));
			        			 }
			        		 }
			        	 }
			         }
			         
			         
			     }
		  }
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
  
  /**
   * Gets the user.
   *
   * @return the user
   */
  public User getUser() {
    
    Category userCategory = this.database.get(USERS_CATEGORY);
    User thisUser = userCategory.get(username);
    
    return thisUser;
  }

  
  /**
   * Gets the type of the current user
   *
   * @return the type
   */
  public String getType() {
    if(getUser() instanceof Student)
    return "student";
    
    if(getUser() instanceof Faculty)
    return "faculty"; 
    
    return "";
  }
  
  /**
   * Gets the total user.
   *
   * @return the total user
   */
  public int getTotalUser() {
    return this.totalUsers;
  }
}
