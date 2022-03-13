
//Infinite Runner by Dhruv Gupta
//Student Number 20200897


package com.company;

import com.company.util.Vector3f;

import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class GameModel {

    private Player Player;
    private CopyOnWriteArrayList<Platform> Platforms  = new CopyOnWriteArrayList<Platform>();
    private CopyOnWriteArrayList<Money> MoneyList = new CopyOnWriteArrayList<Money>();

    private SoundSystem DEATH = new SoundSystem("res/sounds/lose.wav");


    private Boolean endGame = false;

    private float gameSpeed = 3;

    private int score = 0;
    private int money = 0;
    private int highscore = 0;

    //Score Declaration

    public GameModel() {
        //Setup game world

        //Player
        Player = new Player(new Vector3f(100,200,0));

        //Platforms  starting with four
        Platforms.add(new Platform(new Vector3f(100,400,0)));
        Platforms.add(new Platform(new Vector3f(650,300,0)));

        DEATH.setGain(0.25);

    }

    //Game Logic Implementation
    public void gameLogic(){

        score++;
        Player.playerLogic(gameSpeed, Platforms, MoneyList);

        for(Platform p: Platforms){
            p.platformLogic(gameSpeed);

            if(p.ishasObstacle()) {
                Obstacle o = p.getObstacle();
                o.obstacleLogic(p, gameSpeed);
            }
            //Delete Platforms when out of screen
            if(p.getCentre().getX()<-480){
                Platforms.remove(p);
                Random rand = new Random();
                Platform plat = new Platform((new Vector3f(700 + rand.nextInt(55), 200 + rand.nextInt(200), 0)));
                Platforms.add(plat);
                if(score>1000){
                    if(plat.ishasMoney()){
                        for(int i = 0; i<plat.getLength()/4;i++){
                            Money m = new Money(new Vector3f(plat.getCentre().getX()+i*64 + 64,plat.getCentre().getY()-30,0));
                            if(plat.ishasObstacle()) {
                                if(m.hitbox.intersects(plat.getObstacle().hitbox)){
                                    m.setCentre(m.getCentre().AddVector(new Vector3f(0,-45,0)));
                                }
                            }
                            MoneyList.add(m);
                        }
                        plat.setHasMoney(false);
                    }
                }
            }
        }
        for(Money m : MoneyList){
            m.keyLogic(gameSpeed);
            if(m.collected){
                money++;
                MoneyList.remove(m);
            }
            if(m.getCentre().getX()<-480) {
                MoneyList.remove(m);
            }
        }


        if(Player.getCentre().getX() < -100 || Player.getCentre().getY() > 750){
            endGame = true;
            DEATH.run();
            if(score>highscore) highscore = score;
        }

        if(score%500 == 0){
            gameSpeed += 0.15;
        }

    }


    public void oldHighScore(int hs){
        highscore = hs;
    }
    public void setMoney(int m){
        money = m;
    }



    public Player getPlayer() {return Player;}
    public CopyOnWriteArrayList<Platform> getPlatforms() {return Platforms;}
    public CopyOnWriteArrayList<Money> getMoneyList(){return MoneyList;}


    public int getScore() {return score;}
    public int getHighScore() {return highscore;}
    public int getMoney(){return money;}

    public Boolean getEndGame() {return endGame;}
}
