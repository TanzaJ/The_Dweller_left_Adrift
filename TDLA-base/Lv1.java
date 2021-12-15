import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Lvl1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Lv1 extends scrollWorld
{
    private int groundHeight = 50;
    private int width;
    private int height;

    /**
     * Constructor for objects of class Lvl1.
     * 
     */
    public Lv1()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(900, 600, 1, "bgLv1.png", 1925); 
        
        width = getBackground().getWidth();
        height = getBackground().getHeight();
        addObject(new Npc(), 180, 455);
        addObject(new Floor(getBackground().getWidth(), 30), getWidth() / 2 + 300, 495);
        addObject(new Floor(25, 1000), 0, 0);
        addObject(new Floor(25, 1000), 1500, 0);
        addObject(new Warp(1), 1450, 460);
        prepare();
    }
    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        addObject(new Floor(72, 15), 381, 396);
        addObject(new Floor(350, 15), 598, 329);
        addObject(new Floor(350, 15), 1080, 329);
        addObject(new Spikes(115,18), 397,469);
        addObject(new Spikes(685,18), 912,469);
    }
}
