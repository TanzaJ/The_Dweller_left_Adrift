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
    private int menuWaitTime = 0; // time before can press esc again
    private static int delayAni = 0;
    private int curImg; // use if have more than 2 imgs for animation
    
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
    
        direction = 'r';
        setImage("RStand1.png");
    }
    public void returnSpeed(){
        
        
    }
    public void act()
    {
        getWorld().showText("" + delayAni, 600, 500);
        if (delayAni > 0) delayAni--;
        if (((ImageScrollWorld) getWorld()).checkEnable())  {
            getWorld().setPaintOrder(Player.class);
            if (menuWaitTime > 0) 
                menuWaitTime--; // only decrease when enable and time > 0
            if (menuWaitTime == 0 && Greenfoot.isKeyDown("escape")) {
                getWorld().addObject(new EscMenu(), getWorld().getWidth() / 2, getWorld().getHeight() / 2);
                ((ImageScrollWorld) getWorld()).setEnable(false); // disable movement
                menuWaitTime = 40; // set wait time
                Greenfoot.playSound("menuInteract.wav"); // activate the menu sound
            }
            checkKey();
            setAni();
            
            if (dashCD > 0) dashCD--;
            if (dashingTime > 0) {
                vSpeed = 0;
                int accelaration = (direction == 'l') ? -10 : 10;
                if (direction == 'l' && checkLeftWall()) hSpeed = 0;
                else if (direction == 'r' && checkRightWall()) hSpeed = 0;
                else hSpeed = accelaration;
                hMove();
                dashingTime--;
            }
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
           (getOneObjectAtOffset(w / 2 + 8, h * -1/3, Collisions.class)) != null ||
            getOneObjectAtOffset(w / 2 + 8, 0, Collisions.class) != null ||
            getOneObjectAtOffset(w / 2 + 8, h * 1/3, Collisions.class) != null) {
                return true;        
        }
        return false;
    }
    public boolean checkLeftWall() {
        int w = getImage().getWidth(); 
        int h = getImage().getHeight();
        
        if (hSpeed < 0 && 
           (getOneObjectAtOffset(w / -2 - 8, h * -1/3, Collisions.class)) != null ||
            getOneObjectAtOffset(w / -2 - 8, 0, Collisions.class) != null ||
            getOneObjectAtOffset(w / -2 - 8, h * 1/3, Collisions.class) != null) {
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

        if ((getOneObjectAtOffset(w / -2 + 5, h / 2, Collisions.class) != null ||
            getOneObjectAtOffset(w / 2 - 5, h / 2, Collisions.class) != null)) {
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
        } else if (checkBumped()) {
            vSpeed = 0;
            // Greenfoot.playSound("collision.wav"); // bumped/collision sound
        }
        else vSpeed++;
    }
    
    public void checkKey() {
        //dashing
        if (dashCD == 0 && dashingTime == 0 && Greenfoot.isKeyDown("a")) {
            dashingTime = 15;
            dashCD = 90;
        }
        //horizontal move
        if (!Greenfoot.isKeyDown("left") && !Greenfoot.isKeyDown("right")) hSpeed = 0;
        if (dashingTime == 0 && !checkLeftWall() && Greenfoot.isKeyDown("left")) {
             hSpeed = -3;
             direction = 'l';
             hMove();
             // Greenfoot.playSound("walking.wav"); // walking sound
        }
        if (dashingTime == 0 &&  !checkRightWall() && Greenfoot.isKeyDown("right")) {
            hSpeed = 3;
            direction = 'r';
            hMove();
            // Greenfoot.playSound("walking.wav"); // walking sound
        }
        //vertical move:
        int jumpHeight = -15;
        if (checkGround()  && Greenfoot.isKeyDown("space")) {
            vSpeed = jumpHeight;
            Greenfoot.playSound("jump.wav"); // jumping sound
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
     * Set Image
     */
    public void setAni() {
        if (delayAni > 0) delayAni--; // animation delay time decrease
        if (dashingTime == 0 && vSpeed > 2) setImage("fall.png");
        if (direction == 'l') { // if direction = left 
            //dash
            if (dashingTime > 0) {
                boolean equal_to_one = false;
                for (int i = 1; i <= 4; i++) {
                    if (ImageVisitor.equal(getImage(), new GreenfootImage("LDash" + i + ".png")))  {
                        equal_to_one = true;
                        break;
                    }
                }
                if(!equal_to_one) {
                    setImage("LDash1.png");
                    delayAni = 0;
                    curImg = 0;
                }
                if (delayAni == 0 && ImageVisitor.equal(getImage(), new GreenfootImage("LDash" + (curImg % 4 + 1) + ".png"))) {
                    setImage("LDash" + ((curImg + 1) % 4 + 1) + ".png");
                    delayAni = 30 / 4;
                    curImg++;
                    //getWorld().showText("" + curImg, 600, 500);
                }
            }
            //standing
            if (checkGround() && hSpeed == 0) {
                if (delayAni == 0 && !ImageVisitor.equal(getImage(), new GreenfootImage("LStand1.png"))) {
                    setImage("LStand1.png");
                    delayAni = 180;
                    }
                if (delayAni == 0 && ImageVisitor.equal(getImage(), new GreenfootImage("LStand1.png"))) {
                    delayAni = 50;
                    setImage("LStand2.png");
                }
            }
            
            // moving
            if (dashingTime == 0 && checkGround() && Greenfoot.isKeyDown("left")) {
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
                    curImg = 0;
                }
                if (delayAni == 0 && ImageVisitor.equal(getImage(), new GreenfootImage("LMove" + (curImg % 8 + 1) + ".png"))) {
                    setImage("LMove" + ((curImg + 1) % 8 + 1) + ".png");
                    delayAni = 10;
                    curImg++;
                }
            }
            if (vSpeed < 0) setImage("LJump.png");
        }
        if (direction == 'r') {
            //dashing
            if (dashingTime > 0) {
                boolean equal_to_one = false;
                for (int i = 1; i <= 4; i++) {
                    if (ImageVisitor.equal(getImage(), new GreenfootImage("RDash" + i + ".png")))  {
                        equal_to_one = true;
                        break;
                    }
                }
                if(!equal_to_one) {
                    setImage("RDash1.png");
                    delayAni = 0;
                    curImg = 0;
                }
                if (delayAni == 0 && ImageVisitor.equal(getImage(), new GreenfootImage("RDash" + (curImg % 4 + 1) + ".png"))) {
                    setImage("RDash" + ((curImg + 1) % 4 + 1) + ".png");
                    delayAni = 15 / 4;
                    curImg++;
                }
            } 
            //standing
            if (checkGround() && hSpeed == 0) {
                if (delayAni == 0 && !ImageVisitor.equal(getImage(), new GreenfootImage("RStand1.png"))) {
                    setImage("RStand1.png");
                    delayAni = 180;
                    }
                if (delayAni == 0 && ImageVisitor.equal(getImage(), new GreenfootImage("RStand1.png"))) {
                    delayAni = 50;
                    setImage("RStand2.png");
                }
            }
            //moving
            if (dashingTime == 0 && checkGround() && Greenfoot.isKeyDown("right")) {
                boolean equal_to_one = false;
                for (int i = 1; i <= 8; i++) {
                    if (ImageVisitor.equal(getImage(), new GreenfootImage("RMove" + i + ".png")))  {
                        equal_to_one = true;
                        break;
                    }
                }
                if(!equal_to_one) {
                    setImage("RMove1.png");
                    delayAni = 0;
                    curImg = 0;
                }
                if (delayAni == 0 && ImageVisitor.equal(getImage(), new GreenfootImage("RMove" + (curImg % 8 + 1) + ".png"))) {
                    setImage("RMove" + ((curImg + 1) % 8 + 1) + ".png");
                    delayAni = 10;
                    curImg++;
                }
            }
            if (vSpeed < 0) setImage("RJump.png");
        }
    }
    
    public static boolean viewMoreThanOne(){
        return moreThanOne;
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
    
    public double getMaxHp() {
        return maxHp;
    }
    
    public double getHp() {
        return hp;
    }
}
