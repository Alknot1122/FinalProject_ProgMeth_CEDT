package pane;

import Utils.getDisplay;
import gameLogic.Item;
import gameLogic.Recipe;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

import java.util.Objects;

public class PinningPane extends Pane {
    private final Text FoodName ;
    private final VBox itemListPane ;
    public PinningPane(){

        setVisible(false);
        setLayoutX(78);
        setLayoutY(386);
        setPrefHeight(200);
        setPrefWidth(228);

        //set Pane's image Background
        Image bgimg = new Image(Objects.requireNonNull(getClass().getResource("/Background/pinning.png")).toExternalForm());
        BackgroundImage BG = new BackgroundImage(bgimg, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        setBackground(new Background(BG));

        FoodName = getDisplay.getText("", 17, 169, 14,32);

        //Make Vbox for list of items
        itemListPane = new VBox();
        itemListPane.setPadding(new Insets(5));
        itemListPane.setSpacing(5);
        itemListPane.setPrefHeight(154); itemListPane.setPrefWidth(205);
        itemListPane.setLayoutX(9); itemListPane.setLayoutY(40);

        //make close button
        Button closeButton = getDisplay.getButton("/Button/exitButton.png",38,37,195,-4);
        closeButton.setOnMousePressed(mouseEvent -> setVisible(false));

        getChildren().addAll(FoodName, itemListPane, closeButton);
    }
    public void setRecipeList(Recipe recipe){
     // change current recipe inof to new recipe
        FoodName.setText(recipe.getFood().getItemName());
        itemListPane.getChildren().clear();
        for (Item item : recipe.getItems()){
            Text itemname = getDisplay.getText(item.getItemName(), 15, 195, 0,0);
            itemListPane.getChildren().add(itemname);
        }
    }
}
