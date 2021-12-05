import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class InstructionsMenu here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class InstructionsMenu extends World
{
    public static final int WIDTH = 900;
    public static final int HEIGHT = 600;
    /**
     * Constructor for objects of class MainOptions.
     * 
     */
    public InstructionsMenu()
    {    
        super(WIDTH, HEIGHT, 1);
    }
    
    public void act() 
    {
        addObject(new MenuArrowButton("Options"), 50, HEIGHT - 50);
        showText("Left arrow: move left", WIDTH / 2, 100);
        showText("Right arrow: move right", WIDTH / 2, 150);
        showText("Space: Jump", WIDTH / 2, 200);
        showText("E: Interact", WIDTH / 2, 250);
        showText("A: Dash", WIDTH / 2, 300);
        showText("S: Light attack", WIDTH / 2, 350);
        showText("D: Heavy attack", WIDTH / 2, 400);
    }
}
