package pane;

import Utils.getDisplay;
import gameLogic.Food;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class OneOrderPane extends Pane {
    private final Food FOOD;
    private final Text TIMER;

    private final Button SENDING_BUTTON;
    public OneOrderPane(Food food){
        setStyle("-fx-background-color:transparent;");
        setPrefSize(170,70);

        this.FOOD = food;

        //set order's name and timer
        Text orderName = getDisplay.getText(food.getItemName(), 16,118, 70,20);
        TIMER = getDisplay.getText("", 18, 77, 70,61);

        SENDING_BUTTON = getDisplay.getButton("/Button/deliveryButton.png",34,33,141,42);
        SENDING_BUTTON.setStyle("-fx-background-image: url('/Button/deliveryButton.png'); -fx-background-color: transparent;");

        ImageView foodImageShow = food.getItemImageView(58);
        foodImageShow.setLayoutY(6); foodImageShow.setLayoutX(8);
        foodImageShow.setPreserveRatio(true);
        getChildren().addAll(foodImageShow, SENDING_BUTTON, TIMER, orderName);

    }

    public Food getFood() {
        return FOOD;
    }

    public Text getTimer() {
        return TIMER;
    }

    public Button getSendingbutton() {
        return SENDING_BUTTON;
    }
}
