package gameLogic;

import gameLogic.Item;

import java.util.ArrayList;

public class IngridentsRef {
    private ArrayList<Item> ingridnets;
    public IngridentsRef(){
        ingridnets = new ArrayList<Item>();
      ingridnets.add(new Item ("Lemon", "Lemon.png"));
        ingridnets.add(new Item ("Milk", "Lemon.png"));
        ingridnets.add(new Item ("StawBerry", "Lemon.png"));
        ingridnets.add(new Item ("RaspBerry", "Lemon.png"));
        ingridnets.add(new Item ("Lemon", "Lemon.png"));
        ingridnets.add(new Item ("Lemon", "Lemon.png"));
        ingridnets.add(new Item ("Lemon", "Lemon.png"));
        ingridnets.add(new Item ("Lemon", "Lemon.png"));
        ingridnets.add(new Item ("Lemon", "Lemon.png"));


    }

    public ArrayList<Item> getIngridnets() {
        return ingridnets;
    }
}
