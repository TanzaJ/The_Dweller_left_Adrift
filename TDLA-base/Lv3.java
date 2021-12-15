import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Lv3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Lv3 extends scrollWorld
{

    /**
     * Constructor for objects of class Lv3.
     * 
     */
    public Lv3()
    {
        super(900, 600, 1, "bgLv3.png", 1943);
        addObject(new Floor(getBackground().getWidth(), 30), getWidth() / 2 + 300, 495);
        addObject(new Floor(25, 3000), 0, 750);
        addObject(new Floor(25, 3000), 1500, 750);
        prepare();
    }
    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        addObject(new Spikes(95, 20), 170, 475);
        addObject(new Spikes(125, 20), 192, 390);
        addObject(new Spikes(20, 20), 260, 475);
        addObject(new Spikes(127, 22), 394, 390);
        addObject(new Spikes(95, 20), 378, 475);
        addObject(new Spikes(1000, 20), 1020, 470);
        
        addObject(new Floor(25, 20), 535, 430);
        addObject(new Floor(125, 20), 626, 400);
        addObject(new Floor(25, 20), 535, 300);
        addObject(new Floor(160, 50), 192, 355);
        addObject(new Floor(160, 50), 395, 355);
        addObject(new Floor(31, 20), 291, 340);
        addObject(new Floor(20, 250), 320, 100);
        addObject(new Spikes(20, 35), 290, 320);
        addObject(new Spikes(20, 30), 292, 240);
        addObject(new Floor(132, 20), 107, 255);
        addObject(new Floor(25, 25), 203, 188);
        
        addObject(new Floor(33, 32), 98, 160);
        addObject(new Floor(25, 25), 203, 90);
        addObject(new Floor(25, 25), 107, 60);
        addObject(new Floor(25, 25), 203, 10);
        addObject(new Spikes(20, 255), 240, 95);
        addObject(new Floor(67, 60), 292, -10);
        
        addObject(new Floor(25, 25), 400, -50);
        addObject(new Spikes(15, 25), 469, -54);
        addObject(new Floor(72, 25), 521, -43);
        addObject(new Spikes(15, 25), 573, -54);
        addObject(new Floor(25, 25), 343, -130);
        addObject(new Floor(25, 25), 463, -126);
        addObject(new Floor(25, 25), 410, -207);
        addObject(new Floor(25, 25), 352, -282);
        addObject(new Floor(25, 25), 299, -360);
        addObject(new Floor(27, 25), 267, -475);
        addObject(new Floor(27, 25), 330, -559);
        addObject(new Floor(27, 25), 397, -635);
        addObject(new Floor(27, 25), 462, -715);
        addObject(new Floor(27, 25), 557, -788);
        addObject(new Floor(29, 25), 452, -860);
        addObject(new Floor(29, 25), 383, -942);
        addObject(new Floor(29, 25), 310, -1032);
        addObject(new Floor(29, 25), 240, -1112);
        addObject(new Floor(160, 110), 70, -1070);
        
        addObject(new Floor(29, 25), 315, -1200);
        addObject(new Floor(1030, 20), 998, -1235);
        addObject(new Warp(3), 1420, -1280);
    }
}
