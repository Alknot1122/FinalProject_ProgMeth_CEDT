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

        Text orderName = new Text(food.getItemName());
        orderName.setFont(Font.loadFont(getClass().getResourceAsStream("/PeaberryBase.ttf"), 16));
        orderName.setLayoutX(64); orderName.setLayoutY(20);
        orderName.setWrappingWidth(118);

         timer = new Text();
        timer.setFont(Font.loadFont(getClass().getResourceAsStream("/PeaberryBase.ttf"), 18));
        timer.setLayoutX(64); timer.setLayoutY(61);
        timer.setWrappingWidth(77);

         sendingbutton = new Button("Delivery");
        sendingbutton.setLayoutX(141); sendingbutton.setLayoutY(42);


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
