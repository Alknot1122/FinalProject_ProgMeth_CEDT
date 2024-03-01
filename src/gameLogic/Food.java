package gameLogic;


import java.util.ArrayList;

public class Food extends Item{
    private ArrayList<Item> recipe;


    public Food(int price, String name, ArrayList<Item> recipe) {
        super(name, price);
        setRecipe(recipe);
    }


    public ArrayList<Item> getRecipe() {
        return recipe;
    }

    public void setRecipe(ArrayList<Item> recipe) {
        this.recipe = recipe;
    }
}
