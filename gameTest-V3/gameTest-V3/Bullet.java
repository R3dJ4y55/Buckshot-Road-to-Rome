import greenfoot.*;  

public class Bullet extends Actor
{
    private GreenfootImage[] bulletFrames;
    private int frame;
    private int animationDelay;
    private int delayCount;
    private int speed;
    
    public Bullet(int speed)
    {
        // Load bullet frames (10 images)
        bulletFrames = new GreenfootImage[10];
        for (int i = 0; i < 10; i++) {
            bulletFrames[i] = new GreenfootImage("bulletAnimations/bullet" + (i+1) + ".png");
        }
        
        frame = 0;
        animationDelay = 3;  
        delayCount = 0;
        this.speed = speed;
        setImage(bulletFrames[0]); 
    }
    
    public void act()
    {
        animate();
        move(speed);
        checkEdge();
    }
    
    private void animate()
    {
        delayCount++;
        if (delayCount < animationDelay) return;
        delayCount = 0;
        
        setImage(bulletFrames[frame]);
        frame++;
        if (frame >= bulletFrames.length) {
            frame = 0; // loop back
        }
    }
    
    private void checkEdge()
    {
        if (isAtEdge()) {
            explode();   // ?play explosion when bullet disappears
            getWorld().removeObject(this);
        }
    }
    
    private void explode()
    {
        Explosion boom = new Explosion();
        getWorld().addObject(boom, getX(), getY());
    }
}