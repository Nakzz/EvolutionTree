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

	/**
	 * main that launches the program
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
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
		TextField signUpTextField = new TextField("Username");
		grid.add(loginPrompt, 0, 0);
		grid.add(loginTextField, 1, 0);
		grid.add(submit, 3, 0);
		grid.add(signupPrompt, 0, 1);
		grid.add(signUpTextField, 1, 1);

		//ArrayList that contains all of the textfields in the login screen
		ArrayList<TextField> textFieldList = new ArrayList<TextField>() {
			{
				add(loginTextField);
				add(signUpTextField);
			}
		};
		
		signUpTextField.setOnMouseClicked(event->{
			if (signUpTextField.getText().equals("Username")) {
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
		
		grid.add(userTypeStudent, 1, 2);
		grid.add(userTypeFaculty, 1, 3);
		grid.add(blankText, 1, 4);
		grid.add(signup, 1, 5);

		// button functionality
		signup.setOnAction(student -> {
			
//			try {
//				addNewUser()
//				Text userNameAlreadyTaken = new Text("Username Already Taken!");
//				grid.add(userNameAlreadyTaken, 1, 4);
//			} catch (UserExistsException e){
//			}
			/*
			 *  ^
			 * 	|
			 * I need the class/method for finding whether the username was taken or not. 
			 * 
			 * 
			 */			
			if (userTypeStudent.isSelected()) {
				ArrayList<String> textFromLoginScreen = this.getTextFieldInformationFromTextField(textFieldList);
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
				Scene studentSignUp = signupScreenStudent();
				primaryStage.setScene(studentSignUp);
				primaryStage.show();
			} else {
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

		TextField nameTextField = new TextField();
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
			ArrayList textFromStudentSignUpTextField = this.getTextFieldInformationFromTextField(signUpFacultyTextFieldList);
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
	 * Create Sign-up scene for Faculty
	 */
	private Scene signupScreenFaculty() {
		ArrayList<Text> fields = new ArrayList<Text>();
		fields.add(new Text("Name: "));
		fields.add(new Text("Email: "));
		fields.add(new Text("Office building: "));
		fields.add(new Text("Office room number: "));
		fields.add(new Text("Classes taught: "));
		fields.add(new Text("Office Hours: "));
		
		TextField nameTextField = new TextField();
		TextField emailGraduationTextField = new TextField();
		TextField officeBuildingTextField = new TextField();
		TextField officeRoomNumberTextField = new TextField();
		TextField classesTaughtTextField = new TextField();
		TextField officeHoursTextField = new TextField();
		ArrayList<TextField> signUpFacultyTextFieldList = new ArrayList<TextField>() {
			{
				add(nameTextField);
				add(emailGraduationTextField);
				add(officeBuildingTextField);
				add(officeRoomNumberTextField);
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
			ArrayList<String> textFromFacultySignUpTextField = this.getTextFieldInformationFromTextField(signUpFacultyTextFieldList);
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

	private ArrayList<String> getTextFieldInformationFromTextField(ArrayList<TextField> list) {
		ArrayList<String> textList = new ArrayList<String>();
		for (TextField i : list) {
			textList.add(i.getText());
		}
		return textList;
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
			ArrayList<String> textFromSearchList = this.getTextFieldInformationFromTextField(searchTextFieldList);
			/*
			 * 
			 * 
			 * 
			 * 
			 * Here is the output from the search button (textFromSearchList). It Contains all of the information
			 * that the user typed into all of the text fields in the search screen.
			 * the zeroth index is the text that the user typed into the courses textField 
			 * 
			 * 
			 * 
			 * 
			 */
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
}
