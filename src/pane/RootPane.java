package pane;


import gameLogic.Food;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Objects;


public class RootPane extends Pane {
    private static RootPane instance;

    private RootPane() {
        RecipesBookPane recipesBookPane = new RecipesBookPane();
        getChildren().add(recipesBookPane);

    }


    public static RootPane getRootPane() {
        if (instance == null)
            instance = new RootPane();
        return instance;
    }
}
