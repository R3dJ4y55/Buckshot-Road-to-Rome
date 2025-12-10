import greenfoot.*;

public class MenuPlayer extends Actor
{
    public MenuPlayer()
    {
        setImage("playerAnimations/Idle/playerIdle.png");
    }

    public void shootAtTarget(Button button)
    {
        if (button != null && getWorld() != null)
        {
            MenuBullet b = new MenuBullet(10, 1, button);
            getWorld().addObject(b, getX(), getY());
        }
    }
    
    public void aimAt(Button button) {
        if (button != null)
            turnTowards(button.getX(), button.getY());
        }
}

