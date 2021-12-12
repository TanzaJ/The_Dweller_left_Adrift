import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class InteractIcon here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class InteractIcon extends Objects
{
    GifImage gifImage = new GifImage("DDown.gif");
    /**
     * Act - do whatever the InteractIcon wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act ()
    {
        setImage(gifImage.getCurrentImage());
    }
}
