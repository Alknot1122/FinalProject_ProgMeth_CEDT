package application;

import Utils.IngredientButton;
import Utils.OrderButtonAnimation;
import Utils.RecipeButton;
import gameLogic.*;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.Pane;
import javafx.animation.ScaleTransition;
import javafx.scene.effect.Glow;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import javafx.util.Duration;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import pane.*;

import java.util.Objects;

public class GamePage {

    private static Pane root;
    private static TextField inputField;
    private static IngredientsPane ingredientsPane;
    private static OrderPane orderPane;
    private static RecipesBookPane recipesBookPane;
    private static TimerBar timerBarPane; // Add TimerBarPane
    private static InventoryPane inventoryPane;
    private static PinningPane pinningPane;


   // private final Timeline animationTimeline = new Timeline();


    public GamePage() {
        Player player = new Player();
        inventoryPane = new InventoryPane(player);
        int minute = 5;
        int sec = 0;
        Timer t = new Timer(minute,sec);
        timerBarPane = new TimerBar(t); // Initialize TimerBarPane
        timerBarPane.startCountDownTimer(t);
        orderPane = new OrderPane();
        RecipesRef recipesRef = new RecipesRef();
        GameController gameController = new GameController(player, inventoryPane, timerBarPane, orderPane, recipesRef);

        root = new Pane();
        root.setPrefSize(963, 722);

        inputField = new TextField();
        pinningPane = new PinningPane();
        ingredientsPane = new IngredientsPane(inventoryPane, player );
        recipesBookPane = new RecipesBookPane( gameController, recipesRef, pinningPane);



       /* inputField.setPromptText("Enter your input");
        setPos( 250,200, inputField); // Adjust the position as needed

        inputField.setBackground(Background.EMPTY); // Make background transparent
        inputField.setBorder(null); // Remove border
        inputField.setFont(Font.loadFont(getClass().getResourceAsStream("/PeaberryBase.ttf"), 50)); // Load and set Bubblegum Sans font with size 30

        // Apply CSS styling
        inputField.setStyle("-fx-stroke: pink; " +
                "-fx-stroke-width: 3; " +
                "-fx-stroke-line-cap: round; " +
                "-fx-text-fill: white; " +
                "-fx-fill: transparent; " +
                "-fx-effect: dropshadow(three-pass-box, orange, 6, 0.0, 0.0, 1); ");*/

        //this is just in case : add oven image manually
        //[might remove later]
        String  oven1png = ClassLoader.getSystemResource("oven1.png").toString();

        Image OvenImage = new Image(oven1png);

        ImageView oven1 = new ImageView(OvenImage);
        ImageView oven2 = new ImageView(OvenImage);
        ImageView oven3 = new ImageView(OvenImage);
        ImageView[] imgs = new ImageView[]{oven1, oven2, oven3};
        int plus = 0;
        for (ImageView imageView : imgs){
            imageView.setFitHeight(152);
            imageView.setPreserveRatio(true);
            imageView.setLayoutX(3 + plus);
            imageView.setLayoutY(198);
            plus += 112;
            root.getChildren().add(imageView);
        }

        // Load and set the background image

        Image bgImage = new Image(Objects.requireNonNull(getClass().getResource("/Background/kitchen.png")).toExternalForm());
        BackgroundImage backgroundImage = new BackgroundImage(bgImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        root.setBackground(new Background(backgroundImage));
        //play background music



        // Add TimerBarPane
        root.getChildren().add(timerBarPane);
        // Position TimerBarPane on the left side
        setPos(10,10, timerBarPane); // Adjust the X position as needed

        //make game start to random add the order
        GameController.orderenter();



        //set buttons for open and close pane
        Button ingredientsPaneButton = getDisplay.getButton("/Button/ingridentButton.png",154,318,714,31);
        IngredientButton.applyButtonAnimation(ingredientsPaneButton, recipesBookPane);

        Button recipebookButton = getDisplay.getButton("/Button/recipeBookPaneButton.png", 109, 77,320,510);
        RecipeButton.applyButtonAnimation(recipebookButton, recipesBookPane);

        Button OrderButton = getDisplay.getButton("/Button/phoneButton.png", 32, 45, 560, 460);
        OrderButtonAnimation.applyButtonAnimation(OrderButton, orderPane);

        root.getChildren().addAll(player.getInputField(), OrderButton, ingredientsPaneButton, recipebookButton);
        root.getChildren().addAll(orderPane, ingredientsPane, recipesBookPane,inventoryPane, pinningPane);
        root.getChildren().addAll(player.getErrorText(), player.getDisplayEventText(), player.getDisplayScore(),
                player.getImageDisplay());
        root.getChildren().add(player.getGameOverPane());

    }

    public static Parent getRoot() {
        return root;
    }

    public static TextField getInputField() {
        return inputField;
    }


    public void setPos(double Xpos, double Ypos, Node node){
        node.setLayoutX(Xpos);
        node.setLayoutY(Ypos);
    }


}
