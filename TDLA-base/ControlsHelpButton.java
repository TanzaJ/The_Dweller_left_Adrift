import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Controls here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ControlsHelpButton
 extends MenuUtils
{
    /**
     * Act - do whatever the Controls wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if(Greenfoot.mouseClicked(this)) {
            getWorld().addObject(new ControlsHelp(), getWorld().getWidth() / 2, getWorld().getHeight() / 2);
            getWorld().addObject(new MenuArrow("closeControls"), 50, getWorld().getHeight() - 50);
            
            getWorld().removeObject(this);
        }    
    }
}
