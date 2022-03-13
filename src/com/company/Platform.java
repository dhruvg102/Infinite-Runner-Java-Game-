//Infinite Runner by Dhruv Gupta
//Student Number 20200897


package com.company;

import com.company.util.GameObject;
import com.company.util.Vector3f;

import java.awt.Rectangle;
import java.util.Random;


public class Platform extends GameObject{

    private int length;
    private double xspeed;
    private boolean hasObstacle = false;
    private boolean hasMoney = false;

    Rectangle hitbox;
    Obstacle o;

    public Platform( Vector3f centre) {
        super("res", centre);
        generatePlatform();
        hitbox = new Rectangle((int)getCentre().getX(),(int)getCentre().getY(),16*(length+2),32);
        spawnObstacle();
        spawnMoney();
        if(hasObstacle == true){
            Random rand = new Random();
            o = new Obstacle(new Vector3f(getCentre().getX()+ rand.nextInt(16*(length-2)) + 32,getCentre().getY()-60,0));
        }
    }

    //Generate new Platforms
    public void generatePlatform(){
        Random rand = new Random();
        length = rand.nextInt(5);
        length += 25;
    }



    //Platform Logic
    public void platformLogic(float speed){
        xspeed = -speed;



        setCentre(getCentre().AddVector(new Vector3f((int)xspeed, 0,0)));

        hitbox.x = (int)getCentre().getX();
        hitbox.y = (int)getCentre().getY();


    }

    public void spawnObstacle(){
        Random rand = new Random();
        int chance = rand.nextInt(10);
        if(chance>3){
            hasObstacle = true;
        }
    }

    public void spawnMoney(){
        Random rand = new Random();
        int chance = rand.nextInt(10);
        if(chance>8){
            hasMoney = true;
        }
    }
    public void setHasMoney(boolean money){
        hasMoney = money;
    }
    Obstacle getObstacle(){return o;}
    public boolean ishasObstacle() {return hasObstacle;}
    public boolean ishasMoney() {return hasObstacle;}
    public int getLength() {
        return length;
    }
}
