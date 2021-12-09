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
    private int dashCD = 0; // dash cooldown
    private int dashingTime = 0; // positve = dashing state + disable horizontal movements

    private char direction;
    private static boolean enable;
    private int menuWaitTime = 0; // time before can press esc again
    private static int delayAni = 0;
    private static GreenfootImage curImg;
    
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
        direction = 'l';
        enable = true;
    }
    public void returnSpeed(){
        
        
    }
    public void act()
    {
        getWorld().showText("" + delayAni, 600, 500);
        if (delayAni > 0) delayAni--;
        if (enable) {
            getWorld().setPaintOrder(Player.class);
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
                if (!checkLeftWall())
                    setLocation(getX() + hSpeed, getY()); // each time dashingTime - 1, move 10u (total distance move = hSpeed * dashingTime)
            }
            setAni();
            checkKey(); 
            interact();
            fall();
            attack();
            viewMoreThanOne();
            getWorld().showText("" + hSpeed, 500, 500);
        }
    }
    /**
     * horizontal move method
     */
    public void hMove () {
        setLocation(getX() + hSpeed, getY());
    }
    
    public void interact() {

    }
    
    /**
     * checking if touching wall
     */
    public boolean checkRightWall() {
        int w = getImage().getWidth(); 
        int h = getImage().getHeight();
        
        if (hSpeed > 0 && 
           (getOneObjectAtOffset(w / 2 + 2, h * -1/3, Collisions.class)) != null ||
            getOneObjectAtOffset(w / 2 + 1, 0, Collisions.class) != null ||
            getOneObjectAtOffset(w / 2 + 2, h * 1/3, Collisions.class) != null || 
            getOneObjectAtOffset(w / 2 + 1, h / -2, Collisions.class) != null) {
                return true;        
        }
        return false;
    }
    public boolean checkLeftWall() {
        int w = getImage().getWidth(); 
        int h = getImage().getHeight();
        
        if (hSpeed < 0 && 
           (getOneObjectAtOffset(w / -2 - 2, h * -1/3, Collisions.class)) != null ||
            getOneObjectAtOffset(w / -2 - 1, 0, Collisions.class) != null ||
            getOneObjectAtOffset(w / -2 - 2, h * 1/3, Collisions.class) != null ||
            getOneObjectAtOffset(w / -2 - 1, h / -2, Floor.class) != null) {
                return true;
        }
        return false;
    }
    
    /**
     * checking if is on ground (platform)
     */
    public boolean checkGround() {
        int w = getImage().getWidth(); 
        int h = getImage().getHeight();

        if ((getOneObjectAtOffset(w / -2 + 10, h / 2, Collisions.class) != null ||
            getOneObjectAtOffset(w / 2 - 10, h / 2, Collisions.class) != null)) {
            return true;
        }
        return false;
    }
    
    /**
     * checking if bumped head or not
     */
    public boolean checkBumped() {
        int w = getImage().getWidth(); //will change later, this must = width of standing img
        int h = getImage().getHeight();
        
        if (vSpeed < 0 && 
            (getOneObjectAtOffset(w / -2 + 2, h / -2 - 3, Floor.class) != null ||
            getOneObjectAtOffset(w / 2 - 2, h / -2 - 3, Floor.class) != null)) {
            return true;
        }
        return false;
    }
    
    public void fall() {
        setLocation(getX(), getY() + vSpeed);
        if (checkGround()) {
             vSpeed = 0;
             while (checkGround()) setLocation(getX(), getY() - 1);
             setLocation(getX(), getY() + 1);
        } else if (checkBumped()) vSpeed = 0;
        else vSpeed++;
    }
    
    public void checkKey() {
        //horizontal move
        if (!Greenfoot.isKeyDown("left") && !Greenfoot.isKeyDown("right")) hSpeed = 0;
        if (!checkLeftWall() && dashingTime == 0 && Greenfoot.isKeyDown("left")) {
             hSpeed = -2;
             direction = 'l';
             hMove();
        }
        if (!checkRightWall() && dashingTime == 0 && Greenfoot.isKeyDown("right")) {
            hSpeed = 2;
            direction = 'r';
            setImage("RMove1.png");
            hMove();
        }
        if (dashCD == 0 && dashingTime == 0 && Greenfoot.isKeyDown("a")) {
            int accelaration = (direction == 'l') ? -10 : 10;
            hSpeed = accelaration;
            dashingTime = 15;
            dashCD = 90;
        }
        
        //vertical move:
        int jumpHeight = -15;
        if (checkGround()  && Greenfoot.isKeyDown("space")){
            vSpeed = jumpHeight;
            
        }
        
        
        //move vertically :
        //int jumpHeight = -15;
        //if (isOnGround()  && Greenfoot.isKeyDown("space") && !upPressed){
        //    vSpeed = jumpHeight;
        //    upPressed = true;
        //}
        //if (upPressed && !Greenfoot.isKeyDown("space")){
        //    upPressed = false;
        //}
        
        // move horizontally :
        //if (dashingTime == 0) 
        //    hSpeed = 2; // if not dashing, speed set to 2 (speed may change in future)
        //if (dashCD == 0 && dashingTime == 0 && Greenfoot.isKeyDown("a")) {
        //    int accelaration = 10;
        //    if (direction == 'r') 
        //        hSpeed = accelaration;
        //    else
        //        hSpeed = -accelaration;
        //    dashingTime = 15; // give count down dashingTime + prevent spamming dash (no need boolean)
        //    dashCD = 90; // set CD + prevent spamming dash (no need boolean)
        //}
        //if (dashingTime == 0 && Greenfoot.isKeyDown("left")){
        //    setLocation(getX() - hSpeed, getY());
        //    setImage("SLeft.png");
        //    direction = 'l';
        //}
        //if (dashingTime == 0 && Greenfoot.isKeyDown("right")) {
        //    setLocation(getX() + hSpeed, getY());
        //    setImage("SRight.png");
        //    direction = 'r';
        //}
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
     * 
     * 
     * @param image image's name + .extesion
     */
    public void setAni() {
        getWorld().showText("" + delayAni, 600, 500);
        curImg = getImage();
        if (delayAni > 0) delayAni--;
        if (direction == 'l') {
            if (checkGround() && hSpeed == 0) {
                if (delayAni == 0 && !ImageVisitor.equal(getImage(), new GreenfootImage("LStand1.png"))) {
                    setImage("LStand1.png");
                    delayAni = 150;
                    }
                if (delayAni == 0 && ImageVisitor.equal(getImage(), new GreenfootImage("LStand1.png"))) {
                    delayAni = 35;
                    setImage("LStand2.png");
                }
            }
            
            // moving
            if (checkGround() && hSpeed < 0) {
                boolean equal_to_one = false;
                for (int i = 1; i <= 8; i++) {
                    if (ImageVisitor.equal(getImage(), new GreenfootImage("LMove" + i + ".png")))  {
                        equal_to_one = true;
                        break;
                    }
                }
                if(!equal_to_one) {
                    setImage("LMove1.png");
                    delayAni = 0;
                }
                
                if (delayAni == 0 && ImageVisitor.equal(getImage(), new GreenfootImage("LMove8.png"))) {
                    setImage("LMove1.png");
                    delayAni = 10;
                }
                if (delayAni == 0 && ImageVisitor.equal(getImage(), new GreenfootImage("LMove1.png"))) {
                    setImage("LMove2.png");
                    delayAni = 10;
                }
                if (delayAni == 0 && ImageVisitor.equal(getImage(), new GreenfootImage("LMove2.png"))) {
                    setImage("LMove3.png");
                    delayAni = 10;
                }
                if (delayAni == 0 && ImageVisitor.equal(getImage(), new GreenfootImage("LMove3.png"))) {
                    setImage("LMove4.png");
                    delayAni = 10;
                }
                if (delayAni == 0 && ImageVisitor.equal(getImage(), new GreenfootImage("LMove4.png"))) {
                    setImage("LMove5.png");
                    delayAni = 10;
                }
                if (delayAni == 0 && ImageVisitor.equal(getImage(), new GreenfootImage("LMove5.png"))) {
                    setImage("LMove6.png");
                    delayAni = 10;
                }
                if (delayAni == 0 && ImageVisitor.equal(getImage(), new GreenfootImage("LMove6.png"))) {
                    setImage("LMove7.png");
                    delayAni = 10;
                }
                if (delayAni == 0 && ImageVisitor.equal(getImage(), new GreenfootImage("LMove7.png"))) {
                    setImage("LMove8.png");
                    delayAni = 10;
                }
            }
        }
    }
    
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
