package gameLogic;

import java.util.ArrayList;

public class IngridentsRef {
    private final ArrayList<Item> ingridnets;
    public IngridentsRef(){
        ingridnets = new ArrayList<Item>();
        //diary
        ingridnets.add(new Item ("Egg", "Item/Egg.png"));
        ingridnets.add(new Item ("Milk", "Item/Milk.png"));
        ingridnets.add(new Item ("Butter", "Item/Butter.png"));
        ingridnets.add(new Item ("Cream", "Item/Cream.png"));
        ingridnets.add(new Item ("Cream Cheese", "Item/CreamCheese.png"));
        //powder
        ingridnets.add(new Item ("Sugar", "Item/Sugar.png"));
        ingridnets.add(new Item ("Powdered sugar", "Item/PowderedSugar.png"));
        ingridnets.add(new Item ("Flour", "Item/Flour.png"));
        ingridnets.add(new Item ("Cocoa Powder", "Item/CocoPowder.png"));
        //spice
        ingridnets.add(new Item ("Vanilla", "Item/Vanilla.png"));
        ingridnets.add(new Item ("Cinnamon", "Item/Cinnamon.png"));

        //ect
        ingridnets.add(new Item ("Cracker", "Item/Cracker.png"));
        ingridnets.add(new Item ("Chocolate", "Item/Chocolate.png"));
        ingridnets.add(new Item ("Magic Color Drop", "Item/MagicColorDrop.png"));

        //fruit
        ingridnets.add(new Item ("StrawBerry", "Item/Strawberry.png"));
        ingridnets.add(new Item ("Raspberry", "Item/Raspberry.png"));
        ingridnets.add(new Item ("Blueberry", "Item/Blueberry.png"));
        ingridnets.add(new Item ("Lemon", "Item/Lemon.png"));

    }

    public ArrayList<Item> getIngridnets() {
        return ingridnets;
    }
}
