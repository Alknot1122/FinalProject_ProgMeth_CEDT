package pane;

import Utils.AnimatedOtherButton;
import application.GamePage;
import gameLogic.GameController;
import gameLogic.Recipe;
import gameLogic.RecipesRef;
import javafx.animation.ScaleTransition;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.util.Objects;

public class RecipesBookPane extends Pane {
    private final GridPane ingredientPane;
    private ImageView foodImage;
    private final Text foodName ;
    private final TextField searchTextfield ;
    private int page;
    private final RecipesRef recipesRef;

    public RecipesBookPane (GameController gameController, RecipesRef recipesRef, PinningPane pinningPane){

        page =0;
        this.recipesRef  = recipesRef;
       setPrefWidth(728);
       setPrefHeight(479);
       setLayoutX(150);
       setLayoutY(120);
       setVisible(false);
       //set pane's background image
        Image bgimg = new Image(Objects.requireNonNull(getClass().getResource("/Background/recipesBookPane.png")).toExternalForm());
        BackgroundImage BGimg = new BackgroundImage(bgimg, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        setBackground(new Background(BGimg));

        foodImage = new ImageView();
        foodImage.setFitHeight(200);

        foodName = getDisplay.getText("", 29, 275, 425,73);

        //set buttons
       Button goLeftButton = getDisplay.getButton("/Button/goleftButton.png", 34,40,-10,214);
       Button goRightButton = getDisplay.getButton("/Button/goRightButton.png", 34,40,701,214);
       Button closeButton = getDisplay.getButton("/Button/exitButton.png", 38,37,686,-12);
       Button cookButton = getDisplay.getButton("/Button/cookbutton.png", 133,56,95,340);
       Button pinButton = getDisplay.getButton("/Button/pinningButton.png", 58,60,240,340);

        AnimatedOtherButton.applyHoverEffect(goLeftButton);
        AnimatedOtherButton.applyHoverEffect(goRightButton);
        AnimatedOtherButton.applyHoverEffect(closeButton);
        AnimatedOtherButton.applyHoverEffect(cookButton);
        AnimatedOtherButton.applyHoverEffect(pinButton);


        cookButton.setOnMousePressed(mouseEvent -> {
            // Play button click sound
            SoundController clockingButtonnoise = new SoundController("res/Sound/buttonclick.mp3");
            clockingButtonnoise.getMediaPlayer().setVolume(0.7);
            clockingButtonnoise.playMusic();

            setVisible(false);

            // Start cooking process
            gameController.StartCooking(recipesRef.getRecipes().get(page));
        });
       goLeftButton.setOnMousePressed(mouseEvent -> {
           // Play turn left sound
            SoundController turnLeftSound = new SoundController("res/Sound/turnLeftRecipeBook.mp3");
           turnLeftSound.playMusic();
           //go to earlier recipe
           goLeft();

       });


       goRightButton.setOnMousePressed(mouseEvent -> {
           // Play turn right sound
           SoundController turnRightSound = new SoundController("res/Sound/turnRightRecipeBook.mp3");
           turnRightSound.playMusic();

           //go to next recipe
           goRight();
       });


        closeButton.setOnMousePressed(mouseEvent -> {
           //play close book sound
           SoundController turnleftsound = new SoundController("res/Sound/CloseBook.mp3");
           turnleftsound.playMusic();

           //close RecipeBookPane
           setVisible(false);
       });

       pinButton.setOnMousePressed(mouseEvent -> {
           SoundController buttonClickSound = new SoundController("res/Sound/buttonclick.mp3");
           buttonClickSound.getMediaPlayer().setVolume(0.7);
           buttonClickSound.playMusic();

           //make pinningPane visible and set the recipe to current recipe
           pinningPane.setVisible(true);
           pinningPane.setRecipeList(recipesRef.getRecipes().get(page));
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
            // search the recipe from player's input
            if (keyEvent.getCode().equals(KeyCode.ENTER)) {
                // Get the trimmed lowercase input
                String searchInput = searchTextfield.getText().trim().toLowerCase();

                for (int i = 0; i < recipesRef.getRecipes().size(); i++) {
                    // Get the trimmed lowercase recipe name
                    String recipeName = recipesRef.getRecipes().get(i).getFood().getItemName().trim().toLowerCase();

                    //if searchInput equals to any recipe name
                    if (searchInput.equals(recipeName)) {

                        //set page to that recipe index
                        page = i;
                        ingredientPane.getChildren().clear();

                        //make page out of this recipe
                        MakePage(recipesRef.getRecipes().get(page));

                        // clear player' input
                        searchTextfield.setText("");
                        break;
                    }
                }
            }
        });

        //set ingredientPane, searchTextfield, foodImage position
        setNodePosition(ingredientPane, 447,110);
        setNodePosition(searchTextfield, 52,42);
        foodImage = recipesRef.getRecipes().get(page).getFood().getItemImageView(200);
        foodImage.setX(95.0);
        foodImage.setY(110.0);


        getChildren().addAll(ingredientPane, foodName, searchTextfield, foodImage);
        getChildren().addAll( closeButton, goLeftButton, goRightButton, cookButton, pinButton);

        //show the first recipe
        MakePage(recipesRef.getRecipes().getFirst());
    }
    public void MakePage(Recipe recipe){

         foodImage.setImage(recipe.getFood().getItemImage());
         foodName.setText(recipe.getFood().getItemName());

        // make ingredientPane display items that is from that recipe
        int colum =0;
        int row =0;
        for (int i =0; i <recipe.getItems().length; i++){

            ImageView imageItem = recipe.getItems()[i].getItemImageView(100);
            GridPane.setColumnIndex(imageItem, colum);
            GridPane.setRowIndex(imageItem, row);

            // make items display from top to bottom, left to right
            colum++;
            if (colum == 2) {
                row ++;
                colum = 0;
            }

            ingredientPane.getChildren().add(imageItem);
        }
    }
    public void goLeft(){
        //clear children in ingredientPane
        ingredientPane.getChildren().clear();

        page --;
        // if page is already at -1, return to the last recipe in recipeRef
        page = page == -1? recipesRef.getRecipes().size()-1 : page;

        MakePage(recipesRef.getRecipes().get(page));
    }
    public void goRight(){
        //clear children in ingredientPane
        ingredientPane.getChildren().clear();

        //if page reach the last recipe, return to the beginning of recipeRef
        page = (page + 1) % recipesRef.getRecipes().size();

       MakePage(recipesRef.getRecipes().get(page));
    }
    public void setNodePosition (Node nodem, double Xpos, double Ypos){
        nodem.setLayoutX(Xpos);
        nodem.setLayoutY(Ypos);
    }


}
