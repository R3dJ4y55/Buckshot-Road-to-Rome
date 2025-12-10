import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class GameOverWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GameOverWorld extends World
{
    TaxCollector tc = new TaxCollector();

    /**
     * Constructor for objects of class GameOverWorld.
     * 
     */
    public GameOverWorld()
    {    
        super(600, 200, 1); 

        GreenfootImage bg = new GreenfootImage(597, 197);
        bg.setColor(Color.RED);
        bg.fill();
        setBackground(bg);
        
        addObject(new Label("GAME OVER", 100, Color.BLACK), getWidth()/2, getHeight()/2);
        
        addObject (tc, 0, (getHeight()) - 27);
    }
    
    public void act(){
        tc.move(2);
        if (tc.isAtEdge()) { removeObject(tc); Greenfoot.stop();}
        
    }
}
