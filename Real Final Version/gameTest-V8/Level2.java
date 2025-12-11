import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Write a description of class Level2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Level2 extends World
{

    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public Level2()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1320, 400, 1); 
        prepare();
    }
    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        Player player = new Player();
        addObject(player,318,280);
        spawnMinotaur();
    }
    
    public void spawnMinotaur()
    {
        if (!getObjects(Minotaur.class).isEmpty()) {
            return;
        }
        Minotaur m = new Minotaur();
        if (Greenfoot.getRandomNumber(2) == 0) {
            addObject(new Minotaur(), 50, getHeight() - 1);
        } else {
            addObject(new Minotaur(), getWidth() - 50, getHeight() - 1);
        }
        addObject(new Minotaur(), getWidth() - 300, getHeight() - 1);
        addObject(new Minotaur(), getWidth() - 200, getHeight() - 1);
    }
    public void act()
    {
        Label coins;
        List labels = getObjects(Label.class);
        removeObjects(labels);
        
        int xPos = (Player.getGold() < 10) ? 50 : 60;
        
        coins = new Label("Gold Coins: " + Player.getGold(), 20, Color.BLACK);
        addObject(coins, xPos, 20);
    }
}
