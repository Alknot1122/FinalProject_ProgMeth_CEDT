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
    //add this
      //set pane size to the screensize cus rn its not in right size
     private final Text scoreText;
      public gameOverPane(){
          setVisible(false);
          setBackground(Background.fill(Color.WHITE));
          setLayoutX(0);
          setLayoutY(0);
          setPrefHeight(722);
          setPrefWidth(963);
         setBackground(Background.fill(Color.rgb(255,255,255,0.75)));
          String   gameoverpic = ClassLoader.getSystemResource("Background/GameOverPane.png").toString();
          Image gameoverimage = new Image(gameoverpic);
          ImageView gameOverImg = new ImageView(gameoverimage);
          gameOverImg.setFitHeight(175);
          gameOverImg.setPreserveRatio(true);
          gameOverImg.setLayoutX(250);
          gameOverImg.setLayoutY(145);


           scoreText = new Text();
          Button replayButton = new Button ("Start again?");
          replayButton.setPrefWidth(105);
          replayButton.setPrefHeight(43);
          replayButton.setLayoutY(418); replayButton.setLayoutX(430);

          scoreText.setFont(Font.loadFont(getClass().getResourceAsStream("/PeaberryBase.ttf"), 32));
          scoreText.setLayoutY(396);
          scoreText.setLayoutX(348);
          getChildren().addAll(gameOverImg, scoreText, replayButton);
          replayButton.setOnMousePressed(new EventHandler<MouseEvent>() {
              @Override
              public void handle(MouseEvent mouseEvent) {
                 setVisible(false);
                 GameController.restart();

              }
          });

      }
   public void setscore (int scores){
    //   System.out.println("set score to " + scores);
       scoreText.setText("Your score : "+ scores);
   }
}
