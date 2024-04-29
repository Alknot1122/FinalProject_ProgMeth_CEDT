// ZoomTransitionUtil.java

package Utils;

import javafx.animation.ScaleTransition;
import javafx.scene.Node;
import javafx.util.Duration;

public class ZoomTransitionUtil {

    public static void applyZoomTransition(Node node) {
        // Create a ScaleTransition
        ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(1), node);
        // Set the from and to scale values
        scaleTransition.setFromX(1);
        scaleTransition.setToX(1.5);
        scaleTransition.setFromY(1);
        scaleTransition.setToY(1.5);
        // Play the transition
        scaleTransition.play();
    }

    public static void applyZoomOutTransition(Node node) {
        // Create a ScaleTransition
        ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(1), node);
        // Set the from and to scale values
        scaleTransition.setFromX(1.5);
        scaleTransition.setToX(1);
        scaleTransition.setFromY(1.5);
        scaleTransition.setToY(1);
        // Play the transition
        scaleTransition.play();
    }
}
