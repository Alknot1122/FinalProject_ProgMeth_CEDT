package buttons;

import Animation.ButtonAnimation;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;

public class AnimatedOtherButton implements ButtonAnimation {
    public static void applyButtonAnimation(Button button) {
        button.setOnMouseEntered(e -> {
            button.setScaleX(1.1);
            button.setScaleY(1.1);
            button.setEffect(new DropShadow(20, Color.WHITE));
        });

        button.setOnMouseExited(e -> {
            button.setScaleX(1.0);
            button.setScaleY(1.0);
            button.setEffect(null);
        });
    }
}
