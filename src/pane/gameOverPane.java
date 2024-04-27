package pane;

import gameLogic.Player;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class gameOverPane extends VBox {
    //add this
      //set pane size to the screensize cus rn its not in right size
      public gameOverPane(Player player){
          setPrefHeight(500);
          setPrefWidth(500);
          setOpacity(0.5);
          Text gameOverTExt = new Text("Game Over!");
          Text score = new Text("Your score : "+ player.getScores());
          Button replayButton = new Button ("Start again?");
          replayButton.setOnMousePressed(new EventHandler<MouseEvent>() {
              @Override
              public void handle(MouseEvent mouseEvent) {

                  setVisible(false);

              }
          });

      }

}
