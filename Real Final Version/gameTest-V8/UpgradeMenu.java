import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class UpgradeMenu here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class UpgradeMenu extends World
{
    private Button goldBonusButton;
    private Button healthButton;
    private Button defenseButton;
    /**
     * Constructor for objects of class UpgradeMenu.
     * 
     */
    public UpgradeMenu()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1); 
        
        GreenfootImage bg = new GreenfootImage(597, 197);
        bg.setColor(Color.LIGHT_GRAY);
        bg.fill();
        setBackground(bg);
        
        goldBonusButton = new Button("Click to Upgrade GOLD BONUS");
        healthButton = new Button("Click to Upgrade HEALTH");
        defenseButton = new Button("Click to Upgrade DEFENSE");
        
        addObject(goldBonusButton, getWidth()/2, 100);
        addObject(healthButton, getWidth()/2, 300);
        addObject(defenseButton, getWidth()/2, 200);
    }
    public void act()
    {
        if (Greenfoot.mousePressed(goldBonusButton)) {
            Player.addToGoldBonus(0.05);
            Greenfoot.playSound("coins2.mp3");
            Greenfoot.setWorld(new Level2());
        }
        if (Greenfoot.mousePressed(healthButton)) {
            Player.increaseMaxHealth();
            Greenfoot.playSound("coins2.mp3");
            Greenfoot.setWorld(new Level2());
        }
        if (Greenfoot.mousePressed(defenseButton)) {
            Player.addToDefense(-0.15);
            Greenfoot.playSound("coins2.mp3");
            Greenfoot.setWorld(new Level2());
        }
    }
}
