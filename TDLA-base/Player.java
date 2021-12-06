import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

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
    private static boolean enable;
    private int menuWaitTime = 0; // time before can press esc again
    //Collisions
    private Floor floor = new Floor();
    // Textbox 
    private static boolean moreThanOne;
    
    //Player stats:
    private final double maxHp = 100; // MaxHp + pre-initial (only for hpbar creation)
    private double hp = 100; // create hp variable + pre-initial (for hpBar creation)
    private double armor; // create armor variable
    private double lightDmg; // create lightDmg variable
    private double heavyDmg; // create heavyDmg variable
    
    public Player() {
        //initialize stats:
        hp = 100;
        armor = 25;
        lightDmg = 25;
        heavyDmg = 75;
        
        setImage("SRight.png");
        direction = 'r';
        enable = true;
        upPressed = false;
    }
    public void returnSpeed(){
        
        
    }
    public void act()
    {
        if (enable) {
            if (menuWaitTime > 0) 
                menuWaitTime--; // only decrease when enable and time > 0
            if (menuWaitTime == 0 && Greenfoot.isKeyDown("escape")) {
                getWorld().addObject(new EscMenu(), getWorld().getWidth() / 2, getWorld().getHeight() / 2);
                enable = false; // set enable
                menuWaitTime = 40; // set wait time
            }
            if (dashCD > 0) dashCD--;
            if (dashingTime > 0) {
                dashingTime--;
                //if (o
                setLocation(getX() + hSpeed, getY()); // each time dashingTime - 1, move 10u (total distance move = hSpeed * dashingTime)
            }
            interact();
            movement();
            attack();
            getWorld().setPaintOrder(Player.class);
            viewMoreThanOne();
            fall();
            getWorld().showText("" + getY(), 300, 500);
        }
    }
    public void interact() {

    }
    
    /**
     * checking if is on ground (platform)
     */
    public boolean isOnGround() {
        boolean onGround = false;

        int imageWidth = getImage().getWidth(); //will change later, this must = width of standing img
        int imageHeight = getImage().getHeight();

        //Actor curPlatform = getOneIntersectingObject(Floor.class);
        if ((getOneObjectAtOffset(imageWidth / -2, imageHeight / 2, Floor.class) != null ||
            getOneObjectAtOffset(imageWidth / 2, imageHeight / 2, Floor.class) != null)) {
            onGround = true;
        }
        return onGround;
    }
    
    /**
     * checking if bumped head or not
     */
    public boolean isBumpedHead() {
        boolean bumpedHead = false;
        
        int imageWidth = getImage().getWidth(); //will change later, this must = width of standing img
        int imageHeight = getImage().getHeight();
        
        if (getOneObjectAtOffset(imageWidth / -2, imageHeight / -2, Floor.class) != null ||
            getOneObjectAtOffset(imageWidth / 2, imageHeight / -2, Floor.class) != null) {
            bumpedHead = true; // bumped
        }
        
        return bumpedHead;
    }
    
    public void fall() {
        setLocation(getX(), getY() + vSpeed);
        if (isOnGround()) {
             vSpeed = 0;
             while (isOnGround()) setLocation(getX(), getY() - 1);
             setLocation(getX(), getY() + 1);
        } 
        else if (vSpeed < 0 && 
        isBumpedHead()) vSpeed = 0;
        else vSpeed++;
    }
    public void movement() {
        //move vertically :
        int jumpHeight = -15;
        if (isOnGround()  && Greenfoot.isKeyDown("space")){
            vSpeed = jumpHeight;
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
            dashingTime = 15; // give count down dashingTime + prevent spamming dash (no need boolean)
            dashCD = 90; // set CD + prevent spamming dash (no need boolean)
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
    //public void setGifAni(GifImage image) {
    //    setImage(image.getCurrentImage());
    //}
    
    /**
     * enable setter
     */
    public static void setEnable(boolean state) {
        enable = state;
    }
    public static boolean viewMoreThanOne(){
        return moreThanOne;
    }
    
    //Getter && Setter stats
    public int getMaxHp() {
        return (int) maxHp;
    }
    public int getHp() {
        return (int) hp;
    }
    
    public double getArmor() {
        return armor;
    }
    
    public double getLightDmg() {
        return lightDmg;
    }
    
    public double getHeavyDmg() {
        return heavyDmg;
    }
    
    public void setHp(double newHp) {
        hp = newHp;
    }
    
    public void setArmor(double newArmor) {
        armor = newArmor;
    }
    
    public void setLightDmg(double newDmg) {
        lightDmg = newDmg;
    }
    
    public void setHeavyDmg(double newDmg) {
        heavyDmg = newDmg;
    }
}
