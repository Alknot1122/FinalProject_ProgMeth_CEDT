package application;

<<<<<<< Updated upstream
=======
import gameLogic.Food;
import gameLogic.GameController;
import gameLogic.Player;
>>>>>>> Stashed changes
import gameLogic.Timer;
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
import pane.OrderPane;
import pane.RecipesBookPane;
import pane.TimerBar;
import java.util.Objects;

public class GamePage {

    private static Pane root;
    private static TextField inputField;
    private static IngredientsPane ingredientsPane;
    private static OrderPane orderPane;
    private static RecipesBookPane recipesBookPane;
    private static TimerBar timerBarPane; // Add TimerBarPane

   // private final Timeline animationTimeline = new Timeline();


    public GamePage() {
        Player player = new Player();
        inventoryPane = new InventoryPane(player);
        GameController gameController = new GameController(player, inventoryPane);
        root = new Pane();
<<<<<<< Updated upstream
        root.setPrefSize(1440, 1080);

        ingredientsPane = new IngredientsPane();
        orderPane = new OrderPane();
        recipesBookPane = new RecipesBookPane();
        timerBarPane = new TimerBar(new Timer(1,0)); // Initialize TimerBarPane

        // Load and set the background image
        Image bgImage = new Image(Objects.requireNonNull(getClass().getResource("/kitchen.png")).toExternalForm());
        BackgroundImage backgroundImage = new BackgroundImage(bgImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        root.setBackground(new Background(backgroundImage));

        // Add TimerBarPane
        root.getChildren().add(timerBarPane);
        // Position TimerBarPane on the left side
        timerBarPane.setLayoutX(10); // Adjust the X position as needed
        timerBarPane.setLayoutY(10); // Adjust the Y position as needed

        // Add input text field
        inputField = new TextField();
        inputField.setPromptText("Enter your input");
        inputField.setLayoutX(550); // Adjust the position as needed
        inputField.setLayoutY(350); // Adjust the position as needed
=======
        root.setPrefSize(963, 722);

        // Add input text field
        inputField = player.getInputField();
       // inputField = new TextField();


        ingredientsPane = new IngredientsPane(inventoryPane, player );
        orderPane = new OrderPane();
        recipesBookPane = new RecipesBookPane(inventoryPane, player, gameController);
        Timer t = new Timer(1,0);
        timerBarPane = new TimerBar(t); // Initialize TimerBarPane

       // timerBarPane.startCountDownTimer(t);
       /* inputField.setPromptText("Enter your input");
        setPos( 250,200, inputField); // Adjust the position as needed

>>>>>>> Stashed changes
        inputField.setBackground(Background.EMPTY); // Make background transparent
        inputField.setBorder(null); // Remove border
        inputField.setFont(Font.loadFont(getClass().getResourceAsStream("/PeaberryBase.ttf"), 70)); // Load and set Bubblegum Sans font with size 30

        // Apply CSS styling
        inputField.setStyle("-fx-stroke: pink; " +
                "-fx-stroke-width: 3; " +
                "-fx-stroke-line-cap: round; " +
                "-fx-text-fill: white; " +
                "-fx-fill: transparent; " +
<<<<<<< Updated upstream
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

=======
                "-fx-effect: dropshadow(three-pass-box, orange, 6, 0.0, 0.0, 1); ");*/
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

        Image bgImage = new Image(Objects.requireNonNull(getClass().getResource("/kitchen.png")).toExternalForm());
        BackgroundImage backgroundImage = new BackgroundImage(bgImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        root.setBackground(new Background(backgroundImage));
        //play background music

        SoundController backgroundMusic = new SoundController("res/Sound/Backgroundmusic.mp3");
        backgroundMusic.getMediaPlayer().setVolume(0.75);
        backgroundMusic.getMediaPlayer().setOnEndOfMedia(() -> {
            backgroundMusic.getMediaPlayer().seek(Duration.ZERO);
            backgroundMusic.getMediaPlayer().play();
        });
        backgroundMusic.getMediaPlayer().play();

        // Add TimerBarPane
        root.getChildren().add(timerBarPane);
        // Position TimerBarPane on the left side
        setPos(10,10, timerBarPane); // Adjust the X position as needed




        //set buttons for open and close pane
        Button ingredientsPaneButton = new Button();
        setButton(154,318,714,31, ingredientsPaneButton);
        setBGImageforButton("/ingridentButton.png", ingredientsPaneButton);
        OpenCloseButton(ingredientsPaneButton, ingredientsPane);

        Button recipebookButton = new Button();
        setButton(109,77,520,510, recipebookButton);
        setBGImageforButton("/recipeBookPaneButton.png", recipebookButton);
        OpenCloseButton(recipebookButton, recipesBookPane);

        Button OrderButton = new Button();
        setButton(50,72,560,460, OrderButton);
        OpenCloseButton(OrderButton, orderPane);
        setBGImageforButton("/phoneButton.png", OrderButton);
        root.getChildren().addAll(inputField , OrderButton, ingredientsPaneButton, recipebookButton);
        root.getChildren().addAll(orderPane, ingredientsPane, recipesBookPane,inventoryPane);
        root.getChildren().addAll(player.getErrorText(), player.getDisplayEventText());
/*
>>>>>>> Stashed changes
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
        animationTimeline.play();*/
    }

    public static Parent getRoot() {
        return root;
    }

    public static TextField getInputField() {
        return inputField;
    }
<<<<<<< Updated upstream
=======
    public void OpenCloseButton(Button button, Node node){
        button.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                node.setVisible(!node.isVisible());

            }
        });
    }
    public void setButton(double Xsize, double Ysize, double Xpos, double Ypos, Button button){
        button.setPrefWidth(Xsize);
        button.setPrefHeight(Ysize);
        button.setLayoutX(Xpos);
        button.setLayoutY(Ypos);
    }
    public void setBGImageforButton (String imgpath, Button button ){
        Image buttonimg = new Image(Objects.requireNonNull(getClass().getResource(imgpath)).toExternalForm());
        BackgroundImage BGbuttonimg = new BackgroundImage(buttonimg, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        button.setBackground(new Background(BGbuttonimg));
    }
    public void setPos(double Xpos, double Ypos, Node node){
        node.setLayoutX(Xpos);
        node.setLayoutY(Ypos);
    }


>>>>>>> Stashed changes
}
