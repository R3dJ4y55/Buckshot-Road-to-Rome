import greenfoot.*;

public class MenuWorld extends World
{
    private MenuPlayer player;
    public static GreenfootSound menuMusic = new GreenfootSound("startMusic1.wav");
    private GreenfootSound clickSound;
    public MenuWorld()
    { 
        super(1200, 675, 1);
        
        GreenfootImage bg = new GreenfootImage("Menu_Background.jpg");
        bg.scale(1200, 675);
        setBackground(bg);

        menuMusic.playLoop();
        
        clickSound = new GreenfootSound("buttonClickSound.mp3");
        clickSound.setVolume(80);
        
        player = new MenuPlayer();
        addObject(player, 700, 550);

        prepareMenu();
    }

    private void prepareMenu()
    {
        TitleImage title = new TitleImage();
        addObject(title, getWidth() / 2, 110);

        ImageButton startButton = new ImageButton("StartGameButton.png");
        startButton.setAction(new Runnable() {
            public void run() {
                clickSound.play();
                menuMusic.stop();
                Greenfoot.setWorld(new MyWorld()); 
            }
        });
        addObject(startButton, getWidth() / 2, 300);

        ImageButton instructionsButton = new ImageButton("InstructionsButton.png");
        instructionsButton.setAction(new Runnable() {
            public void run() {
                clickSound.play();
                menuMusic.stop();
                Greenfoot.setWorld(new InstructionsWorld());
            }
        });
        addObject(instructionsButton, getWidth() / 2, 400);

        ImageButton quitButton = new ImageButton("QuitButton.png");
        quitButton.setAction(new Runnable() {
            public void run() {
                clickSound.play();
                menuMusic.stop();
                Greenfoot.stop();
            }
        });
        addObject(quitButton, getWidth() / 2, 500);
    }

    @Override
    public void act()
    {
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if (mouse != null) {
            Actor hoveredButton = getObjectsAt(mouse.getX(), mouse.getY(), ImageButton.class).isEmpty() ? null : getObjectsAt(mouse.getX(), mouse.getY(), ImageButton.class).get(0);
            player.aimAt(hoveredButton);
            if (Greenfoot.mousePressed(null)) {
                player.shootAtTarget(hoveredButton);
            }
        }
    }
}