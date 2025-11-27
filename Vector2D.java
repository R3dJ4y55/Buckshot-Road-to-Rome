/**
 * Write a description of class Vector2D here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Vector2D  
{
    // instance variables - replace the example below with your own
    private double x;
    private double y;
    
    /**
     * Constructor for objects of class Vector2D
     */
    public Vector2D(double x, double y)
    {
        this.x = x;
        this.y = y;
    }

    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public double getX(){
        return x;
    }
    
    public double getY(){
        return y;
    }
    
    public void setX(double x)
    {
        this.x = x;
    }
    
    public void setY(double y)
    {
        this.y = y;
    }
    
    public Vector2D add(Vector2D v){
        x = x + v.getX();
        y = y + v.getY();
        
        return new Vector2D(x, y);
    }
    
    public Vector2D substract(Vector2D v){
        x = x - v.getX();
        y = y - v.getY();
        
        return new Vector2D(x, y);
    }
    
    public Vector2D multiply(double m){
        x = x*m;
        y = y*m;
        
        return new Vector2D(x, y);
    }
    
    public Vector2D divide(double m){
        x = x/m;
        y = y/m;
        
        return new Vector2D(x, y);
    }
}
