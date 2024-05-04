package application;

import Utils.*;
import buttons.IngredientButton;
import buttons.OrderButtonAnimation;
import buttons.RecipeButton;
import gameLogic.*;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.Pane;
import pane.*;

import java.util.Objects;

public class GamePage {

    private static Pane root;
    private static inputField inputField;
    private static IngredientsPane ingredientsPane;
    private static OrderPane orderPane;
    private static RecipesBookPane recipesBookPane;
    private static TimerBar timerBarPane; // Add TimerBarPane
    private static InventoryPane inventoryPane;
    private static PinningPane pinningPane;
    private static PlayerIdleAnimation playerIdleAnimation;


   // private final Timeline animationTimeline = new Timeline();


    public GamePage() {
        Player player = new Player();
        inventoryPane = new InventoryPane(player);

        //set timer
        int minute = 5;
        int sec = 0;
        Timer t = new Timer(minute,sec);
        timerBarPane = new TimerBar(t); // Initialize TimerBarPane

        //set timer to start
        timerBarPane.startCountDownTimer(t);

        playerIdleAnimation = new PlayerIdleAnimation();
        orderPane = new OrderPane();
        RecipesRef recipesRef = new RecipesRef();
        GameController gameController = new GameController(player, inventoryPane, timerBarPane, orderPane, recipesRef);

        root = new Pane();
        root.setPrefSize(963, 722);

        inputField = player.getInputField();
        inputField.setVisible(false);
        pinningPane = new PinningPane();
        ingredientsPane = new IngredientsPane(inventoryPane, player );
        recipesBookPane = new RecipesBookPane( gameController, recipesRef, pinningPane);

        //add oven
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

        // Add TimerBarPane
        root.getChildren().add(timerBarPane);
        // Position TimerBarPane on the left side
        setPos(10,10, timerBarPane); // Adjust the X position as needed

        //make game start to random add the order
        GameController.startOrderEvent();

        // Retrieve player image view
        ImageView playerImageView = playerIdleAnimation.getPlayerImageView();
        playerIdleAnimation.playAnimation();


        //set buttons for open and close pane
        Button ingredientsPaneButton = getDisplay.getButton("/Button/ingridentButton.png",154,318,714,31);
        IngredientButton.applyButtonAnimation(ingredientsPaneButton, ingredientsPane);

        Button recipebookButton = getDisplay.getButton("/Button/recipeBookPaneButton.png", 109, 77,320,510);
        RecipeButton.applyButtonAnimation(recipebookButton, recipesBookPane);

        Button OrderButton = getDisplay.getButton("/Button/phoneButton.png", 32, 45, 560, 460);
        OrderButtonAnimation.applyButtonAnimation(OrderButton, orderPane);

        root.getChildren().add(playerImageView);
        root.getChildren().addAll(player.getInputField(), OrderButton, ingredientsPaneButton, recipebookButton);
        root.getChildren().addAll(orderPane, ingredientsPane, recipesBookPane,inventoryPane, pinningPane);
        root.getChildren().addAll(player.getErrorText(), player.getDisplayEventText(), player.getDisplayScore(),
                                  player.getImageDisplay());
        root.getChildren().add(player.getGameOverPane());

        setPos(180, 450, player.getDisplayEventText());
        setPos(250, 250, player.getInputField());
        centerText(player.getDisplayEventText(), root.getPrefWidth());
    }

    public static Parent getRoot() {
        return root;
    }

    public static inputField getInputField() {
        return inputField;
    }

    public static IngredientsPane getIngredientsPane() {
        return ingredientsPane;
    }

    public static OrderPane getOrderPane() {
        return orderPane;
    }

    public static RecipesBookPane getRecipesBookPane() {
        return recipesBookPane;
    }

    public static PinningPane getPinningPane() {
        return pinningPane;
    }

    public static PlayerIdleAnimation getPlayerAnimation() {
        return playerIdleAnimation;
    }

    public void setPos(double Xpos, double Ypos, Node node){
        node.setLayoutX(Xpos);
        node.setLayoutY(Ypos);
    }

    public void centerText(Node node, double sceneWidth) {
        double textWidth = node.getBoundsInLocal().getWidth();
        double xPos = (sceneWidth - textWidth) / 2.0;
        node.setLayoutX(xPos);
    }


}
