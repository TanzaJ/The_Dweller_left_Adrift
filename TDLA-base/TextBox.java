import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class TextBox here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TextBox extends Objects
{
    public static String[] texts = {"", "hello", "This a test","Which came first the fruit or the color orange?", null};
    private int i;
    private boolean isPressed = false;
    private boolean exists = false;
    
    public TextBox(){
    }
    /**
     * Act - do whatever the text wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        getImage().scale(getWorld().getWidth(), 150);
        textbox();
        text();
    }
    public void textbox(){
        if (!isPressed && Greenfoot.isKeyDown("e") && i < texts.length - 1){
            i++;
            isPressed = true;
        }
        if (isPressed && !Greenfoot.isKeyDown("e")){
            isPressed = false;
        }
    }
    
    public void text(){
        getWorld().showText(texts[i], this.getX() + 5, this.getY() - 5 );
        if (i == texts.length - 1){
            getWorld().removeObject(this);
            Player.setEnable(true);
        }
    }
    
}
