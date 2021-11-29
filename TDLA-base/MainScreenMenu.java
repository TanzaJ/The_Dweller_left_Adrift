import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MainScreenMenu here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MainScreenMenu extends Actor
{   
    String name;
    
    public MainScreenMenu(String name) 
    {
        this.name = name;
        setImage(name + "MenuButton.png");
    }
    /**
     * Act - do whatever the MainScreenMenu wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        switch(name)
        {
            case "Play": 
                if(Greenfoot.mouseClicked(this))
                {
                    Greenfoot.setWorld(new ImageScrollWorld());
                    this.setImage("PlayMenuButton.png");
                }
                break;
            case "Options": 
                if(Greenfoot.mouseClicked(this))
                {
                    Greenfoot.setWorld(new MainOptions());
                    this.setImage("OptionsMenuButton.png");
                }
                break;
            case "Credits": 
                if(Greenfoot.mouseClicked(this))
                {
                    Greenfoot.setWorld(new MainCredits());
                    this.setImage("CreditsMenuButton.png");
                }
                break;
        }
    }
}
