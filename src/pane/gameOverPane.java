package pane;

import Utils.getDisplay;
import gameLogic.GameController;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class gameOverPane extends Pane {

     private final Text SCORE_TEXT;
      public gameOverPane(){

          setVisible(false);
          setBackground(Background.fill(Color.rgb(255,255,255,0.75)));
          setLayoutX(0);
          setLayoutY(0);
          setPrefHeight(722);
          setPrefWidth(963);

          // make gameOverImage
          String   gameOverPic = ClassLoader.getSystemResource("Background/GameOverPane.png").toString();
          Image gameoverimage = new Image(gameOverPic);
          ImageView gameOverImg = new ImageView(gameoverimage);

          // set gameOverImage Layout
          gameOverImg.setFitHeight(175);
          gameOverImg.setPreserveRatio(true);
          gameOverImg.setLayoutX(250);
          gameOverImg.setLayoutY(145);

          SCORE_TEXT = getDisplay.getText("", 32, 400, 376,348);

          Button replayButton = getDisplay.getButton("/Button/restartButton.png",201,59,395,380);
          getChildren().addAll(gameOverImg, SCORE_TEXT, replayButton);

          replayButton.setOnMousePressed(mouseEvent -> {
              // close this pane and restart the game
             setVisible(false);
             GameController.restart();
          });

      }
   public void setScore(int scores){
       //tell player the total score they have
       SCORE_TEXT.setText("Your score : "+ scores);
   }
}
