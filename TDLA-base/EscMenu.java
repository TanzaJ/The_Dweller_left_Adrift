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
    MenuArrowButton arrow = new MenuArrowButton("EscMenu1");
    public EscMenu() {
        getImage().scale(800, 500);
    }
    /**
     * Act - do whatever the Menu wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {   
        // the code above is trying to let the escMenu know which world to set
        if (arrow != null) getWorld().addObject(arrow, 100, getWorld().getHeight() - 100);
        if (menuWaitTime > 0) 
            menuWaitTime--;
        if (menuWaitTime == 0 && (Greenfoot.isKeyDown("escape") || Greenfoot.mouseClicked(arrow))) {
            getWorld().removeObject(arrow);
            ((ImageScrollWorld) getWorld()).setEnable(true);
            getWorld().removeObject(this);
        }
    }
    
}
