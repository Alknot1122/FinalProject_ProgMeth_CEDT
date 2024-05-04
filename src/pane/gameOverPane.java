package pane;

import gameLogic.GameController;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
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

          // make gameOverimage
          String   gameoverpic = ClassLoader.getSystemResource("Background/GameOverPane.png").toString();
          Image gameoverimage = new Image(gameoverpic);
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
   public void setscore (int scores){
       //tell player the total score they have
       SCORE_TEXT.setText("Your score : "+ scores);
   }
}
