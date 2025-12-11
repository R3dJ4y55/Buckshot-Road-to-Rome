import greenfoot.*;

public class MenuBullet extends Bullet
{
    private Actor targetButton;

    public MenuBullet(int speed, int damage, Actor target)
    {
        super(speed, damage);
        this.targetButton = target;
    }

    @Override
    public void act()
    {
        animate();

        if (getWorld() == null || targetButton == null) {
            return;
        }

        double dx = targetButton.getX() - getX();
        double dy = targetButton.getY() - getY();
        double distance = Math.sqrt(dx*dx + dy*dy);
        setRotation((int)Math.toDegrees(Math.atan2(dy, dx)));

        if (distance > speed) {
            setLocation(getX() + (int)(dx / distance * speed), getY() + (int)(dy / distance * speed));
        } else {
            setLocation(targetButton.getX(), targetButton.getY());
            Greenfoot.playSound("button.mp3");
            MenuExplosion boom = new MenuExplosion(targetButton);
            getWorld().addObject(boom, getX(), getY());
            if (getWorld() != null) {
                getWorld().removeObject(this);
            }
        }
    }
}