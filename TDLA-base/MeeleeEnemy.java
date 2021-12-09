import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Enemy here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MeeleeEnemy extends Actor
{
    private double hp;
    private double armor;
    private double dmg;
    private int type; //1 ground, 2 fly, 3 elite monster (if wanted)
    private char dir;
    private int hSpeed;
    /**
     * @param level monster's lv
     */
    public MeeleeEnemy(int level) // level only needed if wanted enemy be stronger each stage
    {
        dir = 'r';
        hp = 100;
        armor = 17;
        dmg = 10;
    }
    
    public void act()
    {
        Actor player = (Actor) getWorld().getObjects(Player.class).get(0);
        if (getX() - player.getX() > 0) dir = 'l';
        else dir = 'r';
        hSpeed = (dir == 'r') ? 10 : -10;
        
        if (isSpot() && !isAttacking()) move();
        if (isAttacking()) attack();
    }
    
    private void move() {
        setLocation(getX() + hSpeed, getY());
    }
    private boolean isSpot() // detect player in range (currently, radius = 20)
    {
        boolean spotted= false;
        if (!getObjectsInRange(10, Player.class).isEmpty()) spotted = true;
        return spotted;
    }
    
    private boolean isAttacking() {
        boolean fighting = false;
        if (!getObjectsInRange(getImage().getWidth() / 2, Player.class).isEmpty()) 
            fighting = true;
        return fighting;
    }
    
    private void attack() {
        
    }
    
    private void setAnimation() {
        
    }
}
