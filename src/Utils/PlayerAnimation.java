package Utils;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import java.util.Objects;

public class PlayerAnimation {

    private final ImageView playerImageView;
    private final Image playerImage1;
    private final Image playerImage2;
    private final Image happyImage1;
    private final Image happyImage2;
    private final Image sadImage1;
    private final Image sadImage2;
    private final Timeline idleAnimationTimeline;
    private final Timeline happyAnimationTimeline;
    private final Timeline sadAnimationTimeline;

    public PlayerAnimation() {
        // Load player images
        playerImage1 = new Image(Objects.requireNonNull(getClass().getResource("/Player/idel1.png")).toExternalForm());
        playerImage2 = new Image(Objects.requireNonNull(getClass().getResource("/Player/idel2.png")).toExternalForm());
        happyImage1 = new Image(Objects.requireNonNull(getClass().getResource("/Player/happy1.png")).toExternalForm());
        happyImage2 = new Image(Objects.requireNonNull(getClass().getResource("/Player/happy2.png")).toExternalForm());
        sadImage1 = new Image(Objects.requireNonNull(getClass().getResource("/Player/sad1.png")).toExternalForm());
        sadImage2 = new Image(Objects.requireNonNull(getClass().getResource("/Player/sad2.png")).toExternalForm());

        // Create an ImageView for the player
        playerImageView = new ImageView(playerImage1);
        playerImageView.setFitWidth(131);
        playerImageView.setFitHeight(152);
        playerImageView.setLayoutX(420); // Adjust X position as needed
        playerImageView.setLayoutY(290); // Adjust Y position as needed

        // Create idle animation timeline
        idleAnimationTimeline = new Timeline(
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
        idleAnimationTimeline.setCycleCount(Animation.INDEFINITE);

        happyAnimationTimeline = new Timeline(
                new KeyFrame(Duration.seconds(1.5), event -> {
                    playerImageView.setImage(happyImage1);
                    playerImageView.setRotate(10);
                }),
                new KeyFrame(Duration.seconds(2.5), event -> {
                    playerImageView.setImage(happyImage2);
                    playerImageView.setRotate(0);
                })
        );

        sadAnimationTimeline = new Timeline(
                new KeyFrame(Duration.seconds(1.5), event -> {
                    playerImageView.setImage(sadImage1);
                    playerImageView.setRotate(10);
                }),
                new KeyFrame(Duration.seconds(2.5), event -> {
                    playerImageView.setImage(sadImage2);
                    playerImageView.setRotate(0);
                })
        );
    }

    public ImageView getPlayerImageView() {
        return playerImageView;
    }

    public void playIdleAnimation() {
        idleAnimationTimeline.play();
        happyAnimationTimeline.stop();
    }

    public void playHappyAnimation() {
        idleAnimationTimeline.stop();
        happyAnimationTimeline.play();
    }

    public void playSadAnimation() {
        idleAnimationTimeline.stop();
        sadAnimationTimeline.play();
    }
}
