package application;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


public class Main extends Application {
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
   */
  private  Scene loginScreen() {
    // log in with valid username 
    Text header = new Text(20,30,"Login: ");
    TextField userEntry = new TextField();
    Button submit = new Button("Submit");
    HBox loginEntry = new HBox();
    loginEntry.getChildren().addAll(header, userEntry,submit);
    
    
    Button signup = new Button("Sign-up");
    // signup.setOnAction(new EventHandler<ActionEvent>());
    // VBox
    VBox loginMenu = new VBox();
    loginMenu.getChildren().addAll(loginEntry);
    loginMenu.setAlignment(Pos.CENTER);
    
    BorderPane root = new BorderPane();
    root.setCenter(loginMenu);

    root.setBottom(signup);
    Scene login = new Scene(root,800,600);
    login.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
    return login;
  }
  
  /**
   * Create Sign-up scene
   */
  private Scene signupScreen() {
    ArrayList<String> fields = new ArrayList<String>();
    fields.add("Name: ");
    fields.add("Email: ");
    fields.add("");
    Text name = new Text(20,30,"Name: ");
    TextField nameField = new TextField();
    
    return null;
  }
  
}
