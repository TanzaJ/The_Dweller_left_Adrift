import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class ObjectNpc here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ObjectNpc extends Actor

{
    private int isTouchingPlayer = 0; // To check if player is near Npc
    private InteractIcon interactIcon = new InteractIcon();
    private int spawn1 = 0;
    public void act(){
        check();
        displayInteract();
    }
    
    public void check(){
        if (isTouchingPlayer == 0 && isTouching(Player.class)){
            getWorld().addObject(interactIcon, this.getX(), this.getY() - 100);
        }
        if (Player.viewMoreThanOne()){
            isTouchingPlayer = 1;
        }
        if (!isTouching(Player.class) || isTouchingPlayer == 1){
            getWorld().removeObject(interactIcon);
        }

        }
    
    public void displayInteract(){
    }
}
