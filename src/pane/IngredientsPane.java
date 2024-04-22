package pane;

import gameLogic.IngridentsRef;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class IngredientsPane extends AnchorPane {

    public IngredientsPane(){
        setVisible(false);
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
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        VBox itemlist = new VBox(4);
        itemlist.setFillWidth(true);
        itemlist.setAlignment(Pos.CENTER);

        scrollPane.setContent(itemlist);
        IngridentsRef ingridentsRef = new IngridentsRef();
        for (int i =0; i < ingridentsRef.getIngridnets().size(); i++){
            GridPane itemPane = new GridPane(4,4);

            Text itemName = new Text (ingridentsRef.getIngridnets().get(i).getItemName());
            itemName.setFont(new Font(20));
            itemName.setFill(Color.WHITE);

            ImageView itemImage = ingridentsRef.getIngridnets().get(i).getItemImageView(80);


            itemPane.setPrefWidth(260);
            itemPane.setBackground(Background.fill(Color.GRAY));

            itemPane.setColumnIndex(itemImage, 0);
            itemPane.setRowIndex(itemImage, 0);
            itemPane.setRowSpan(itemImage, 2);
            itemPane.setColumnIndex(itemName, 1);
            itemPane.setRowIndex(itemName, 1);

            itemPane.getChildren().addAll(itemImage, itemName);
            itemlist.getChildren().add(itemPane);
        }

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
