package gameLogic;

import gameLogic.Item;

import java.util.ArrayList;

public class IngridentsRef {
    private final ArrayList<Item> ingridnets;
    public IngridentsRef(){
        ingridnets = new ArrayList<Item>();
      ingridnets.add(new Item ("Egg", "Egg.png"));
        ingridnets.add(new Item ("Sugar", "Sugar.png"));
        ingridnets.add(new Item ("Milk", "Milk.png"));
        ingridnets.add(new Item ("Powdered sugar", "PowderedSugar.png"));
        ingridnets.add(new Item ("Flour", "Flour.png"));
        ingridnets.add(new Item ("Butter", "Butter.png"));
        ingridnets.add(new Item ("Cream", "Cream.png"));
        ingridnets.add(new Item ("Cracker", "Cracker.png"));
        ingridnets.add(new Item ("Vanilla", "Vanilla.png"));
        ingridnets.add(new Item ("Magic Color Drop", "MagicColorDrop.png"));
        ingridnets.add(new Item ("Chocolate", "Chocolate.png"));
        ingridnets.add(new Item ("Cinnamon", "Cinnamon.png"));
        ingridnets.add(new Item ("Cream Cheese", "CreamCheese.png"));
        ingridnets.add(new Item ("Cocoa Powder", "CocoPowder.png"));
        ingridnets.add(new Item ("StrawBerry", "Strawberry.png"));
        ingridnets.add(new Item ("Raspberry", "Raspberry.png"));
        ingridnets.add(new Item ("Blueberry", "Blueberry.png"));
        ingridnets.add(new Item ("Lemon", "Lemon.png"));



    }

    public ArrayList<Item> getIngridnets() {
        return ingridnets;
    }
}
