import greenfoot.*;

public class LightningStrike extends Actor
{
    private GreenfootImage[] frames;
    private int frame = 0;
    private int delay = 3;
    private int delayCounter = 0;

    private Minotaur target;
    private boolean hit = false;

    public LightningStrike(Minotaur target)
    {
        this.target = target;

        frames = new GreenfootImage[5];
        for(int i = 0; i < 5; i++)
        {
            frames[i] = new GreenfootImage(
                "lightningStrike/lightning_skill4_frame" + (i + 1) + ".png"
            );
        }

        setImage(frames[0]);
    }

    public void act()
    {
        animate();
    }

    private void animate()
    {
        delayCounter++;
        if (delayCounter < delay) return;

        delayCounter = 0;

        if (target != null && target.getWorld() != null)
        {
            setLocation(target.getX(), target.getY());
        }

        if (!hit && frame == 3)
        {
            Greenfoot.playSound("lightningStrike.mp3");
            applyDamage();
            hit = true;
        }

        setImage(frames[frame]);
        frame++;

        if (frame >= frames.length)
        {
            getWorld().removeObject(this);
        }
    }

    private void applyDamage()
    {
        if (target == null || target.getWorld() == null)
            return;

        target.takeDamage(999);
    }
}
