package gameLogic;
import javafx.animation.TranslateTransition;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;
import pane.gameOverPane;
import Utils.getDisplay;
import pane.inputField;




public class Player  {

    private int scores ;
    private final Text DISPLAY_SCORES;

    private final inputField INPUT_FIELD;
    private final Text DISPLAY_EVENT_TEXT;
    private final Text ERROR_TEXT;
    private final gameOverPane GAMEOVER_PANE;
    private final ImageView IMAGE_DISPLAY;


    public Player(){
        scores =0;

        DISPLAY_SCORES = new Text("Score : " + scores);
        GAMEOVER_PANE = new gameOverPane();
        ERROR_TEXT = getDisplay.getText("", 29, 808,78,587);
        INPUT_FIELD = new inputField();
        DISPLAY_EVENT_TEXT = getDisplay.getText("", 37,500, 250,470);
        IMAGE_DISPLAY = getDisplay.getImage("Item/Lemon.png", 75, true);

        INPUT_FIELD.setVisible(false);
        INPUT_FIELD.setAlignment(Pos.CENTER);

        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(1), DISPLAY_EVENT_TEXT);
        translateTransition.setByY(-30); // Adjust this value to change the amplitude of the animation
        translateTransition.setCycleCount(TranslateTransition.INDEFINITE);
        translateTransition.setAutoReverse(true);

        // Play the animation
        translateTransition.play();
        IMAGE_DISPLAY.setVisible(false);
        IMAGE_DISPLAY.setLayoutY(280); IMAGE_DISPLAY.setLayoutX(470);

        //set displayEventText properties
        DISPLAY_EVENT_TEXT.setLayoutX(250); DISPLAY_EVENT_TEXT.setLayoutY(190);
        DISPLAY_EVENT_TEXT.setFont(Font.loadFont(getClass().getResourceAsStream("/PeaberryBase.ttf"),37));
        DISPLAY_EVENT_TEXT.setStyle("-fx-fill : rgb(255,241,234);" + "-fx-stroke: rgb(116,35,16);" + "-fx-stroke-width: 4px;"
                + "-fx-text-alignment: center;" + "-fx-stroke-type:outside;");

        //set ErrorText properties
        ERROR_TEXT.setTextAlignment(TextAlignment.CENTER);
        ERROR_TEXT.setStyle("-fx-fill : rgb(255,241,234);" + "-fx-stroke: rgb(116,35,16);" + "-fx-stroke-width: 4px;"
        + "-fx-text-alignment: center;" + "-fx-stroke-type:outside;");

        //set displayScore properties
        DISPLAY_SCORES.setFont(Font.loadFont(getClass().getResourceAsStream("/PeaberryBase.ttf"),31));
        DISPLAY_SCORES.setLayoutX(10); DISPLAY_SCORES.setLayoutY(62);

    }

    public Text getDisplayScore (){
        return DISPLAY_SCORES;
    }

    public int getScores() {
        return scores;
    }

    public void setScores(int scores) {
        this.scores = scores;
        DISPLAY_SCORES.setText("Score : " + scores);
    }

    public ImageView getImageDisplay() {
        return IMAGE_DISPLAY;
    }

    public inputField getInputField() {
        return INPUT_FIELD;
    }

    public Text getDisplayEventText() {
        return DISPLAY_EVENT_TEXT;
    }

    public Text getErrorText() {
        return ERROR_TEXT;
    }

    public gameOverPane getGameOverPane() {
        return GAMEOVER_PANE;
    }
}
