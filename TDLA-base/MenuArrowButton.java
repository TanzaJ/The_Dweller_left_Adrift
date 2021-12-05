import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MenuArrowButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MenuArrowButton extends Menus
{
    /**
     * Act - do whatever the MenuArrowButton wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if(Greenfoot.mouseClicked(this)) {
            Greenfoot.setWorld(new MainScreen());
            // this.setImage("ArrowMenuButton.png");
        }
    }
}
