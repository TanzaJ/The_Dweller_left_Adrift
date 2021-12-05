import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Enemy here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Enemy extends Actor
{
    private double hp;
    private double armor;
    private double dmg;
    private int type; //1 ground, 2 fly, 3 elite monster (if wanted)
    private int hSpeed = 2;
    private boolean inBattle;
    private int wanderTime;
    /**
     * @param type 1 = ground, 2 = fy
     * @param level monster's lv
     */
    public Enemy(int type, int level) // level only needed if wanted enemy be stronger each stage
    {
        hp = 100; //base hp for monster
        switch (type) {
            case 1:
                 armor = 17;
                 dmg = 10;
                 break;
             case 2:
                 armor = 10;
                 dmg = 20;
        }
    }
    
    public void act()
    {
        
    }
    
    private void move() {
        
    }
    private void isSpot() // detect player in range (currently, radius = 20)
    {
        if (getObjectsInRange(0, Player.class).isEmpty()) inBattle = false;
        else inBattle = true;
    }
    
    private void attack() {
        
    }
    
    private void beHit() {
        
    }
}
