package gameLogic;

public class Food extends Item{
    private int Points;
    public Food(String name, String imagePath, int points) {
        super(name, imagePath);
        Points = Math.max(5, points);
    }
<<<<<<< Updated upstream
=======
    public Food(Food food){
        super(food.getItemName(), food.getImagePath());
        Points = food.getPoints();
    }
>>>>>>> Stashed changes
    public int getPoints(){
        return Points;
    }
}
