//Infinite Runner by Dhruv Gupta
//Student Number 20200897


package com.company;

import com.company.util.GameObject;
import com.company.util.Vector3f;

import java.awt.Rectangle;

public class Money extends GameObject {

    private double xspeed;
    public boolean collected = false;
    Rectangle hitbox;

    public Money(Vector3f centre) {
        super("res/sprites/Money.png",30,30, centre);
        hitbox = new Rectangle((int)getCentre().getX(),(int)getCentre().getY() , getWidth(),getHeight());

    }

    public void keyLogic(float speed){
        xspeed = -speed;



        setCentre(getCentre().AddVector(new Vector3f((int)xspeed, 0,0)));

        hitbox.x = (int)getCentre().getX();
        hitbox.y = (int)getCentre().getY();

    }


}
