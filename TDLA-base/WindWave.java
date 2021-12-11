import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class WindWave here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WindWave extends Actor
{
    int hSpeed;
    int existTime;
    public WindWave(char direction) {
        hSpeed = (direction == 'l') ? -7 : 7;
        existTime = 100;
        setImage((direction == 'l') ? "LWindWave.png" : "RWindWave.png");
    }
    public void act()
    {
        if (existTime > 0) existTime--;
        if (this != null) setLocation(getX() + hSpeed, getY());
        if (existTime == 0 && this != null) getWorld().removeObject(this);

    }
}
