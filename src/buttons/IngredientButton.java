package buttons;

import Utils.BaseButtonAnimation;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Button;
import javafx.scene.effect.Glow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import Utils.SoundController;

public class IngredientButton extends BaseButtonAnimation {

    public static void applyButtonAnimation(Button button, Pane ingredientPane) {
        Glow glow = new Glow(0.0);
        button.setEffect(glow);

        Timeline idleAnimation = new Timeline(
                new KeyFrame(Duration.seconds(1), event -> button.setRotate(0.5)),
                new KeyFrame(Duration.seconds(2), event -> button.setRotate(-0.5))
        );
        idleAnimation.setCycleCount(Animation.INDEFINITE);
        idleAnimation.play();

        Timeline pulseAnimation = new Timeline(
                new KeyFrame(Duration.ZERO, new javafx.animation.KeyValue(glow.levelProperty(), 0.0)),
                new KeyFrame(Duration.seconds(1.5), new javafx.animation.KeyValue(glow.levelProperty(), 0.6)),
                new KeyFrame(Duration.seconds(2), new javafx.animation.KeyValue(glow.levelProperty(), 0.0))
        );
        pulseAnimation.setCycleCount(Timeline.INDEFINITE);

        pulseAnimation.play();

        applyButtonAnimation(button, glow, idleAnimation, pulseAnimation, mouseEvent -> {
            SoundController OpenIngredientPane = new SoundController("res/Sound/OpenIngredientPane.mp3");
            OpenIngredientPane.getMediaPlayer().setVolume(0.75);
            OpenIngredientPane.playMusic();
            ingredientPane.setVisible(!ingredientPane.isVisible());
        });
    }
}
