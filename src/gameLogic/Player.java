package gameLogic;

import java.util.ArrayList;
import java.util.Objects;

public class Player {
    private int money;
    private String name;
    private itemList inventory;
    private int bill;


    public Player(String name){
        setName(name);
        inventory = new itemList(9);
        bill = 5;
        setMoney(100);
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int getBill() {
        return bill;
    }

    public void setBill(int bill) {
        this.bill = Math.max(bill, 5);
    }

    public itemList getInventory() {
        return inventory;
    }
}
