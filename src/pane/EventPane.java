package pane;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.Objects;

public class EventPane extends VBox {
    private Text ogtext;
    private TextField playerText;

    private VBox stackpane;
    public EventPane (){

        stackpane = new VBox();

        stackpane.setPrefHeight(55);
        stackpane.setPrefWidth(285);

        stackpane.setBackground(Background.fill(Color.GRAY));
        stackpane.setLayoutX(309);
        stackpane.setLayoutY(401);
         ogtext = new Text();
         playerText = new TextField();

        ogtext.setFont(new Font(20));
        playerText.setFont(new Font(20));
        stackpane.getChildren().addAll(ogtext, playerText);
        playerText.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (stackpane.isVisible()){
                    if (Objects.equals(playerText.getText(), ogtext.getText())){
                        stackpane.setVisible(false);
                    }
                }

            }
        });

    }
    public void start (String startText){

       stackpane.setVisible(true);
       ogtext.setText(startText);
       playerText.setText("");
    }

}
