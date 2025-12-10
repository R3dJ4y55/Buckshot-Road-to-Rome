import greenfoot.*;

public class LootParticle extends Actor
{
    private int speed;
    private GreenfootImage baseImage;
    private int rotationSpeed;

    public LootParticle(String imageFile)
    {
        baseImage = new GreenfootImage(imageFile);

        int size = Greenfoot.getRandomNumber(30) + 40;
        baseImage.scale(size, size);
        setImage(baseImage);

        speed = Greenfoot.getRandomNumber(2) + 1;  
        rotationSpeed = Greenfoot.getRandomNumber(3)+1; 
    }

    public void act()
    {
        setLocation(getX() + speed, getY());

        turn(rotationSpeed);

        int worldWidth = getWorld().getWidth();
        int alpha = 255 - (int)((double)getX() / worldWidth * 255);
        alpha = Math.max(0, Math.min(255, alpha));

        GreenfootImage faded = new GreenfootImage(baseImage);
        faded.rotate(getRotation());
        faded.setTransparency(alpha);
        setImage(faded);
        
        int halfWidth = getImage().getWidth() / 2;

        if (getX() - halfWidth > worldWidth)
        {
            getWorld().removeObject(this);
        }
    }
}
