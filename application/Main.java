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
import java.util.Map;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
	UserDriverApplication currentDriver;
	String currentUsername;
	/**
	 * The start method that sets the stage
	 */
	@Override
	public void start(Stage primaryStage) {
		try {
			logout.setOnAction(toLogout -> {
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
		grid.add(loginPrompt, 0, 0);
		grid.add(loginTextField, 1, 0);
		grid.add(submit, 3, 0);
		grid.add(signupPrompt, 0, 1);
		grid.add(signUpTextField, 1, 1);
		
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
		
		grid.add(userTypeStudent, 1, 2);
		grid.add(userTypeFaculty, 1, 3);
		grid.add(blankText, 1, 4);
		grid.add(signup, 1, 5);

		// button functionality
		signup.setOnAction(student -> {
			if(signUpTextField.getText().split(" ").length>1) {
				grid.add(userNameMoreThanOneWord, 1, 4);
			}
			else if (userTypeStudent.isSelected()) {
				this.currentDriver = new UserDriverApplication();
				try {
					this.currentDriver.register(signUpTextField.getText());
					this.currentUsername = signUpTextField.getText();
				} catch (UserExists e) {
					grid.add(userNameTakenText, 1, 4);
				}
				Scene studentSignUp = signupScreenStudent();
				primaryStage.setScene(studentSignUp);
				primaryStage.show();
			} else {
				this.currentDriver = new UserDriverApplication();
				try {
					this.currentDriver.register(signUpTextField.getText());
					this.currentUsername = signUpTextField.getText();
				} catch (UserExists e) {
					grid.add(userNameTakenText, 1, 4);
				}
				Scene facultySignUp = signupScreenFaculty();
				primaryStage.setScene(facultySignUp);
			}
		});

		// button functionality
		submit.setOnAction(toSearch -> {
			Scene search = search();
			primaryStage.setScene(search);
			primaryStage.show();
		});

		grid.setAlignment(Pos.CENTER);
		BorderPane root = new BorderPane();
		root.setCenter(grid);
		root.setBottom(logout);

		Scene login = new Scene(root, 800, 600);
		login.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		return login;
	}

	private Map<String,ArrayList<String>> addAllUserText(Map<String,ArrayList<String>> map){
		
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
		fields.add(new Text("Minor: "));
		fields.add(new Text("Clubs: "));
		fields.add(new Text("Scholarships: "));
		fields.add(new Text("Courses: "));
		fields.add(new Text("Work Experience: "));

		TextField nameTextField = new TextField(this.currentUsername);
		TextField emailGraduationTextField = new TextField();
		TextField yearOfGraduationTextField = new TextField();
		TextField majorTextField = new TextField();
		TextField minorTextField = new TextField();
		TextField clubsTextField = new TextField();
		TextField scholarshipsTextField = new TextField();
		TextField coursesTextField = new TextField();
		TextField workExperienceTextField = new TextField();
		ArrayList<TextField> signUpFacultyTextFieldList = new ArrayList<TextField>() {
			{
				add(nameTextField);
				add(emailGraduationTextField);
				add(yearOfGraduationTextField);
				add(majorTextField);
				add(minorTextField);
				add(clubsTextField);
				add(scholarshipsTextField);
				add(coursesTextField);
				add(workExperienceTextField);
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
			Map<String,ArrayList<String>> studentMap = this.createNewStudentMap();
			
			this.currentDriver.addUser(this.currentUsername, profileInfo)
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
			
			/*
			 * 
			 * 
			 * 
			 * 
			 * 
			 * Here is where the output for the signUpButton is. There is an arrayList
			 * already created that has the two textFields text (String) in it. It is the textFromLoginScreen
			 * 
			 * 
			 * 
			 * 
			 * 
			 */
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
		fields.add(new Text("Minor: "));
		fields.add(new Text("Clubs: "));
		fields.add(new Text("Scholarships: "));
		fields.add(new Text("Work Experience: "));
		fields.add(new Text("Office building: "));
		fields.add(new Text("Office room number: "));
		fields.add(new Text("Classes taught: "));
		fields.add(new Text("Office Hours: "));

		TextField coursesTextField = new TextField();
		TextField yearsGraduationTextField = new TextField();
		TextField majorTextField = new TextField();
		TextField minorTextField = new TextField();
		TextField clubsTextField = new TextField();
		TextField scholarshipsTextField = new TextField();
		TextField workExperienceTextField = new TextField();
		TextField officeBuildingTextField = new TextField();
		TextField officeRoomNumberTextField = new TextField();
		TextField classesTaughtTextField = new TextField();
		TextField officeHoursTextField = new TextField();
		ArrayList<TextField> searchTextFieldList = new ArrayList<TextField>() {
			{
				add(coursesTextField);
				add(yearsGraduationTextField);
				add(majorTextField);
				add(minorTextField);
				add(clubsTextField);
				add(scholarshipsTextField);
				add(workExperienceTextField);
				add(officeBuildingTextField);
				add(officeRoomNumberTextField);
				add(classesTaughtTextField);
				add(officeHoursTextField);
			}
		};

		GridPane grid = new GridPane();
		grid.add(userType, 0, 0);
		for (int i = 1; i < fields.size() + 1; i++) {
			grid.add(fields.get(i - 1), 0, i);
			grid.add(searchTextFieldList.get(i - 1), 1, i);
		}

		Button searchButton = new Button("Search");
		grid.add(searchButton, 1, fields.size() + 1);

		// button functionality
		searchButton.setOnAction(toSearch -> {
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
		Text user1 = new Text("Erica Heying: eheying@wisc.edu");
		Text user2 = new Text("Ben Procknow: bprocknow@wisc.edu");
		Text user3 = new Text("Ajman Naqab : @wisc.edu");
		Text user4 = new Text("Callan Patel: cpatel@wisc.edu");
		Text otherInfo = new Text("More information about your search: ");
		Text otherInfo1 = new Text("Recommended next course: CS352");
		Text otherInfo2 = new Text("This search returned 5% of users.");
		GridPane grid = new GridPane();
		grid.add(users, 0, 0);
		grid.add(user1, 1, 2);
		grid.add(user2, 1, 3);
		grid.add(user3, 1, 4);
		grid.add(user4, 1, 5);
		grid.add(otherInfo, 0, 7);
		grid.add(otherInfo1, 1, 8);
		grid.add(otherInfo2, 1, 9);

		BorderPane borderPane = new BorderPane();
		borderPane.setCenter(grid);
		borderPane.setBottom(logout);

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

