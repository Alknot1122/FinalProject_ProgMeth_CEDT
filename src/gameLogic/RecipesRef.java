package gameLogic;

import java.util.ArrayList;

public class RecipesRef {
    private ArrayList<Recipe> recipes;
    public RecipesRef(){
        recipes = new ArrayList<Recipe>();
        Food f1 = new Food("food1", "RedVelvetCake.png", 20);
        Item t1 = new Item ("item11", "Milk.png");
        Item t2 = new Item ("item12", "MixedBerryPie.png");

        Food f2 = new Food("food2", "PowderedSugar.png", 20);
        Item t3 = new Item ("item21", "Meringue.png");
        Item t4 = new Item ("item22", "Lemon.png");
        Item t5 = new Item ("item23", "Flour.png");

        Food f3 = new Food("food3", "RainbowCakeRoll.png", 20);
        Item t6 = new Item ("item31", "Egg.png");

        Recipe r1 = new Recipe(f1, new Item[]{t1, t2} );
        Recipe r2 = new Recipe(f2, new Item[]{t3, t4, t5} );
        Recipe r3 = new Recipe(f3, new Item[]{t6});

        recipes.add(r1);
        recipes.add(r2);
        recipes.add(r3);
    }

    public ArrayList<Recipe> getRecipes() {
        return recipes;
    }
}
