import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * creates a limited (to scrolling background image size) center screen actor followin scrollin world
 * 
 * @author danpost
 */
public class ImageScrollWorld extends World
{
    public static final int WIDTH = 900; // world width (viewport)
    public static final int HEIGHT = 600; // world height (view port)
    private int groundHeight = 50;
    Scroller scroller; // object that performs the scrolling
    Actor scrollActor; // an actor to stay in view

    public ImageScrollWorld()
    {    
        super(WIDTH, HEIGHT, 1);  // creates an bounded world
        GreenfootImage bg = new GreenfootImage("bg.png"); // creates the image to scroll
        int bgWide = bg.getWidth(); // scrolling image width
        int bgHigh = bg.getHeight(); // scrolling image height
        scroller = new Scroller(this, bg, bgWide, bgHigh); // creates the Scroller object
        scrollActor = new Player(); // creates the actor to maintain view on
        addObject(scrollActor, bgWide / 2, bgHigh); //add actor to world (wherever)
        scroll(); // sets initial background image and puts main actor in view if needed
        prepare();
    }

    public void act() {
        if (scrollActor != null) scroll();
    }

    //attempts scrolling when actor is not in center of visible world
    private void scroll() {
        //determine scrolling offsets and scroll
        int dsx = scrollActor.getX() - WIDTH / 2; // horizontal offset from center screen
        int dsy = scrollActor.getY() - HEIGHT / 2; //vertical offset from center screen
        scroller.scroll(dsx, dsy);
    }

    public int getGroundHeight() {
        return groundHeight;
    }
    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        ObjectNpc objectNpc = new ObjectNpc();
        addObject(objectNpc,566,516);
    }
}
