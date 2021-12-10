import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MainScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class StartScreen extends World
{
    public static final int WIDTH = 900; 
    public static final int HEIGHT = 600;
    private GreenfootSound music = new GreenfootSound("MainScreen.mp3");
    private Title title = new Title();
    private int vSlided;
    private boolean added;
    private boolean musicOn;
    private boolean night;
    private Black black = new Black();
    private int opacity = 230;
    
    /**
     * Constructor for objects of class MainScreen.
     * 
     */
    public StartScreen()
    {    
        super(WIDTH, HEIGHT, 1, false);
        prepare();
        vSlided = -100;
        //music.playLoop();
        addObject(title, WIDTH / 2, vSlided);
        added = false;
        musicOn = false;
        night = true;
    }

    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        black.getImage().setTransparency(opacity);
        addObject(black, getWidth() / 2, getHeight() / 2);
    }
    
    public void act() 
    {   
        effect();
        if (!musicOn) playMusic();

    }
    public void effect() {   
        if (vSlided < HEIGHT * 1/7) {
            vSlided ++;
            title.setLocation(WIDTH / 2, vSlided);
        }
        if (!added && title.getY() == HEIGHT * 1/7) {
            addObject(new MainScreenButton("play", 220, 70), WIDTH * 4/5 - 25, 600 * 3/5);
            addObject(new MainScreenButton("options", 150, 50), WIDTH * 4/5 - 25, 600 * 3/5 + 90);
            addObject(new MainScreenButton("credits", 150 ,50), WIDTH * 4/5 - 25, 600 * 3/5 + 150);
            added = true;
            for (int i = opacity; i > 0; i--) black.getImage().setTransparency(opacity);
        }
        if (opacity > 0) {
            black.getImage().setTransparency(opacity);
            opacity--;
        }
        
    }
    
    public void playMusic() {
        music.setVolume(35);
        music.playLoop();
    }
    public void stopMusic() {
        music.stop();
    }
}
