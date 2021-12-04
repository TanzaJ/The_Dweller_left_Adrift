import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class ObjectNpc here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ObjectNpc extends Actor

{
     // To check if player is near Npc
    private InteractIcon interactIcon = new InteractIcon();
    private int spawn1 = 0;
    private boolean isSpoken; //already speak or not
    public ObjectNpc(){
         boolean isSpoken;
    }
    public void act(){
        check();
        displayInteract();
        createObjectText();
    }
    
    public void check(){
        if (!isSpoken && isTouching(Player.class)){
            getWorld().addObject(interactIcon, this.getX(), this.getY() - 100);
        }
        if (!isTouching(Player.class)){
            getWorld().removeObject(interactIcon);
        }

    }
    public void createObjectText(){
        if (isTouching(Player.class) && Greenfoot.isKeyDown("e") && !isSpoken){
            TextBox textBox = new TextBox();
            getWorld().addObject(new TextBox(), this.getX(), this.getY() - 250);
            isSpoken = true;
            Player.setEnable(false);
        }
    }
    public void displayInteract(){
        
    }
}
