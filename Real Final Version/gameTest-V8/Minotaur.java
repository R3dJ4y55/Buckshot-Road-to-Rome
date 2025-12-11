import greenfoot.*;

public class Minotaur extends Actor
{
    private int health = 5;    
    private int speed = 1;
    private int attackRange = 40;
    private int frameDelay = 6; 
    private GreenfootImage[] walk = new GreenfootImage[12];
    private GreenfootImage[] hurt = new GreenfootImage[3];
    private GreenfootImage[] attack = new GreenfootImage[5];
    private GreenfootImage[] dead = new GreenfootImage[5];
    private GreenfootImage[] walkFlip;
    private GreenfootImage[] hurtFlip;
    private GreenfootImage[] attackFlip;
    private GreenfootImage[] deadFlip;
    private int frame = 0;
    private int delayCounter = 0;
    private enum State { 
        WALK, HURT, ATTACK, DEAD 
    }
    private State state = State.WALK;
    private boolean flipped = false;

    public Minotaur()
    {
        loadFrames(walk, "Minotaur/MinotaurWalk/MinoWalk", 12);
        loadFrames(hurt, "Minotaur/MinotaurHurt/MinoHurt", 3);
        loadFrames(attack, "Minotaur/MinotaurAttack/MinoAttack", 5);
        loadFrames(dead, "Minotaur/MinotaurDead/MinoDead", 5);
        walkFlip = mirrorFrames(walk);
        hurtFlip = mirrorFrames(hurt);
        attackFlip = mirrorFrames(attack);
        deadFlip = mirrorFrames(dead);
        setImage(walk[0]);
    }
    private void clampToGround() {
        if (getWorld() == null) {
            return;
        }
        int groundY = getWorld().getHeight() - getImage().getHeight() / 2;
        if (getY() > groundY) {
            setLocation(getX(), groundY);
        }
    }

    public void act()
    {
        clampToGround();
        if (state != State.DEAD) {
            checkHit();
        }
        if (state == State.HURT) {
            animate(hurt, hurtFlip, State.WALK);
            return;
        }
        if (state == State.ATTACK) {
            animate(attack, attackFlip, State.WALK);
            return;
        }
        if (state == State.DEAD) {
            animate(dead, deadFlip, null);
            return;
        }
        checkAttackRange();
        chasePlayer();
        animate(walk, walkFlip, State.WALK);
    }

    private void chasePlayer()
    {
        if (getWorld() == null) {
            return;
        }
        java.util.List<Player> players = getWorld().getObjects(Player.class);
        
        if (players.isEmpty()) {
            return;
        }
        Player p = players.get(0);

        if (p.getX() < getX()) {
            setLocation(getX() - speed, getY());
            flipped = true;
        } else {
            setLocation(getX() + speed, getY());
            flipped = false;
        }
    }

    private void checkAttackRange()
    {
        if (getWorld() == null) {
            return;
        }
        java.util.List<Player> players = getWorld().getObjects(Player.class);
        if (players.isEmpty()) {
            return;
        }
        Player p = players.get(0);
        if (Math.abs(p.getX() - getX()) <= attackRange && state == State.WALK) {
            startAnimation(State.ATTACK);
            Greenfoot.playSound("swordSwing.mp3");
            Player.takeDamage(2);
        }
    }

    private void checkHit()
    {
        Bullet b = (Bullet)getOneIntersectingObject(Bullet.class);
        if (b == null) {
            return;
        }
        getWorld().removeObject(b);
        health--;

        if (health <= 0) {
            startAnimation(State.DEAD);
        } else {
            startAnimation(State.HURT);
        }
    }

    private void startAnimation(State newState)
    {
        state = newState;
        frame = 0;
        delayCounter = 0;
    }

    private void animate(GreenfootImage[] frames, GreenfootImage[] flippedFrames, State returnTo)
    {
        delayCounter++;
        if (delayCounter < frameDelay) {
            return;
        }
        delayCounter = 0;

        if (frame >= frames.length) {
            frame = frames.length - 1;
        }
        setImage(flipped ? flippedFrames[frame] : frames[frame]);
        frame++;
        if (frame >= frames.length) {
            if (state == State.DEAD) {
                getWorld().removeObject(this);
                Player.addGold((int) (5* Player.getGoldBonus()));
                Player.addKill();
                return;
            }
            state = returnTo;
            frame = 0;
        }
    }

    private void loadFrames(GreenfootImage[] arr, String base, int count)
    {
        for (int i = 0; i < count; i++) {
            arr[i] = new GreenfootImage(base + (i + 1) + ".png");
        }
    }

    private GreenfootImage[] mirrorFrames(GreenfootImage[] src)
    {
        GreenfootImage[] flipped = new GreenfootImage[src.length];

        for (int i = 0; i < src.length; i++) {
            flipped[i] = new GreenfootImage(src[i]);
            flipped[i].mirrorHorizontally();
        }

        return flipped;
    }
    
    public void takeDamage(int damage)
    {
        health -= damage;

        if (health <= 0) {
            startAnimation(State.DEAD);
        } else {
            startAnimation(State.HURT);
            Greenfoot.playSound("minoHurt.wav");
        }
    }
}