package application;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import pane.OrderPane;

import static pane.RootPane.getRootPane;

public class Main extends Application {

    // launch the application
    public void start(Stage stage){
    Scene scene = new Scene(getRootPane(), 800,800);

        stage.setTitle("Cooking game mf");
        stage.setScene(scene);
        stage.setResizable(false);

        stage.show();
    }

    // Main Method
    public static void main(String args[])
    {

        // launch the application
        launch(args);
    }
}