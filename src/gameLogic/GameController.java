package gameLogic;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.util.Duration;
import pane.*;

import java.awt.*;
import java.util.Objects;
import java.util.Random;

public class GameController {
    private int Process;
    private int live;
    private String foodName ;
    private static Player player;
    private static InventoryPane inventoryPane;
    private static Recipe recipe;
    private static TimerBar timerBar;
    private static Thread orderTrade ;
    private static Boolean isThreadRunning = true;

 private static OrderPane orderPane;
 private static RecipesRef recipesRef;

    public GameController(Player player, InventoryPane inventoryPane, TimerBar timerBar, OrderPane orderPane, RecipesRef recipesRef){
        GameController.inventoryPane = inventoryPane;
        GameController.player = player;
        GameController.timerBar = timerBar;
        this.orderPane = orderPane;
        this.recipesRef = recipesRef;
    }
    public static void restart(){
        player.setScores(0);
        for (int i =0; i < 9; i++){
            inventoryPane.ItemOut(i);
        }
        gameLogic.Timer t = new Timer(0,10);
        timerBar.reset(t);
        timerBar.startCountDownTimer(t);
        isThreadRunning = true;
        orderenter();
    }
    public static synchronized void orderenter(){

       Random random = new Random();
        int randomNumforOrder1 = random.nextInt(40) + 60;
        int randomRecipe1 = random.nextInt(23);
        orderPane.OrderIn(recipesRef.getRecipes().get(randomRecipe1).getFood(), randomNumforOrder1);
        orderTrade = new Thread(() -> {
            while (isThreadRunning) {
                try {
                    int randomnumforWait = random.nextInt(30) + 35;
                    int randomNumforOrder = random.nextInt(30) + 60;
                    int randomRecipe = random.nextInt(23);
                    Thread.sleep(randomnumforWait * 1000);
                    orderPane.OrderIn(recipesRef.getRecipes().get(randomRecipe).getFood(), randomNumforOrder);
                 //  System.out.println(recipesRef.getRecipes().get(randomRecipe).getFood().getItemName());

                } catch (InterruptedException e) {
                    System.out.println("guh");
                }

            }
        });
        orderTrade.start();
    }
    public  static void GameOver(){

          //  System.out.println("thread ended");
            isThreadRunning = false;
            player.getGameOverPane().setVisible(true);
           // System.out.println(player.getGameOverPane().isVisible());
            player.getGameOverPane().setscore(player.getScores());
         //   System.out.println("gameOver!");


    }

   public void StartCooking ( Recipe recipe){



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
       player.getInputField().setExpectedString(recipe.getFood().getItemName());
       player.getDisplayEventText().setText("now Type : " + player.getInputField().getExpectedString());


   }
   public static boolean Ordersending(Food foodOrder){
       for (int i =0; i < inventoryPane.getItems().length; i++){
           if (inventoryPane.getItems()[i] != null){
               if (Objects.equals(inventoryPane.getItems()[i].getItemName(), foodOrder.getItemName())){
                   player.setScores(player.getScores() + foodOrder.getPoints());
                   inventoryPane.ItemOut(i);
                   return true;
               }
           }

       }
       return false;
   }

   public static void Cookingpass(){
       SoundController CookingFailedSound = new SoundController("res/Sound/CookingSuccessed.mp3");
       CookingFailedSound.playMusic();

       String   passImage = ClassLoader.getSystemResource("boxwhencookgood.png").toString();
       Image passimg = new Image(passImage);
       player.getImageDisplay().setVisible(true);
       player.getImageDisplay().setImage(passimg);

       Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2.5), event -> {
           player.getImageDisplay().setVisible(false);
       }));
       timeline.setCycleCount(1);
       timeline.play();

       for (Item ingrident : recipe.getItems()){
           inventoryPane.ItemOut(ingrident.getItemName());
       }
       inventoryPane.Itemin(new Food(recipe.getFood()));
       player.getInputField().setEventing(false);
       player.getInputField().setExpectedString("");
       player.getDisplayEventText().setText("");
       player.getDisplayEventText().getText();
   }
   public static void CookingFailed(){
       SoundController CookingFailedSound = new SoundController("res/Sound/CookingFailed.mp3");
       CookingFailedSound.playMusic();

       String   failedImage = ClassLoader.getSystemResource("burntfoodForBadFood.png").toString();
       Image failimg = new Image(failedImage);
       player.getImageDisplay().setVisible(true);
       player.getImageDisplay().setImage(failimg);

       Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2.5), event -> {
           player.getImageDisplay().setVisible(false);
       }));
       timeline.setCycleCount(1);
       timeline.play();
       for (Item ingrident : recipe.getItems()){
           inventoryPane.ItemOut(ingrident.getItemName());
       }
       player.getInputField().setEventing(false);
       player.getInputField().setExpectedString("");
       player.getDisplayEventText().setText("");
       player.getDisplayEventText().getText();
   }


}
