import greenfoot.*;

public class TransitionWorld extends World
{
    private int timer = 0;
    private int fadeAlpha = 0;
    private boolean fading = false;
    private GreenfootImage fadeOverlay;

    public TransitionWorld()
    {
        super(643, 360, 1);

        GreenfootImage bg = new GreenfootImage(getWidth(), getHeight());
        bg.setColor(Color.BLACK);
        bg.fill();
        setBackground(bg);

        addObject(new Label("BunchOfDudes Studio", 28), getWidth()/2, 160);
        addObject(new Label("Sponsored by Vanier", 18), getWidth()/2, 200);

        fadeOverlay = new GreenfootImage(getWidth(), getHeight());
        fadeOverlay.setColor(new Color(0, 0, 0, 0));
        fadeOverlay.fill();
    }

    @Override
    public void act()
    {
        timer++;
        if (timer >= 180)
            fading = true;

        if (fading)
        {
            fadeAlpha += 2;
            if (fadeAlpha > 255) fadeAlpha = 255;

            fadeOverlay.setColor(new Color(0, 0, 0, fadeAlpha));
            fadeOverlay.fill();

            getBackground().drawImage(fadeOverlay, 0, 0);

            if (fadeAlpha >= 255)
            {
                Greenfoot.setWorld(new StartWorld());
            }
        }
    }
}
