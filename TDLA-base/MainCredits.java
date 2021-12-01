import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MainCredits here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MainCredits extends World
{
    public static final int WIDTH = 900; 
    public static final int HEIGHT = 600;
    /**
     * Constructor for objects of class MainCredits.
     * 
     */
    public MainCredits()
    {    
         super(WIDTH ,HEIGHT ,1);
    }
    
    public void act() 
    {
        addObject(new MenuArrowButton(), 50, HEIGHT - 50);
    }
}
