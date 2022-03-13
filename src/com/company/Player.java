//Infinite Runner by Dhruv Gupta
//Student Number 20200897

package com.company;
import com.company.util.Controller;
import com.company.util.GameObject;
import com.company.util.Vector3f;

import java.awt.Rectangle;
import java.util.concurrent.CopyOnWriteArrayList;

public class Player extends GameObject {

    Controller PlayerController = Controller.getInstance();

    private double yspeed = 0;
    private double xspeed = 0;

    final double gravity = 0.25;

    final String[] STATES = {"RUN","JUMP"};
    private String currState;
    Rectangle hitbox;

    SoundSystem JUMP = new SoundSystem("res/sounds/jump.wav");
    SoundSystem MONEY = new SoundSystem("res/sounds/coin.wav");

    public Player(Vector3f centre) {

        super("res/sprites/Cyborg_", 70, 70, centre);
        hitbox = new Rectangle((int)getCentre().getX(), (int)getCentre().getY(), getWidth(), getHeight());

        JUMP.setGain(0.3);
        MONEY.setGain(0.25);
    }

    public void playerLogic(float speed, CopyOnWriteArrayList<Platform> p, CopyOnWriteArrayList<Money> m){

        if(yspeed<-10.5) yspeed = -10.5;
        if(xspeed<0) xspeed = -speed;

        if(PlayerController.isKeySpacePressed()){
            hitbox.y++;
            for(Platform platform: p){
                if(platform.hitbox.intersects(hitbox)){
                    yspeed -=10.5;
                    currState = STATES[1];
                    JUMP.run();
                }
                if(platform.ishasObstacle()){
                    if(platform.getObstacle().hitbox.intersects(hitbox)) {
                        yspeed -= 10.5;
                        currState = STATES[1];
                        JUMP.run();
                    }
                }
            }

            hitbox.y--;
        }
        yspeed += gravity;


        //Implement Collision With Platform
        //Vertical Collision
        hitbox.y += yspeed;
        for(Platform platform : p){
            if(hitbox.intersects(platform.hitbox)){
                hitbox.y -= yspeed;
                while(!platform.hitbox.intersects(hitbox)) hitbox.y+= Math.signum(yspeed);
                hitbox.y -= Math.signum(yspeed);
                yspeed = 0;
                getCentre().setY(hitbox.y);
                currState = STATES[0];

            }

        }
        //Horizontal Collision
        hitbox.x += speed;
        for(Platform platform : p){
            if (hitbox.intersects(platform.hitbox)) {
                hitbox.x -= speed;
                while (!platform.hitbox.intersects(hitbox)) hitbox.x++;
                hitbox.x--;
                xspeed = -speed;
                getCentre().setX(hitbox.x);
                currState = STATES[0];

            }
        }


        //Implement Collision with Obstacles
        //Vertical Collision
        hitbox.y += yspeed;
        for(Platform platform : p){
            if(platform.ishasObstacle()) {
                Obstacle obstacle = platform.getObstacle();
                if (hitbox.intersects(obstacle.hitbox)) {
                    hitbox.y -= yspeed;
                    while (!obstacle.hitbox.intersects(hitbox)) hitbox.y += Math.signum(yspeed);
                    hitbox.y -= Math.signum(yspeed);
                    yspeed = 0;
                    getCentre().setY(hitbox.y);
                }
            }
        }

        //Horizontal Collision
        hitbox.x += speed;
        for(Platform platform : p){
            if(platform.ishasObstacle()) {
                Obstacle obstacle = platform.getObstacle();
                if (hitbox.intersects(obstacle.hitbox)) {
                    hitbox.x -= speed;
                    while (!obstacle.hitbox.intersects(hitbox)) hitbox.x++;
                    hitbox.x--;
                    xspeed = -speed;
                    getCentre().setX(hitbox.x);
                }
            }
        }

        //Implement Collision with Money

        //Vertical Collision
        hitbox.y += yspeed;
        for(Money money : m) {
            if (hitbox.intersects(money.hitbox)) {
                hitbox.y -= yspeed;
                while (!money.hitbox.intersects(hitbox)) hitbox.y += Math.signum(yspeed);
                hitbox.y -= Math.signum(yspeed);
                money.collected = true;
                MONEY.run();
            }
        }
        //Horizontal Collision
        hitbox.x += speed;
        for(Money money : m) {
            if (hitbox.intersects(money.hitbox)) {
                hitbox.x -= speed;
                while (!money.hitbox.intersects(hitbox)) hitbox.x++;
                hitbox.x--;
                money.collected = true;
                MONEY.run();
            }
        }

        setCentre(getCentre().AddVector(new Vector3f((int)xspeed, (int)yspeed,0)));

        hitbox.x = (int)getCentre().getX();
        hitbox.y = (int)getCentre().getY();




    }
    public String getCurrState(){
        return currState;
    }


}
