import greenfoot.*;  

public class Explosion extends Actor
{
    private GreenfootImage[] explosionFrames;
    private int frame;
    private int animationDelay;
    private int delayCount;
    
    public Explosion()
    {
        explosionFrames = new GreenfootImage[40];
        for (int i = 0; i < 40; i++) {
            explosionFrames[i] = new GreenfootImage("explosionStars/explosion" + i + ".png");
        }
        frame = 0;
        animationDelay = 2; 
        delayCount = 0;
        setImage(explosionFrames[0]);
    }
    
    public void act()
    {
        animate();
        
    }
    
    private void animate()
    {
        delayCount++;
        if (delayCount < animationDelay) return;
        delayCount = 0;
        
        if (frame < explosionFrames.length) {
            setImage(explosionFrames[frame]);
            frame++;
        } else {
            getWorld().removeObject(this);
        }
    }
}