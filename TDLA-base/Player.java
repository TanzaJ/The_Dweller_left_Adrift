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
    private int halfImageY = getImage().getHeight() / 2;
    private int halfImageX = getImage().getWidth() / 2;
    private static int dashCD = 0;
    private int state = 0; // animation state: 0 stand, 1 move, 2 attack, 3 dash, 4 jump (if had)
    private boolean upPressed;
    private boolean upPressedShift; 
    private boolean facingRight;
    public void act()
    {
        interact();
        movement();
        attack();
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
        hSpeed = 2;
        if (!upPressedShift && Greenfoot.isKeyDown("shift")) {
            if (facingRight == true) {
                hSpeed = 50;
            }
            else {
                hSpeed = -50;
            }
            setLocation(getX() + hSpeed, getY());
            upPressedShift = true;
        }
        if (upPressedShift && !Greenfoot.isKeyDown("shift")){
            upPressedShift = false;
        }
        if (Greenfoot.isKeyDown("left")){
            setLocation(getX() - hSpeed, getY());
            facingRight = false;
        }
        if (Greenfoot.isKeyDown("right")) {
            setLocation(getX() + hSpeed, getY());
            facingRight = true;
        }
    }
    
    public void attack() {
        if (Greenfoot.isKeyDown("d")); // light attack go here
        if (Greenfoot.isKeyDown("s")) {
            //heavy attack go here
            if (Greenfoot.isKeyDown("up")) {
                //upper attack go here
            }
        }
    }
    
    /**
     * do animation depends of current state
     * state:
     * 0 stand
     * 1 move
     * 2 attack
     * 3 dash
     * 4 jump
     * 
     * @param state current state
     * 
     */
    public void animation(int state) {
        if (state == 0) {
            // code goes here
        } else if (state == 1) {
            // code goes here
        } else if (state == 2) {
            // code goes here
        } else if (state == 3) {
            // code goes here
        } else if (state == 4) {
            
        }
    }
}
