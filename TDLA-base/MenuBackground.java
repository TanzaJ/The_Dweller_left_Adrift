import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MainOptions here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MenuBackground extends Menus
{
    public MenuBackground() {
    }
    public void act()
    {
        if (this != null) {
            getWorld().setPaintOrder(MenuArrow.class, ControlsHelp.class, ControlsHelpButton.class, MenuBackground.class);
        }
    }
}
