package Utils;

import javafx.animation.*;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.effect.Glow;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

public abstract class BaseButtonAnimation {
    protected static void applyButtonAnimation(Button button, Glow glow, Timeline idleAnimation, Timeline pulseAnimation, EventHandler<MouseEvent> onMousePressed) {
        ScaleTransition scaleTransitionEnter = new ScaleTransition(Duration.millis(200), button);
        scaleTransitionEnter.setToX(1.2);
        scaleTransitionEnter.setToY(1.2);

        ScaleTransition scaleTransitionExit = new ScaleTransition(Duration.millis(200), button);
        scaleTransitionExit.setToX(1.0);
        scaleTransitionExit.setToY(1.0);

        double initialAngle = 5;

        RotateTransition rotateTransition = new RotateTransition(Duration.millis(1000), button);
        rotateTransition.setFromAngle(-initialAngle);
        rotateTransition.setToAngle(initialAngle);
        rotateTransition.setCycleCount(Timeline.INDEFINITE);
        rotateTransition.setAutoReverse(true);

        button.setOnMouseEntered(event -> {
            // Start scale up animation
            scaleTransitionEnter.play();
            // Increase glow effect
            glow.setLevel(0.5);
            rotateTransition.play();
            pulseAnimation.stop();
            idleAnimation.stop();
        });

        button.setOnMouseExited(event -> {
            // Start scale down animation
            scaleTransitionExit.play();
            // Reset glow effect
            glow.setLevel(0.0);
            rotateTransition.stop();
            pulseAnimation.play();
            idleAnimation.play();
        });

        button.setOnMousePressed(onMousePressed);
    }
}
