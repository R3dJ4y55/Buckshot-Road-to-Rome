import greenfoot.*;
import java.util.List;

public class Minotaur extends Actor
{
    private int health = 5;    
    private int speed = 1;
    private int attackRange = 40;
    private int frameDelay = 6; 
    private GreenfootImage[] walk   = new GreenfootImage[12];
    private GreenfootImage[] hurt   = new GreenfootImage[3];
    private GreenfootImage[] attack = new GreenfootImage[5];
    private GreenfootImage[] dead   = new GreenfootImage[5];
    private GreenfootImage[] walkFlip;
    private GreenfootImage[] hurtFlip;
    private GreenfootImage[] attackFlip;
    private GreenfootImage[] deadFlip;
    private int frame = 0;
    private int delayCounter = 0;
    private enum State { WALK, HURT, ATTACK, DEAD }
    private State state = State.WALK;
    private boolean flipped = false;
    private List<Player> players;
    private boolean died;
    private boolean moneyAdded;
    private static MyWorld w = new MyWorld();

    public Minotaur()
    {
        loadFrames(walk,   "Minotaur/MinotaurWalk/MinoWalk", 12);
        loadFrames(hurt,   "Minotaur/MinotaurHurt/MinoHurt", 3);
        loadFrames(attack, "Minotaur/MinotaurAttack/MinoAttack", 5);
        loadFrames(dead,   "Minotaur/MinotaurDead/MinoDead", 5);
        died = false;
        moneyAdded = false;
        walkFlip   = mirrorFrames(walk);
        hurtFlip   = mirrorFrames(hurt);
        attackFlip = mirrorFrames(attack);
        deadFlip   = mirrorFrames(dead);
        setImage(walk[0]);
        updatePlayer();
    }

    public void act()
    {
        if (state != State.DEAD)
            checkHit();

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
            players.get(0).addMoney(5);
            moneyAdded=true;
            died = true;
            checkDead();
            return;
        }
        updatePlayer();
        checkAttackRange();
        chasePlayer();
        animate(walk, walkFlip, State.WALK);
    }
    
    public boolean checkDead()
    {
        if (died){
            if (!moneyAdded){
                players.get(0).addMoney(5);
                moneyAdded=true;
            }
            w.removeObject(this);
            spawnNewMinotaur();
            return true;
        }
        return false;
    }
    
    private void spawnNewMinotaur()
    {
        boolean spawnLeft = Greenfoot.getRandomNumber(2) == 0;
        if (Greenfoot.getRandomNumber(2) == 0)
            w.addObject(new Minotaur(), 50, 290);
        else
            w.addObject(new Minotaur(), getWorld().getWidth() - 50, 290);
    }
    
    public void updatePlayer()
    {
        if (getWorld() != null) players = getWorld().getObjects(Player.class);
    }

    private void chasePlayer()
    {
        if (getWorld() == null) return;
        if (players.isEmpty()) return;
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
        if (getWorld() == null) return;
        if (players.isEmpty()) return;
        Player p = players.get(0);
        if (Math.abs(p.getX() - getX()) <= attackRange && state == State.WALK) {
            startAnimation(State.ATTACK);
            Greenfoot.playSound("swordSwing.mp3");
        }
    }

    private void checkHit()
    {
        Bullet b = (Bullet)getOneIntersectingObject(Bullet.class);
        if (b == null) return;
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
        if (delayCounter < frameDelay)
            return;
        delayCounter = 0;

        if (frame >= frames.length) {
            frame = frames.length - 1;
        }

        setImage(flipped ? flippedFrames[frame] : frames[frame]);
        frame++;
        if (frame >= frames.length) {
            if (state == State.DEAD) {
                getWorld().removeObject(this);
                return;
            }
            state = returnTo;
            frame = 0;
        }
    }

    private void loadFrames(GreenfootImage[] arr, String base, int count)
    {
        for (int i = 0; i < count; i++)
            arr[i] = new GreenfootImage(base + (i + 1) + ".png");
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

        if (health <= 0)
            startAnimation(State.DEAD);
        else
            startAnimation(State.HURT);
            Greenfoot.playSound("minoHurt.wav");
    }
}