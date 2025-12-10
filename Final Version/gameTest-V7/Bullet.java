import greenfoot.*;  

public class Bullet extends Actor
{
    protected GreenfootImage[] bulletFrames;
    protected int frame;
    protected int animationDelay;
    protected int delayCount;
    protected int speed;
    private int damage;  

    public Bullet(int speed, int damage)
    {
        bulletFrames = new GreenfootImage[10];
        for (int i = 0; i < 10; i++) {
            bulletFrames[i] = new GreenfootImage("bulletAnimations/bullet" + (i+1) + ".png");
        }
        frame = 0;
        animationDelay = 3;  
        delayCount = 0;
        this.speed = speed;
        this.damage = damage; 

        setImage(bulletFrames[0]); 
    }

    public int getDamage() 
    {
        return damage;
    }

    public void act()
    {
        animate();
        move(speed);
        if (checkCollision()) return;
        if (checkEdge()) return;     
    }

    protected void animate()
    {
        delayCount++;
        if (delayCount < animationDelay) return;
        delayCount = 0;

        setImage(bulletFrames[frame]);
        frame++;
        if (frame >= bulletFrames.length) {
            frame = 0;
        }
    }

    private boolean checkCollision()
    {
        Minotaur m = (Minotaur)getOneIntersectingObject(Minotaur.class);
        if (m != null)
        {
            m.takeDamage(damage);  
            explode();
            getWorld().removeObject(this);

            return true;
        }
        return false;
    }

    private boolean checkEdge()
    {
        if (isAtEdge()) {
            explode();
            getWorld().removeObject(this);
            return true;
        }
        return false;
    }

    protected void explode()
    {
        Explosion boom = new Explosion();
        getWorld().addObject(boom, getX(), getY());
    }
}