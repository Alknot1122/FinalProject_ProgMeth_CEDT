package pane;

import gameLogic.Food;
import gameLogic.GameController;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.image.Image;
import javafx.scene.control.Button;
import javafx.scene.text.TextAlignment;

import java.util.Objects;

public class OrderPane extends Pane {



  private Thread timerTread ;
  private VBox orderBox;
    public OrderPane (){
        setVisible(false);
        Image bgImage = new Image(Objects.requireNonNull(getClass().getResource("/Background/OrderPane.png")).toExternalForm());
        BackgroundImage backgroundImage = new BackgroundImage(bgImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        setBackground(new Background(backgroundImage));

        setLayoutX(684);
        setLayoutY(172);
        setPrefHeight(432);
        setPrefWidth(211); setScaleY(1.15); setScaleX(1.15);

        Button exitButton = getDisplay.getButton("/Button/exitButton.png", 38,37,178,2);


        exitButton.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                SoundController clockingButtonnoise = new SoundController("res/Sound/buttonclick.mp3");
                clockingButtonnoise.getMediaPlayer().setVolume(0.5);
                clockingButtonnoise.playMusic();
                setVisible(!isVisible());

            }
        });
        Text order = getDisplay.getText("ORDER", 32, 106, 51, 67);
        order.setFill(Color.WHITE);
        order.setTextAlignment(TextAlignment.CENTER);

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setStyle("-fx-background: transparent;\n -fx-background-color: transparent");
        scrollPane.setLayoutX(6);scrollPane.setLayoutY(66);

        scrollPane.setPrefSize(200,331);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        orderBox = new VBox();
        orderBox.setPadding(new Insets(5));
        orderBox.setSpacing(5);
        orderBox.setStyle("-fx-background-color:transparent;");
        scrollPane.setContent(orderBox);
        getChildren().addAll(exitButton, order,scrollPane);
        addnoOrderPic();

    }
    public void countdown(int timeInSec, Text textDisplay, Pane pane){

        final int[] Minute = {timeInSec / 60};
        final int[] Sec = {timeInSec % 60};

         timerTread = new Thread(() -> {
            while (true){
                try {
                    Thread.sleep(1000);
                    if (Sec[0] >=0 && Minute[0] >=0){

                        String tim = String.format("%02d:%02d", Minute[0], Sec[0]);
                        setText(tim, textDisplay);
                        if (Sec[0] == 0){
                            Minute[0]--;
                            Sec[0] = 59;
                        }
                        else {
                            Sec[0]--;
                        }
                    }
                    else {
                       orderGone(pane);
                       // System.out.println("guhgugh");
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
    public void setText (String text , Text layout){
        layout.setText(text);
    }
    public void orderGone (Pane pane){
        Platform.runLater(()->{
                orderBox.getChildren().remove(pane);
                });


    }
    public void OrderIn(Food food, int timerInSec){
        Platform.runLater(()-> {
           if (orderBox.getChildren().getFirst() instanceof  ImageView){

               orderBox.getChildren().removeFirst();
           }


            OneOrderPane oneOrderPane = new OneOrderPane(food );

            orderBox.getChildren().add(oneOrderPane);
            oneOrderPane.getSendingbutton().setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (GameController.Ordersending(oneOrderPane.getFood())){

                        orderGone(oneOrderPane);
                        if (orderBox.getChildren().isEmpty()){
                            addnoOrderPic();
                        }
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
        Pane borderPane = new Pane();
        noorder.setScaleX(0.75);
        noorder.setScaleY(0.75);

        borderPane.setPrefWidth(200);

        orderBox.getChildren().add(borderPane);
    }
}
