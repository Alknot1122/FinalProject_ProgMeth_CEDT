package application;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.util.Duration;
import pane.IngredientsPane;
import pane.InventoryPane;
import pane.OrderPane;
import pane.RecipesBookPane;

import java.util.Objects;

public class GamePage {

    private static Pane root;
    private static TextField inputField;
    private static IngredientsPane ingredientsPane;
    private static InventoryPane inventoryPane;
    private static OrderPane orderPane;
    private static RecipesBookPane recipesBookPane;

    private final Timeline animationTimeline = new Timeline();


    public GamePage() {
        root = new Pane();
        root.setPrefSize(1440, 1080);

        ingredientsPane = new IngredientsPane();
        inventoryPane = new InventoryPane();
        orderPane = new OrderPane();
        recipesBookPane = new RecipesBookPane();

        // Load and set the background image
        Image bgImage = new Image(Objects.requireNonNull(getClass().getResource("/kitchen.png")).toExternalForm());
        BackgroundImage backgroundImage = new BackgroundImage(bgImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        root.setBackground(new Background(backgroundImage));

        // Add input text field
        inputField = new TextField();
        inputField.setPromptText("Enter your input");
        inputField.setLayoutX(550); // Adjust the position as needed
        inputField.setLayoutY(350); // Adjust the position as needed
        inputField.setBackground(Background.EMPTY); // Make background transparent
        inputField.setBorder(null); // Remove border
        inputField.setFont(Font.loadFont(getClass().getResourceAsStream("/PeaberryBase.ttf"), 70)); // Load and set Bubblegum Sans font with size 30

        // Apply CSS styling
        inputField.setStyle("-fx-stroke: pink; " +
                "-fx-stroke-width: 3; " +
                "-fx-stroke-line-cap: round; " +
                "-fx-text-fill: white; " +
                "-fx-fill: transparent; " +
                "-fx-effect: dropshadow(three-pass-box, orange, 6, 0.0, 0.0, 1); ");

        inputField.setOnAction(event -> {
            String inputText = inputField.getText();
            System.out.println("Input: " + inputText);
            inputField.clear();

            if ("Ingredients".equalsIgnoreCase(inputText)) {
                // Show IngredientsPane
                if (!root.getChildren().contains(ingredientsPane)) {
                    root.getChildren().add(ingredientsPane);
                }
            } else if ("Inventory".equalsIgnoreCase(inputText)) {
                // Show InventoryPane
                if (!root.getChildren().contains(inventoryPane)) {
                    root.getChildren().add(inventoryPane);
                }
            } else if ("Order".equalsIgnoreCase(inputText)) {
                // Show OrderPane
                if (!root.getChildren().contains(orderPane)) {
                    root.getChildren().add(orderPane);
                }
            } else if ("Recipes".equalsIgnoreCase(inputText)) {
                // Show RecipesBookPane
                if (!root.getChildren().contains(recipesBookPane)) {
                    root.getChildren().add(recipesBookPane);
                }
            }
        });

        root.getChildren().addAll(inputField);

        // Define animation properties
        double startY = inputField.getLayoutY();
        double amplitude = 5;
        double animationDuration = 1000;

        // Create keyframes for animation
        KeyValue keyValueYStart = new KeyValue(inputField.layoutYProperty(), startY);
        KeyValue keyValueYTop = new KeyValue(inputField.layoutYProperty(), startY - amplitude);
        KeyValue keyValueYEnd = new KeyValue(inputField.layoutYProperty(), startY);

        KeyFrame keyFrame1 = new KeyFrame(Duration.ZERO, keyValueYStart);
        KeyFrame keyFrame2 = new KeyFrame(Duration.millis(animationDuration / 2), keyValueYTop);
        KeyFrame keyFrame3 = new KeyFrame(Duration.millis(animationDuration), keyValueYEnd);

        animationTimeline.getKeyFrames().addAll(keyFrame1, keyFrame2, keyFrame3);
        animationTimeline.setCycleCount(Timeline.INDEFINITE);

        // Play the animation
        animationTimeline.play();
    }

    public static Parent getRoot() {
        return root;
    }

    public static TextField getInputField() {
        return inputField;
    }
}
