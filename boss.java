import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
/**
 * Write a description of class boss here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class boss extends SmoothMover
{
    private GreenfootImage star = new GreenfootImage("bossStar.png");
    private int leftBorder = 100;
    private int rightBorder = 500;
    private int direction = 1;
    private boolean alive = true;
    private int count = 0;
    //private int timer = 0;
    private int strength = 3;
    private GreenfootImage ninja1 = new GreenfootImage("boss.png");
    private int x = 0;
    private int hitCnt = 0;
    public void act()
    {
        setLocation(getX()+direction, getY());
        if (getX()==leftBorder || getX()==rightBorder) {
            direction *= -1;

        }
        count++;
        if(count %50 ==0) {
            World world = getWorld();
            world.addObject(new bossStar(strength), getX(), getY());
        }
        List<lives> hearts = getWorld().getObjects(lives.class);
        for(lives heart : hearts) {
           if(heart.getType() == 0) {
           heart.setLives(strength);
          }
        }
        checkIfHit();
    }

    public void checkIfHit() {
        
       if(isTouching(Shuriken.class)) //get damage from stars
        { 
          if(hitCnt % 80 == 0) //makes sure there is leeway time
          {
                strength--;
                //System.out.println("boss has been hit!! Remaining health: " + strength);
          }
          hitCnt++;
        }
        
        if(strength <= 0) {
        alive = false;
        Space.setStageTwo(getWorld());
       }
    }

}
