package gameLogic;

<<<<<<< Updated upstream
public class Player {
=======
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import pane.InventoryPane;
import pane.inputField;

public class Player  {
>>>>>>> Stashed changes
    private int scores ;
    private Text displayScore;

   private final inputField inputField;
    private Text displayEventText;
    private Text ErrorText;

    public ImageView getImageDisplay() {
        return imageDisplay;
    }

    private ImageView imageDisplay;


    public inputField getInputField() {
        return inputField;
    }

    public Player(){
        scores =0;
        displayScore = new Text("Score : " + scores);
        ErrorText = new Text();
        inputField = new inputField();
        displayEventText = new Text();
        displayEventText.setLayoutX(250); displayEventText.setLayoutY(190);
        displayEventText.setFont(Font.loadFont(getClass().getResourceAsStream("/PeaberryBase.ttf"),37));
        ErrorText.setStyle("-fx-fill : rgb(255,241,234);" + "-fx-stroke: rgb(116,35,16);" + "-fx-stroke-width: 4px;"
                + "-fx-text-alignment: center;" + "-fx-stroke-type:outside;");

        ErrorText = new Text("");
        ErrorText.setFont(Font.loadFont(getClass().getResourceAsStream("/PeaberryBase.ttf"),29));
        ErrorText.setLayoutX(78);
        ErrorText.setLayoutY(587);

        ErrorText.setWrappingWidth(808);
        ErrorText.setTextAlignment(TextAlignment.CENTER);
        ErrorText.setStyle("-fx-fill : rgb(255,241,234);" + "-fx-stroke: rgb(116,35,16);" + "-fx-stroke-width: 4px;"
        + "-fx-text-alignment: center;" + "-fx-stroke-type:outside;");

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



    public Text getDisplayEventText() {
        return displayEventText;
    }

    public Text getErrorText() {
        return ErrorText;
    }





}
