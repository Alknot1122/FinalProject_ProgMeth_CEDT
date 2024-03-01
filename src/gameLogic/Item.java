package gameLogic;

public class Item {
   private String name;
   private int Price;

    public Item(String name, int price) {
        setName(name);
        setPrice(price);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;// make it cannot be less than 1
    }
}
