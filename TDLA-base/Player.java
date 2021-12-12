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
    private int beHitTime;
    private int attackTime;
    private int heavyAttackCD = 0;
    
    // Textbox 
    private static boolean moreThanOne;
    
    //Player stats:
    private final double maxHp = 100; // MaxHp + pre-initial (only for hpbar creation)
    private double hp = 100; // create hp variable + pre-initial (for hpBar creation)
    private double armor; // create armor variable
    private double lightDmg; // create lightDmg variable
    private double heavyDmg; // create heavyDmg variable
    
    private String bag;
    
    
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
            beHitEffect();
            
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
        if (checkGround()  && Greenfoot.isKeyDown("space")) {
            vSpeed = jumpHeight;
            Greenfoot.playSound("jump.mp3"); // jumping sound
        }
        
        if (checkGround() && Greenfoot.isKeyDown("e")) {
            ;
        }
    }
 
    /**
     * Set Image
     */
    public void setAni() {
        if (delayAni > 0) delayAni--; // animation delay time decrease
        if (heavyAttackCD > 0) heavyAttackCD--;
        if (attackTime > 0) {
            vSpeed = 0;
            attackTime--;
            
        }
        getWorld().showText("" + attackTime, 300, 500);
        
        if (dashingTime == 0 && vSpeed > 2) setImage("fall.png");
        if (direction == 'l') { // if direction = left 
            //dash
            if (attackTime == 0 && dashingTime > 0) {
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
            if (attackTime == 0 && checkGround() && hSpeed == 0) {
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
            //attack:
            //light attack
            if (attackTime == 0 && Greenfoot.isKeyDown("s")) {
                ;
            }
            //heavy attack
            if (heavyAttackCD == 0 && attackTime == 0 && Greenfoot.isKeyDown("d")) {
                getWorld().addObject(new WindWave(direction), getX() + 5, getY() - 55);
                attackTime = 14;
                heavyAttackCD = 200;
            }
            
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
    
    public void attack() {
            if (attackTime == 0 && Greenfoot.isKeyDown("s")) {
                attackTime = 25;
            }
    }
    
    public void beHitEffect() {
        int knockBackX = (direction == 'l') ? -3 : 3;
        int knockBackY = (direction == 'l') ? -2 : 2;
        while (beHitTime > 0) {
            setLocation(getX() + knockBackX, getY() + knockBackY);
            beHitTime--;
        }
    }
    
    /**
     * when enemy hit, call this method to do dmg
     */
    public void getHit(int dmg) {
        this.hp -= dmg * (1 - armor / 100);
        beHitTime = 10;
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
