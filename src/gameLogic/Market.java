package gameLogic;

import java.util.ArrayList;

public class Market {
  private final ArrayList<Ingredient> ingredientList;

    public Market(ArrayList<Ingredient> ingredientList) {
        this.ingredientList = ingredientList;
    }

    public boolean BuyItem(Player player, String name, int amount, int quality, int ogPrice){
    int totalPrice = (ogPrice * quality) * amount;
    if (player.getMoney() >= totalPrice){
      Ingredient item = new Ingredient(name, amount, quality, ogPrice);
      player.getInventory().insertItem(item);
      player.setMoney(player.getMoney() - totalPrice);
      return true;
    }
    return false;

  }

  public ArrayList<Ingredient> getIngredientList() {
    return ingredientList;
  }
}
