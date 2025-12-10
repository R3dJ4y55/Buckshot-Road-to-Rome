import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Minotaur here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import greenfoot.*;

public class Minotaur extends Actor
{
    private GreenfootImage[] walkFrames = new GreenfootImage[12];
    private int frame = 0;
    private int animDelay = 5;
    private int animCounter = 0;

    private int speed = 1;
    private boolean flipped = false;

    public Minotaur()
    {
        for (int i = 0; i < 12; i++) {
            walkFrames[i] = new GreenfootImage("Minotaur/MinotaurWalk/MinoWalk" + (i+1) + ".png");
        }
        setImage(walkFrames[0]);
    }

    public void act()
    {
        chasePlayer();
        animateWalk();
    }

    private void chasePlayer()
    {
        Player p = getWorld().getObjects(Player.class).get(0);
        if (p == null) return;
        int playerX = p.getX();
        if (playerX < getX()) {
            setLocation(getX() - speed, getY());
            flipped = true;
        } else {
            setLocation(getX() + speed, getY());
            flipped = false;
        }
    }

    private void animateWalk()
    {
        animCounter++;
        if (animCounter < animDelay) return;
        animCounter = 0;
        frame = (frame + 1) % walkFrames.length;
        GreenfootImage img = new GreenfootImage(walkFrames[frame]);
        if (flipped) img.mirrorHorizontally();

        setImage(img);
    }
}