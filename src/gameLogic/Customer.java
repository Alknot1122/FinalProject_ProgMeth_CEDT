package gameLogic;

import java.util.ArrayList;
import java.util.Objects;

public class Customer {
    //this is customer that have timer
    // if timer runs out customer will be gone
    // when get food from player , customer have to check that is that food
    //are in their order. if yes , player will get money and that food will be gome from customer's orderList
    private final ArrayList<Item> orderList;

    public Customer(ArrayList<Item> orderList) {
        this.orderList = orderList;
    }
    public void gotFood(Item food, Player player){
        for( int i = orderList.size() -1; i >= 0; i--){
            if(Objects.equals(getOrderList().get(i).getName(), food.getName())){
                player.setMoney(player.getMoney() + food.getPrice() + getOrderList().get(i).getPrice());
                player.getInventory().withdrawItem(food.getName(), 1);
                getOrderList().remove(i);
                break;
            }
        }
    }

    public ArrayList<Item> getOrderList() {
        return orderList;
    }
}
