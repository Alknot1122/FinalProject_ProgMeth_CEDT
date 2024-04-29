package pane;

import gameLogic.IngridentsRef;
import gameLogic.Item;
import gameLogic.Player;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.Objects;

public class IngredientsPane extends AnchorPane {

    public IngredientsPane(InventoryPane inventoryPane, Player player){

        setVisible(false);
        setPrefHeight(491);
        setPrefWidth(239);
        setLayoutX(654); setLayoutY(120);
        Image bgimg = new Image(Objects.requireNonNull(getClass().getResource("/ingridentsPane.png")).toExternalForm());
        BackgroundImage BG = new BackgroundImage(bgimg, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        setBackground(new Background(BG));

        Button exitButton = new Button ();
        Image buttonimg = new Image(Objects.requireNonNull(getClass().getResource("/exitButton.png")).toExternalForm());
        BackgroundImage BGbutton = new BackgroundImage(buttonimg, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        exitButton.setBackground(new Background(BGbutton));
        exitButton.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                setVisible(false);

            }
        });
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setStyle("-fx-background: transparent;\n -fx-background-color: transparent");
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        VBox itemlist = new VBox(8);

        itemlist.setStyle("-fx-background-color:transparent;");
        itemlist.setFillWidth(true);
        itemlist.setAlignment(Pos.CENTER);

        scrollPane.setContent(itemlist);
        IngridentsRef ingridentsRef = new IngridentsRef();
        for (int i =0; i < ingridentsRef.getIngridnets().size(); i++){
            GridPane itemPane = new GridPane();
            itemPane.setHgap(5); itemPane.setVgap(5);
            itemPane.setPrefWidth(212);
            itemPane.setPrefHeight(62);
            itemPane.setStyle("-fx-background-color:transparent;");
            Text itemName = new Text (ingridentsRef.getIngridnets().get(i).getItemName());
            itemName.setWrappingWidth(125);

            itemName.setFont(Font.loadFont(getClass().getResourceAsStream("/PeaberryBase.ttf"),20));
            itemName.setFill(Color.WHITE);

            ImageView itemImage = ingridentsRef.getIngridnets().get(i).getItemImageView(80);




            Button additemButton = new Button("+");
            int finalI = i;
            additemButton.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    Item tem = new Item(ingridentsRef.getIngridnets().get(finalI));

                    inventoryPane.Itemin(tem);
                   
                }
            });

            GridPane.setColumnIndex(itemImage, 0);
            GridPane.setRowIndex(itemImage, 0);
            GridPane.setRowSpan(itemImage, 4);

            GridPane.setColumnIndex(itemName, 1);
            GridPane.setRowIndex(itemName, 1);
            GridPane.setRowSpan(itemName, 2);

            GridPane.setColumnIndex(additemButton, 1);
            GridPane.setRowIndex(additemButton, 3);

            itemPane.getChildren().addAll(itemImage, itemName, additemButton);
            itemlist.getChildren().add(itemPane);
        }




        setTopAnchor(exitButton, 0.0);
        setLeftAnchor(exitButton, 200.0);
        setBottomAnchor(exitButton, 453.40);
        setRightAnchor(exitButton, 0.0);

        setTopAnchor(scrollPane, 13.0);
        setLeftAnchor(scrollPane, 12.0);
        setBottomAnchor(scrollPane, 14.20);
        setRightAnchor(scrollPane, 13.0);
        getChildren().addAll(scrollPane,exitButton);
    }
}
