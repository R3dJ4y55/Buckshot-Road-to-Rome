import greenfoot.*;

public class MenuExplosion extends Actor
{
    private GreenfootImage[] frames;
    private int frame = 0;
    private int delay = 2;
    private int counter = 0;
    private Button buttonToClick;

    public MenuExplosion(Button button)
    {
        this.buttonToClick = button;

        frames = new GreenfootImage[40];
        for (int i = 0; i < 40; i++) {
            frames[i] = new GreenfootImage("explosionStars/explosion" + i + ".png");
        }
        setImage(frames[0]);
    }

    @Override
    public void act()
    {
        counter++;
        if (counter < delay) return;
        counter = 0;

        frame++;
        if (frame < frames.length)
        {
            setImage(frames[frame]);
        }
        else
        {
            if (getWorld() != null)
                getWorld().removeObject(this);

            if (buttonToClick != null)
                buttonToClick.performClick();
        }
    }
}
