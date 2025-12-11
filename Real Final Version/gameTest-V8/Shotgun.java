import greenfoot.*;

public class Shotgun extends Actor
{
    public Shotgun()
    {
        setImage("playerIdle.png"); 
    }

    public void hoverAt(Actor button)
    {
        if (button != null) {
            int offsetX = -50;
            int offsetY = 0;
            setLocation(button.getX() + offsetX, button.getY() + offsetY);
        }
    }
}
