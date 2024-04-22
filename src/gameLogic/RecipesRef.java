package gameLogic;

import java.util.ArrayList;

public class RecipesRef {
    private ArrayList<Recipe> recipes;
    public RecipesRef(){
        recipes = new ArrayList<Recipe>();
        Food f1 = new Food("food1", "RedVelvetCake.png", 20);
        Item t1 = new Item ("item11", "Milk.png");
        Item t2 = new Item ("item12", "MixedBerryPie.png");

        Recipe r1 = new Recipe(f1, new Item[]{t1, t2} );
        recipes.add(r1);

    }

    public ArrayList<Recipe> getRecipes() {
        return recipes;
    }

}
