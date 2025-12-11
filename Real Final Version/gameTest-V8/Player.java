import greenfoot.*;

public class Player extends Actor
{
    private static int gold = 0;
    private static int kills = 0;
    private static int health = 3;
    private static int maxHealth = 3;
    private static double defense = 1.0;
    private static double goldBonus = 1.0;
    private static int timer;
    public static boolean startTimer = false;
    
    private GreenfootImage playerIdle;
    private GreenfootImage[] playerShoot;
    private GreenfootImage[] playerReload;
    private int frame;           
    private int animationDelay;
    private boolean flipped = false;
    private int delayCount;
    private int ammo;           
    private int maxAmmo = 5;    
    private int shootCooldown = 20;
    private int shootTimer = 0;
    private Vector2D velocity = new Vector2D(0, 0);
    private double gravity = 0.8;
    private double groundY; 
    private static int iFrames = 30;
    private static int iFrameTimer = 0;
    private enum State { 
        IDLE, SHOOT, RELOAD 
    }
    private State currentState = State.IDLE;
    
    public Player()
    {
        playerIdle = new GreenfootImage("playerAnimations/Idle/playerIdle.png");
        playerShoot = new GreenfootImage[5];
        for(int i = 0; i < 5; i++) {
            playerShoot[i] = new GreenfootImage("playerAnimations/Shoot/playerShoot" + (i+1) + ".png");
        }
        
        playerReload = new GreenfootImage[5];
        for(int i = 0; i < 5; i++) {
            playerReload[i] = new GreenfootImage("playerAnimations/Reload/playerReload" + (i+1) + ".png");
        }
        setImage(playerIdle);
        frame = 0;
        animationDelay = 5;
        delayCount = 0;
        ammo = maxAmmo;
        health = maxHealth;
        setStartTimer(false);
    }
    
    public void act()
    {
        handleInput();
        animate();
        followMouse();
        applyPhysics();
        if (shootTimer > 0) {
            shootTimer--;
        }
        if (iFrameTimer > 0) {
            iFrameTimer--;
        }
        if (startTimer) {
            timer += 1;
        }
        if (!startTimer) {
            timer = 0;
        }
    }
    
    private static void setStartTimer(boolean bool) {
        startTimer = bool;
    }
    
    public static int getGold() {
        return gold;
    }
    
    public static void addGold(int num) {
        gold += num;
    }
    
    public static int getKills() {
        return kills;
    }
    
    public static void addKill() {
        kills += 1;
    }
    
    public static int getHealth() {
        return health;
    }
    
    public static int getMaxHealth() {
        return maxHealth;
    }
    
    public static void setHealth(int newHealth) {
        health = newHealth;
    }
    
    public static void removeHealth(int dmg) {
        health -= dmg;
    }
    
    public static void increaseMaxHealth()
    {
        maxHealth += 1;
    }
    
    public static double getDefense(){ 
        return defense;
    }
    
    public static void addToDefense(double num){ 
        defense += num;
    }
    
    public static double getGoldBonus(){ 
        return goldBonus;
    }
    
    public static void addToGoldBonus(double num) {
        goldBonus += num;
    }
    
    public void addedToWorld(World w) {
        double offset = 20;
        groundY = w.getHeight() - getImage().getHeight() / 2.0 - offset;;
    }

    private void applyPhysics()
    {
        // 1) gravity
        velocity.add(new Vector2D(0, gravity));

        // 2) apply velocity to position x & y
        double newX = getX() + velocity.getX();
        double newY = getY() + velocity.getY();
        setLocation((int)Math.round(newX), (int)Math.round(newY));

        // 3) some air drag cuz we built different
        velocity.multiply(0.99); 

        // 4) add some ground collision
        if (getY() > groundY) {
            //int clampedX = getX();
            setLocation(getX(), (int)groundY);  
            velocity = new Vector2D(velocity.getX() * 0.6, 0);
        }
    }

    private void followMouse()
    {
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if (mouse != null) {
            turnTowards(mouse.getX(), mouse.getY());
        }
    }
    
    private void handleInput()
    {
        if (Greenfoot.mousePressed(null) && currentState != State.RELOAD && ammo > 0 && shootTimer == 0) {   
            currentState = State.SHOOT;
            frame = 0;
            ammo--; 
            shootTimer = shootCooldown;
        }
        else if (Greenfoot.isKeyDown("r") && currentState != State.RELOAD) {  
            currentState = State.RELOAD;
            frame = 0;
        }
        else if (ammo == 0 && currentState != State.RELOAD) {
            currentState = State.RELOAD;
            frame = 0;
        }
        else if (currentState != State.SHOOT && currentState != State.RELOAD) {
            currentState = State.IDLE;
        }
    }
    
    private void animate()
    {
        delayCount++;
        if (delayCount < animationDelay) {
            return;
        } 
        delayCount = 0;
        GreenfootImage currentFrame = null;
        switch(currentState) {
            case IDLE:
                currentFrame = new GreenfootImage(playerIdle);
                break;
            case SHOOT:
                currentFrame = new GreenfootImage(playerShoot[frame]);
                if (frame == 0) {
                    applyRecoil();
                    fireBullet();
                    Greenfoot.playSound("shotFired.mp3");
                }
                frame++;
                if (frame >= playerShoot.length) {
                    frame = 0;
                    currentState = State.IDLE;
                }
                break;
            case RELOAD:
                currentFrame = new GreenfootImage(playerReload[frame]);
                frame++;
                if (frame >= playerReload.length) {
                    frame = 0;
                    ammo = maxAmmo;  
                    currentState = State.IDLE;
                }
                break;
        }
        if (currentFrame != null) {
            setImage(currentFrame);
            updateSpriteFlip();
        }
    }
    
    private void updateSpriteFlip()
    {
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if (mouse == null) {
            return;
        }
        boolean shouldFlip = mouse.getX() < getX();
        if (shouldFlip != flipped) {
            GreenfootImage img = getImage();
            img.mirrorVertically();
            setImage(img);
            flipped = shouldFlip;
        }
    }

    private void applyRecoil()
    {
        double angle = Math.toRadians(getRotation() + 180); 
        double strength = 12; 
        double rx = Math.cos(angle) * strength;
        double ry = Math.sin(angle) * strength;
        velocity.add(new Vector2D(rx, ry));
    }

    private void fireBullet()
    {
        if (isAimingUp()) {
            LightningBullet lb = new LightningBullet();
            getWorld().addObject(lb, getX(), getY());
            lb.setRotation(getRotation());
        } else {
            Bullet b = new Bullet(6, 1); 
            getWorld().addObject(b, getX(), getY());
            b.setRotation(getRotation());
        }
    }
    
    private boolean isAimingUp() {
        int rot = getRotation();
        return (rot > 225 && rot < 315);
    }
    
    public static void takeDamage(int amount) {
        if (iFrameTimer > 0) {
            return;
        }
        health -= (int) amount * defense;
        iFrameTimer = iFrames;
        
        if (health <= 0) {
            die();
        }
    }
    
    private static void die() {
        startTimer = true;
        if (timer == 30) {
            Greenfoot.setWorld(new GameOverWorld());
        }
    }
    public static void victory() {
        startTimer = true;
        if (timer == 60) {
            Greenfoot.setWorld(new UpgradeMenu());
        }
    }
}