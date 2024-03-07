package gameLogic;

import javax.swing.plaf.IconUIResource;
import java.util.ArrayList;
import java.util.Objects;

public class itemList {
    private final ArrayList<Item> itemList;
    private final int MAX_SLOT;
    public itemList(int maxSlot) {
        this.itemList = new ArrayList<Item>();
        this.MAX_SLOT = Math.max(5, maxSlot);
    }
    public ArrayList<Item> getItemList() {
        return itemList;
    }
    public boolean insertItem (Item item){

        if (getItemList().size() != MAX_SLOT){
            if (item instanceof  Ingredient){

                for (Item items : getItemList()){
                    if (items instanceof  Ingredient){
                        if(items.equals(item) ){
                            ((Ingredient) items).setAmount(
                                    ((Ingredient) items).getAmount() + ((Ingredient) item).getAmount()

                            );

                            return true;

                        }
                    }
                }

                getItemList().add(item);
                return true;
            }
            else {
                getItemList().add(item);
                return true;
            }
        }

        return false;
    }
    public boolean withdrawItem(String name , int amount){
        int count = 0;
        for (int i = getItemList().size() -1; i >=0 ; i--){
            if (Objects.equals(getItemList().get(i).getName(), name)){
                if (getItemList().get(i) instanceof Ingredient){
                    int remains = amount - count;
                    if(remains > ((Ingredient) getItemList().get(i)).getAmount()){
                        count += ((Ingredient) getItemList().get(i)).getAmount();
                        getItemList().remove(i);
                    }
                    else{
                        ((Ingredient) getItemList().get(i)).setAmount(
                                ((Ingredient) getItemList().get(i)).getAmount() - remains
                        );
                        if(((Ingredient) getItemList().get(i)).getAmount() == 0){
                            getItemList().remove(i);
                        }
                        return true;
                    }
                }
                else {
                    getItemList().remove(i);
                    count ++;
                    if(count == amount) return true;
                }

            }
        }
        return false;
    }

    public boolean findIngredient (Ratio ratio){
        int count =0;
        for (Item item : getItemList()){
            if(item instanceof Ingredient){
                if(Objects.equals(item.getName(), ratio.getName())){
                    int remains = ratio.getAmount() - count;
                    if(remains > ((Ingredient) item).getAmount()){
                        count += ((Ingredient) item).getAmount();

                    }
                    else{return true;}
                }
            }
        }
        return false;

    }
    public int getPriceOfOrderedRatio(Ratio ratio){
        int count =0;
        int price =0;
        for (Item item : getItemList()){
            if(item instanceof Ingredient){
                if(Objects.equals(item.getName(), ratio.getName())){
                    int remains = ratio.getAmount() - count;
                    if(remains > ((Ingredient) item).getAmount()){
                        count += ((Ingredient) item).getAmount();
                        price += ((Ingredient) item).getAmount() * ((Ingredient) item).getRealPrice();
                    }
                    else{
                        price += remains * ((Ingredient) item).getRealPrice();
                        return price;
                    }
                }
            }
        }

        return price;
    }
}
