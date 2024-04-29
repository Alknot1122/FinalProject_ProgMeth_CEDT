package pane;

import gameLogic.Food;
import gameLogic.Item;
import gameLogic.Recipe;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.util.Objects;

public class PinningPane extends Pane {
    private Text FoodName ;
    private VBox itemListPane ;
    public PinningPane(){
        setVisible(false);
        setLayoutX(78); setLayoutY(386);
        setPrefHeight(200);
        setPrefWidth(228);
        Image bgimg = new Image(Objects.requireNonNull(getClass().getResource("/Background/pinning.png")).toExternalForm());
        BackgroundImage BG = new BackgroundImage(bgimg, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        setBackground(new Background(BG));

        FoodName = getDisplay.getText("", 17, 169, 14,32);

        itemListPane = new VBox();
        itemListPane.setPadding(new Insets(5));
        itemListPane.setSpacing(5);
        itemListPane.setPrefHeight(154); itemListPane.setPrefWidth(205);
        itemListPane.setLayoutX(9); itemListPane.setLayoutY(40);

        Button exitButton = getDisplay.getButton("/Button/exitButton.png",38,37,195,-4);
      /*  exitButton.setPrefWidth(38); exitButton.setPrefHeight(37);
        exitButton.setLayoutY(-4); exitButton.setLayoutX(195);*/
        exitButton.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                setVisible(false);
            }
        });
        getChildren().addAll(FoodName, itemListPane, exitButton);
    }
    public void setFoodList(Recipe recipe){
        FoodName.setText(recipe.getFood().getItemName());
        itemListPane.getChildren().clear();
        for (Item item : recipe.getItems()){
            Text itemname = getDisplay.getText(item.getImagePath(), 15, 195, 0,0);
            itemListPane.getChildren().add(itemname);

        }

    }
}