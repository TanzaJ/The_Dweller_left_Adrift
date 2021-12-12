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
        super(900, 600, 1, "Lv1.png", 550); 
        
        width = getBackground().getWidth();
        height = getBackground().getHeight();
        prepare();
    }
    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        addObject(scrollActor, 5, 200); //add actor to world (wherever)
        //addObject(new Npc(),566,550);
        //addObject(new Floor(),150,480);
        HpBar hpBar = new HpBar("", "");
        addObject(hpBar, scrollActor.getX(), scrollActor.getY() - scrollActor.getImage().getHeight() * 3/4);
        
        //Floor floor = new Floor();
        //for (int i = 0; i < 31; i++){
        //    addObject(new Floor(), 100 * i, 877);
        //    addObject(new Floor(), 100 * i, 0);
        //}
        //for (int i = 0; i < 9; i++){
        //    addObject(new Floor(), 0, 100 * i);
        //    addObject(new Floor(), 3000, 100 * i);            
        //}
    }
}
