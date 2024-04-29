package pane;

import gameLogic.IngridentsRef;
import gameLogic.Item;
import gameLogic.Player;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
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

public class IngredientsPane extends Pane {

    public IngredientsPane(InventoryPane inventoryPane, Player player){

        setVisible(false);
        setPrefHeight(491);
        setPrefWidth(239);
        setLayoutX(654); setLayoutY(120);
        Image bgimg = new Image(Objects.requireNonNull(getClass().getResource("/Background/ingridentsPane.png")).toExternalForm());
        BackgroundImage BG = new BackgroundImage(bgimg, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        setBackground(new Background(BG));

        Button exitButton = getDisplay.getButton("/Button/exitButton.png", 38,37, 206,-4);

        exitButton.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                setVisible(false);
                SoundController turnoffSound = new SoundController("res/Sound/CloseIngridentPane.mp3");
                turnoffSound.playMusic();
            }
        });
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setPrefWidth(219); scrollPane.setPrefHeight(460);
        scrollPane.setLayoutY(10); scrollPane.setLayoutX(10);
        scrollPane.setStyle("-fx-background: transparent;\n -fx-background-color: transparent");
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        VBox itemlist = new VBox();
        itemlist.setPadding(new Insets(5));
        itemlist.setSpacing(8);

        itemlist.setStyle("-fx-background-color:transparent;");
        itemlist.setFillWidth(true);
        itemlist.setAlignment(Pos.CENTER);

        scrollPane.setContent(itemlist);
        IngridentsRef ingridentsRef = new IngridentsRef();
        Button templateButton = getDisplay.getButton("/Button/addToinventory.png",33, 29,93,51 );
        for (int i =0; i < ingridentsRef.getIngridnets().size(); i++){
            Pane itemPane = new Pane();

            itemPane.setPrefWidth(212);
            itemPane.setPrefHeight(90);

            itemPane.setStyle("-fx-background-color:transparent;");
            Text itemName = getDisplay.getText(ingridentsRef.getIngridnets().get(i).getItemName(),17,119,93,20 );
            /*Text itemName = new Text (ingridentsRef.getIngridnets().get(i).getItemName());
            itemName.setWrappingWidth(119);
            itemName.setLayoutX(93);*/

            //itemName.setFont(Font.loadFont(getClass().getResourceAsStream("/PeaberryBase.ttf"),17));
            itemName.setFill(Color.WHITE);

            ImageView itemImage = ingridentsRef.getIngridnets().get(i).getItemImageView(80);
            itemImage.setLayoutY(7);
            itemImage.setLayoutX(5);

            Button additemButton = getDisplay.getButton("/Button/addToinventory.png",33, 29,93,51 );
            additemButton.setStyle("-fx-background-image: url('/Button/addToinventory.png'); -fx-background-color: transparent;");

            int finalI = i;
            additemButton.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    SoundController clockingButtonnoise = new SoundController("res/Sound/buttonclick.mp3");
                    clockingButtonnoise.getMediaPlayer().setVolume(0.5);
                    clockingButtonnoise.playMusic();

                    Item tem = new Item(ingridentsRef.getIngridnets().get(finalI));

                    inventoryPane.Itemin(tem);
                   
                }
            });


            itemPane.getChildren().addAll(itemImage, itemName, additemButton);
            itemlist.getChildren().add(itemPane);
        }





        getChildren().addAll(scrollPane,exitButton);
    }
}
