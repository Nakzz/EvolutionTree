package application;

import java.util.ArrayList;
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
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


public class Main extends Application {
  Stage primaryStage;
  Button logout = new Button("Logout"); // add logout button functionality somewhere
  @Override
  public void start(Stage primaryStage) {
    try {
      logout.setOnAction(toLogout->{
        Scene loginScreen = loginScreen();
        primaryStage.setScene(loginScreen);
        primaryStage.show();
      });
      this.primaryStage = primaryStage;
      Scene login = loginScreen();
      primaryStage.setScene(login);
      primaryStage.show();

    } catch(Exception e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    launch(args);
  }

  /**
   * Create the log in scene 
   * @return 
   */
  private  Scene loginScreen() {
    // log in with valid username 
    Text loginPrompt = new Text(20,30,"Login: ");
    TextField userEntry = new TextField();
    Button submit = new Button("Submit");
    HBox loginEntry = new HBox();
    loginEntry.getChildren().addAll(loginPrompt, userEntry,submit);


    Text signupPrompt = new Text(20,30,"Sign-up: ");
    TextField newUsername = new TextField();

    // radio button for the user type when a user wants to sign up
    ToggleGroup type = new ToggleGroup();

    RadioButton userTypeStudent = new RadioButton("Student");
    userTypeStudent.setToggleGroup(type);
    userTypeStudent.setSelected(true);

    RadioButton userTypeFaculty = new RadioButton("Faculty");
    userTypeFaculty.setToggleGroup(type);

    Button signup = new Button("Sign-up!");

    HBox part1 = new HBox();
    part1.getChildren().addAll(signupPrompt, newUsername);

    VBox part2 = new VBox();
    part2.getChildren().addAll(part1,userTypeStudent,userTypeFaculty,signup);

    signup.setOnAction(student -> {
      if(userTypeStudent.isSelected()) {
        Scene studentSignUp = signupScreenStudent();
        primaryStage.setScene(studentSignUp);
        primaryStage.show();
      }
      else {
        Scene facultySignUp = signupScreenFaculty();
        primaryStage.setScene(facultySignUp);
      }
    });

    submit.setOnAction(toSearch->{
      Scene search = search();
      primaryStage.setScene(search);
      primaryStage.show();
    });
    // VBox
    VBox loginMenu = new VBox();
    loginMenu.getChildren().addAll(loginEntry,part2);
    loginMenu.setAlignment(Pos.CENTER);

    BorderPane root = new BorderPane();
    root.setCenter(loginMenu);

    Scene login = new Scene(root,800,600);
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

    GridPane grid = new GridPane();
    for(int i=0; i<fields.size(); i++) {
      grid.add(fields.get(i), 0, i);
      grid.add(new TextField(), 1, i);
    }
    Button signup = new Button("Sign-up!");
    grid.add(signup, 1, fields.size());

    // button functionality
    signup.setOnAction(toSearch -> {
      Scene search = search();
      primaryStage.setScene(search);
      primaryStage.show();      
    });

    BorderPane borderPane = new BorderPane();
    borderPane.setCenter(grid);
    borderPane.setBottom(logout);

    Scene signupScreen = new Scene(borderPane,800,600);
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

    GridPane grid = new GridPane();
    for(int i=0; i<fields.size(); i++) {
      grid.add(fields.get(i), 0, i);
      grid.add(new TextField(), 1, i);
    }
    Button signup = new Button("Sign-up!");
    grid.add(signup, 1, fields.size());

    // button functionality
    signup.setOnAction(toSearch -> {
      Scene search = search();
      primaryStage.setScene(search);
      primaryStage.show();      
    });


    BorderPane borderPane = new BorderPane();
    borderPane.setCenter(grid);
    borderPane.setBottom(logout);

    Scene signupScreen = new Scene(borderPane,800,600);
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
    userType.getChildren().addAll(userTypeStudent,userTypeFaculty);

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

    GridPane grid = new GridPane();
    grid.add(userType, 0, 0);
    for(int i=1; i<fields.size()+1; i++) {
      grid.add(fields.get(i-1),0,i);
      grid.add(new TextField(), 1, i);
    }

    Button search = new Button("Search");
    grid.add(search,1, fields.size()+1);

    // button functionality
    search.setOnAction(toSearch -> {
      Scene searchResults = searchResults();
      primaryStage.setScene(searchResults);
      primaryStage.show();      
    });
    BorderPane borderPane = new BorderPane();
    borderPane.setCenter(grid);
    borderPane.setBottom(logout);

    Scene searchScreen = new Scene(borderPane,800,600);
    searchScreen.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
    return searchScreen;
  }

  /**
   * search results screen
   */
  private Scene searchResults() {
    Text users = new Text("Users with your search criteria: ");
    Text otherInfo = new Text("More information about your search: ");
    GridPane grid = new GridPane();
    grid.add(users, 0, 0);
    grid.add(otherInfo, 0, 1);

    BorderPane borderPane = new BorderPane();
    borderPane.setCenter(grid);
    borderPane.setBottom(logout);

    Scene searchResults = new Scene(borderPane,800,600);
    searchResults.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
    return searchResults;
  }
}
