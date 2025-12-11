import greenfoot.*;

public class Label extends Actor
{
    public Label(String text, int fontSize, Color textColor)
    {
        GreenfootImage img = new GreenfootImage(text, fontSize, textColor, new Color(0,0,0,0));
        setImage(img);
    }
    
    public Label(String text, int fontSize)
    {
        this(text, fontSize, Color.WHITE);
    }
}
