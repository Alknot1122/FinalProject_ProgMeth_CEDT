package gameLogic;

import java.util.ArrayList;

public class RecipesRef {
    private ArrayList<Recipe> recipes;
    public RecipesRef(){
        recipes = new ArrayList<Recipe>();
        //All Ingredients total: 18
        Item blueberry = new Item ("Blueberry", "Item/Blueberry.png");
        Item butter = new Item ("Butter", "Item/Butter.png");
        Item chocolate = new Item ("Chocolate", "Item/Chocolate.png");
        Item cinnamon = new Item ("Cinnamon", "Item/Cinnamon.png");
        Item cocoaPowder = new Item ("Cocoa Powder", "Item/CocoPowder.png");
        Item cracker = new Item ("Cracker", "Item/Cracker.png");
        Item cream = new Item ("Cream", "Item/Cream.png");
        Item creamCheese = new Item ("Cream Cheese", "Item/CreamCheese.png");
        Item egg = new Item ("Egg", "Item/Egg.png");
        Item flour = new Item ("Flour", "Item/Flour.png");
        Item lemon = new Item ("Lemon", "Item/Lemon.png");
        Item magicColorDrop = new Item ("Magic Color Drop", "Item/MagicColorDrop.png");
        Item milk = new Item ("Milk", "Item/Milk.png");
        Item powderedSugar = new Item ("Powdered Sugar", "Item/PowderedSugar.png");
        Item raspberry = new Item ("Raspberry", "Item/Raspberry.png");
        Item strawberry = new Item ("Strawberry", "Item/Strawberry.png");
        Item sugar = new Item ("Sugar", "Item/Sugar.png");
        Item vanilla = new Item ("Vanilla", "Item/Vanilla.png");

        //All Foods total: 24
        Food animalBiscuit = new Food("Animal Biscuit", "Food/AnimalBiscuit.png", 60);
        Food blondie = new Food("Blondie", "Food/Blondie.png", 60);
        Food blueberryMuffin = new Food("Blueberry Muffin", "Food/BlueberryMuffin.png", 60);
        Food brownie = new Food("Brownie", "Food/Brownie.png", 50);
        Food cheesecake = new Food("Cheesecake", "Food/Cheesecake.png", 50);
        Food chocChipCookie = new Food("Choc-Chip Cookie", "Food/ChocChipCookie.png", 60);
        Food chocoBun = new Food("Chocobun", "Food/Chocobun.png", 60);
        Food chocolateCake = new Food("Chocolate Cake", "Food/ChocolateCake.png", 60);
        Food cinnamonRoll = new Food("Cinnamon Roll", "Food/CinnamonRoll.png", 60);
        Food cinnamonSugarPretzel = new Food("Cinnamon Sugar Pretzel", "Food/CinnamonSugarPretzel.png", 50);
        Food cupcake = new Food("Cupcake", "Food/Cupcake.png", 60);
        Food custardPie = new Food("CustardPie", "Food/CustardPie.png", 60);
        Food lemonMeringuePie = new Food("Lemon Meringue Pie", "Food/LemonMeringuePie.png", 50);
        Food lemonSandwichCookie = new Food("Lemon Sandwich Cookie", "Food/LemonSandwichCookie.png", 60);
        Food macaron = new Food("Macaron", "Food/Macaron.png", 50);
        Food melonBun = new Food("Melon Bun", "Food/MelonBun.png", 60);
        Food meringue = new Food("Meringue", "Food/Meringue.png", 40);
        Food mixedBerryPie = new Food("Mixed Berry Pie", "Food/MixedBerryPie.png", 60);
        Food quaso = new Food("Quaso", "Food/Quaso.png", 50);
        Food rainbowCakeRoll = new Food("Rainbow Cake Roll", "Food/RainbowCakeRoll.png", 50);
        Food raspberryDonut = new Food("RaspberryDonut", "Food/RaspberryDonut.png", 60);
        Food raspberryTart  = new Food("Raspberry Tart", "Food/RaspberryTart.png", 50);
        Food redVelvetCake = new Food("Red Velvet Cake", "Food/RedVelvetCake.png", 60);
        Food strawberryShortcake = new Food("Strawberry Shortcake", "Food/StrawberryShortCake.png", 60);

        //All recipes
        Recipe animalBiscuitR = new Recipe(animalBiscuit, new Item[]{butter, cinnamon, egg, flour, sugar, vanilla} );
        recipes.add(animalBiscuitR);
        Recipe blondieR = new Recipe(blondie, new Item[]{blueberry, butter, egg, flour, lemon, sugar} );
        recipes.add(blondieR);
        Recipe blueberryMuffinR = new Recipe(blueberryMuffin, new Item[]{blueberry, butter, egg, flour, milk, vanilla});
        recipes.add(blueberryMuffinR);
        Recipe brownieR = new Recipe(brownie, new Item[]{butter, chocolate, cocoaPowder, egg, flour});
        recipes.add(brownieR);
        Recipe cheesecakeR = new Recipe(cheesecake, new Item[]{cracker, cream, creamCheese, egg, vanilla});
        recipes.add(cheesecakeR);
        Recipe chocChipCookieR = new Recipe(chocChipCookie, new Item[]{butter, chocolate, egg, flour, sugar, vanilla});
        recipes.add(chocChipCookieR);
        Recipe chocoBunR = new Recipe(chocoBun, new Item[]{butter, chocolate, egg, flour, milk, sugar});
        recipes.add(chocoBunR);
        Recipe chocolateCakeR = new Recipe(chocolateCake, new Item[]{chocolate, cocoaPowder, egg, flour, milk, sugar});
        recipes.add(chocolateCakeR);
        Recipe cinnamonRollR = new Recipe(cinnamonRoll, new Item[]{butter, cinnamon, egg, flour, milk, powderedSugar});
        recipes.add(cinnamonRollR);
        Recipe cinnamonSugarPretzelR = new Recipe(cinnamonSugarPretzel, new Item[]{butter, cinnamon, flour, milk, sugar});
        recipes.add(cinnamonSugarPretzelR);
        Recipe cupcakeR = new Recipe(cupcake, new Item[]{butter, cream, egg, flour, sugar, vanilla});
        recipes.add(cupcakeR);
        Recipe custardPieR = new Recipe(custardPie, new Item[]{butter, egg, flour, milk, sugar, vanilla});
        recipes.add(custardPieR);
        Recipe lemonMeringuePieR = new Recipe(lemonMeringuePie, new Item[]{butter, egg, flour, lemon, powderedSugar});
        recipes.add(lemonMeringuePieR);
        Recipe lemonSandwichCookieR = new Recipe(lemonSandwichCookie, new Item[]{butter, creamCheese, egg, flour, lemon, powderedSugar});
        recipes.add(lemonSandwichCookieR);
        Recipe macaronR = new Recipe(macaron, new Item[]{cream, egg, flour, magicColorDrop, powderedSugar});
        recipes.add(macaronR);
        Recipe melonBunR= new Recipe(melonBun, new Item[]{butter, egg, flour, milk, sugar, vanilla});
        recipes.add(melonBunR);
        Recipe meringueR = new Recipe(meringue, new Item[]{egg, magicColorDrop, powderedSugar, sugar});
        recipes.add(meringueR);
        Recipe mixedBerryPieR = new Recipe(mixedBerryPie, new Item[]{blueberry, butter, flour, raspberry, strawberry, sugar});
        recipes.add(mixedBerryPieR);
        Recipe quasoR = new Recipe(quaso, new Item[]{butter, egg, flour, milk, sugar});
        recipes.add(quasoR);
        Recipe rainbowCakeRollR = new Recipe(rainbowCakeRoll, new Item[]{cream, egg, flour, magicColorDrop, vanilla});
        recipes.add(rainbowCakeRollR);
        Recipe raspberryDonutR = new Recipe(raspberryDonut, new Item[]{butter, egg, flour, powderedSugar, raspberry, vanilla});
        recipes.add(raspberryDonutR);
        Recipe raspberryTartR = new Recipe(raspberryTart, new Item[]{butter, cracker, egg, raspberry, sugar});
        recipes.add(raspberryTartR);
        Recipe redVelvetCakeR = new Recipe(redVelvetCake, new Item[]{cocoaPowder, creamCheese, egg, flour, magicColorDrop, milk});
        recipes.add(redVelvetCakeR);
        Recipe strawberryShortcakeR = new Recipe(strawberryShortcake, new Item[]{cream, egg, flour, milk, sugar, vanilla});
        recipes.add(strawberryShortcakeR);

    }

    public ArrayList<Recipe> getRecipes() {
        return recipes;
    }

}
