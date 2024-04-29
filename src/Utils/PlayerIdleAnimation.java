package Utils;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import java.util.Objects;

public class PlayerIdleAnimation {

    private final ImageView playerImageView;
    private final Image playerImage1;
    private final Image playerImage2;
    private final Timeline animationTimeline;

    public PlayerIdleAnimation() {
        // Load player images
        playerImage1 = new Image(Objects.requireNonNull(getClass().getResource("/Player/idel1.png")).toExternalForm());
        playerImage2 = new Image(Objects.requireNonNull(getClass().getResource("/Player/idel2.png")).toExternalForm());

        // Create an ImageView for the player
        playerImageView = new ImageView(playerImage1);
        playerImageView.setFitWidth(131);
        playerImageView.setFitHeight(152);
        playerImageView.setLayoutX(420); // Adjust X position as needed
        playerImageView.setLayoutY(290); // Adjust Y position as needed

        // Create animation timeline
        animationTimeline = new Timeline(
                new KeyFrame(Duration.seconds(1), event -> {
                    playerImageView.setImage(playerImage2);
                    playerImageView.setRotate(0);
                }),
                new KeyFrame(Duration.seconds(2), event -> {
                    playerImageView.setImage(playerImage2);
                    playerImageView.setRotate(10);
                }),
                new KeyFrame(Duration.seconds(2), event -> {
                    playerImageView.setImage(playerImage1);
                    playerImageView.setRotate(-6);
                })
        );
        animationTimeline.setCycleCount(Animation.INDEFINITE);
        animationTimeline.play();
    }

    public ImageView getPlayerImageView() {
        return playerImageView;
    }
}
