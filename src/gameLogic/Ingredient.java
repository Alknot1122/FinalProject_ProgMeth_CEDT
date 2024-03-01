package gameLogic;

public class Ingredient extends Item{
    private int amount;
    private int quality;

    public Ingredient(String name,int quanity, int quality,  int price) {
        super(name, price);
        setQuality(quality);
        setAmount(quanity);

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


}
