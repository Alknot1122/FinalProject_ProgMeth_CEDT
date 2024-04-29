package gameLogic;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Item {

    private final String imagePath;

   private Image itemImage;
    private String itemName;

    public Item (String name , String imagePath){
        itemName = name;




        this.imagePath = imagePath;

        String   classLoaderPath = ClassLoader.getSystemResource(imagePath).toString();


        itemImage = new Image(classLoaderPath);

    }

    public String getImagePath() {
        return imagePath;
    }

    public  Item (Item item){
        this.imagePath = item.getImagePath();
        this.itemName = item.getItemName();
        this.itemImage = item.getItemImage();



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
