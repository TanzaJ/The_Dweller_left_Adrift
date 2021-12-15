import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Lv2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Lv2 extends scrollWorld
{

    /**
     * Constructor for objects of class Lv2.
     * 
     */
    public Lv2()
    {
        super(900, 600, 1, "bgLv2.png", 1945);
        addObject(new Floor(getBackground().getWidth(), 30), getWidth() / 2 + 300, 492);
        addObject(new Floor(25, 1000), 0, 0);
        addObject(new Floor(25, 1000), 1500, 0);
        prepare();
    }

    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        addObject(new Spikes(1038, 29), 593 , 457);
        addObject(new Spikes(100, 29), 1310 , 457);
        addObject(new Floor(75, 15), 170, 385);
        addObject(new Spikes(15, 30), 200, 365);
        addObject(new Spikes(15, 28), 338, 220);
        addObject(new Floor(28, 15), 110,310);
        addObject(new Floor(16, 18), 181, 244);
        addObject(new Spikes(15, 18), 180, 268);
        addObject(new Floor(23, 30), 268, 180);
        addObject(new Spikes(15, 20), 200, 221);
        addObject(new Spikes(16, 23), 223, 190);
        addObject(new Spikes(16, 23), 317, 190);
        addObject(new Spikes(16, 23), 400, 195);
        addObject(new Floor(28, 25), 458, 189);
        addObject(new Floor(20, 40, 51), 433, 198);
        addObject(new Floor(20, 42, 129), 482, 198);
        addObject(new Spikes(16, 23), 519, 195);
        addObject(new Floor(33, 110), 601, 261);
        addObject(new Floor(203, 40), 757, 362);
        addObject(new Spikes(18, 350), 670, 25);
        addObject(new Spikes(18, 350), 752, 25);
        addObject(new Floor(20, 25), 883, 265);
        addObject(new Floor(20, 25), 882, 125);
        addObject(new Floor(20, 25), 882, 0);
        addObject(new Floor(86, 20), 811, 208);
        addObject(new Floor(86, 20), 808, 58);
        addObject(new Floor(86, 20), 808, -75);
        
        addObject(new Floor(52, 50), 712, -146);
        addObject(new Floor(20, 26), 805, -159);
        addObject(new Floor(20, 26), 747, -246);
        addObject(new Floor(87, 20), 871, -287);
        addObject(new Spikes(18, 350), 752, 25);
        addObject(new Floor(52, 50), 952, -148);
        addObject(new Spikes(18, 350), 913, 25);
        addObject(new Spikes(21, 25), 940, -193);
        
        addObject(new Floor(23, 28), 1082, -162);
        addObject(new Floor(23, 27), 1120, -1);
        addObject(new Floor(23, 27), 1164, 163);
        addObject(new Floor(23, 28), 1209, 331);
        
        addObject(new Warp(2), 1490, 455);
    }
}
