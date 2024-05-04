package buttons;

import Animation.ButtonAnimation;
import javafx.animation.*;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.effect.Glow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import Utils.SoundController;

public class IngredientButton implements ButtonAnimation {


    public static void applyButtonAnimation(Button button, Pane ingredientPane) {
        Glow glow = new Glow(0.0);
        button.setEffect(glow);

        ScaleTransition scaleTransitionEnter = new ScaleTransition(Duration.millis(200), button);
        scaleTransitionEnter.setToX(1.1);
        scaleTransitionEnter.setToY(1.1);

        ScaleTransition scaleTransitionExit = new ScaleTransition(Duration.millis(200), button);
        scaleTransitionExit.setToX(1.0);
        scaleTransitionExit.setToY(1.0);

        double initialAngle = 5;

        RotateTransition rotateTransition = new RotateTransition(Duration.millis(600), button);
        rotateTransition.setFromAngle(-initialAngle);
        rotateTransition.setToAngle(initialAngle);
        rotateTransition.setCycleCount(Timeline.INDEFINITE);
        rotateTransition.setAutoReverse(true);

        Timeline IdleAnimation = new Timeline(
                new KeyFrame(Duration.seconds(1), event -> button.setRotate(0.5)),
                new KeyFrame(Duration.seconds(2), event -> button.setRotate(-0.5))
        );
        IdleAnimation.setCycleCount(Animation.INDEFINITE);
        IdleAnimation.play();

        Timeline pulseAnimation = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(glow.levelProperty(), 0.0)),
                new KeyFrame(Duration.seconds(1.5), new KeyValue(glow.levelProperty(), 0.6)),
                new KeyFrame(Duration.seconds(2), new KeyValue(glow.levelProperty(), 0.0))
        );
        pulseAnimation.setCycleCount(Timeline.INDEFINITE);

        pulseAnimation.play();

        button.setOnMouseEntered(event -> {
            // Start scale up animation
            scaleTransitionEnter.play();
            // Increase glow effect
            glow.setLevel(0.6);
            rotateTransition.play();
            pulseAnimation.stop();
            IdleAnimation.stop();
        });

        button.setOnMouseExited(event -> {
            // Start scale down animation
            scaleTransitionExit.play();
            // Reset glow effect
            glow.setLevel(0.0);
            rotateTransition.stop();
            button.setRotate(0);
            pulseAnimation.play();
            IdleAnimation.play();
        });

        button.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                SoundController OpenIngridentPane = new SoundController("res/Sound/OpenIngridentPane.mp3");
                OpenIngridentPane.getMediaPlayer().setVolume(0.75);
                OpenIngridentPane.playMusic();
                ingredientPane.setVisible(!ingredientPane.isVisible());
            }
        });
    }
}
