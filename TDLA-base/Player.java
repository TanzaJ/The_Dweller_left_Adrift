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
    private int lives;
    
    //Jump check
    private boolean isJump = false;
    
    // Textbox 
    private static boolean moreThanOne;
    
    
    private String bag;
    
    
    public Player() {
        direction = 'r';
        setImage("RStand.png");
        lives = 5;
    }
    public void returnSpeed(){
        
        
    }
    public void act()
    {
        if (delayAni > 0) delayAni--;
        getWorld().showText("" + lives, 500, 500);
        if (((scrollWorld) getWorld()).checkEnable())  {
            getWorld().setPaintOrder(Player.class);
            if (menuWaitTime > 0) 
                menuWaitTime--; // only decrease when enable and time > 0
            if (menuWaitTime == 0 && Greenfoot.isKeyDown("escape")) {
                getWorld().addObject(new EscMenu(), getWorld().getWidth() / 2, getWorld().getHeight() / 2);
                ((scrollWorld) getWorld()).setEnable(false); // disable movement
                menuWaitTime = 40; // set wait time
                Greenfoot.playSound("menuInteract.wav"); // activate the menu sound
            }
            checkKey();
            checkLife();
            fall();
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
            viewMoreThanOne();
        }
    }
    /**
     * horizontal move method
     */
    public void hMove () {
        setLocation(getX() + hSpeed, getY());
    }
    
    /**
     * checking if touching wall
     */
    public boolean checkRightWall() {
        int w = getImage().getWidth(); 
        int h = getImage().getHeight();
        
        if (hSpeed > 0 && 
           (getOneObjectAtOffset(w / 2 + 8, h * -1/3, Floor.class)) != null ||
            getOneObjectAtOffset(w / 2 + 8, 0, Floor.class) != null ||
            getOneObjectAtOffset(w / 2 + 8, h * 1/3, Floor.class) != null) {
                return true;        
        }
        return false;
    }
    public boolean checkLeftWall() {
        int w = getImage().getWidth(); 
        int h = getImage().getHeight();
        
        if (hSpeed < 0 && 
           (getOneObjectAtOffset(w / -2 - 8, h * -1/3, Floor.class)) != null ||
            getOneObjectAtOffset(w / -2 - 8, 0, Floor.class) != null ||
            getOneObjectAtOffset(w / -2 - 8, h * 1/3, Floor.class) != null) {
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

        if ((getOneObjectAtOffset(w / -2 + 5, h / 2, Floor.class) != null ||
            getOneObjectAtOffset(w / 2 - 5, h / 2, Floor.class) != null) ||
            getOneObjectAtOffset(0, h / 2, Floor.class) != null) {
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
            //Greenfoot.playSound("collision.wav");
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
            dashingTime = 13;
            dashCD = 60;
        }
        //horizontal move
        if (!Greenfoot.isKeyDown("left") && !Greenfoot.isKeyDown("right")) hSpeed = 0;
        if (dashingTime == 0 && !checkLeftWall() && Greenfoot.isKeyDown("left")) {
             hSpeed = -2;
             direction = 'l';
             hMove();
             // Greenfoot.playSound("walking.wav"); // walking sound
        }
        if (dashingTime == 0 &&  !checkRightWall() && Greenfoot.isKeyDown("right")) {
            hSpeed = 2;
            direction = 'r';
            hMove();
            // Greenfoot.playSound("walking.wav"); // walking sound
        }
        //vertical move:
        int jumpHeight = -15;
        if (checkGround()  && Greenfoot.isKeyDown("space") && !isJump) {
            vSpeed = jumpHeight;
            Greenfoot.playSound("jump.mp3"); // jumping sound
            isJump = true;
        }
        if (!Greenfoot.isKeyDown("space") && isJump) {
            isJump = false;
        }
    }
 
    /**
     * Set Image
     */
    public void setAni() {
        if (delayAni > 0) delayAni--; // animation delay time decrease
        if (direction == 'l') { // if direction = left 
            //dash
            if (dashingTime > 1) {
                setImage("LDash.png");
            }
            //standing
            if (checkGround() && hSpeed == 0) {
                setImage("LStand.png");
            }
            
            // moving
            if (dashingTime == 0 && checkGround() && Greenfoot.isKeyDown("left")) {
                boolean equal_to_one = false;
                for (int i = 1; i <= 6; i++) {
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
                if (delayAni == 0 && ImageVisitor.equal(getImage(), new GreenfootImage("LMove" + (curImg % 6 + 1) + ".png"))) {
                    setImage("LMove" + ((curImg + 1) % 6 + 1) + ".png");
                    delayAni = 13;
                    curImg++;
                }
            }
            if (vSpeed < 0) setImage("LJump.png");
        }
        if (direction == 'r') {
            //dashing
            if (dashingTime > 1) {
                setImage("RDash.png");
            }
            //standing
            if (checkGround() && hSpeed == 0) {
                setImage("RStand.png");
            }
            //moving
            if (dashingTime == 0 && checkGround() && Greenfoot.isKeyDown("right")) {
                boolean equal_to_one = false;
                for (int i = 1; i <= 6; i++) {
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
                if (delayAni == 0 && ImageVisitor.equal(getImage(), new GreenfootImage("RMove" + (curImg % 6 + 1) + ".png"))) {
                    setImage("RMove" + ((curImg + 1) % 6 + 1) + ".png");
                    delayAni = 13;
                    curImg++;
                }
            }
            if (vSpeed < 0) setImage("RJump.png");
        }
    }

    public void checkLife() {
        if (lives <= 0) {
            Greenfoot.setWorld(new GameOver());
        }
    }
    
    public void loseLife(int amount) {
        this.lives--;
    }

    public static boolean viewMoreThanOne(){
        return moreThanOne;
    }
}
