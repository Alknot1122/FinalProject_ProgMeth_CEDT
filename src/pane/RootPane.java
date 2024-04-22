package pane;


import gameLogic.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import javafx.util.Duration;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Objects;


public class RootPane extends Pane {
    private static RootPane instance;

    private RootPane() {

        SoundController backgroundMusic = new SoundController("res/Sound/Backgroundmusic.mp3");
        backgroundMusic.getMediaPlayer().setVolume(0.75);
        backgroundMusic.getMediaPlayer().setOnEndOfMedia(() -> {
            backgroundMusic.getMediaPlayer().seek(Duration.ZERO);
            backgroundMusic.getMediaPlayer().play();
        });
        //please set pane's position afterward
        //this is example of closeing and open pane
        backgroundMusic.getMediaPlayer().play();
        IngredientsPane ingredientsPane = new IngredientsPane();
       RecipesBookPane recipesBookPane = new RecipesBookPane();
       OrderPane orderPane = new OrderPane();

       Button ingredientsPaneButton = new Button("ingridentPane button");
        ingredientsPaneButton.setLayoutX(100);

        Button recipebookButton = new Button("recipesBookPane button");
        recipebookButton.setLayoutX(200);

        Button OrderButton = new Button("orderPane button");
        OrderButton.setLayoutY(300);

       //all pane i made will set isvisible to fasle first and we make button like this in game
        //except gameover pane, u gotta turn the isvisible true when its game over
       ingredientsPaneButton.setOnMousePressed(new EventHandler<MouseEvent>() {
           @Override
           public void handle(MouseEvent mouseEvent) {
               ingredientsPane.setVisible(!ingredientsPane.isVisible());
           }
       });
        recipebookButton.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                recipesBookPane.setVisible(!recipesBookPane.isVisible());
            }
        });
        OrderButton.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                orderPane.setVisible(!orderPane.isVisible());
            }
        });

        getChildren().addAll(recipesBookPane, ingredientsPane, orderPane, ingredientsPaneButton,recipebookButton,OrderButton);
    }


    public static RootPane getRootPane() {
        if (instance == null)
            instance = new RootPane();
        return instance;
    }
}
