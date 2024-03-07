package gameLogic;

public class Ratio {
    private final String name ;
    private final int amount;

    public Ratio(String name, int amount) {
        this.name = name;
        this.amount = Math.max(1, amount);
    }

    public String getName() {
        return name;
    }

    public int getAmount() {
        return amount;
    }
}
