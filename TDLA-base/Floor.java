import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Floor here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Floor extends Collisions
{
    public Floor(int width, int height, int rotation) {
        getImage().scale(width, height);
        setRotation(rotation);
        getImage().setTransparency(0);
    }
    
    public Floor(int width, int height) {
        getImage().scale(width, height);
        getImage().setTransparency(0);
    }
    public Floor() {
        this(100, 50);
        getImage().setTransparency(0);
    }
    public void act()
    {
    }
}
