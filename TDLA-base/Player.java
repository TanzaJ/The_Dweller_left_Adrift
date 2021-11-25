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
    private int state = 0; // animation state: 0 stand, 1 move, 2 attack, 3 dash, 4 jump (if had)
    private boolean upPressed;
    private boolean isDash; 
    private boolean facingRight;
    private int dashCD = 0;
    private int dashingTime = 0; // dashing state - disable horizontal movements + 
    
    public void act()
    {
        if (dashingTime > 0)
        {
             dashingTime--;
             setLocation(getX() + hSpeed, getY());
        }
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
        
        if (dashingTime == 0) hSpeed = 2;
        if (dashingTime == 0 && !isDash && Greenfoot.isKeyDown("shift")) {
            isDash = true;
            dashingTime = 8;
            hSpeed = 10;
        }
        if (isDash && !Greenfoot.isKeyDown("shift")){
            isDash = false;
        }
        if (!isDash && Greenfoot.isKeyDown("left")){
            setLocation(getX() - hSpeed, getY());
            facingRight = false;
        }
        if (!isDash && Greenfoot.isKeyDown("right")) {
            setLocation(getX() + hSpeed, getY());
            facingRight = true;
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
     * perform special animation
     * 
     * @param image image's name + .extesion
     */
    public void animation(String image) {
        
    }
}
