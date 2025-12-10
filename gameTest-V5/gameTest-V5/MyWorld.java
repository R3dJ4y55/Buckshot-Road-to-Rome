import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{

    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1320, 440, 1); 
        prepare();
    }
    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        Player player = new Player();
        addObject(player,158,280);
        spawnMinotaur();
    }
    
    public void spawnMinotaur()
    {
        if (!getObjects(Minotaur.class).isEmpty()) return;
        Minotaur m = new Minotaur();
        int y = 290;
        boolean spawnLeft = Greenfoot.getRandomNumber(2) == 0;
        if (spawnLeft)
            addObject(m, 50, y);
        else
            addObject(m, getWidth() - 50, y);
    }

}
