import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * creates a limited (to scrolling background image size) center screen actor followin scrollin world
 * 
 * @author danpost
 */
public class ImageScrollWorld extends World
{
    private int world_width;
    private int world_height;
    
    private int groundHeight;
    private Scroller scroller; // object that performs the scrolling
    Actor scrollActor; // an actor to stay in view
    Actor npc;
    Actor floor;
    Actor spikes;
    GreenfootImage bg;
    public boolean enable = true;
    
    private boolean isTouchingSpikes = false;
    
    
    public ImageScrollWorld()
    {    
        super(900, 600, 1, false);  // creates an bounded world
        world_width = 900;
        world_height = 600;
        //Scroller world stuff
        
        bg = new GreenfootImage("bg.png"); // creates the image to scroll
        int bgWide = bg.getWidth(); // scrolling image width
        int bgHigh = bg.getHeight(); // scrolling image height
        scroller = new Scroller(this, bg, bgWide, bgHigh); // creates the Scroller object
        scrollActor = new Player(); // creates the actor to maintain view on
        addObject(scrollActor, 26, 548); //add actor to world (wherever)
        scroll(); // sets initial background image and puts main actor in view if needed
        Floor ground = new Floor(bgWide, 50);
        addObject(ground, world_width / 2, world_height);
        prepare();
        
        // Add all Objects into the level
        npc = new Npc();
        floor = new Floor();
        spikes = new Spikes();
        addObject(npc, 100, 548);
        for (int i = 1; i < 16; i++){
            addObject(new Spikes(), 400 + i * 72, 548);
        }
        addObject(new Floor(), 309, 509);
        addObject(new Floor(), 480, 420);
        addObject(new Floor(), 771, 408);
        //Other Stuffs:
        setPaintOrder(Player.class, HpBar.class, Enemies.class, InteractIcon.class);
    }
    public void act() {
        if (scrollActor != null) scroll();
        if (EscMenu.class != null)
            setPaintOrder(MenuArrow.class, EscMenu.class);
    }

    //attempts scrolling when actor is not in center of visible world
    private void scroll() {
        //determine scrolling offsets and scroll
        int dsx = scrollActor.getX() - world_width / 2; // horizontal offset from center screen
        int dsy = scrollActor.getY() - world_height / 2; //vertical offset from center screen
        scroller.scroll(dsx, dsy);
    }
    
    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
    }
    
    public int getGroundHeight() {
        return groundHeight;
    }
    
    public int getBgWidth() {
        return bg.getWidth();
    }
    
    public int getBgHeight() {
        return bg.getHeight();
    }
    
    
    public boolean checkEnable() {
        return enable;
    }
    
    /**
     * true to enable
     * false to disable
     */
    public void setEnable(boolean state) {
        enable = state;
    }
}
