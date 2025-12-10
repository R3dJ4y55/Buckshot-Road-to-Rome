import greenfoot.*;

public class MenuWorld extends World
{
    public MenuWorld()
    { 
        super(1200, 675, 1); 
        GreenfootImage bg = new GreenfootImage("Menu_Background.jpg"); // make sure the file is in images folder
        setBackground(bg);

        prepareMenu();
    }

    private void prepareMenu()
    {
        Label title = new Label("SHOTGUN OF ROME", 60);
        addObject(title, getWidth()/2, 100);

        Button startButton = new Button("START GAME");
        addObject(startButton, getWidth()/2, 250);

        Button instructionsButton = new Button("INSTRUCTIONS");
        addObject(instructionsButton, getWidth()/2, 350);

        Button exitButton = new Button("EXIT");
        addObject(exitButton, getWidth()/2, 450);
      
        MenuPlayer player = new MenuPlayer();
        addObject(player, 700, 500); 
    }
}
