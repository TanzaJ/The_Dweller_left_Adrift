import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MenuArrowButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MenuArrowButton extends Menus
{   
    String name;
    public MenuArrowButton(String name) 
    {
        this.name = name;
    }
    
    /**
     * Act - do whatever the MenuArrowButton wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {   
        // gonna allow to the button to see where it has been activated
        if(Greenfoot.mouseClicked(this)) {
            switch (name) {
                case "Options":
                    Greenfoot.setWorld(new MainOptions());
                    break;
                case "MainScreen":
                    Greenfoot.setWorld(new MainScreen());
                    break;
                case "EscMenu1":
                    Greenfoot.setWorld(new ImageScrollWorld());
            }
            // this.setImage("ArrowMenuButton.png");
        }
    }
}
