import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class UpgradeSystem here.
 * 
 * 
 */
public class UpgradeSystem extends Actor
{
    private static int maxAmmo = 5;
    private static double moneyPerKill = 5.0;
    /**
     * Act - do whatever the UpgradeSystem wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
    }
    
    public void upgradeMaxAmmo()
    {
        maxAmmo += 1;
    }
    
    public void upgradeMoneyReturnsPerKill()
    {
        moneyPerKill += (moneyPerKill * 0.15);
    }
}
