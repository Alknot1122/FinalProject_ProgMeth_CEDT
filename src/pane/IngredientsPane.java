package pane;

import Utils.SoundController;
import Utils.getDisplay;
import buttons.AnimatedOtherButton;
import gameLogic.IngredientsRef;
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
import javafx.scene.text.Text;

import java.util.Objects;

public class IngredientsPane extends Pane {

    public IngredientsPane(InventoryPane inventoryPane, Player player){

        setVisible(false);
        setPrefHeight(491);
        setPrefWidth(239);
        setLayoutX(654); setLayoutY(120);

        Image bgImg = new Image(Objects.requireNonNull(getClass().getResource("/Background/ingredientsPane.png")).toExternalForm());
        BackgroundImage BG = new BackgroundImage(bgImg, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        setBackground(new Background(BG));

        Button exitButton = getDisplay.getButton("/Button/exitButton.png", 38,37, 206,-4);
        AnimatedOtherButton.applyButtonAnimation(exitButton);

        exitButton.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                setVisible(false);
                SoundController turnoffSound = new SoundController("res/Sound/CloseIngredientPane.mp3");
                turnoffSound.playMusic();
            }
        });
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setPrefWidth(219); scrollPane.setPrefHeight(460);
        scrollPane.setLayoutY(10); scrollPane.setLayoutX(10);
        scrollPane.setStyle("-fx-background: transparent;\n -fx-background-color: transparent");
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        VBox itemList = new VBox();
        itemList.setPadding(new Insets(5));
        itemList.setSpacing(8);

        itemList.setStyle("-fx-background-color:transparent;");
        itemList.setFillWidth(true);
        itemList.setAlignment(Pos.CENTER);

        scrollPane.setContent(itemList);
        IngredientsRef ingredientsRef = new IngredientsRef();
        for (int i = 0; i < ingredientsRef.getIngredients().size(); i++){

            //make Pane about item's inof
            Pane itemPane = new Pane();
            itemPane.setPrefWidth(212);
            itemPane.setPrefHeight(90);
            itemPane.setStyle("-fx-background-color:transparent;");

            Text itemName = getDisplay.getText(ingredientsRef.getIngredients().get(i).getItemName(),17,119,93,20, Color.WHITE );

            ImageView itemImage = ingredientsRef.getIngredients().get(i).getItemImageView(80);
            itemImage.setLayoutY(7);
            itemImage.setLayoutX(5);

            Button additemButton = getDisplay.getButton("/Button/addToInventory.png",33, 29,93,51 );
            additemButton.setStyle("-fx-background-image: url('/Button/addToInventory.png'); -fx-background-color: transparent;");
            AnimatedOtherButton.applyButtonAnimation(additemButton);

            int finalI = i;
            //when on click, clone the item into inventory pane
            additemButton.setOnMousePressed(mouseEvent -> {
                SoundController clickingButtonNoise = new SoundController("res/Sound/buttonClick.mp3");
                clickingButtonNoise.getMediaPlayer().setVolume(0.7);
                clickingButtonNoise.playMusic();

                Item tem = new Item(ingredientsRef.getIngredients().get(finalI));
                inventoryPane.Itemin(tem);
            });

            itemPane.getChildren().addAll(itemImage, itemName, additemButton);
            itemList.getChildren().add(itemPane);
        }

        getChildren().addAll(scrollPane,exitButton);
    }
}
