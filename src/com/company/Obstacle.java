//Infinite Runner by Dhruv Gupta
//Student Number 20200897


package com.company;

import com.company.util.GameObject;
import com.company.util.Vector3f;

import java.awt.Rectangle;

public class Obstacle extends GameObject {

    Rectangle hitbox;
    double xspeed;
    double yspeed;

    final double gravity = 0.25;

    public Obstacle(Vector3f centre) {
        super("res/tiles/Box1.png",55,55, centre);
        hitbox = new Rectangle((int)getCentre().getX(),(int)getCentre().getY(),getWidth(),getHeight());
    }

    public void obstacleLogic(Platform platform, float speed){
        xspeed = -speed;
        yspeed+=gravity;

        //Implement Collision With Platform
        //Vertical Collision
        hitbox.y += yspeed;
        if(hitbox.intersects(platform.hitbox)){
            hitbox.y -= yspeed;
            while(!platform.hitbox.intersects(hitbox)) hitbox.y+= Math.signum(yspeed);
            hitbox.y -= Math.signum(yspeed);
            yspeed = 0;
            getCentre().setY(hitbox.y);
        }




        setCentre(getCentre().AddVector(new Vector3f((int)xspeed, (int)yspeed,0)));

        hitbox.x = (int)getCentre().getX();
        hitbox.y = (int)getCentre().getY();


    }

}
