import greenfoot.*;

public class FogParticle extends Actor
{
    private int speed;
    private int alpha;

    public FogParticle()
    {
        int size = Greenfoot.getRandomNumber(60) + 40;
        alpha = Greenfoot.getRandomNumber(60) + 30;
        GreenfootImage img = new GreenfootImage(size, size);
        img.setColor(new Color(200,200,200,alpha));
        img.fillOval(0, 0, size, size);
        setImage(img);

        speed = Greenfoot.getRandomNumber(2) + 1;
    }

    public void act()
    {
        setLocation(getX() + speed, getY());
        int halfWidth = getImage().getWidth() / 2;

        if (getX() - halfWidth > getWorld().getWidth())
        {
            getWorld().removeObject(this);
        }
    }
}
