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
        this.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
        this.setAlignment(Pos.TOP_CENTER);
        this.setSpacing(16);
        this.setPadding(new Insets(32, 0, 32, 0));
        Text text = new Text("Cooking Game");
        text.setFill(Color.DARKCYAN);
        text.setFont(Font.font("Verdana", FontWeight.BOLD, 32));
        this.getChildren().add(text);

        Image bgImage = new Image(Objects.requireNonNull(getClass().getResource("/resource/kitchen.png")).toExternalForm());
        ImageView imageView = new ImageView(bgImage);
        imageView.setFitWidth(1080);
        imageView.setPreserveRatio(true);
        this.getChildren().add(imageView);
    }

    public static RootPane getRootPane() {
        if (instance == null)
            instance = new RootPane();
        return instance;
    }
}
