package gameLogic;
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
import pane.InventoryPane;
import pane.gameOverPane;
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
        ErrorText = new Text();
        inputField = new inputField();
        displayEventText = new Text();
        ErrorText = new Text("");

        String   classLoaderPath = ClassLoader.getSystemResource("Food/AnimalBiscuit.png").toString();
        Image itemImage = new Image(classLoaderPath);
        imageDisplay = new ImageView(itemImage);
        imageDisplay.setVisible(false);
        imageDisplay.setFitHeight(75);
        imageDisplay.setPreserveRatio(true);
        imageDisplay.setLayoutY(280); imageDisplay.setLayoutX(470);

        displayEventText.setLayoutX(250); displayEventText.setLayoutY(190);
        displayEventText.setFont(Font.loadFont(getClass().getResourceAsStream("/PeaberryBase.ttf"),37));


        ErrorText.setFont(Font.loadFont(getClass().getResourceAsStream("/PeaberryBase.ttf"),29));
        ErrorText.setLayoutX(78);
        ErrorText.setLayoutY(587);
        ErrorText.setWrappingWidth(808);
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
