package gameLogic;

import java.util.ArrayList;

public class IngredientsRef {
    private final ArrayList<Item> ingredients;
    public IngredientsRef(){
        ingredients = new ArrayList<Item>();

        //diary
        ingredients.add(new Item ("Egg", "Item/Egg.png"));
        ingredients.add(new Item ("Milk", "Item/Milk.png"));
        ingredients.add(new Item ("Butter", "Item/Butter.png"));
        ingredients.add(new Item ("Cream", "Item/Cream.png"));
        ingredients.add(new Item ("Cream Cheese", "Item/CreamCheese.png"));

        //powder
        ingredients.add(new Item ("Sugar", "Item/Sugar.png"));
        ingredients.add(new Item ("Powdered sugar", "Item/PowderedSugar.png"));
        ingredients.add(new Item ("Flour", "Item/Flour.png"));
        ingredients.add(new Item ("Cocoa Powder", "Item/CocoPowder.png"));

        //spice
        ingredients.add(new Item ("Vanilla", "Item/Vanilla.png"));
        ingredients.add(new Item ("Cinnamon", "Item/Cinnamon.png"));

        //ect
        ingredients.add(new Item ("Cracker", "Item/Cracker.png"));
        ingredients.add(new Item ("Chocolate", "Item/Chocolate.png"));
        ingredients.add(new Item ("Magic Color Drop", "Item/MagicColorDrop.png"));

        //fruit
        ingredients.add(new Item ("StrawBerry", "Item/Strawberry.png"));
        ingredients.add(new Item ("Raspberry", "Item/Raspberry.png"));
        ingredients.add(new Item ("Blueberry", "Item/Blueberry.png"));
        ingredients.add(new Item ("Lemon", "Item/Lemon.png"));

    }

    public ArrayList<Item> getIngredients() {
        return ingredients;
    }
}
