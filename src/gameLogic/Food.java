package gameLogic;

public class Food extends Item{
    private int Points;
    public Food(String name, String imagePath, int points) {
        super(name, imagePath);
        Points = Math.max(5, points);
    }
    public int getPoints(){
        return Points;
    }
}
