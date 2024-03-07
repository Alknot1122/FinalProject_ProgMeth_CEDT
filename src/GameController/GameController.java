package GameController;

import gameLogic.*;

import java.util.ArrayList;
import java.util.Random;

public class GameController {
    private itemList storage;
    private Market market;
    private Player player;
    private ArrayList<String> doableRecpie;
    private ArrayList<Recipe> recpieList;
    private ArrayList<Oven> ovenList; // we have 3 oven in this game
    public  boolean isGameOver(Player player){
        return player.getMoney() < player.getBill();
    }
    public String makeFood(Player player, Recipe recipe){ //when in ui u can use this function to tell player
        for (Oven oven : ovenList){
            //check if there's oven available
            if (oven.isOvenEmpty()){
                //check if player have enough ingredent for food
                for (Ratio ratio : recipe.getRecipe()){
                    if(! player.getInventory().findIngredient(ratio)){
                        return "not enough Ingredient";
                    }
                }
                int totalCost  = 0;
                for (Ratio ratio : recipe.getRecipe()){
                    totalCost += player.getInventory().getPriceOfOrderedRatio(ratio);
                }
                oven.setFood(recipe.getFoodName(), totalCost);
                return "cooking Successfully!";
            }
        }
        return "there's no Oven Available";

    }
    public void endDay(Player player){
        player.setMoney(player.getMoney() - player.getBill());
        player.setBill(player.getBill() + 5);

    }
    public void setUp(String playerName){
        player = new Player(playerName);
        market = new Market(new ArrayList<Ingredient>());
        storage = new itemList(50);
        doableRecpie = new ArrayList<>();
        recpieList = new ArrayList<Recipe>();
        //add some basic recpie that player can do in the begining
        //add all recpie in recpielist but make the basic recpies's islock = false
    }
    public Customer makeCustomer(){
        Random random = new Random();
        ArrayList<Item> OrderList = new ArrayList<Item>();
        int randomOrderTotal = random.nextInt(3) + 1;
        for (int i =0; i < randomOrderTotal; i++){
            int randomeRecpies = random.nextInt(getDoableRecpie().size());
            Item order = new Item(getDoableRecpie().get(randomeRecpies), 15);
            OrderList.add(order);
        }
        return new Customer(OrderList);
    }

    public itemList getStorage() {
        return storage;
    }

    public Market getMarket() {
        return market;
    }

    public Player getPlayer() {
        return player;
    }

    public ArrayList<String> getDoableRecpie() {
        return doableRecpie;
    }

    public ArrayList<Recipe> getRecpieList() {
        return recpieList;
    }
}
