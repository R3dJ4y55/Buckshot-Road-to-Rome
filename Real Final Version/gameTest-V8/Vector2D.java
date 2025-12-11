/**
 * Write a description of class Vector2D here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Vector2D {
    private double x;
    private double y;
    
    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }
    
    public double getX() { 
        return x; 
    }
    
    public double getY() { 
        return y; 
    }
    
    public void add(Vector2D v) {
        this.x += v.x;
        this.y += v.y;
    }
    
    public Vector2D subtract(Vector2D v) {
        return new Vector2D(this.x - v.x, this.y - v.y);
    }
    
    public void multiply(double m) {
        this.x *= m;
        this.y *= m;
    }
}