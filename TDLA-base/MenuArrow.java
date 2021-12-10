import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Write a description of class MenuArrowButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MenuArrow extends MenuUtils
{   
    private int menuWaitTime = 40; //before can press esc again
    String name;
    public MenuArrow(String name) 
    {
        this.name = name;
    }
    
    /**
     * Act - do whatever the MenuArrowButton wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {   
        if(Greenfoot.mousePressed(this)) {
            
        }
        // gonna allow to the button to see where it has been activated
        if(Greenfoot.mouseClicked(this)) {
            switch (name) {
                case "closeOptions":
                    Actor optionsMenu = (Actor)getWorld().getObjects(MenuBackground.class).get(0);
                    if (optionsMenu != null) getWorld().removeObject(optionsMenu);
                    
                    Actor ctrlButton = (Actor)getWorld().getObjects(ControlsHelpButton.class).get(0);
                    if (ctrlButton != null) getWorld().removeObject(ctrlButton);
                    
                    getWorld().removeObject(this);
                    break;
                    
                //case "MainScreen":
                //    Greenfoot.setWorld(new StartScreen());
                //    break;
                //case "EscMenu1":
                    //getWorld().removeObject(this);   
                case "closeControls":
                    Actor controlsHelp = (Actor)getWorld().getObjects(ControlsHelp.class).get(0);
                    if (controlsHelp != null) getWorld().removeObject(controlsHelp);
                    getWorld().addObject(new ControlsHelpButton(), getWorld().getWidth() / 2, getWorld().getHeight() / 2);
                    getWorld().removeObject(this);
                    break;
            }
            // this.setImage("ArrowMenuButton.png");
        }

    }
    
    public void removeSelf() {
       // getWorld().showText("2", 500, 500);
       // getWorld().removeObject(this);
    }
}
