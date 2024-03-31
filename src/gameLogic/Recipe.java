package gameLogic;

import java.util.ArrayList;

public class Recipe {
    private final ArrayList<Ratio> Recipe;
    private final String foodName;
    private final int timeAmount;
    private boolean isLock;
    private final String[] words;

    public Recipe(ArrayList<Ratio> recipe, String foodname, int timeAmount, boolean isLock, String[] words) {
        Recipe = recipe;
        this.foodName = foodname;
        this.timeAmount = Math.max(5, timeAmount);
        this.isLock = isLock;
        this.words = words;
    }

    public ArrayList<Ratio> getRecipe() {
        return Recipe;
    }

    public String[] getWords() {
        return words;
    }

    public String getFoodName() {
        return foodName;
    }

    public int getTimeAmount() {
        return timeAmount;
    }



    public boolean isLock() {
        return isLock;
    }

    public void setLock(boolean lock) {
        isLock = lock;
    }
}

