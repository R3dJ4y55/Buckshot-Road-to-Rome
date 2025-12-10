import greenfoot.*;

public class FadeOverlay extends Actor
{
    private int alpha;
    private boolean fadeIn = false;
    private boolean fadeOut = false;
    private boolean finished = false;

    public FadeOverlay(boolean startFadeIn)
    {
        setImage(new GreenfootImage(800, 600));
        getImage().setColor(Color.BLACK);

        if (startFadeIn)
        {
            alpha = 255;
            fadeIn = true;
        }
        else
        {
            alpha = 0;
        }

        updateImage();
    }

    public void act()
    {
        if (fadeIn)
        {
            alpha -= 6;
            if (alpha <= 0)
            {
                alpha = 0;
                fadeIn = false;
            }
            updateImage();
        }

        else if (fadeOut)
        {
            alpha += 6;
            if (alpha >= 255)
            {
                alpha = 255;
                fadeOut = false;
                finished = true;
            }
            updateImage();
        }
    }

    private void updateImage()
    {
        GreenfootImage img = getImage();
        img.setColor(new Color(0,0,0,alpha));
        img.fill();
    }

    public void startFadeOut()
    {
        fadeOut = true;
    }

    public boolean isFadingOut()
    {
        return fadeOut;
    }

    public boolean isFinished()
    {
        return finished;
    }
}
