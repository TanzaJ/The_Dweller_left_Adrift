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
                        Greenfoot.setWorld(new ImageScrollWorld(900, 600, 1));
                        this.setImage("PlayMenuButton.png");
                    break;
                case "options": 
                        Greenfoot.setWorld(new MainOptions());
                        this.setImage("OptionsMenuButton.png");
                    break;
                case "credits": 
                        Greenfoot.setWorld(new MainCredits());
                        this.setImage("CreditsMenuButton.png");
                    break;
            }
    }
}
