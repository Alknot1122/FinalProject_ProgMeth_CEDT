package pane;

import gameLogic.Food;
import gameLogic.GameController;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class OneOrderPane extends Pane {
    private Food food;
    private Text timer;

    private Button sendingbutton;
    public OneOrderPane(Food food){
        setStyle("-fx-background-color:transparent;");
        this.food = food;

        setPrefSize(170,70);

        Text orderName = getDisplay.getText(food.getItemName(), 16,118, 70,20);
         timer = getDisplay.getText("", 18, 77, 70,61);


         sendingbutton = getDisplay.getButton("/Button/deliveryButton.png",34,33,141,42);
        sendingbutton.setStyle("-fx-background-image: url('/Button/deliveryButton.png'); -fx-background-color: transparent;");


        ImageView foodimagShow = food.getItemImageView(58);
        foodimagShow.setLayoutY(6); foodimagShow.setLayoutX(8);
        foodimagShow.setPreserveRatio(true);
        getChildren().addAll(foodimagShow, sendingbutton, timer, orderName);

    }

    public Food getFood() {
        return food;
    }

    public Text getTimer() {
        return timer;
    }

    public Button getSendingbutton() {
        return sendingbutton;
    }
}
