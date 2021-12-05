import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Menu here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EscMenu extends Menus
{
    private int menuWaitTime = 40; //before can press esc again
    
    /**
     * Act - do whatever the Menu wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if (menuWaitTime > 0) 
            menuWaitTime--;
        if (menuWaitTime == 0 && Greenfoot.isKeyDown("escape")) {
            getWorld().removeObject(this);
            Player.setEnable(true);
        }
    }
}
