package gameLogic;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Item {
    private final Image itemImage ;
    private final String itemName;

    public Item (String name , String imagePath){
        itemName = name;
        String classLoaderPath = ClassLoader.getSystemResource(imagePath).toString();
        itemImage = new Image(classLoaderPath);
    }

    public ImageView getItemImage(int fitHight) {
        ImageView image = new ImageView(itemImage);
        image.setFitHeight(fitHight);
        image.setPreserveRatio(true);
        return image;
    }

    public String getItemName() {
        return itemName;
    }
}
