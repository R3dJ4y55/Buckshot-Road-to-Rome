import greenfoot.*;

public class StartWorld extends World
{
    private PressAnyKeyText prompt;
    private FadeOverlay fade;

    public StartWorld()
    {    
        super(800, 600, 1);

        setBackground(new GreenfootImage("startBackground.png"));

        prompt = new PressAnyKeyText();
        addObject(prompt, getWidth()/2, getHeight() - 120);

        fade = new FadeOverlay(true);   // TRUE = fade in
        addObject(fade, getWidth()/2, getHeight()/2);
    }

    public void act()
    {
        spawnFog();

        if ((Greenfoot.mousePressed(null) || Greenfoot.getKey() != null)
            && !fade.isFadingOut())
        {
            fade.startFadeOut();
        }

        if (fade.isFinished())
        {
            Greenfoot.setWorld(new MenuWorld());
        }
    }

    private void spawnFog()
    {
        if (Greenfoot.getRandomNumber(12) == 0)
        {
            addObject(new FogParticle(),
                      Greenfoot.getRandomNumber(getWidth()),
                      Greenfoot.getRandomNumber(getHeight()));
        }
    }
}
