package Utils;

import javafx.scene.image.ImageView;

public abstract class PlayerAnimation {

    protected final ImageView playerImageView;

    public PlayerAnimation(ImageView playerImageView) {
        this.playerImageView = playerImageView;
    }

    public abstract void playAnimation();
}
