package gameLogic;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Item {

    private final String IMAGE_PATH;

   private final Image ITEM_IMAGE;
    private final String ITEM_NAME;

    public Item (String name , String imagePath){
        ITEM_NAME = name;
        this.IMAGE_PATH = imagePath;
        String   classLoaderPath = ClassLoader.getSystemResource(imagePath).toString();
        ITEM_IMAGE = new Image(classLoaderPath);

    }

    public String getImagePath() {
        return IMAGE_PATH;
    }

    public  Item (Item item){
        this.IMAGE_PATH = item.getImagePath();
        this.ITEM_NAME = item.getItemName();
        this.ITEM_IMAGE = item.getItemImage();
    }

    public ImageView getItemImageView(int fitHeight) {
        ImageView image = new ImageView(getItemImage());
        image.setFitHeight(fitHeight);
        image.setPreserveRatio(true);
        return image;
    }

    public String getItemName() {
        return ITEM_NAME;
    }
    public  Image getItemImage(){
        return ITEM_IMAGE;
    }

}
