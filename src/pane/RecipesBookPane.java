package pane;

import application.GamePage;
import gameLogic.GameController;
import gameLogic.Recipe;
import gameLogic.RecipesRef;
import javafx.animation.ScaleTransition;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.util.Objects;

public class RecipesBookPane extends Pane {
    private GridPane ingredientPane;
    private ImageView foodImage;
    private Text foodName ;
    private TextField searchTextfield ;
    private int page;
    private RecipesRef recipesRef;

    public RecipesBookPane (GameController gameController, RecipesRef recipesRef, PinningPane pinningPane){

        page =0;
        this.recipesRef  = recipesRef;
       setPrefWidth(728);setPrefHeight(479);
       setLayoutX(150);setLayoutY(120);
       setVisible(false);

        Image bgimg = new Image(Objects.requireNonNull(getClass().getResource("/Background/recipesBookPane.png")).toExternalForm());
        BackgroundImage BGimg = new BackgroundImage(bgimg, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        setBackground(new Background(BGimg));

        foodImage = new ImageView();
        foodImage.setFitHeight(200);
        foodName = getDisplay.getText("", 29, 275, 425,73);

       Button goLeftButton = getDisplay.getButton("/Button/goleftButton.png", 34,40,-10,214);
       Button goRightButton = getDisplay.getButton("/Button/goRightButton.png", 34,40,701,214);
       Button exitButton = getDisplay.getButton("/Button/exitButton.png", 38,37,686,-12);
       Button cookButton = getDisplay.getButton("/Button/cookbutton.png", 133,56,95,340);
       Button pinButton = getDisplay.getButton("/Button/pinningButton.png", 58,60,240,340);





        cookButton.setOnMousePressed(mouseEvent -> {
            // Play button click sound
            SoundController clockingButtonnoise = new SoundController("res/Sound/buttonclick.mp3");
            clockingButtonnoise.getMediaPlayer().setVolume(0.5);
            clockingButtonnoise.playMusic();

            setVisible(false);

            // Start cooking process
            gameController.StartCooking(recipesRef.getRecipes().get(page));
        });
       goLeftButton.setOnMousePressed(mouseEvent -> {
            SoundController turnleftsound = new SoundController("res/Sound/turnLeftRecipeBook.mp3");
           turnleftsound.playMusic();
           goLeft();

       });


       goRightButton.setOnMousePressed(mouseEvent -> {
           SoundController turnRightSound = new SoundController("res/Sound/turnRightRecipeBook.mp3");
           turnRightSound.playMusic();
           goRight();

       });


       exitButton.setOnMousePressed(mouseEvent -> {
           SoundController turnleftsound = new SoundController("res/Sound/CloseBook.mp3");
           turnleftsound.playMusic();
           setVisible(false);
       });
       pinButton.setOnMousePressed(new EventHandler<MouseEvent>() {
           @Override
           public void handle(MouseEvent mouseEvent) {
               SoundController clockingButtonnoise = new SoundController("res/Sound/buttonclick.mp3");
               clockingButtonnoise.getMediaPlayer().setVolume(0.5);
               clockingButtonnoise.playMusic();
               pinningPane.setVisible(true);
               pinningPane.setFoodList(recipesRef.getRecipes().get(page));
           }
       });


        ingredientPane = new GridPane(4, 4);

        ingredientPane.setStyle("-fx-background-color: rgba(245, 145, 32,0.25);");

        searchTextfield = new TextField();
        searchTextfield.setPromptText("Find Recipes Here!");
        searchTextfield.setPrefWidth(265);
        searchTextfield.setFont(Font.loadFont(getClass().getResourceAsStream("/PeaberryBase.ttf"),20));
        searchTextfield.setBackground(Background.EMPTY);

        searchTextfield.setOnMouseEntered(event -> {
            searchTextfield.requestFocus();
        });


        searchTextfield.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode().equals(KeyCode.ENTER)) {
                String searchInput = searchTextfield.getText().trim().toLowerCase(); // Get the trimmed lowercase input
                for (int i = 0; i < recipesRef.getRecipes().size(); i++) {
                    String recipeName = recipesRef.getRecipes().get(i).getFood().getItemName().trim().toLowerCase(); // Get the trimmed lowercase recipe name
                    if (searchInput.equals(recipeName)) {
                        page = i;
                        clear();
                        MakePage(recipesRef.getRecipes().get(page));
                        searchTextfield.setText("");
                        break;
                    }
                }
            }
        });


        setNodePosition(ingredientPane, 447,110);
        setNodePosition(searchTextfield, 52,42);
        foodImage = recipesRef.getRecipes().get(page).getFood().getItemImageView(200);
        foodImage.setX(95.0);
        foodImage.setY(110.0);

        getChildren().addAll(ingredientPane, foodName, searchTextfield,foodImage, exitButton, goLeftButton, goRightButton, cookButton, pinButton);
        MakePage(recipesRef.getRecipes().getFirst());

    }
    public void MakePage(Recipe recipe){


     foodImage.setImage(recipe.getFood().getItemImage());

     foodName.setText(recipe.getFood().getItemName());
        int colum =0;
        int row =0;
        for (int i =0; i <recipe.getItems().length; i++){
            ImageView imageItem = recipe.getItems()[i].getItemImageView(100);
            GridPane.setColumnIndex(imageItem, colum);
            GridPane.setRowIndex(imageItem, row);

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
    if (page == -1) {
        page = recipesRef.getRecipes().size()-1;
    }

    MakePage(recipesRef.getRecipes().get(page));

    }
    public void goRight(){
        clear();
        page = (page + 1) % recipesRef.getRecipes().size();

       MakePage(recipesRef.getRecipes().get(page));

    }
    public void setNodePosition (Node nodem, double Xpos, double Ypos){
        nodem.setLayoutX(Xpos);
        nodem.setLayoutY(Ypos);

    }
    public void setSizeButton(Button button, double width, double hight){
        button.setPrefHeight(hight);
        button.setPrefWidth(width);
    }
    public void clear (){
            for (int i = ingredientPane.getChildren().size()-1; i >=0 ; i--){
                ingredientPane.getChildren().remove(i);
            }
    }
    public void setBGImageforButton (String imgpath, Button button ){
        Image buttonimg = new Image(Objects.requireNonNull(getClass().getResource(imgpath)).toExternalForm());
        BackgroundImage BGbuttonimg = new BackgroundImage(buttonimg, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        button.setBackground(new Background(BGbuttonimg));
    }
}
