import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player extends Actor
{
    private int hSpeed; //horizontal speed (move left + right)
    private int vSpeed = 0; // vertical speed (gravity + jump)
    private int halfImageX = getImage().getWidth() / 2; // 1/2 current image's width
    private int halfImageY = getImage().getHeight() / 2; // 1/2 current image's height 
    private boolean upPressed;
    private int dashCD = 0; // dash cooldown
    private int dashingTime = 0; // positve = dashing state + disable horizontal movements
    private GifImage gifImg; // For any animation need involves using Gif - Use: assign gifImg first then call setGifAni(...)
                             // If not gif, can just directly in setImage(file). Not yet sure if all need gif. Will think of it
                             // Set default animation/image in constructor, then no more change without Actions/Controls
                             // If no gif in future, make int delay animation variable = delay each image ();
    private char direction;
    private static boolean enable = true;
    private int menuWaitTime = 0; // time before can press esc again
    
    // Textbox 
    private boolean moreThanOne = false;
    
    public Player() {
        setImage("SRight.png");
        direction = 'r';
    }
    public void returnSpeed(){
    
    }
    public void act()
    {
        if (enable) {
            if (menuWaitTime > 0) 
                menuWaitTime--; // only decrease when enabled again
            if (menuWaitTime == 0 && Greenfoot.isKeyDown("escape")) {
                getWorld().addObject(new Menu(), 300, 300);
                enable = false; // set enable
                menuWaitTime = 50; // set wait time
            }
            if (dashCD > 0) dashCD--;
            if (dashingTime > 0) {
                 dashingTime--;
                 setLocation(getX() + hSpeed, getY()); // each time dashingTime - 1, move 10u (total distance move = hSpeed * dashingTime)
            }
            interact();
            movement();
            attack();
            createObjectText();
            getWorld().setPaintOrder(Player.class);
        }
    }
    public void interact() {
        
    }
    public void movement() {
        //move vertically :
        boolean onGround = false; //stand on a platform = remove gravity effect
        vSpeed++; // change gravity (failing faster after a while on air)
        setLocation(getX(), getY() + vSpeed); // gravity
        World world = (ImageScrollWorld)getWorld();
        int groundHeight = 50;
        
        if (getY() > world.getHeight() - groundHeight) {
            vSpeed = 0; // kill vertical speed
            onGround = true; // on ground
            setLocation(getX(), world.getHeight() - groundHeight); // position player
        }
        if (!upPressed && onGround && Greenfoot.isKeyDown("space")){
            vSpeed = -15; // adjust initial jump speed as needed
            upPressed = true;
        }
        if (upPressed && !Greenfoot.isKeyDown("space")){
            upPressed = false;
        }
        // move horizontally :
        if (dashingTime == 0) 
            hSpeed = 2; // if not dashing, speed set to 2 (speed may change in future)
        if (dashCD == 0 && dashingTime == 0 && Greenfoot.isKeyDown("a")) {
            int accelaration = 10;
            if (direction == 'r') 
                hSpeed = accelaration;
            else
                hSpeed = -accelaration;
            dashingTime = 8; // give count down dashingTime + prevent spamming dash (no need boolean)
            dashCD = 150; // set CD + prevent spamming dash (no need boolean)
        }
        if (dashingTime == 0 && Greenfoot.isKeyDown("left")){
            setLocation(getX() - hSpeed, getY());
            setImage("SLeft.png");
            direction = 'l';
        }
        if (dashingTime == 0 && Greenfoot.isKeyDown("right")) {
            setLocation(getX() + hSpeed, getY());
            setImage("SRight.png");
            direction = 'r';
        }
    }
    
    public void attack() {
        if (Greenfoot.isKeyDown("d")); // light attack goes here
        if (Greenfoot.isKeyDown("s")) {
            //heavy attack goes here
        }
        if (Greenfoot.isKeyDown("s") && Greenfoot.isKeyDown("up")) {
            // upper slash goes here
        }
    }
    
    /**
     * Perform Gif Animation / set Gif Animation
     * 
     * @param image image's name + .extesion
     */
    public void setGifAni(GifImage image) {
        setImage(image.getCurrentImage());
    }
    
    /**
     * enable setter
     */
    public static void setEnable(boolean state) {
        enable = state;
    }
    
        public void createObjectText(){
        if (isTouching(ObjectNpc.class) &&Greenfoot.isKeyDown("e") && !moreThanOne){
            getWorld().addObject(new TextBox(), 300, 200);
            moreThanOne = true;
            enable = false;
        }
//        if (){
//            shouldMove = true;
//        }
    }
    
}
