


import greenfoot.*;
public class bossStar extends SmoothMover
{
    /**
     * Act - do whatever the bossStar wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private boolean direction = false; 
    private int health;
    public bossStar(int h) {
     health = h;   
    }
    public void act() 
    {
        
        if(direction ==false) {
         turn(Greenfoot.getRandomNumber(360));
         direction = true;
        }
        move(10);
        if( isAtEdge() && health > 2)
        { 
          getWorld().removeObject(this);
        }
        else if(isAtEdge() && health <= 2)
        turn(Greenfoot.getRandomNumber(360));
    }   
    
    
}
