package gameLogic;

import application.Main;

public class Recipe {
    private final Food food;
    private final Item[] items;


    public Recipe(Food food, Item[] items) {
        this.food = food;
        this.items = items;


    }

    public Food getFood() {
        return food;
    }

    public Item[] getItems() {
        return items;
    }


}