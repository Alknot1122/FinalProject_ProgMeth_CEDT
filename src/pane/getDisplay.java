package pane;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.Objects;

public class getDisplay {
    public static Button getButton(String imagePath, int width, int hight, int posX, int posY){
        Button button = new Button();
        Image bgImage = new Image(Objects.requireNonNull(getDisplay.class.getResource(imagePath)).toExternalForm());
        BackgroundImage backgroundImage = new BackgroundImage(bgImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        button.setBackground(new Background(backgroundImage));
        button.setPrefHeight(hight); button.setPrefWidth(width);
        button.setLayoutX(posX); button.setLayoutY(posY);
        return button;
    }
    public static ImageView getImage (String imagePath, int fitHigh, boolean preserveRatio){
        String   classLoaderPath = ClassLoader.getSystemResource(imagePath).toString();
        Image image = new Image(classLoaderPath);
        ImageView imageview = new ImageView(image);
        imageview.setPreserveRatio(preserveRatio);
        imageview.setFitHeight(fitHigh);
        return imageview;
    }
    public static Text getText (String string, int Fontsize, int wrappingWidth, int posX, int posY){
        Text text = new Text(string);
        text.setFont(Font.loadFont(getDisplay.class.getResourceAsStream("/PeaberryBase.ttf"),Fontsize));
        text.setWrappingWidth(wrappingWidth);
        text.setLayoutX(posX); text.setLayoutY(posY);
        return text;
    }

}
