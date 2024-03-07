package gameLogic;

import java.util.Objects;

public class Ingredient extends Item{
    private int amount;
    private int quality;

    public Ingredient(String name,int amount, int quality,  int price) {
        super(name, price);
        setQuality(quality);
        setAmount(amount);

    }


    public int getRealPrice(){

        return super.getPrice() * getQuality();
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int quanity) {
        this.amount = quanity;
    }

    public int getQuality() {
        return quality;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingredient that = (Ingredient) o;
        return  quality == that.quality && Objects.equals(this.getName(), ((Ingredient) o).getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, quality);
    }
}
