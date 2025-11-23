import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Vector;

/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player extends Actor
{
    private final double SHOT_STRENGTH = 15;
    private final Vector2D GRAVITY = new Vector2D(0.0, -0.5);
    private final Vector2D AIR_RESISTANCE = new Vector2D(-0.5, 0.0);
    
    private Vector2D velocity;
    private Vector2D movement;
    private MouseInfo m = Greenfoot.getMouseInfo();
    private boolean shooting = false;
    private int timer;
    
    
    public Player(){
        velocity = new Vector2D(0.0, 0.0);
        movement = new Vector2D(0.0, 0.0);
    }
    /**
     * Act - do whatever the Player wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        updateMouseInfo();
        updateVelocity();
        shoot();
        move();
        timer--;
    }
    private void updateMouseInfo(){
        m = Greenfoot.getMouseInfo();
    }
    
    private boolean checkShooting(){
        if (timer > 0){
            shooting = true;
        }
        else {
            shooting = false;
        }
        return shooting;
    }
    
    public void shoot(){
        if (m == null){
            
        }
        if (m != null){
            if (Greenfoot.mouseClicked(null) && !checkShooting())
            {
            timer = 30;
            Vector2D playerToMouse = new Vector2D(m.getX() - getX(), m.getY() - getY());
            
            double magnitude = Math.pow((Math.pow(playerToMouse.getX(), 2) + Math.pow(playerToMouse.getY(), 2)), 0.5);
            Vector2D shotVector = new Vector2D(playerToMouse.getX() / magnitude, playerToMouse.getY() / magnitude);
            
            velocity = velocity.substract(shotVector.multiply(SHOT_STRENGTH));
            }
        }
    }
    
    private void updateVelocity(){
        
        // Gravity (y)
        if (getY() < 500)
        {
        velocity = velocity.substract(GRAVITY);
        } else{
            velocity.setY(0);
            setLocation(getX(), 500);
        }
        
        // Air Resistance (x)
        if (m != null){
            if (this.getX() > 60 || this.getX() < 840){
                if (m.getX() < getX() && velocity.getX() > 0){
                    velocity.add(AIR_RESISTANCE);
                    if (velocity.getX() < 0){
                        velocity.setX(0);
                    }
                }
                else if (m.getX() > getX() && velocity.getX() < 0){
                    velocity.substract(AIR_RESISTANCE);
                    if (velocity.getX() > 0){
                        velocity.setX(0);
                    }
                }
            } 
            else {
                if (getX() < 200){
                    velocity.setX(0);
                    setLocation(60, getY());
                }
                else if (getX() > 700){
                    velocity.setX(0);
                    setLocation(840, getY());
                }
                System.out.println("-----");
            }
            if (Greenfoot.getRandomNumber(5) == 3){
            System.out.printf("\n X: %d \n Y: %d", (int) velocity.getX(), (int) velocity.getY());
            }
        }
        
    }
    
    public void move(){
        double x = getX() + velocity.getX();
        double y = getY() + velocity.getY();
        setLocation( (int) x, (int) y);
    }
}
