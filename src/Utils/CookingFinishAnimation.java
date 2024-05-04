package Utils;

import application.GamePage;
import javafx.animation.*;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class CookingFinishAnimation  extends PlayerAnimation  {

    private final ImageView imageView;
    private final double initialX;
    private final double initialY;

    public CookingFinishAnimation(ImageView playerImageView) {
        super(playerImageView);
        this.imageView = playerImageView;
        this.initialX = 500;
        this.initialY = 300;
    }

    public void playAnimation() {
        // Store the initial position
        double initialX = imageView.getTranslateX();
        double initialY = imageView.getTranslateY();

        FadeTransition fadeInTransition = new FadeTransition(Duration.seconds(1.5), imageView);
        fadeInTransition.setFromValue(0);
        fadeInTransition.setToValue(1);

        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(1.5), imageView);
        translateTransition.setByY(-50); // Adjust this value for the distance to float up

        FadeTransition fadeOutTransition = new FadeTransition(Duration.seconds(1.5), imageView);
        fadeOutTransition.setFromValue(1);
        fadeOutTransition.setToValue(0);



        SequentialTransition sequentialTransition = new SequentialTransition(
                fadeInTransition,
                translateTransition,
                new PauseTransition(Duration.seconds(0.5)), // Pause before fade-out
                fadeOutTransition
        );

        // Reset the layout position after the animation completes
        sequentialTransition.setOnFinished(event -> {
            imageView.setVisible(false);
            imageView.setTranslateX(initialX);
            imageView.setTranslateY(initialY);
            ZoomTransitionUtil.applyZoomOutTransition(GamePage.getRoot());
        });

        sequentialTransition.play();
    }



}
