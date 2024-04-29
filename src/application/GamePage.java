package application;

import gameLogic.*;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;
import pane.*;

import javax.naming.Name;
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
       // timerBarPane.startCountDownTimer(t);
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

        //make game start to random add the order
        GameController.orderenter();



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
        setButton(32,45,560,460, OrderButton);
        OpenCloseButton(OrderButton, orderPane);
        setBGImageforButton("/phoneButton.png", OrderButton);

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


}
