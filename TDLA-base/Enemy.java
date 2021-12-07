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
    /**
     * @param type 1 = ground, 2 = fy
     * @param level monster's lv
     */
    public Enemy(int level) // level only needed if wanted enemy be stronger each stage
    {
        dir = 'r';
        hp = 100;
        armor = 17;
        dmg = 10;
        break;
    }
    
    public void act()
    {
        if (getX() - player.getX() > 0) dir = 'l';
        else dir = 'r';
        hSpeed = (dir == 'r') ? 10 : -10;
    }
    
    private void move() {
        Actor player = (Actor) getWorld().getObject(Player.class).get(0);
        
        if (isSpot() && !isAttacking())
            setLocation(getX() + hSpeed, getY());
    }
    private boolean isSpot() // detect player in range (currently, radius = 20)
    {
        return getObjectsInRange(10, Player.class).isEmpty();
    }
    
    private boolean isAttacking() {
        return getObjectInRange(getImage().getWidth() / 2, Player.class);
    }
    
    private void attack() {
        if (isAttacking())
            //  attack codes
    }
    
    private void setAnimation() {
        
    }
}
