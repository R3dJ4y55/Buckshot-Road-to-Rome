import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{

    private int playerKillsOnEnter;
    
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1320, 400, 1); 
        prepare();
        Player.setHealth(Player.getMaxHealth());
        playerKillsOnEnter = Player.getKills();
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
        if (!getObjects(Minotaur.class).isEmpty()) return;

        if (Greenfoot.getRandomNumber(2) == 0)
            addObject(new Minotaur(), 50, getHeight() - 1);
        else
            addObject(new Minotaur(), getWidth() - 50, getHeight() - 1);
    }
    public void act()
    {
        Label coins;
        List labels = getObjects(Label.class);
        removeObjects(labels);
        
        int xPos = (Player.getGold() < 10) ? 50 : 60;
        
        coins = new Label("Gold Coins: " + Player.getGold(), 20, Color.BLACK);
        addObject(coins, xPos, 20);
        
        if (Player.getKills() >= (playerKillsOnEnter + 1)) Player.victory();
    }
}
