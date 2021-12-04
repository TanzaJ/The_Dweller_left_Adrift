import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Lvl1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Lvl1 extends World
{

    /**
     * Constructor for objects of class Lvl1.
     * 
     */
    public Lvl1()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(3000, 900, 1); 
        prepare();
    }
    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        Floor floor = new Floor();
        for (int i = 0; i < 31; i++){
            addObject(new Floor(), 100 * i, 877);
            addObject(new Floor(), 100 * i, 0);
        }
        for (int i = 0; i < 9; i++){
            addObject(new Floor(), 0, 100 * i);
            addObject(new Floor(), 3000, 100 * i);            
        }
    }
}
