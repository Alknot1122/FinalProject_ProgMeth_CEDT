package pane;

import gameLogic.IngridentsRef;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class IngredientsPane extends AnchorPane {

    public IngredientsPane(){
        setPrefHeight(574);
        setPrefWidth(278);
        setBackground(Background.fill(Color.CORAL));
        Button exitButton = new Button ("X");
        exitButton.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                setVisible(false);
            }
        });
        ScrollPane scrollPane = new ScrollPane();
        VBox itemlist = new VBox(4);
        itemlist.setFillWidth(true);
        scrollPane.setContent(itemlist);
        IngridentsRef ingridentsRef = new IngridentsRef();
        for (int i =0; i < ingridentsRef.getIngridnets().size(); i++){
            GridPane itemPane = new GridPane();
            ImageView itemImage = ingridentsRef.getIngridnets().get(i).getItemImage(80);
            Text itemName = new Text (ingridentsRef.getIngridnets().get(i).getItemName());
            itemPane.getChildren().addAll(itemImage, itemName);
            itemPane.setColumnIndex(itemImage, 0);
            itemPane.setRowIndex(itemImage, 0);
            itemPane.setRowSpan(itemImage, 2);

            itemPane.setColumnIndex(itemName, 1);
            itemPane.setRowIndex(itemName, 1);
            itemlist.getChildren().add(itemPane);
        }



        scrollPane.setBackground(Background.fill(Color.GRAY));
        exitButton.setScaleZ(8);
        exitButton.setLayoutX(570);




        setTopAnchor(exitButton, 0.0);
        setLeftAnchor(exitButton, 274.0);
        setBottomAnchor(exitButton, 570.0);
        setRightAnchor(exitButton, 0.0);

        setTopAnchor(scrollPane, 35.0);
        setLeftAnchor(scrollPane, 10.0);
        setBottomAnchor(scrollPane, 10.0);
        setRightAnchor(scrollPane, 10.0);
        getChildren().addAll(exitButton,scrollPane);
    }
}
