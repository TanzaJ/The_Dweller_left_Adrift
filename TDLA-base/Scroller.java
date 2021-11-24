import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This is a support class for a scrolling wolrd. It contains two constructors;
 * one for unlimited scrolling and one for limited scrolling. Both constructors have 'image'
 * parameter. Because image manipulation can hog up CPU time, it is important to remember that
 * it is better not to have a scrolling background image
 * For unlimited scrolling using a background image, the smalle that background image to be tiled,
 * the better. Making the view port (the size of the world that is visible) smaller can help in
 * CPU expense, also. Scrolling world should be unbounded, allowing actors to move beyong the visible area.
 * Ensuring that actors are removed from the world if no longer needed when out of view will help to prevent
 * las as well.
 * 
 * It is the responsibility of the World object that creates a Scroller object to determine whne to scroll and by how much.
 * 
 * @author danpost
 */
public class Scroller
{
    private World world; //view window world
    private GreenfootImage scrollImage; //scrolling image
    private boolean limited; // flag to indicate whether scrolling is limited or not
    private int scrolledX, scrolledY; //current scrolled distances
    private int width, height; // if limited, dimensions of scrollign area else of image to wrap
    
    /**
     * This constructor is for an unlimited scrolling world;
     * If 'image' is null, the background will not change; else the given iamge is wrapped
     * 
     * @param viewWorld
     * @param image the background iamge that will tiled, if needed, and wrap with scrolling
     */
    public Scroller(World viewWorld, GreenfootImage image) {
        world = viewWorld;
        scrollImage = image;
        if (image != null) {
            width = image.getWidth();
            height = image.getHeight();
        }
        scroll(0,0); // set initial backgroudn image
        
    }
    
    public Scroller(World viewWorld, GreenfootImage image, int width, int height) {
        this.width = width;
        this.height = height;
        limited = true;
        world = viewWorld;
        if (image != null) {
            //create an image as large as scrolling area; tiled if needed
            scrollImage = new GreenfootImage(width * world.getCellSize(), height * world.getCellSize());
            for (int x = 0; x < width * world.getCellSize(); x += image.getWidth())
                for (int y = 0; y < height * world.getCellSize(); y += image.getHeight())
                    scrollImage.drawImage(image, x, y);
            // set initial backgound image
            scroll(0,0);
        }
    }
    public void scroll(int dsx, int dsy) {
        //adjust scroll amounts and scroll background image
        if (limited) {
            //caculate limits of scrolling
            int maxX = width - world.getWidth();
            int maxY = height - world.getHeight();
            // apply limits to distances to scroll
            if (scrolledX + dsx < 0) dsx = -scrolledX;
            if (scrolledX + dsx >= maxX) dsx = maxX - scrolledX;
            if (scrolledY + dsy < 0) dsy = -scrolledY;
            if (scrolledY + dsy >= maxY) dsy = maxY - scrolledY;
            //update scroll positions
            scrolledX += dsx;
            scrolledY += dsy;
            //scroll background image
            if (scrollImage != null) {
                world.getBackground().drawImage
                (
                scrollImage,
                -scrolledX * world.getCellSize(),
                -scrolledY * world.getCellSize()
                );
            }
        } else {
            //unlimimted iamge wrapping
            //update scroll positions
            scrolledX += dsx;
            scrolledY += dsy;
            //scrolled background iamge
            if (scrollImage != null) {
                //create working variables of scroll positions
                int imageX = scrolledX * world.getCellSize();
                int imageY = scrolledY * world.getCellSize();
                // get near-zero starting position for drawing 'scrollImage'
                imageX = imageX % width;
                imageY = imageY % height;
                //adjust negative values as needed
                if (imageX < 0) imageX += width;
                if (imageY < 0) imageY += height;
                //create iamge of appropriate size and tile fill 'scrollImage' onto it
                GreenfootImage hold = new GreenfootImage(scrollImage);
                hold.drawImage(scrollImage, -imageX, -imageY);
                if (imageX > 0) hold.drawImage(scrollImage, width - imageX, -imageY);
                if (imageY > 0) hold.drawImage(scrollImage, -imageX, height - imageY);
                if (imageX > 0 && imageY > 0)
                    hold .drawImage(scrollImage, width - imageX, height - imageY);
                // set image to backgound of 'world'
                world.setBackground(hold);
            }
        }
        //adjust position of all actors (that can move with 'setLocation')
        for (Object obj : world.getObjects(null)) {
            Actor actor = (Actor) obj;
            actor.setLocation(actor.getX() - dsx, actor.getY() - dsy);
        }
    }
    
    /**
     * getter method for the current total scrolled disctance horizontally
     * 
     * @return the current total offset of horizontal scrolling
     */
    public int getScrolledX() {
        return scrolledX;
    }
    
    /**
     * getter method for the current total scrolled disctance vertically
     * 
     * @return the current total offset of vertical scrolling
     */
    public int getScrolledY() {
        return scrolledY;
    }
}
