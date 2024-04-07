package application;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pane.RootPane;

public class Main extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(RootPane.getRootPane(), 1440, 1080);

        stage.setScene(scene);

        stage.setTitle("Cooking Game");

        stage.setResizable(false);

        stage.show();
    }
}