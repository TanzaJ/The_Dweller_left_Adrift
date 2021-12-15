import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Spikes here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Spikes extends Collisions
{
    
    private boolean isTouchingSpikes = false;
    
    public Spikes(int width, int height){
        getImage().scale(width, height);
        getImage().setTransparency(0);
    }
    /**
     * Act - do whatever the Spikes wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        touchSpike(); // checking collision with spikes
    }

    /**
     * Check to see if Player is touching the spikes
     */
    public void touchSpike(){

        if (isTouching(Player.class)){
            Player player = (Player)getWorld().getObjects(Player.class).get(0);
            player.loseLife(1); // a method declared in player.class
            player.setLocation(35, 450);
        }
    }
}
