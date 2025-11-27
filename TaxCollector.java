import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
/**
 * The enemy.
 * 
 * @author R3dJ4Y55 
 * @version 1
 */
public class TaxCollector extends Actor
{
    private Vector2D directionToPlayer;
    private Vector2D velocity = new Vector2D(0,0);;
    private List<Player> p;
    private Player player;
    
    public TaxCollector(){
 
    }
    /**
     * Act - do whatever the TaxCollector wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        updatePlayer();
        if (player != null){
            followPlayer();
        }
    }
    
    private void updatePlayer()
    {
        if(getObjectsInRange(1000, Player.class) != null)
        {
            p = getObjectsInRange(1000, Player.class);
            player = p.get(0);
        }
    }
    
    private Vector2D getAndUpdateDirectionToPlayer()
    {
            directionToPlayer = new Vector2D(player.getX() - getX(), player.getY() - getY());
            double magnitude = Math.pow(Math.pow(directionToPlayer.getX(), 2) + Math.pow(directionToPlayer.getY(), 2), 0.5);
            directionToPlayer.divide(magnitude);
            return directionToPlayer;
    }
    
    private void followPlayer()
    {
        getAndUpdateDirectionToPlayer();
        
        //figures out if the player is to the right or left of the enemy
        double directionX = directionToPlayer.getX();
        directionX *= 10;
        
        boolean toLeft = directionX < 0;
        boolean toRight = directionX > 0;
        if (toLeft){
            velocity.setX(-4);
        } else if(toRight){
            velocity.setX(4);
        } else{
            velocity.setX(0);
        }
        
        // movement
    }
}
