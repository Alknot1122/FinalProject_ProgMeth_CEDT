package gameLogic;

import java.util.ArrayList;

public class RecipesRef {
    private ArrayList<Recipe> recipes;
    public RecipesRef(){
        recipes = new ArrayList<Recipe>();
        Food f1 = new Food("food1", "Food/RedVelvetCake.png", 20);
        Item t1 = new Item ("item11", "Item/Milk.png");
        Item t2 = new Item ("item12", "Food/MixedBerryPie.png");

        Food f2 = new Food("food2", "Item/Cinnamon.png", 20);
        Item t3 = new Item ("item13", "Item/Flour.png");
        Item t4 = new Item ("item14", "Item/Vanilla.png");

        Recipe r1 = new Recipe(f1, new Item[]{t1, t2} );
        Recipe r2 = new Recipe(f2, new Item[]{t3, t4} );
        recipes.add(r1);
        recipes.add(r2);

    }

    public ArrayList<Recipe> getRecipes() {
        return recipes;
    }

}
