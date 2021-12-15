import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Warp here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Warp extends Actor
{   
    String worldDestination;
    int destinationX, destinationY;
    int lv;
    
    public Warp(int stage) {
        lv = stage;
        getImage().setTransparency(0);
    }
    /**
     * Act - do whatever the Warp wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if(isTouching(Player.class)) {
            switch(lv) {
                case 1:
                    Greenfoot.setWorld(new Lv2());
                    break;
                case 2:
                    Greenfoot.setWorld(new Lv3());
                    break;
                case 3:
                   // Greenfoot.setWorld(new TheEnd());
            }
        }
    }
}
