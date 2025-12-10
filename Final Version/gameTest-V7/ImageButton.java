import greenfoot.*;

public class ImageButton extends Actor
{
    private Runnable action;
    public ImageButton(String imageFile)
    {
        GreenfootImage img = new GreenfootImage(imageFile);
        img.scale(200, 130);
        setImage(img);
    }
    
    public void setAction(Runnable action) {
        this.action = action;
    }
    
    public void performClick() {
        if (action != null)
            action.run();
    }
}
