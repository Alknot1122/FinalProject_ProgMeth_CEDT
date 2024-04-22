package application;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Objects;

public class Main extends Application {

    private static final Font FONT = Font.font("", FontWeight.BOLD, 42);
    private VBox menuBox;
    private int currentItem = 0;
    private GamePage gamePage;
    private Scene mainScene; // Declare mainScene at the class level

    private Parent createContent() {
        Pane root = new Pane();
        root.setPrefSize(1440, 1080);

        // Load and set the background image
        Image bgImage = new Image(Objects.requireNonNull(getClass().getResource("/kitchen.png")).toExternalForm());
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
                    mainScene.setRoot(gamePage.getRoot()); // Switch to game page after fade out
                    gamePage.getInputField().requestFocus();
                    FadeTransition fadeIn = new FadeTransition(Duration.seconds(1), gamePage.getRoot());
                    fadeIn.setFromValue(0.0);
                    fadeIn.setToValue(1.0);
                    fadeIn.play();
                });
                fadeOut.play();
            } else {
                gamePage.getInputField().requestFocus();
            }
        });
        menuBox = new VBox(10,
                itemStart,
                new MenuItem("GUIDE"),
                itemExit);
        menuBox.setAlignment(Pos.TOP_CENTER);
        menuBox.setTranslateX(220);
        menuBox.setTranslateY(185);

        getMenuItem(0).setActive(true);

        root.getChildren().addAll(menuBox);
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
            super(15);
            setAlignment(Pos.CENTER);

            text = new Text(name);
            text.setFont(FONT);
            text.setEffect(new GaussianBlur(2));

            getChildren().addAll(star1 , text , star2 );
            setActive(false);
            setOnActivate(() -> System.out.println(name + " activated"));
        }

        private static class Star extends Parent {
            public Star(){
                ImageView imageView = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/star1.png")).toExternalForm()));

                imageView.setFitWidth(25);
                imageView.setFitHeight(25);
                imageView.setEffect(new GaussianBlur(2));

                getChildren().add(imageView);

            }
        }

        public void setActive(boolean b){
            star1.setVisible(b);
            star2.setVisible(b);
            text.setFill(b ? Color.WHITE : Color.GRAY);
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

        mainScene = new Scene(createContent(), 1440, 1080); // Rename the variable to avoid shadowing
        mainScene.setOnKeyPressed(event -> {
            if(event.getCode() == KeyCode.UP){
                if(currentItem > 0){
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
