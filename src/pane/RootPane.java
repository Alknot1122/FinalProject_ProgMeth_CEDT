package pane;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import utils.Goto;

import java.util.Objects;


public class RootPane extends VBox {
    private static RootPane instance;

    private RootPane() {
        Image bgImage = new Image(Objects.requireNonNull(getClass().getResource("/kitchen.png")).toExternalForm());
        ImageView imageView = new ImageView(bgImage);
        this.getChildren().add(imageView);
    }

    public static RootPane getRootPane() {
        if (instance == null)
            instance = new RootPane();
        return instance;
    }
}
