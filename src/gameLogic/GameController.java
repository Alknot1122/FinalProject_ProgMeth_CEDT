package gameLogic;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.util.Duration;
import pane.InventoryPane;
import pane.TimerBar;

import java.awt.*;
import java.util.Objects;

public class GameController {
    private int Process;
    private int live;
    private String foodName ;
    private static Player player;
    private static InventoryPane inventoryPane;
    private static Recipe recipe;

    public GameController(Player player, InventoryPane inventoryPane ){
        GameController.inventoryPane = inventoryPane;
        GameController.player = player;

    }

   public void StartCooking ( Recipe recipe){
       String   passImage = ClassLoader.getSystemResource("boxwhencookgood.png").toString();
       String   failedImage = ClassLoader.getSystemResource("burntfoodForBadFood.png").toString();

       for (Item ingrident : recipe.getItems()){
           boolean cookable = false;

           for (int i =0; i < inventoryPane.getItems().length; i++) {
               if (inventoryPane.getItems()[i] != null) {
                   if (Objects.equals(inventoryPane.getItems()[i].getItemName(), ingrident.getItemName())) {

                       cookable = true;

                   }
               }
           }

           if (!cookable){
               player.getErrorText().setText("Missing ingrident :" + ingrident.getItemName());
               Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2.5), event -> {
                   player.getErrorText().setText("");
               }));
               timeline.setCycleCount(1);
               timeline.play();

               return ;

           }
       }

       GameController.recipe = recipe;
       player.getInputField().setEventing(true);
       player.getInputField().setExpectedString("a");
       player.getDisplayEventText().setText("now Type : " + player.getInputField().getExpectedString());


   }
   public boolean Ordersending(Food foodOrder){
       for (int i =0; i < inventoryPane.getItems().length; i++){
           if (Objects.equals(inventoryPane.getItems()[i].getItemName(), foodOrder.getItemName())){
               player.setScores(player.getScores() + foodOrder.getPoints());
               inventoryPane.ItemOut(i);
               return true;
           }
       }
       return false;
   }
   public Boolean BuyIngrident ( Item item, Player player, InventoryPane inventoryPane){


       return false;
   }
   public static void Cookingpass(){
       for (Item ingrident : recipe.getItems()){
           inventoryPane.ItemOut(ingrident.getItemName());
       }
       inventoryPane.Itemin(new Food(recipe.getFood()));
       player.getInputField().setEventing(false);
       player.getDisplayEventText().setText("");
   }
   public static void CookingFailed(){
       System.out.println("lmao L");
       player.getInputField().setEventing(false);
       player.getDisplayEventText().setText("");
   }


}
