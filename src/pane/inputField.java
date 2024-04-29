package pane;

import application.GamePage;
import gameLogic.GameController;
import gameLogic.Player;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.text.Font;
import javafx.util.Duration;

import java.util.Objects;

import static gameLogic.Player.*;

public class inputField extends TextField {
 private boolean Eventing;
 private String expectedString = "";
    private final Timeline animationTimeline = new Timeline();

 public String getExpectedString() {
  return expectedString;
 }

 public void setExpectedString(String expectedString) {
  this.expectedString = expectedString;
 }

 public boolean isEventing() {
  return Eventing;
 }

 public void setEventing(boolean eventing) {
  Eventing = eventing;
 }

 public inputField(){

     Eventing = false;
       setPromptText("Enter your input");

        setLayoutX(250); setLayoutY(200);
        setBackground(Background.EMPTY); // Make background transparent
        setBorder(null); // Remove border
        setFont(Font.loadFont(getClass().getResourceAsStream("/PeaberryBase.ttf"), 47));

        // Apply CSS styling
        setStyle("-fx-stroke: pink; " +
                "-fx-stroke-width: 3; " +
                "-fx-stroke-line-cap: round; " +
                "-fx-text-fill: white; " +
                "-fx-fill: transparent; " +
                "-fx-effect: dropshadow(three-pass-box, orange, 6, 0.0, 0.0, 1); ");
        // Define animation properties
        double startY = getLayoutY();
        double amplitude = 5;
        double animationDuration = 1000;

        // Create keyframes for animation
        KeyValue keyValueYStart = new KeyValue(layoutYProperty(), startY);
        KeyValue keyValueYTop = new KeyValue(layoutYProperty(), startY - amplitude);
        KeyValue keyValueYEnd = new KeyValue(layoutYProperty(), startY);

        KeyFrame keyFrame1 = new KeyFrame(Duration.ZERO, keyValueYStart);
        KeyFrame keyFrame2 = new KeyFrame(Duration.millis(animationDuration / 2), keyValueYTop);
        KeyFrame keyFrame3 = new KeyFrame(Duration.millis(animationDuration), keyValueYEnd);

        animationTimeline.getKeyFrames().addAll(keyFrame1, keyFrame2, keyFrame3);
        animationTimeline.setCycleCount(Timeline.INDEFINITE);

        // Play the animation
        animationTimeline.play();
        setOnAction(new EventHandler<ActionEvent>() {
         @Override
         public void handle(ActionEvent actionEvent) {
          if (isEventing()){
              if (getText().equalsIgnoreCase(getExpectedString())) {
                  GameController.Cookingpass();
                  GamePage.getInputField().setVisible(false);
                  clear();
              }
             else {
             GameController.CookingFailed();
             GamePage.getInputField().setVisible(false);
             clear();
             }
          }
         }
        });
    }


}
