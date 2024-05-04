package gameLogic;
import javafx.animation.TranslateTransition;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;
import pane.InventoryPane;
import pane.gameOverPane;
import pane.getDisplay;
import pane.inputField;




public class Player  {

    private int scores ;
    private final Text displayScore;

    private final inputField inputField;
    private final Text displayEventText;
    private Text ErrorText;
    private gameOverPane gameOverPane;
    private ImageView imageDisplay;


    public Player(){
        scores =0;

        displayScore = new Text("Score : " + scores);
        gameOverPane = new gameOverPane();
        ErrorText = getDisplay.getText("", 29, 808,78,587);
        inputField = new inputField();
        inputField.setVisible(false);
        inputField.setAlignment(Pos.CENTER);
        displayEventText = getDisplay.getText("", 37,500, 250,470);
        imageDisplay = getDisplay.getImage("Item/Lemon.png", 75, true);


        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(1), displayEventText);
        translateTransition.setByY(-30); // Adjust this value to change the amplitude of the animation
        translateTransition.setCycleCount(TranslateTransition.INDEFINITE);
        translateTransition.setAutoReverse(true);

        // Play the animation
        translateTransition.play();
        imageDisplay.setVisible(false);
        imageDisplay.setLayoutY(280); imageDisplay.setLayoutX(470);

        displayEventText.setLayoutX(250); displayEventText.setLayoutY(190);
        displayEventText.setFont(Font.loadFont(getClass().getResourceAsStream("/PeaberryBase.ttf"),37));
        displayEventText.setStyle("-fx-fill : rgb(255,241,234);" + "-fx-stroke: rgb(116,35,16);" + "-fx-stroke-width: 4px;"
                + "-fx-text-alignment: center;" + "-fx-stroke-type:outside;");

       
        ErrorText.setTextAlignment(TextAlignment.CENTER);
        ErrorText.setStyle("-fx-fill : rgb(255,241,234);" + "-fx-stroke: rgb(116,35,16);" + "-fx-stroke-width: 4px;"
        + "-fx-text-alignment: center;" + "-fx-stroke-type:outside;");

        displayScore.setFont(Font.loadFont(getClass().getResourceAsStream("/PeaberryBase.ttf"),31));
        displayScore.setLayoutX(10); displayScore.setLayoutY(62);

    }

    public Text getDisplayScore (){
        return displayScore;
    }

    public int getScores() {
        return scores;
    }

    public void setScores(int scores) {
        this.scores = scores;
        displayScore.setText("Score : " + scores);
    }

    public ImageView getImageDisplay() {
        return imageDisplay;
    }

    public inputField getInputField() {
        return inputField;
    }

    public Text getDisplayEventText() {
        return displayEventText;
    }

    public Text getErrorText() {
        return ErrorText;
    }

    public gameOverPane getGameOverPane() {
        return gameOverPane;
    }
}
