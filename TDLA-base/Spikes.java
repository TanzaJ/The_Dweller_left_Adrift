import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Spikes here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Spikes extends Collisions
{
    // Death count
    private static int deathCount = 5;
    
    private boolean isTouchingSpikes = false;
    
    public Spikes(){
        deathCount = 5;
    }
    /**
     * Act - do whatever the Spikes wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        touchSpike();
        gameOver();
    }
    
    public void gameOver(){
        if (deathCount == 0){
            GameOver gameOver = new GameOver();
            Greenfoot.setWorld(gameOver);
        }
    }
    /**
     * Check to see if Player is touching the spikes
     */
    public void touchSpike(){

        if (isTouching(Player.class)){
            deathCount--;
            Player player = (Player)getWorld().getObjects(Player.class).get(0);
            player.setLocation(26, 548);
            getWorld().showText("Lives: " + deathCount , 448 , 338);
        }
    }
}
