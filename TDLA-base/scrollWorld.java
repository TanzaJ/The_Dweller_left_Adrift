import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class scrollWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class scrollWorld extends World
{
    private int world_width;
    private int world_height;
    
    private Scroller scroller; // object that performs the scrolling
    Actor scrollActor; // an actor to stay in view
    GreenfootImage bg;
    public boolean enable = true;
    
    
    public scrollWorld(int width, int height, int cell, String background, int groundHeight)
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(width, height, cell, false);
        world_width = width;
        world_height = height;
        
        bg = new GreenfootImage(background); // creates the image to scroll
        
        int bgWide = bg.getWidth(); // scrolling image width
        int bgHigh = bg.getHeight(); // scrolling image height
        
        scroller = new Scroller(this, bg, bgWide, bgHigh); // creates the Scroller object
        scrollActor = new Player(); // creates the actor to maintain view on
        addObject(scrollActor, 30, groundHeight); //add actor to world (wherever)
        
        
        setPaintOrder(Player.class, MoveTurtorial.class);
        
        scroll(); // sets initial background image and puts main actor in view if needed
        
        
    }
    public void act() {
        if (scrollActor != null) scroll();
        if (EscMenu.class != null) setPaintOrder(MenuArrow.class, EscMenu.class);
    }
    
    private void scroll() {
        //determine scrolling offsets and scroll
        int dsx = scrollActor.getX() - world_width / 2; // horizontal offset from center screen
        int dsy = scrollActor.getY() - world_height * 3/4; //vertical offset from center screen
        scroller.scroll(dsx, dsy);
    }
    
    public boolean checkEnable() {
        return enable;
    }
    
    public void setEnable(boolean state) {
        enable = state;
    }
}
