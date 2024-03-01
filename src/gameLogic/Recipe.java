package gameLogic;

import java.util.ArrayList;

public class Recipe {
    private ArrayList<Item> Recipe;
    private Food food;
    private int timeAmount;
    private boolean isLock;

    public Recipe(ArrayList<Item> recipe, Food food, int timeAmount, boolean isLock) {
        Recipe = recipe;
        this.food = food;
        this.timeAmount = timeAmount;
        this.isLock = false;
    }

}
