///////////////////////////////////////////////////////////////////////////////
//
// Title:           Final Project: EvolutionTree
// Due Date:        Milestone 1: 4/25/2019 10pm
//                  Milestone 2: 5/2/2019 10pm
//                  
// Course:          CS400, Spring 2018, Lecture 002
//
// Authors:         Erica Heying, Ben Procknow, Ajman Naqab, Callan Patel
// A team:          63
// Lecturer's Name: Deb Deppeler
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Persons:         NONE
// Online Sources:  https://docs.oracle.com/javafx/2/ui_controls/radio-button.htm
//
///////////////////////////// KNOWN BUGS///////////////////////////////////////
// Known Bugs:      NONE     
///////////////////////////////////////////////////////////////////////////////
package application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

/**
 * Main application
 * 
 * @author erica, ben, aj, callan
 *
 */
public class Main extends Application {
  Stage primaryStage; // the one and only stage
  Button logout = new Button("Logout"); // add logout button functionality on each page
  UserDriverApplication currentDriver; // driver application for user/search functionality
  String currentUsername; // the current user, either logging in or registering
  List<User> searchReturn; // the list of users that are returned by the search
  List<String> recommended; // the list of recommended classes to take 
  
  /**
   * The start method that sets the stage
   */
  @Override
  public void start(Stage primaryStage) {
    try {
      currentDriver = new UserDriverApplication();
      logout.setOnAction(toLogout -> {
        currentDriver.logout();
        Scene loginScreen = loginScreen();
        primaryStage.setScene(loginScreen);
        primaryStage.show();
      });
      this.primaryStage = primaryStage;
      Scene login = loginScreen();
      primaryStage.setScene(login);
      primaryStage.show();

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private Map<String, ArrayList<String>> createNewStudentMap(){
    Map<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
    map.put(Config.USERNAME_FIELD, new ArrayList<String>());
    map.put(Config.NAME_FIELD, new ArrayList<String>());
    map.put(Config.PROFILE_TYPE_FIELD, new ArrayList<String>());
    map.put(Config.IS_ADMIN_FIELD, new ArrayList<String>());
    map.put(Config.IS_PUBLIC_FIELD, new ArrayList<String>());
    map.put(Config.MAJORS_FIELD, new ArrayList<String>());
    map.put(Config.CERTIFICATES_FIELD, new ArrayList<String>());
    map.put(Config.CLUBS_FIELD, new ArrayList<String>());
    map.put(Config.SCHOLARSHIPS_FIELD, new ArrayList<String>());
    map.put(Config.COURSES_FIELD, new ArrayList<String>());
    map.put(Config.WORK_EXPERIENCES_FIELD, new ArrayList<String>());
    map.put(Config.YEAROFGRAD_FIELD, new ArrayList<String>());
    return map;
  }

  private Map<String, ArrayList<String>> createNewFacultyMap(){
    Map<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
    map.put(Config.USERNAME_FIELD, new ArrayList<String>());
    map.put(Config.NAME_FIELD, new ArrayList<String>());
    map.put(Config.OFFICELOCATION_FIELD, new ArrayList<String>());
    map.put(Config.COURSESTAUGHT_FILED, new ArrayList<String>());
    map.put(Config.OFFICEHOURS_FIELD, new ArrayList<String>());
    return map;
  }

  /**
   * Create the log in scene
   * 
   * @return
   */
  private Scene loginScreen() {
    // log in with valid username
    Text loginPrompt = new Text(20, 30, "Login: ");
    Button submit = new Button("Submit");
    Text signupPrompt = new Text(20, 30, "Sign-up: ");
    GridPane grid = new GridPane();
    TextField loginTextField = new TextField();
    TextField signUpTextField = new TextField("Email Address");
    grid.add(loginPrompt, 0, 1);
    grid.add(loginTextField, 1, 1);
    grid.add(submit, 3, 1);
    grid.add(signupPrompt, 0, 2);
    grid.add(signUpTextField, 1, 2);

    signUpTextField.setOnMouseClicked(event->{
      if (signUpTextField.getText().equals("Email Address")) {
        signUpTextField.clear();
      }
    });

    // radio button for the user type when a user wants to sign up
    ToggleGroup type = new ToggleGroup();

    RadioButton userTypeStudent = new RadioButton("Student");
    userTypeStudent.setToggleGroup(type);
    userTypeStudent.setSelected(true);

    RadioButton userTypeFaculty = new RadioButton("Faculty");
    userTypeFaculty.setToggleGroup(type);

    Button signup = new Button("Sign-up!");

    //This is needed to add a blank space between the Radio Button and the Sign-Up block.  If the username is
    //Already taken this space will be filled with a new text field saying that the username has already been chosen.
    Text blankText = new Text("");
    //userNameTakenText will popup if the username is already taken.
    Text userNameTakenText = new Text("This username has already been taken, try again!");
    Text userNameMoreThanOneWord = new Text("Make sure the username email adress is correct.  Must be one word.");

    grid.add(userTypeStudent, 1, 3);
    grid.add(userTypeFaculty, 1, 4);
    grid.add(blankText, 1, 5);
    grid.add(signup, 1, 6);

    // button functionality
    signup.setOnAction(student -> {
      if(signUpTextField.getText().split(" ").length>1) {
        grid.add(userNameMoreThanOneWord, 1, 5);
      }
      else if (userTypeStudent.isSelected()) {
        currentUsername = signUpTextField.getText();
        Scene studentSignUp = signupScreenStudent();
        primaryStage.setScene(studentSignUp);
        primaryStage.show();
      }
        else {
          currentUsername = signUpTextField.getText();
          Scene facultySignUp = signupScreenFaculty();
          primaryStage.setScene(facultySignUp);
        }
      });

    // button functionality for logging in
    submit.setOnAction(toSearch -> {
      try { // try logging in the user with the username provided
        if(currentDriver.login(loginTextField.getText())) {
          // once logged in, display the search screen
          Scene search = search();
          primaryStage.setScene(search);
          primaryStage.show();
        }
        else
          System.out.println("currentDriver.login returned false, which shouldn't happen");
      } // if the user is not registered then display the following message 
      catch (InvalidUsername e) {
        grid.add(new Label("Invalid Username. Please sign up or try again."), 1, 0);
      }
    });

    grid.setAlignment(Pos.CENTER);
    BorderPane root = new BorderPane();
    root.setCenter(grid);
    root.setBottom(logout);

    Scene login = new Scene(root, 800, 600);
    login.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
    return login;
  }


  /**
   * Create a map out of the user input from the ArrayList of TextFields.  The keys are all of the keys are the attributes that a student can have. 
   * @param map
   * @param userInput
   * @return
   */
  private Map<String,ArrayList<String>> addStudentUserText(Map<String,ArrayList<String>> map, ArrayList<TextField> userInput){
      map.get(Config.USERNAME_FIELD).add(this.currentUsername);
      
      map.get(Config.PROFILE_TYPE_FIELD).add("student");
      
      String[] nameArray = userInput.get(0).getText().split(",");
      for (int curIndex=0;curIndex<nameArray.length;curIndex++) {
          nameArray[curIndex] = nameArray[curIndex].trim();
      }
      map.get(Config.NAME_FIELD).addAll(Arrays.asList(nameArray));
      
      String[] yearOfGradArray = userInput.get(2).getText().split(",");
      for (int curIndex=0;curIndex<yearOfGradArray.length;curIndex++) {
          yearOfGradArray[curIndex] = yearOfGradArray[curIndex].trim();
      }
      map.get(Config.YEAROFGRAD_FIELD).addAll(Arrays.asList(yearOfGradArray));
      
      String[] majorArray = userInput.get(3).getText().split(",");
      for (int curIndex=0;curIndex<majorArray.length;curIndex++) {
          majorArray[curIndex] = majorArray[curIndex].trim();
      }
      map.get(Config.MAJORS_FIELD).addAll(Arrays.asList(majorArray));
      
      String[] clubArray = userInput.get(4).getText().split(",");
      for (int curIndex=0;curIndex<clubArray.length;curIndex++) {
          clubArray[curIndex] = clubArray[curIndex].trim();
      }
      map.get(Config.CLUBS_FIELD).addAll(Arrays.asList(clubArray));
      
      String[] scholarshipArray = userInput.get(5).getText().split(",");
      for (int curIndex=0;curIndex<scholarshipArray.length;curIndex++) {
          scholarshipArray[curIndex] = scholarshipArray[curIndex].trim();
      }
      map.get(Config.SCHOLARSHIPS_FIELD).addAll(Arrays.asList(scholarshipArray));
      
      String[] coursesArray = userInput.get(6).getText().split(",");
      for (int curIndex=0;curIndex<userInput.get(6).getText().split(",").length;curIndex++) {
          coursesArray[curIndex] = coursesArray[curIndex].trim();
      }
      map.get(Config.COURSES_FIELD).addAll(Arrays.asList(coursesArray));
      
      String[] workExperienceArray = userInput.get(7).getText().split(",");
      for (int curIndex=0;curIndex<workExperienceArray.length;curIndex++) {
          workExperienceArray[curIndex] = workExperienceArray[curIndex].trim();
      }
      map.get(Config.WORK_EXPERIENCES_FIELD).addAll(Arrays.asList(workExperienceArray));
      return map;
  }
  

  /**
   * Create Sign-up scene for students
   */
  private Scene signupScreenStudent() {
      ArrayList<Text> fields = new ArrayList<Text>();
      fields.add(new Text("Name: "));
      fields.add(new Text("Email: "));
      fields.add(new Text("Year of graduation: "));
      fields.add(new Text("Major: "));
      fields.add(new Text("Clubs: "));
      fields.add(new Text("Scholarships: "));
      fields.add(new Text("Courses: "));
      fields.add(new Text("Work Experience: "));

      TextField nameTextField = new TextField();
      TextField emailTextField = new TextField(this.currentUsername);
      TextField yearOfGraduationTextField = new TextField();
      TextField majorTextField = new TextField();
      TextField clubsTextField = new TextField();
      TextField scholarshipsTextField = new TextField();
      TextField coursesTextField = new TextField();
      TextField workExperienceTextField = new TextField();
      ArrayList<TextField> signUpStudentTextFieldList = new ArrayList<TextField>() {
          {
              add(nameTextField);
              add(emailTextField);
              add(yearOfGraduationTextField);
              add(majorTextField);
              add(clubsTextField);
              add(scholarshipsTextField);
              add(coursesTextField);
              add(workExperienceTextField);
          }
      };
      
      GridPane grid = new GridPane();
      for (int i = 0; i < fields.size(); i++) {
          grid.add(fields.get(i), 0, i);
          grid.add(signUpStudentTextFieldList.get(i), 1, i);
      }
      Button signup = new Button("Sign-up!");
      grid.add(signup, 1, fields.size());

      // button functionality
      signup.setOnAction(toSearch -> {
          Map<String,ArrayList<String>> studentMap = this.createNewStudentMap();
          this.addStudentUserText(studentMap, signUpStudentTextFieldList);
          //this.currentDriver.addUser(this.currentUsername, this.addStudentUserText(studentMap, signUpStudentTextFieldList));
          Scene search = search();
          primaryStage.setScene(search);
          primaryStage.show();
      });

      grid.setAlignment(Pos.CENTER);
      BorderPane borderPane = new BorderPane();
      borderPane.setCenter(grid);
      borderPane.setBottom(logout);

      Scene signupScreen = new Scene(borderPane, 800, 600);
      signupScreen.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
      return signupScreen;
  }
  
  /**
   * Create a map out of the user input from the ArrayList of TextFields.  The keys are all of the keys are the attributes that a student can have. 
   * @param map
   * @param userInput
   * @return
   */
  private Map<String,ArrayList<String>> addFacultyUserText(Map<String,ArrayList<String>> map, ArrayList<TextField> userInput){
      map.get(Config.USERNAME_FIELD).add(this.currentUsername);
      
      map.get(Config.PROFILE_TYPE_FIELD).add("faculty");
      
      String[] nameArray = userInput.get(0).getText().split(",");
      for (int curIndex=0;curIndex<nameArray.length;curIndex++) {
          nameArray[curIndex] = nameArray[curIndex].trim();
      }
      map.get(Config.NAME_FIELD).addAll(Arrays.asList(nameArray));
      
      String[] officeBuildingArray = userInput.get(2).getText().split(",");
      for (int curIndex=0;curIndex<officeBuildingArray.length;curIndex++) {
          officeBuildingArray[curIndex] = officeBuildingArray[curIndex].trim();
      }
      map.get(Config.OFFICELOCATION_FIELD).addAll(Arrays.asList(officeBuildingArray));
      
      String[] classesTaughtArray = userInput.get(3).getText().split(",");
      for (int curIndex=0;curIndex<classesTaughtArray.length;curIndex++) {
          classesTaughtArray[curIndex] = classesTaughtArray[curIndex].trim();
      }
      map.get(Config.COURSESTAUGHT_FILED).addAll(Arrays.asList(classesTaughtArray));
      
      String[] officeHoursArray = userInput.get(4).getText().split(",");
      for (int curIndex=0;curIndex<officeHoursArray.length;curIndex++) {
          officeHoursArray[curIndex] = officeHoursArray[curIndex].trim();
      }
      map.get(Config.OFFICEHOURS_FIELD).addAll(Arrays.asList(officeHoursArray));
      
      return map;
  }
  
  /**
   * Create Sign-up scene for Faculty
   */
  private Scene signupScreenFaculty() {
      ArrayList<Text> fields = new ArrayList<Text>();
      fields.add(new Text("Name: "));
      fields.add(new Text("Email: "));
      fields.add(new Text("Office building: "));
      fields.add(new Text("Classes taught: "));
      fields.add(new Text("Office Hours: "));
      
      TextField nameTextField = new TextField();
      TextField emailGraduationTextField = new TextField();
      TextField officeBuildingTextField = new TextField();
      TextField classesTaughtTextField = new TextField();
      TextField officeHoursTextField = new TextField();
      ArrayList<TextField> signUpFacultyTextFieldList = new ArrayList<TextField>() {
          {
              add(nameTextField);
              add(emailGraduationTextField);
              add(officeBuildingTextField);
              add(classesTaughtTextField);
              add(officeHoursTextField);
          }
      };

      GridPane grid = new GridPane();
      for (int i = 0; i < fields.size(); i++) {
          grid.add(fields.get(i), 0, i);
          grid.add(signUpFacultyTextFieldList.get(i), 1, i);
      }
      Button signup = new Button("Sign-up!");
      grid.add(signup, 1, fields.size());

      // button functionality
      signup.setOnAction(toSearch -> {
          Map<String, ArrayList<String>> facultyMap = this.createNewFacultyMap();
          this.addFacultyUserText(facultyMap, signUpFacultyTextFieldList);
        //this.currentDriver.addUser(this.currentUsername, this.addFacultyUserText(studentMap, signUpStudentTextFieldList));
          Scene search = search();
          primaryStage.setScene(search);
          primaryStage.show();
      });

      grid.setAlignment(Pos.CENTER);
      BorderPane borderPane = new BorderPane();
      borderPane.setCenter(grid);
      borderPane.setBottom(logout);

      Scene signupScreen = new Scene(borderPane, 800, 600);
      signupScreen.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
      return signupScreen;
  }

  private Map<String, ArrayList<String>> createNewSearchStudentMap(){
	    Map<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
	    map.put(Config.PROFILE_TYPE_FIELD, new ArrayList<String>());
	    map.put(Config.COURSES_FIELD, new ArrayList<String>());
	    map.put(Config.YEAROFGRAD_FIELD, new ArrayList<String>());
	    map.put(Config.MAJORS_FIELD, new ArrayList<String>());
	    map.put(Config.CERTIFICATES_FIELD, new ArrayList<String>());
	    map.put(Config.CLUBS_FIELD, new ArrayList<String>());
	    map.put(Config.SCHOLARSHIPS_FIELD, new ArrayList<String>());
	    map.put(Config.WORK_EXPERIENCES_FIELD, new ArrayList<String>());
	    return map;
	  }
  
  private Map<String, ArrayList<String>> createNewSearchFacultyMap(){
	    Map<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
	    map.put(Config.PROFILE_TYPE_FIELD, new ArrayList<String>());
	    map.put(Config.OFFICELOCATION_FIELD, new ArrayList<String>());
	    map.put(Config.COURSESTAUGHT_FILED, new ArrayList<String>());
	    map.put(Config.OFFICEHOURS_FIELD, new ArrayList<String>());
	    return map;
	  }
  
  /**
   * Create a map out of the user input from the ArrayList of TextFields.  The keys are all of the keys are the attributes that a student can have. 
   * @param map
   * @param userInput
   * @return
   */
  private Map<String,ArrayList<String>> addSearchStudentUserText(Map<String,ArrayList<String>> map, ArrayList<TextField> userInput, String userType){
	  
	  map.get(Config.PROFILE_TYPE_FIELD).add(userType);
	  
      String[] courseArray = userInput.get(0).getText().split(",");
      for (int curIndex=0;curIndex<courseArray.length;curIndex++) {
          courseArray[curIndex] = courseArray[curIndex].trim();
      }
      map.get(Config.COURSES_FIELD).addAll(Arrays.asList(courseArray));
      
      String[] yearOfGradArray = userInput.get(1).getText().split(",");
      for (int curIndex=0;curIndex<yearOfGradArray.length;curIndex++) {
          yearOfGradArray[curIndex] = yearOfGradArray[curIndex].trim();
      }
      map.get(Config.YEAROFGRAD_FIELD).addAll(Arrays.asList(yearOfGradArray));
      
      String[] majorArray = userInput.get(2).getText().split(",");
      for (int curIndex=0;curIndex<majorArray.length;curIndex++) {
    	  majorArray[curIndex] = majorArray[curIndex].trim();
      }
      map.get(Config.MAJORS_FIELD).addAll(Arrays.asList(majorArray));
      
      String[] certificatesArray = userInput.get(3).getText().split(",");
      for (int curIndex=0;curIndex<majorArray.length;curIndex++) {
    	  certificatesArray[curIndex] = certificatesArray[curIndex].trim();
      }
      map.get(Config.CERTIFICATES_FIELD).addAll(Arrays.asList(certificatesArray));
      
      String[] clubsArray = userInput.get(4).getText().split(",");
      for (int curIndex=0;curIndex<clubsArray.length;curIndex++) {
          clubsArray[curIndex] = clubsArray[curIndex].trim();
      }
      map.get(Config.CLUBS_FIELD).addAll(Arrays.asList(clubsArray));
     
      String[] scholarshipArray = userInput.get(5).getText().split(",");
      for (int curIndex=0;curIndex<scholarshipArray.length;curIndex++) {
          scholarshipArray[curIndex] = scholarshipArray[curIndex].trim();
      }
      map.get(Config.SCHOLARSHIPS_FIELD).addAll(Arrays.asList(scholarshipArray));
      
      String[] workExperienceArray = userInput.get(6).getText().split(",");
      for (int curIndex=0;curIndex<workExperienceArray.length;curIndex++) {
         	workExperienceArray[curIndex] = workExperienceArray[curIndex].trim();
      }
      map.get(Config.WORK_EXPERIENCES_FIELD).addAll(Arrays.asList(workExperienceArray));
      
      return map;
  }
  
  /**
   * Create a map out of the user input from the ArrayList of TextFields.  The keys are all of the keys are the attributes that a student can have. 
   * @param map
   * @param userInput
   * @return
   */
  private Map<String,ArrayList<String>> addSearchFacultyUserText(Map<String,ArrayList<String>> map, ArrayList<TextField> userInput, String userType){
     
	  map.get(Config.PROFILE_TYPE_FIELD).add(userType);
	  
      String[] officeBuildingArray = userInput.get(0).getText().split(",");
      for (int curIndex=0;curIndex<officeBuildingArray.length;curIndex++) {
          officeBuildingArray[curIndex] = officeBuildingArray[curIndex].trim();
      }
      map.get(Config.OFFICELOCATION_FIELD).addAll(Arrays.asList(officeBuildingArray));
      
      String[] classesTaughtArray = userInput.get(1).getText().split(",");
      for (int curIndex=0;curIndex<classesTaughtArray.length;curIndex++) {
          classesTaughtArray[curIndex] = classesTaughtArray[curIndex].trim();
      }
      map.get(Config.COURSESTAUGHT_FILED).addAll(Arrays.asList(classesTaughtArray));
      
      String[] officeHoursArray = userInput.get(2).getText().split(",");
      for (int curIndex=0;curIndex<officeHoursArray.length;curIndex++) {
          officeHoursArray[curIndex] = officeHoursArray[curIndex].trim();
      }
      map.get(Config.OFFICEHOURS_FIELD).addAll(Arrays.asList(officeHoursArray));
      
      System.out.println(map.toString());
      
      return map;
  }
  
  /**
   * Create search screen
   */
  private Scene search() {
    // radio button for which type of user to return
    ToggleGroup type = new ToggleGroup();

    RadioButton userTypeStudent = new RadioButton("Student");
    userTypeStudent.setToggleGroup(type);
    userTypeStudent.setSelected(true);

    RadioButton userTypeFaculty = new RadioButton("Faculty");
    userTypeFaculty.setToggleGroup(type);

    HBox userType = new HBox();
    userType.getChildren().addAll(userTypeStudent, userTypeFaculty);

    ArrayList<Text> fields = new ArrayList<Text>();
    fields.add(new Text("Courses: "));
    fields.add(new Text("Year of graduation: "));
    fields.add(new Text("Major: "));
    fields.add(new Text("Certificates:  "));
    fields.add(new Text("Clubs: "));
    fields.add(new Text("Scholarships: "));
    fields.add(new Text("Work Experience: "));
    fields.add(new Text("Office building: "));
    fields.add(new Text("Classes taught: "));
    fields.add(new Text("Office Hours: "));

    TextField coursesTextField = new TextField();
    TextField yearsGraduationTextField = new TextField();
    TextField majorTextField = new TextField();
    TextField certificatesTextField = new TextField();
    TextField clubsTextField = new TextField();
    TextField scholarshipsTextField = new TextField();
    TextField workExperienceTextField = new TextField();
    TextField officeBuildingTextField = new TextField();
    TextField classesTaughtTextField = new TextField();
    TextField officeHoursTextField = new TextField();
    ArrayList<TextField> studentSearchTextFieldList = new ArrayList<TextField>() {
      {
        add(coursesTextField);
        add(yearsGraduationTextField);
        add(majorTextField);
        add(certificatesTextField);
        add(clubsTextField);
        add(scholarshipsTextField);
        add(workExperienceTextField);
      }
    };
    ArrayList<TextField> facultySearchTextFieldList = new ArrayList<TextField>() {
    	{
    		add(officeBuildingTextField);
    	    add(classesTaughtTextField);
    	    add(officeHoursTextField);
    	}
    };
    
    GridPane grid = new GridPane();
    grid.add(userType, 0, 0);
    for (int i = 1; i < fields.size() + 1; i++) {
      grid.add(fields.get(i - 1), 0, i);
    }

    for (int i = 0;i<studentSearchTextFieldList.size();i++) {
    	grid.add(studentSearchTextFieldList.get(i), 1, i+1);
    }
    
    for (int i = studentSearchTextFieldList.size();i<(studentSearchTextFieldList.size()+facultySearchTextFieldList.size());i++) {
    	grid.add(facultySearchTextFieldList.get(i-studentSearchTextFieldList.size()), 1, i+1);
    }

    Button searchButton = new Button("Search");
    grid.add(searchButton, 1, fields.size() + 1);

    // button functionality
    searchButton.setOnAction(toSearch -> {
      if (userTypeStudent.isSelected()) {
    	  Map<String,ArrayList<String>> studentSearchMap = this.createNewSearchStudentMap();
    	  this.addSearchStudentUserText(studentSearchMap, studentSearchTextFieldList, "student");
    	  this.currentDriver.searchUser(studentSearchMap);
    	  
      } else {
    	  Map<String, ArrayList<String>> facultySearchMap = this.createNewSearchFacultyMap();
    	  this.addSearchFacultyUserText(facultySearchMap, facultySearchTextFieldList, "faculty");
    	  this.currentDriver.searchUser(facultySearchMap);
      }
      
      Scene searchResults = searchResults();
      primaryStage.setScene(searchResults);
      primaryStage.show();
    });

    grid.setAlignment(Pos.CENTER);
    BorderPane borderPane = new BorderPane();
    borderPane.setCenter(grid);
    borderPane.setBottom(logout);

    Scene searchScreen = new Scene(borderPane, 800, 600);
    searchScreen.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
    return searchScreen;
  }

  /**
   * search results screen
   */
  private Scene searchResults() {
    Text users = new Text("Users with your search criteria: ");
    ListView<String> toDisplay = new ListView<String>();
    ObservableList<String> items = FXCollections.observableArrayList();
    if(searchReturn != null) {
    for(int i=0; i<searchReturn.size(); i++) 
      items.add(searchReturn.get(i).getName() + ": " + searchReturn.get(i).getEmail());
    
    toDisplay.setItems(items);
    }
    else {
      items.add("Search returned 0 users.");
      toDisplay.setItems(items);
    }
    
    Text otherInfo = new Text("More information about your search: ");
    ListView<String> reco = new ListView<String>();
    ObservableList<String> recoItems = FXCollections.observableArrayList();
    if(recommended != null) {
    for(int i=0; i<recommended.size(); i++) 
      recoItems.add(recommended.get(i));
    reco.setItems(recoItems); 
    }
    else {
      recoItems.add("No recommended classes.");
      reco.setItems(recoItems);
    }
    Text otherInfo2 = new Text("This search returned 5% of users.");
    GridPane grid = new GridPane();
    grid.add(users, 0, 0);
    grid.add(toDisplay,1,1);
    grid.add(otherInfo, 0, 2);
    grid.add(reco, 1, 3);
    grid.add(otherInfo2, 1, 9);

    // set button functionality, will return to the search screen
    Button searchAgain = new Button("Search again");
    searchAgain.setOnAction(toSearch -> {
      Scene search = search();
      primaryStage.setScene(search);
      primaryStage.show();
    });
    
    BorderPane borderPane = new BorderPane();
    borderPane.setCenter(grid);
    borderPane.setBottom(logout);
    borderPane.setRight(searchAgain);

    Scene searchResults = new Scene(borderPane, 800, 600);
    searchResults.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
    return searchResults;
  }

  /**
   * main that launches the program
   * 
   * @param args
   */
  public static void main(String[] args) {
    launch(args);
  }
}