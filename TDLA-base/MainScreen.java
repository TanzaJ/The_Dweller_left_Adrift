import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MainScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MainScreen extends World
{
    public static final int WIDTH = 900; 
    public static final int HEIGHT = 600;
    /**
     * Constructor for objects of class MainScreen.
     * 
     */
    public MainScreen()
    {    
        super(WIDTH, HEIGHT, 1);
        prepare();
    }

    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {

    }
    
    public void act() 
    {
        addObject(new MainScreenMenu("Play"), 450, 350);
        addObject(new MainScreenMenu("Options"), 450, 450);
        addObject(new MainScreenMenu("Credits"), 450, 550);
    }
}
