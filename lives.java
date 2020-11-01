import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class lives here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public class lives extends SmoothMover
{
    private int type = 0;
    private int livesCount = 3;
    private GreenfootImage lives3= new GreenfootImage("lives3.png");
    private GreenfootImage lives2= new GreenfootImage("lives2.png");
    private GreenfootImage lives1= new GreenfootImage("lives1.png");
    
    public lives(int type) {
        this.type = type;
    }
    
    public void act() 
    {
        if(livesCount == 3) {
            setImage(lives3);         
        }
        else if (livesCount == 2) {
            setImage(lives2);
        }
        else {
            setImage(lives1);
        } 
    }  
    
    public int getType() {
        return type;
    }
    
    public int getLives() {
      return livesCount;   
    }
    
    public void setLives(int newLives) {
      livesCount = newLives;
    }
}