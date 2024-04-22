package pane;

import gameLogic.Food;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.image.Image;
import javafx.scene.control.Button;
import javafx.scene.text.TextAlignment;

import java.awt.*;

public class OrderPane extends VBox {
  private Text orderName ;
  private Image foodImage ;
  private Text Timer ;
  private Thread timerTread ;
    public OrderPane (){
        setAlignment(Pos.TOP_RIGHT);
        setPadding(new Insets(4));
        setVisible(true);
        setBackground(Background.fill(Color.CORAL));
        setPrefHeight(562);
        setPrefWidth(271);
        Button exitButton = new Button("X");


        exitButton.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                setVisible(!isVisible());
            }
        });
        Text order = new Text("ORDER");
        order.setWrappingWidth(271);

        order.setFont(new Font("Arial Rounded MT Bold", 37));
        order.setTextAlignment(TextAlignment.CENTER);
        getChildren().addAll(exitButton, order);


    }
    public void countdown(int timeInSec, Text textDisplay){

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
                       orderGone();

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
    public void orderGone (){
        Platform.runLater(()->{
                    for (int i = getChildren().size() -1 ; i >= 2; i--){
                        getChildren().remove(i);
                    }
                    Text emptyOrder = new Text( "No order rn :<");
                    emptyOrder.setWrappingWidth(271);
                    emptyOrder.setFont(new Font("Arial Rounded MT Bold", 25));
                    emptyOrder.setOpacity(0.25);
                    emptyOrder.setTextAlignment(TextAlignment.CENTER);
                    getChildren().add(emptyOrder);
                }

                );


    }
    public void OrderIn(Food food, int timerInSec){
        Text timer = new Text();
        timer.setFont(new Font("Arial Rounded MT Bold", 27));
        timer.setWrappingWidth(271);
        timer.setTextAlignment(TextAlignment.CENTER);

        orderName = new Text(food.getItemName());
        orderName.setFont(new Font("Arial Rounded MT Bold", 30));
        orderName.setTextAlignment(TextAlignment.CENTER);
        orderName.setWrappingWidth(271);


        ImageView foodimagShow = food.getItemImageView(200);
        foodimagShow.setFitWidth(271);
        foodimagShow.setPreserveRatio(true);

        countdown(timerInSec, timer);
        getChildren().addAll( foodimagShow, orderName, timer);


    }
}
