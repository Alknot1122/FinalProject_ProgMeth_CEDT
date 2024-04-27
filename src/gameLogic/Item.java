package gameLogic;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Item {
<<<<<<< Updated upstream
=======
    private final String imagePath;
>>>>>>> Stashed changes
    private final Image itemImage ;
    private final String itemName;

    public Item (String name , String imagePath){
        itemName = name;
<<<<<<< Updated upstream


=======
        this.imagePath = imagePath;
>>>>>>> Stashed changes
        String   classLoaderPath = ClassLoader.getSystemResource(imagePath).toString();


        itemImage = new Image(classLoaderPath);
<<<<<<< Updated upstream
=======
    }

    public String getImagePath() {
        return imagePath;
    }

    public  Item (Item item){
        this.imagePath = item.getImagePath();
>>>>>>> Stashed changes


    }

    public ImageView getItemImageView(int fitHight) {
        ImageView image = new ImageView(itemImage);
        image.setFitHeight(fitHight);
        image.setPreserveRatio(true);
        return image;
    }

    public String getItemName() {
        return itemName;
    }
    public  Image getItemImage(){
        return itemImage;
    }

}
