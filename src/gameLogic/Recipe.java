package gameLogic;

import application.Main;

public class Recipe {
    private final Food FOOD;
    private final Item[] ITEMS;
    public Recipe(Food food, Item[] items) {
        this.FOOD = food;
        this.ITEMS = items;
    }

    public Food getFood() {
        return FOOD;
    }

    public Item[] getItems() {
        return ITEMS;
    }


}