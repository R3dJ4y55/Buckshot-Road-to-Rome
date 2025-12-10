import greenfoot.*;

public class InstructionsWorld extends World
{
    public InstructionsWorld()
    {    
        super(800, 600, 1); 
        GreenfootImage bg = new GreenfootImage(800, 600);
        bg.setColor(Color.LIGHT_GRAY);
        bg.fill();
        setBackground(bg);

        Label title = new Label("Instructions", 50, Color.BLACK);
        addObject(title, getWidth() / 2, 100);
        Label instructions = new Label(
            "Shoot bullets and use recoil to move.\n" +
            "Mouse to aim and shoot.\n" +
            "Triple tap to triple jump.\n" +
            "Kill enemies and become the richest in Rome!",
            30,
            Color.BLACK
        );
        addObject(instructions, getWidth() / 2, 300);
        Button backButton = new Button("BACK");
        addObject(backButton, getWidth() / 2, 500);
    }
}
