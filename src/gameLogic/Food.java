package gameLogic;

public class Food extends Item{
    private final int POINTS;
    public Food(String name, String imagePath, int points) {
        super(name, imagePath);
        POINTS = Math.max(5, points);
    }

    public Food(Food food){
        super(food.getItemName(), food.getImagePath());
        POINTS = food.getPoints();
    }

    public int getPoints(){
        return POINTS;
    }
}
