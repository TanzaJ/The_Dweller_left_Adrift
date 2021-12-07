import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class ObjectNpc here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Npc extends NPCS

{
     // To check if player is near Npc
    private InteractIcon interactIcon = new InteractIcon();
    private int spawn1 = 0;
    private boolean isSpoken; //already speak or not
    
    public Npc(){
        boolean isSpoken;
    }
    public void act(){
        check();
        displayInteract();
        //createObjectText();
    }
    
    public void check(){
        if (!isSpoken && isTouching(Player.class)){
            getWorld().addObject(interactIcon, getWorld().getWidth() / 2, this.getY() - getImage().getHeight() * 4/5);
            if (Greenfoot.isKeyDown("e"))
                createObjectText();
        }
        if (!isTouching(Player.class) || isSpoken){
            getWorld().removeObject(interactIcon);
        }

    }
    public void createObjectText(){
        //if (isTouching(Player.class) && Greenfoot.isKeyDown("e") && !isSpoken){
            TextBox textBox = new TextBox();
            getWorld().addObject(new TextBox(), this.getX(), this.getY() - 250);
            isSpoken = true;
            Player.setEnable(false);
        //}
    }
    public void displayInteract(){
        
    }
}
