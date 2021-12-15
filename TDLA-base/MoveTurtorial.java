import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class InteractIcon here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MoveTurtorial extends Objects
{
    GifImage rKey = new GifImage("RightKey.gif");
    GifImage lKey = new GifImage("LeftKey.gif");
    GifImage aKey = new GifImage("AKey.gif");
    GifImage spaceKey = new GifImage("SpaceKey.gif");
    
    boolean allSet;
    /**
     * Act - do whatever the InteractIcon wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void MoveTurtorial() {
        getImage().scale(50, 50);
        allSet = false;
    }
    public void act ()
    {
        if (!allSet) {
            setImage(rKey.getCurrentImage());
            getImage().scale(50, 50);
        }
    }
}
