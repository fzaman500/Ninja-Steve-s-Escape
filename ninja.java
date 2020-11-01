import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
/**
 * Write a description of class ninja here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ninja extends SmoothMover
{
    /**
     * Act - do whatever the ninja wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */

    private boolean isRight = true;
    private int shiftCnt = 0;
    private int walkCnt = 0;
    private int jumpCnt = 0;
    private int throwCnt = 0;
    private int fallCnt = 0;
    private int delay = 0;
    private int velocity = 0;
    private int acceleration = 1;
    private int jumpHeight = -18;
    private int Stage = 0;
    private int health = 3; //health bar
    private int hitCnt = 0; //makes sure no immediate death
    private static boolean generationFlag = true;

    
    private GreenfootImage idleLeft = new GreenfootImage("idle left.png");
    private GreenfootImage idleRight = new GreenfootImage("idle right.png");
    private GreenfootImage walkRight = new GreenfootImage("runNinja left.png");
    private GreenfootImage walkLeft = new GreenfootImage("runNinja right.png");
    private GreenfootImage jumpLeft = new GreenfootImage("jump left.png");
    private GreenfootImage landLeft = new GreenfootImage("land left.png");
    private GreenfootImage fallLeft = new GreenfootImage("flyNinja left.png");
    private GreenfootImage jumpRight = new GreenfootImage("jump right.png");
    private GreenfootImage landRight = new GreenfootImage("land left.png");
    private GreenfootImage fallRight = new GreenfootImage("flyNinja right.png");
    private GreenfootImage throwLeft1 = new GreenfootImage("throw1 left.png");
    private GreenfootImage throwLeft2 = new GreenfootImage("throw2 left.png");
    private GreenfootImage throwLeft3 = new GreenfootImage("throw3 left.png");
    private GreenfootImage throwRight1 = new GreenfootImage("throw1 right.png");
    private GreenfootImage throwRight2 = new GreenfootImage("throw2 right.png");
    private GreenfootImage throwRight3 = new GreenfootImage("throw3 right.png");
    private GreenfootImage deathLeft = new GreenfootImage("deadNinjaMirror.png");
    private GreenfootImage deathRight = new GreenfootImage("deadNinja right.png");

    
    public void act() 
    {
        checkKey();
        if(checkPlatform() == true) 
        {
            velocity = 0;
            checkKey();
        }
        if(checkPlatform() == false)
        {
            fall();    
        }
        if(isTouching(bossStar.class)) //get damage from stars
        { 
            if(hitCnt % 40 == 0) //makes sure there is leeway time
            {
                health--;
                //System.out.println("Ninja was hit by boss! Health: " + health);
                List<lives> hearts = getWorld().getObjects(lives.class);
                if(hearts != null) {
                    for(lives heart : hearts) {
                        if(heart.getType() == 1) {
                            heart.setLives(health);
                        }
                    }
                }
            }
            hitCnt++; 
        }
        if(isTouching(Lava.class))
        {
            death(); //fall damage
            //***********DOTHIS*******reset level
        }
        else if(health <= 0)
        {
            death();
            //go to game over screen
        }
        try {
            checkLocation();
            checkShiftCnt();
        }
        catch(IllegalStateException e) {
           // System.out.println("The ninja was removed already!");
        }
    }

    public boolean checkLocation() {
        if(getY() <= 300) {
            return true;
        }
        return false;
    }

    public void checkShiftCnt() {
        if(shiftCnt % 100 == 0 && shiftCnt != 0) {
            int[] yCords = new int[]{100};
            if(Stage == 1) {
                Space.generateStageOneRow(getWorld(), yCords);
                shiftCnt = 0;
            }
            else if(Stage == 2) 
                Space.generateStageTwoRow(getWorld(), yCords);
            shiftCnt = 0;  
        }    
    }

    
    public void checkKey()
    {
        if(Greenfoot.isKeyDown("left")) 
        {
            isRight = false;
            setLocation(getX() - 7, getY());
            walk();
        }
        else if(Greenfoot.isKeyDown("right")) 
        {
            isRight = true;
            setLocation(getX() + 7, getY());
            walk();
        }
        else if(Greenfoot.isKeyDown("up") && checkPlatform() == true) 
        {
            jump();
        }
        else if(Greenfoot.isKeyDown("down")) 
        {
            fall();
        }
        else if(Greenfoot.isKeyDown("space"))
        {
            throwing();
        }
        else if(isRight == true) {
            setImage(idleRight);
        }
        else if(isRight == false) {
            setImage(idleLeft);
        }
    }

    public int getshiftCnt() {
        return shiftCnt;   
    }

    public void setshiftCnt(int newValue) {
        shiftCnt = newValue;
    }

    public void walk()
    {
        int mod = walkCnt % 2;
        if(isRight == true)
        {
            if(mod == 0)
            {
                setImage(walkRight);
            }
            else if(mod == 1) 
            {
                setImage(idleRight);
            }
        }
        else if(isRight == false)
        {
            if(mod == 0)
            {
                setImage(walkLeft);
            }
            else if(mod == 1) 
            {
                setImage(idleLeft);
            }
        }
        walkCnt++;
    }

    public void jump()
    {
        if(isRight == true)
        {
            setImage(jumpRight);
        }
        else if(isRight == false)
        {
            setImage(jumpLeft);
        }
        velocity = jumpHeight;
        fall();
    }

    public void fall()
    {
        if(isRight == true)
        {
            setImage(fallRight);
            setLocation(getX(), getY() + velocity);
            velocity = velocity + acceleration;
        }
        else if(isRight == false)
        {
            setImage(fallLeft);
            setLocation(getX(), getY() + velocity);
            velocity = velocity + acceleration;
        }
    }

    public void throwing() //throws ninja star
    {
        int mod = throwCnt % 15;
        if(isRight == true) //make ninja facing right
        {
            if(mod >= 0 && mod <= 3)
            {           setImage(throwRight1);
                getWorld().addObject(new Shuriken(), getX() + 15, getY());
            }
            else if(mod >= 4 && mod <= 7) 
            {
                setImage(throwRight2);
                getWorld().addObject(new Shuriken(), getX() + 15, getY());
            }
            else if(mod >= 8 && mod <= 11) 
            {
                setImage(throwRight3);
                getWorld().addObject(new Shuriken(), getX() + 15, getY());
            }
        }
        else if(isRight == false) //make ninja facing left
        {
            if(mod >= 0 && mod <= 3)
            {
                setImage(throwLeft1);                       
                getWorld().addObject(new Shuriken(), getX() + 15, getY());
            }
            else if(mod >= 4 && mod <= 7) 
            {
                setImage(throwLeft2);
                getWorld().addObject(new Shuriken(), getX() + 15, getY());
            }
            else if(mod >= 8 && mod <= 11)
            {
                setImage(throwLeft3);
                getWorld().addObject(new Shuriken(), getX() + 15, getY());
            }
        }
        throwCnt++;
    }

    public void lavaCheck() {
        //if(isTouching(Lava.class)) == true) {
        //TODO: add to this later   
    }

    public void edgeCheck() {
        if(getX() == 0) {
            setLocation(400, getY());
        }
        else if (getX() == 400) {
            setLocation(0, getY());
        }
    }

    public void death() //make ninja go to death image
    {
        if(isRight == true)
        {
            setImage(deathRight);
        }
        else if(isRight == false)
        {
            setImage(deathLeft);
        }
        Space.setLostScreen(getWorld());

    }

    public boolean checkPlatform()
    {
        Actor under = getOneObjectAtOffset(0, getImage().getHeight()/2, platform.class);
        return under != null;
    }

    public int getStage() {
        return Stage;
    }

    public void setStage(int newStage) {
        Stage = newStage;
    }
}

    

