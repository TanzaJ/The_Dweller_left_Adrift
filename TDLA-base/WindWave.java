import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Write a description of class WindWave here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WindWave extends Actor
{
    private int hSpeed;
    private int existTime;
    //private List<Enemies> hitting = getIntersectingObjects(Enemies.class);
    
    public WindWave(char direction) {
        hSpeed = (direction == 'l') ? -7 : 7;
        existTime = 100;
        setImage((direction == 'l') ? "LWindWave.png" : "RWindWave.png");
        getImage().scale (100, 170);
    }
    public void act()
    {
        getWorld().setPaintOrder(Player.class, Collisions.class, WindWave.class);
        if (existTime > 0) existTime--;
        if (this != null) {
            setLocation(getX() + hSpeed, getY());
            attack();
        }
        if (existTime == 0 && this != null) getWorld().removeObject(this);
    }
    
    public void attack() {
        //if (hitting != null) {
        //    for (Enemies enemyGotHit : hitting)
        //       ;
        //}
    }
}
