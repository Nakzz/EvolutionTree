package application;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


public class Main extends Application {
  Stage primaryStage = new Stage();
  @Override
  public void start(Stage primaryStage) {
    try {
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
    Scene signupScreen = new Scene(grid,800,600);
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
    Scene signupScreen = new Scene(grid,800,600);
    signupScreen.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
    return signupScreen;
  }
  
}
