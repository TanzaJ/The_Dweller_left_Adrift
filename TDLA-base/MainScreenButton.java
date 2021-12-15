import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MainScreenButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MainScreenButton extends MenuUtils
{
    String name;
    int iniW;
    int iniH;
    private boolean flashed;
    private boolean mouseOver;
    
    private MenuBackground menu = new MenuBackground();
    
    public MainScreenButton(String name, int width, int height) 
    {
        this.name = name;
        setImage(name + ".png");
        getImage().scale(width, height);
        iniW = width;
        iniH = height;
        flashed = false;
        mouseOver = false;
    }
        
    public void act()
    {
        //mouse on
        if (!mouseOver && Greenfoot.mouseMoved(this))
            switch(name)
            {
                case "play": 
                        getImage().scale(getImage().getWidth() * 4/5, getImage().getHeight() * 4/5);
                        mouseOver = true;
                    break;
                case "options": 
                        getImage().scale(getImage().getWidth() * 4/5, getImage().getHeight() * 4/5);
                        mouseOver = true;
                    break;
                case "credits": 
                        getImage().scale(getImage().getWidth() * 4/5, getImage().getHeight() * 4/5);
                        mouseOver = true;
                    break;
            } 
        if (mouseOver && Greenfoot.mouseMoved(null) && !Greenfoot.mouseMoved(this)) {
            getImage().scale(iniW, iniH);
            mouseOver = false;
        }
        
            
        //click on
        if(Greenfoot.mouseClicked(this))
            switch(name)
            {
                case "play": 
                        ((StartScreen) getWorld()).stopMusic();
                        Greenfoot.setWorld(new Lv1());
                        break;
                        //this.setImage("PlayMenuButton.png");
                case "options": 
                        getWorld().addObject(menu, getWorld().getWidth() / 2, getWorld().getHeight() / 2);
                        getWorld().addObject(new ControlsHelpButton(), getWorld().getWidth() / 2, getWorld().getHeight() / 2);
                        getWorld().addObject(new MenuArrow("closeOptions"), 50, getWorld().getHeight() - 50);
                        break;
                        //getWorld().addObject(new MenuArrow("closeOptions"), 50, getWorld().getWidth() - 50);
                        
                        //this.setImage("OptionsMenuButton.png");
                case "credits": 
                        Greenfoot.setWorld(new MainCredits());
                        break;
                        //this.setImage("CreditsMenuButton.png");
            }
    }
}
