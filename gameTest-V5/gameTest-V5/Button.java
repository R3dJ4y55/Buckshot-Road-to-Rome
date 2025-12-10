import greenfoot.*;

public class Button extends Actor
{
    private String action;
    private GreenfootImage normalImage;
    private GreenfootImage hoverImage;

    public Button(String text)
    {
        action = text;
        normalImage = new GreenfootImage(text, 40, Color.WHITE, Color.DARK_GRAY);
        hoverImage = new GreenfootImage(text, 40, Color.YELLOW, Color.DARK_GRAY);
        setImage(normalImage);
    }

    @Override
    public void act()
    {
        MouseInfo mouse = Greenfoot.getMouseInfo();
        boolean mouseOver = false;

        if (mouse != null)
        {
            mouseOver = Math.abs(mouse.getX() - getX()) < getImage().getWidth()/2 &&
                        Math.abs(mouse.getY() - getY()) < getImage().getHeight()/2;
        }
        if (mouseOver)
        {
            setImage(hoverImage);
            if (!getWorld().getObjects(MenuPlayer.class).isEmpty())
            {
                MenuPlayer player = getWorld().getObjects(MenuPlayer.class).get(0);
                player.aimAt(this); 
            }
        } else {
            setImage(normalImage);
        }

        if (Greenfoot.mouseClicked(this))
        {
            if (!getWorld().getObjects(MenuPlayer.class).isEmpty())
            {
                MenuPlayer player = getWorld().getObjects(MenuPlayer.class).get(0);
                player.shootAtTarget(this);
            }
        }
    }

    public void performClick()
    {
        if (action.equals("START GAME"))
            Greenfoot.setWorld(new MyWorld());
        else if (action.equals("INSTRUCTIONS"))
            Greenfoot.setWorld(new InstructionsWorld());
        else if (action.equals("EXIT"))
            Greenfoot.stop();
    }
}
