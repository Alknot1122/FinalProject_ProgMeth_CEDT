package pane;

import Utils.SoundController;
import Utils.getDisplay;
import gameLogic.Food;
import gameLogic.GameController;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.image.Image;
import javafx.scene.control.Button;
import javafx.scene.text.TextAlignment;

import java.util.Objects;

public class OrderPane extends Pane {
    private final VBox ORDER_VBOX;
    public OrderPane (){
        setVisible(false);
        setLayoutX(684);
        setLayoutY(172);
        setPrefHeight(432);
        setPrefWidth(211);
        setScaleY(1.15);
        setScaleX(1.15);

        Image bgImage = new Image(Objects.requireNonNull(getClass().getResource("/Background/OrderPane.png")).toExternalForm());
        BackgroundImage backgroundImage = new BackgroundImage(bgImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        setBackground(new Background(backgroundImage));

        Button exitButton = getDisplay.getButton("/Button/exitButton.png", 38,37,178,2);
        exitButton.setOnMousePressed(mouseEvent -> {
            SoundController clockingButtonnoise = new SoundController("res/Sound/buttonclick.mp3");
            clockingButtonnoise.getMediaPlayer().setVolume(0.7);
            clockingButtonnoise.playMusic();
            setVisible(!isVisible());
        });

        Text order = getDisplay.getText("ORDER", 32, 106, 51, 67);
        order.setFill(Color.WHITE);
        order.setTextAlignment(TextAlignment.CENTER);

        ScrollPane scrollPane = new ScrollPane();

        scrollPane.setStyle("-fx-background: transparent;\n -fx-background-color: transparent");
        scrollPane.setLayoutX(6);
        scrollPane.setLayoutY(66);
        scrollPane.setPrefSize(200,331);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        ORDER_VBOX = new VBox();

        ORDER_VBOX.setAlignment(Pos.CENTER);
        ORDER_VBOX.setPadding(new Insets(5));
        ORDER_VBOX.setSpacing(5);
        ORDER_VBOX.setStyle("-fx-background-color:transparent;");
        scrollPane.setContent(ORDER_VBOX);
        getChildren().addAll(exitButton, order,scrollPane);
        addnoOrderPic();

    }
    public void countdown(int timeInSec, Text textDisplay, Pane pane){

        final int[] Minute = {timeInSec / 60};
        final int[] Sec = {timeInSec % 60};

        Thread timerTread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000);
                    if (Sec[0] >= 0 && Minute[0] >= 0) {
                        //make text appear as 01:12 format of time
                        String tim = String.format("%02d:%02d", Minute[0], Sec[0]);
                        setText(tim, textDisplay);

                        if (Sec[0] == 0) {
                            Minute[0]--;
                            Sec[0] = 59;
                        } else {
                            Sec[0]--;
                        }

                    } else {
                        //when timer reach 0, order gone
                        orderGone(pane);
                        break;
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                    System.out.println("stop timing!!");
                    break;
                }

            }
        });
        timerTread.start();


    }
    public void setText (String text , Text textDisplay){
        textDisplay.setText(text);
    }
    public void orderGone (Pane pane){
        Platform.runLater(()->{
            ORDER_VBOX.getChildren().remove(pane);
                });
    }
    public void OrderIn(Food food, int timerInSec){
        Platform.runLater(()-> {
            //check if ORDER_VBOX have noOrder image, if do then remove
           if (ORDER_VBOX.getChildren().getFirst() instanceof  ImageView){
               ORDER_VBOX.getChildren().removeFirst();
           }

            OneOrderPane oneOrderPane = new OneOrderPane(food );
            ORDER_VBOX.getChildren().add(oneOrderPane);

            oneOrderPane.getSendingbutton().setOnMousePressed(mouseEvent -> {
                if (GameController.hasOrder(oneOrderPane.getFood())){

                    orderGone(oneOrderPane);
                    if (ORDER_VBOX.getChildren().isEmpty()){
                        addnoOrderPic();
                    }
                }
            });
            countdown(timerInSec, oneOrderPane.getTimer(), oneOrderPane);
        });
    }


    public void addnoOrderPic(){
        String   classLoaderPath = ClassLoader.getSystemResource("noOrder.png").toString();
        ImageView noorder = new ImageView(new Image(classLoaderPath));
        noorder.setPreserveRatio(true);

        ORDER_VBOX.getChildren().add(noorder);
    }
}
