import greenfoot.*;

public class PressAnyKeyText extends Actor
{
    private int alpha = 0;
    private boolean fadingIn = true;
    private double pulse = 0;

    public PressAnyKeyText()
    {
        updateImage();
    }

    public void act()
    {
        animateFade();
        pulse += 0.08;
        updateImage();
    }

    private void animateFade()
    {
        if (fadingIn) {
            alpha += 5;
            if (alpha >= 255) {
                alpha = 255;
                fadingIn = false;
            }
        }
    }

    private void updateImage()
    {
        String text = "PRESS ANY KEY";
        int fontSize = 36 + (int)(Math.sin(pulse) * 3);
        GreenfootImage img = new GreenfootImage(text, fontSize, new Color(255, 255, 255, alpha), new Color(0,0,0,0));
        setImage(img);
    }
}
