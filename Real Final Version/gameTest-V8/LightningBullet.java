import greenfoot.*;

public class LightningBullet extends Actor
{
    private GreenfootImage[] frames;
    private int frame = 0;
    private int delay = 3;
    private int delayCounter = 0;
    private int speed = 10;

    private static int lightningCount = 0;
    private boolean counted = false;

    public LightningBullet()
    {
        frames = new GreenfootImage[4];
        for(int i = 0; i < 4; i++) {
            frames[i] = new GreenfootImage("lightningBullet/lightning_skill1_frame" + (i + 1) + ".png");
        }
        setImage(frames[0]);
    }

    public void act()
    {
        if (!counted && getWorld() != null) {
            counted = true;
            countShot();
        }
        animate();
        move(speed);
        if (isAtEdge()) {
            getWorld().removeObject(this);
        }
    }

    private void animate()
    {
        delayCounter++;
        if (delayCounter < delay) {
            return;
        }
        delayCounter = 0;
        frame++;
        if(frame >= frames.length) {
            frame = 0;
        }
        setImage(frames[frame]);
    }

    private void countShot()
    {
        lightningCount++;

        if (lightningCount >= 5) {
            lightningCount = 0;
            strikeEnemy();
        }
    }

    private void strikeEnemy()
    {
        if (getWorld() == null) {
            return;
        }
        java.util.List<Minotaur> targets = getWorld().getObjects(Minotaur.class);

        if (targets.isEmpty()) {
            return;
        }
        Minotaur closest = null;
        double bestDist = Double.MAX_VALUE;

        for (Minotaur m : targets) {
            double dx = getX() - m.getX();
            double dy = getY() - m.getY();
            double d  = Math.sqrt(dx*dx + dy*dy);
            if (d < bestDist) {
                bestDist = d;
                closest = m;
            }
        }

        if (closest == null) {
            return;
        }
        LightningStrike strike = new LightningStrike(closest);
        getWorld().addObject(strike, closest.getX(), closest.getY());
    }
}
