import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MainOptions here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MainOptions extends World
{
    public static final int WIDTH = 900;
    public static final int HEIGHT = 600;
    /**
     * Constructor for objects of class MainOptions.
     * 
     */
    public MainOptions()
    {    
        super(WIDTH, HEIGHT, 1);
    }
    
    public void act() 
    {
        addObject(new MenuArrowButton("MainScreen"), 50, HEIGHT - 50);
        addObject(new Controls(), getWidth() / 2, 100);
        showText("Volume (Disabled)", getWidth() / 2, 200);
        showText("Visual (Disabled)", getWidth() / 2, 300);
    }
}
