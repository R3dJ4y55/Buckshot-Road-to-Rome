import greenfoot.*;

public class StartWorld extends World
{
    private PressAnyKeyText prompt;
    private FadeOverlay fade;

    public StartWorld()
    {    
        super(643, 360, 1);
        setPaintOrder(FadeOverlay.class, PressAnyKeyText.class, LootParticle.class);
        setBackground(new GreenfootImage("startBackground.png"));

        prompt = new PressAnyKeyText();
        addObject(prompt, getWidth()/2, getHeight() - 120);

        fade = new FadeOverlay(true);
        addObject(fade, getWidth()/2, getHeight()/2);
    }

    public void act()
    {
        spawnParticle();

        if ((Greenfoot.mousePressed(null) || Greenfoot.getKey() != null)
            && !fade.isFadingOut())
        {
            Greenfoot.playSound("coins2.mp3");
            fade.startFadeOut();
        }

        if (fade.isFinished())
        {
            Greenfoot.setWorld(new MenuWorld());
        }
    }

    private void spawnParticle() {
        if (Greenfoot.getRandomNumber(12) == 0) {
            Actor particle;
            if (Greenfoot.getRandomNumber(20) == 0) {
            particle = new LootParticle("StartWorldParticles/sword.png");
            } else if (Greenfoot.getRandomNumber(30) == 0) {
                particle = new LootParticle("StartWorldParticles/gladiatorHelmet.png");
            } else {
                particle = new LootParticle("StartWorldParticles/goldCoin.png");
            }
            addObject(particle, -40, Greenfoot.getRandomNumber(getHeight()));
        }
    }
}
