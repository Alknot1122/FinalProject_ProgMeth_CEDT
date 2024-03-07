package gameLogic;


import java.util.ArrayList;
import java.util.Objects;

public class Oven {
   private int level;
   private String foodName;
   private int price ;
   private final int[]  OVEN_LEVEL= new int[]{1,2,3};
   private int  ovenLevel;

   public Oven() {

      ovenLevel = 1;
   }
   public int getBakeTime (int waitTime){
      return waitTime / OVEN_LEVEL[ovenLevel];
   }
   public void upgrade (){
      ovenLevel ++;
   }
   public int getLevel(){
      return ovenLevel;
   }

   public void giveItemToPlayer(Player player){
      if(!foodName.isBlank()){
         player.getInventory().insertItem(new Item(foodName, price));
         foodName = "";
         price =0;
      }
   }

   public void setFood (String name, int price){
      this.foodName = name;
      this.price = Math.max(1, price);
   }
   public boolean isOvenEmpty(){
      return Objects.equals(foodName, "");
   }

}
