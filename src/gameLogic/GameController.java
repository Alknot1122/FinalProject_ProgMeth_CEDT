package gameLogic;

import Utils.CookingFinishAnimation;
import Utils.PlayerAnimation;
import Utils.ZoomTransitionUtil;
import application.GamePage;
import javafx.animation.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.util.Duration;
import javafx.util.Duration;
import pane.*;

import java.awt.*;
import java.util.Objects;
import java.util.Random;

public class GameController {
    private static Player player;
    private static InventoryPane inventoryPane;
    private static Recipe recipe;
    private static TimerBar timerBar;
    private static Boolean isOrderThreadRunning = false;

    private static Thread OrderEventThred;

 private static OrderPane orderPane;
 private static RecipesRef recipesRef;

    public GameController(Player player, InventoryPane inventoryPane, TimerBar timerBar, OrderPane orderPane, RecipesRef recipesRef){
        GameController.inventoryPane = inventoryPane;
        GameController.player = player;
        GameController.timerBar = timerBar;
        GameController.orderPane = orderPane;
        GameController.recipesRef = recipesRef;

        Random random = new Random();

        OrderEventThred = new Thread(() -> {
            while (isOrderThreadRunning) {
                try {
                    int delayBeforeAnotherOrder = random.nextInt(30) + 35;
                    int timeAmountforOrderTimer = random.nextInt(30) + 60;
                    int randomRecipe = random.nextInt(23);
                    Thread.sleep(delayBeforeAnotherOrder * 1000);

                    orderPane.OrderIn(recipesRef.getRecipes().get(randomRecipe).getFood(), timeAmountforOrderTimer);

                } catch (InterruptedException e) {
                    System.out.println("thread intruded");
                }

            }
        });
    }
    public static void restart(){
        //reset player's score and clear player's inventory
        player.setScores(0);
        for (int i =0; i < 9; i++){
            inventoryPane.ItemOut(i);
        }

        //reset the timer
        int minutes = 5;
        int seconds = 0;
        gameLogic.Timer t = new Timer(minutes,seconds);
        timerBar.reset(t);
        timerBar.startCountDownTimer(t);

        //run the orderThread
       isOrderThreadRunning = true;
    }
    public static synchronized void startOrderEvent(){
        Random random = new Random();

        //add random order with random time for timer
        int randomNumforOrder1 = random.nextInt(40) + 60;
        int randomRecipe1 = random.nextInt(23);
        orderPane.OrderIn(recipesRef.getRecipes().get(randomRecipe1).getFood(), randomNumforOrder1);

        //run the OrderEventThread
        isOrderThreadRunning = true;
        if (!OrderEventThred.isAlive()){
            OrderEventThred.start();
        }

    }
    public  static void GameOver(){
            //stop the OrderEventThred
            isOrderThreadRunning = false;

            //make gameOverPane visible and show player's score
            player.getGameOverPane().setVisible(true);
            player.getGameOverPane().setscore(player.getScores());

    }


   public void StartCooking ( Recipe recipe){

       GameController.recipe = recipe;
       for (Item ingrident : recipe.getItems()){
           boolean cookable = false;

           for (int i =0; i < inventoryPane.getItems().length; i++) {
               if (inventoryPane.getItems()[i] != null) {
                   if (inventoryPane.getItems()[i].getItemName().equalsIgnoreCase(ingrident.getItemName())) {
                       System.out.println(inventoryPane.getItems()[i].getItemName() + "=" + ingrident.getItemName());
                       cookable = true;
                   }
               }
           }

           if (!cookable) {
               StringBuilder missingIngredients = new StringBuilder();
               boolean missing = false;

               for (Item ingredient : recipe.getItems()) {
                   boolean found = false;
                   for (Item inventoryItem : inventoryPane.getItems()) {
                       if (inventoryItem != null && inventoryItem.getItemName().equalsIgnoreCase(ingredient.getItemName())) {
                           found = true;
                           break;
                       }
                   }
                   if (!found) {
                       missingIngredients.append(ingredient.getItemName()).append(", ");
                       missing = true;
                   }
               }

               if (missing) {
                   player.getInputField().setVisible(false);
                   String missingList = missingIngredients.substring(0, missingIngredients.length() - 2); // Remove the last comma and space
                   player.getErrorText().setText("Missing ingredients: " + missingList);
                   Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2.5), event -> {
                       player.getErrorText().setText("");
                   }));
                   timeline.setCycleCount(1);
                   timeline.play();
                   return;
               }
           }
       }
           ZoomTransitionUtil.applyZoomTransition(GamePage.getRoot());
           GamePage.getIngredientsPane().setVisible(false);
           GamePage.getOrderPane().setVisible(false);
           GamePage.getPinningPane().setVisible(false);
           GamePage.getRecipesBookPane().setVisible(false);
           player.getInputField().setVisible(true);
           player.getInputField().requestFocus();
           player.getInputField().setEventing(true);
           player.getInputField().setExpectedString(recipe.getFood().getItemName());
           player.getDisplayEventText().setText("Type : " + player.getInputField().getExpectedString());
       }

   public static boolean Ordersending(Food foodOrder){
        //check inventory pane if it has required food
       for (int i =0; i < inventoryPane.getItems().length; i++){
           if (inventoryPane.getItems()[i] != null){

               if (Objects.equals(inventoryPane.getItems()[i].getItemName(), foodOrder.getItemName())){
                   //if they do have required, give player food's score
                   //remove that food in inventory pane
                   player.setScores(player.getScores() + foodOrder.getPoints());
                   inventoryPane.ItemOut(i);
                   return true;
               }

           }

       }
       return false;
   }

    public static void Cookingpass() {
        SoundController cookingSuccessSound = new SoundController("res/Sound/CookingSuccessed.mp3");
        cookingSuccessSound.playMusic();

        String passImage = ClassLoader.getSystemResource("boxwhencookgood.png").toString();
        Image passimg = new Image(passImage);
        player.getImageDisplay().setVisible(true);
        player.getImageDisplay().setImage(passimg);

        // Play happy animation
        PlayerAnimation playerAnimation = GamePage.getPlayerAnimation();
        playerAnimation.playHappyAnimation();

        // Schedule a task to transition back to idle animation after 5 seconds
        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        playerAnimation.playIdleAnimation();
                    }
                },
                5000
        );

        // Play cooking finish animation
        ImageView imageView = player.getImageDisplay(); // Assuming this is your ImageView
        CookingFinishAnimation cookingFinishAnimation = new CookingFinishAnimation(imageView);
        cookingFinishAnimation.playAnimation();

        // Clearing input field and event text
        for (Item ingredient : recipe.getItems()) {
            inventoryPane.ItemOut(ingredient.getItemName());
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

        PlayerAnimation playerAnimation = GamePage.getPlayerAnimation();
        playerAnimation.playSadAnimation();

        // Schedule a task to transition back to idle animation after 5 seconds
        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        playerAnimation.playIdleAnimation();
                    }
                },
                5000
        );

        // Play cooking finish animation
        ImageView imageView = player.getImageDisplay(); // Assuming this is your ImageView
        CookingFinishAnimation cookingFinishAnimation = new CookingFinishAnimation(imageView);
        cookingFinishAnimation.playAnimation();

       for (Item ingrident : recipe.getItems()){
           inventoryPane.ItemOut(ingrident.getItemName());
       }
       player.getInputField().setEventing(false);
       player.getInputField().setExpectedString("");
       player.getDisplayEventText().setText("");
       player.getDisplayEventText().getText();
   }


}
