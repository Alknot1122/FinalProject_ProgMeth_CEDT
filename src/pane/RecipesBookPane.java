package pane;

import gameLogic.Recipe;
import gameLogic.RecipesRef;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.awt.*;
import java.util.ArrayList;

public class RecipesBookPane extends AnchorPane {
    private GridPane ingredientPane;
    private ImageView foodImage;
    private Text foodName ;
    private TextField searchTextfield ;
    private int page;
    private RecipesRef recipesRef;

    public RecipesBookPane (){
        page =0;
        recipesRef  = new RecipesRef();
       setPrefWidth(712);
       setPrefHeight(467);
       setBackground(Background.fill(Color.GRAY));

       Button goLeftButton = new Button("<");
       goLeftButton.setOnMousePressed(new EventHandler<MouseEvent>() {
           @Override
           public void handle(MouseEvent mouseEvent) {
               goLeft();
           }
       });

       Button goRightButton = new Button(">");
       goRightButton.setOnMousePressed(new EventHandler<MouseEvent>() {
           @Override
           public void handle(MouseEvent mouseEvent) {
               goRight();
           }
       });
       Button exitButton = new Button("X");
       exitButton.setFont(new Font(13));

        Text findRecText = new Text("Find recpices here!");
        findRecText.setFont(new Font(26));

        ingredientPane = new GridPane(4, 4);

        ingredientPane.setBackground(Background.fill(Color.WHITE));
        searchTextfield = new TextField();
        foodImage = new ImageView();

        foodName = new Text("food name guhugh");
        foodName.setFont(new Font(29));
        setNodePosition(ingredientPane, 107.0, 413.0, 52.0, 94.0);
        setNodePosition(foodName, 54.0, 384.0, 381.4, 63.463);
        setNodePosition(searchTextfield, 59.0, 25.0, 385.0, 377.6);
        setNodePosition(findRecText, 24.0, 25.0, 413.47, 482.46);
        setNodePosition(exitButton, 5.0, 675.0, 433.0, 5.0);
        setNodePosition(goLeftButton, 216.0, 4.0, 208.0, 680.4);
        setNodePosition(goRightButton, 216.0, 680.0, 208.0, 4.0);
        MakePage(recipesRef.getRecipes().get(page));

    }
    public void MakePage(Recipe recipe){


      foodImage = recipe.getFood().getItemImage(268);
      setNodePosition(foodImage, 130.3,56.0,0.0, 0.0);
     foodName.setText(recipe.getFood().getItemName());
        int colum =0;
        int row =0;
        for (int i =0; i <recipe.getItems().length; i++){
            ImageView imageItem = recipe.getItems()[i].getItemImage(100);
            ingredientPane.setColumnIndex(imageItem, colum);
            ingredientPane.setRowIndex(imageItem, row);

            colum++;
            if (colum == 2) {
                row ++;
                colum = 0;
            }
            ingredientPane.getChildren().add(imageItem);
        }
    }
    public void goLeft(){
        clear();
    page --;
    if (page == -1) page = recipesRef.getRecipes().size()-1;
        //System.out.println(page);
    MakePage(recipesRef.getRecipes().get(page));
    }
    public void goRight(){
        clear();
        page = (page + 1) % recipesRef.getRecipes().size();
       // System.out.println(page);
       MakePage(recipesRef.getRecipes().get(page));
    }
    public void setNodePosition (Node node, double top, double left, double bottom, double right){
        setTopAnchor(node, top);
        setLeftAnchor(node, left);
        setBottomAnchor(node, bottom);
        setRightAnchor(node, right);
        getChildren().add(node);
    }
    public void clear (){

            for (int i = ingredientPane.getChildren().size()-1; i >=0 ; i--){
                ingredientPane.getChildren().remove(i);
            }
            // remove the inof of earlier recipe
            getChildren().removeLast();


    }
}
