import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class test here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Lava extends SmoothMover
{
    /**
     * Act - do whatever the test wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private GreenfootImage lava = new GreenfootImage("lava3.png");
    private GreenfootImage lava2 = new GreenfootImage("lava4.png");
    private int x = 0;
    private int speedDelay = 0;
    
    public void act() 
    {
        animate();
        rise();
    }
    
    public void animate()
    {
        if (x == 0 && x <= 50)
            setImage(lava);
        if (x == 51 && x <= 100)
            setImage(lava2);
        x++;
        x = x % 100;
    }
    
    public void rise()
    {
        if(speedDelay%3==0) {
        setLocation(getX(), getY() - 1);
    }
    speedDelay++;
    }
    
}
