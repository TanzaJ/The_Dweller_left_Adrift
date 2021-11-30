import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class TextBox here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TextBox extends Objects
{
    private String[] texts = {"", "hello", "This a test","die in a fire", null};
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
        Textbox();
        Text();
    }
    public void Textbox(){
        if (!isPressed && Greenfoot.isKeyDown("e") && i < texts.length - 1){
            i++;
            isPressed = true;
        }
        if (isPressed && !Greenfoot.isKeyDown("e")){
            isPressed = false;
        }
    }
    
    public void Text(){
        getWorld().showText(texts[i], this.getX() + 5, this.getY() - 5 );
        if (i == texts.length - 1){
            getWorld().removeObject(this);
            Player.setEnable(true);
        }
    }

}
