import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class platform here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class platform extends SmoothMover
{
    
    private int delay = 0;
    private boolean move;
    /**
     * Act - do whatever the platform wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public platform(boolean move) {
       this.move = move; 
    }
    public void act() 
    {
        if(move==true) {
        shiftSide();
        }
        if(isTouching(Lava.class) == true) {
            getWorld().removeObject(this);
        }
    }
    
    public void shiftSide() {
    if(delay<50) {
        setLocation(getX()-1, getY());
        delay++;
    }
    else if(delay>=50 && delay < 100) {
        setLocation(getX()+1, getY()); 
        delay++;
        }
    else{
        delay = 0;
    }
    }
}
