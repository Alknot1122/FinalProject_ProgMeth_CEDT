package application;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import Utils.SoundController;
import pane.GamePage;

import java.util.Objects;

public class Main extends Application {

    private static final Font FONT = Font.font("", FontWeight.BOLD, 42);
    private VBox menuBox;
    private int currentItem = 0;
    private GamePage gamePage;
    private Scene mainScene; // Declare mainScene at the class level
    private  SoundController backgroundMusic;

    private Parent createContent() {
        Pane root = new Pane();
        root.setPrefSize(963, 722);

        // Load and set the background image
        Image bgImage = new Image(Objects.requireNonNull(getClass().getResource("/Background/MainMenuBg.png")).toExternalForm());
        BackgroundImage backgroundImage = new BackgroundImage(bgImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        root.setBackground(new Background(backgroundImage));

        MenuItem itemExit = new MenuItem("EXIT");
        itemExit.setOnActivate(() -> System.exit(0));

        MenuItem itemStart = new MenuItem("START");
        itemStart.setOnActivate(() -> {

            if (mainScene.getRoot() != gamePage.getRoot()) {

                FadeTransition fadeOut = new FadeTransition(Duration.seconds(1), mainScene.getRoot());
                fadeOut.setFromValue(1.0);
                fadeOut.setToValue(0.0);
                fadeOut.setOnFinished(e -> {
                    // Switch to game page after fade out
                    mainScene.setRoot(gamePage.getRoot());
                    FadeTransition fadeIn = new FadeTransition(Duration.seconds(1), gamePage.getRoot());
                    fadeIn.setFromValue(0.0);
                    fadeIn.setToValue(1.0);
                    fadeIn.play();
                });
                fadeOut.play();
                }
        });
        MenuItem itemGuide = new MenuItem("GUIDE");


        menuBox = new VBox(10,
                itemStart,
                itemGuide,
                itemExit);
        menuBox.setAlignment(Pos.TOP_CENTER);
        menuBox.setTranslateX(100);
        menuBox.setTranslateY(350);

        getMenuItem(0).setActive(true);

        root.getChildren().addAll(menuBox  );
        return root;
    }


    private MenuItem getMenuItem(int index){
        return (MenuItem)menuBox.getChildren().get(index);
    }

    private static class MenuItem extends HBox {
        private Star star1 = new Star();
        private Star star2 = new Star();

        private Text text;
        private Runnable script;

        public MenuItem(String name){
            super(12);
            setAlignment(Pos.CENTER);

            text = new Text(name);
            text.setFont(FONT);
            text.setEffect(new GaussianBlur(1));

            getChildren().addAll(star1 , text , star2 );
            setActive(false);
            setOnActivate(() -> System.out.println(name + " activated"));
        }

        private static class Star extends Parent {
            public Star(){
                ImageView imageView = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/star1.png")).toExternalForm()));

                imageView.setFitWidth(20);
                imageView.setFitHeight(20);
                imageView.setEffect(new GaussianBlur(1));

                getChildren().add(imageView);

            }
        }

        public void setActive(boolean b){
            star1.setVisible(b);
            star2.setVisible(b);
            text.setFill(b ? Color.rgb(255,184,59) : Color.rgb(242,203,127));

            // Apply animation when setting active state
            if (b) {
                ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), text);
                scaleTransition.setToX(1.1);
                scaleTransition.setToY(1.1);
                scaleTransition.play();
            } else {
                ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), text);
                scaleTransition.setToX(1.0);
                scaleTransition.setToY(1.0);
                scaleTransition.play();
            }
        }

        public void setOnActivate(Runnable r){
            script = r;
        }

        public void activate(){
            if(script != null)
                script.run();
        }
    }


    public void start(Stage stage) {
        gamePage = new GamePage();
        //play background music
        backgroundMusic = new SoundController("Sound/Backgroundmusic.mp3");
        backgroundMusic.getMediaPlayer().setVolume(0.55);
        backgroundMusic.getMediaPlayer().setCycleCount(MediaPlayer.INDEFINITE);
        backgroundMusic.getMediaPlayer().play();


        SoundController clockingButtonnoise = new SoundController("Sound/buttonClick.mp3");
        clockingButtonnoise.getMediaPlayer().setVolume(0.65);
        mainScene = new Scene(createContent(), 963,722); // Rename the variable to avoid shadowing
        mainScene.setOnKeyPressed(event -> {


            clockingButtonnoise.playMusic();

            if(event.getCode() == KeyCode.UP){

                    if (currentItem > 0) {
                        getMenuItem(currentItem).setActive(false);
                        getMenuItem(--currentItem).setActive(true);

                }
            }
            if(event.getCode() == KeyCode.DOWN){

                    if(currentItem < menuBox.getChildren().size() - 1){
                        getMenuItem(currentItem).setActive(false);
                        getMenuItem(++currentItem).setActive(true);
                    }


            }

            if(event.getCode() == KeyCode.ENTER){

                    getMenuItem(currentItem).activate();


            }
        });

        stage.setScene(mainScene);
        stage.setTitle("Cooking Game");
        stage.setResizable(false);
        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }
}
