package pane;

import gameLogic.Item;
import gameLogic.Player;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Objects;

public class InventoryPane extends HBox {
    private final Item[] items ;
    private int itemAmount;
    private int nextBlankSlot ;
    private Player player;

    public int getNextBlankSlot() {
        return nextBlankSlot;
    }

    public InventoryPane(Player player ){
        this.player = player;
        itemAmount =0;
        nextBlankSlot =0;
        items = new Item[9];
        setPrefHeight(103);
        setPrefWidth(808);
        setLayoutX(78);
        setLayoutY(606);
        setScaleX(0.9); setScaleY(0.9);
        setSpacing(10);

        setStyle("-fx-background-color: rgb(168, 86, 59);" +
        "-fx-border-color: rgb(102, 38, 29);" +
        "-fx-border-radius: 5;" +
                "-fx-border-width: 4;" + "-fx-background-radius: 5");


        setPadding(new Insets(10));

        for (int i =0; i < 9 ; i++){
            items[i]=null;
            Pane pane = new Pane();
            int finalI = i;
            pane.setOnMouseClicked(event ->
                    {
                        if (event.getButton() == MouseButton.SECONDARY){
                            ImageView img = (ImageView) pane.getChildren().getFirst();
                            if (img.isVisible()){
                                ItemOut(finalI);
                            }
                        }


                    }


                    );

            pane.setPrefSize(80,80);
            pane.setStyle("-fx-background-color: rgb(148, 69, 47) ; " + "-fx-background-radius:4;");
            ImageView itemimage = new ImageView(new Image(ClassLoader.getSystemResource("Item/Lemon.png").toString()));
            itemimage.setVisible(false);
            itemimage.setFitHeight(80);

            ScaleTransition scaleTransitionEnter = new ScaleTransition(Duration.millis(200), itemimage);
            scaleTransitionEnter.setToX(1.2);
            scaleTransitionEnter.setToY(1.2);

            ScaleTransition scaleTransitionExit = new ScaleTransition(Duration.millis(200), itemimage);
            scaleTransitionExit.setToX(1.0);
            scaleTransitionExit.setToY(1.0);

            double initialAngle = 5;

            itemimage.setOnMouseEntered(event -> {
                scaleTransitionEnter.play();
            });

           itemimage.setOnMouseExited(event -> {
                scaleTransitionExit.play();
            });

            Timeline IdleAnimation = new Timeline(
                    new KeyFrame(Duration.seconds(1), event -> itemimage.setRotate(0.4)),
                    new KeyFrame(Duration.seconds(2), event -> itemimage.setRotate(-0.4))
            );
            IdleAnimation.setCycleCount(Animation.INDEFINITE);
            IdleAnimation.play();

            itemimage.setPreserveRatio(true);
            pane.getChildren().add(itemimage);
            getChildren().add(pane);

        }
        //System.out.println(getChildren().size());
    }
    public void Itemin(Item item){
        if (itemAmount == 9){
            player.getErrorText().setText("Inventory Full!");
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2.5), event -> {
                player.getErrorText().setText("");
            }));
                timeline.setCycleCount(1);
                timeline.play();
        }
        else {
            Pane pain = (Pane) getChildren().get(getNextBlankSlot());
            ImageView img = (ImageView) pain.getChildren().getFirst();

            items[getNextBlankSlot()] = item;
            img.setImage(item.getItemImage());
            img.setVisible(true);

            for (int i = getNextBlankSlot(); i<9 ;i++){

                if (items[i] == null){
                    nextBlankSlot = i;
                    break;
                }
            }
            itemAmount++;
        }


    }


    public  void ItemOut(String name){
         for (int i = 0; i<getChildren().size() ;i++){
             if (items[i] != null){
                 if (Objects.equals(items[i].getItemName(), name)){
                     items[i] = null;

                     if (i <= nextBlankSlot){
                         nextBlankSlot = i;
                     }
                     Pane pain = (Pane) getChildren().get(i);
                     ImageView img = (ImageView) pain.getChildren().getFirst();
                     img.setVisible(false);

                     itemAmount--;
                     break;
                 }
             }

         }

    }
    public  void ItemOut(int index){
        Pane pain = (Pane) getChildren().get(index);
        ImageView img = (ImageView) pain.getChildren().getFirst();
              if (items[index] != null){
                  items[index] = null;
                  if (index <= nextBlankSlot){
                      nextBlankSlot = index;
                  }
                  img.setVisible(false);
                  itemAmount--;
              }
    }

    public Item[] getItems() {
        return items;
    }
}
