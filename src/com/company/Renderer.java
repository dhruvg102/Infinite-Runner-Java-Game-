//Infinite Runner by Dhruv Gupta
//Student Number 20200897


package com.company;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Renderer extends JPanel {

    private long currAnimTimer = 0;
    GameModel gameworld = new GameModel();

    Renderer(GameModel gameModel){
        gameworld = gameModel;
    }

    public void updateView(){
        this.repaint();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        currAnimTimer++;

        //Draw Background
        drawBackground2(g);

        //Draw Player
        int x = (int)gameworld.getPlayer().getCentre().getX();
        int y = (int)gameworld.getPlayer().getCentre().getY();
        int width = gameworld.getPlayer().getWidth();
        int height = gameworld.getPlayer().getHeight();
        String texture = gameworld.getPlayer().getTexture();
        String state = gameworld.getPlayer().getCurrState();
        if(state == "RUN"){
            texture = texture + "run.png";
        }else{
            texture = texture + "jump.png";
        }

        drawPlayer(x, y, width, height, texture,g);
        //Draw Player Hitbox
        //g.drawRect(gameworld.getPlayer().hitbox.x, gameworld.getPlayer().hitbox.y, gameworld.getPlayer().hitbox.width, gameworld.getPlayer().hitbox.height);

        //Draw Platforms
        gameworld.getPlatforms().forEach((temp) ->
        {
            drawPlatform2((int) temp.getCentre().getX(), (int) temp.getCentre().getY(),temp.getLength(), temp.getTexture(),g);
            if(temp.ishasObstacle()){
                Obstacle o = temp.getObstacle();
                drawObstacle((int)o.getCentre().getX(),(int)o.getCentre().getY(),o.getHeight(), o.getWidth(), o.getTexture(),g);
            }
            //Draw Platform Hitbox
            //g.fillRect(temp.hitbox.x+1,temp.hitbox.y+1,temp.hitbox.width-2,temp.hitbox.height-2);
        });


        //Draw Obstacles
        gameworld.getMoneyList().forEach((temp) ->
        {
            if(!temp.collected)
                drawMoney((int) temp.getCentre().getX(), (int) temp.getCentre().getY(),temp.getHeight(),temp.getWidth(), temp.getTexture(),g);
            //Draw Obstacle Hitbox
            //g.fillRect(temp.hitbox.x+1,temp.hitbox.y+1,temp.hitbox.width-2,temp.hitbox.height-2);
        });

        drawMoneyUI(gameworld.getMoney(), g);
        //Draw Score
        int hs = gameworld.getHighScore();
        int s = gameworld.getScore();
        drawScore(s, hs, g);


    }



    private void drawBackground2(Graphics g){
        //TODO PARALLAX
        File b5 = new File("res/background/5.png");
        File b4 = new File("res/background/4.png");
        File b3 = new File("res/background/3.png");
        File b2 = new File("res/background/2.png");
        File b1 = new File("res/background/1.png");

        try{
            Image B5 = ImageIO.read(b5);
            Image B4 = ImageIO.read(b4);
            Image B3 = ImageIO.read(b3);
            Image B2 = ImageIO.read(b2);
            Image B1 = ImageIO.read(b1);

            double pos5 =  (currAnimTimer%720);
            double pos4 =  (currAnimTimer * 0.7)%720;
            double pos3 =  (currAnimTimer * 0.5)%720;
            double pos2 =  (currAnimTimer * 0.2)%720;
            double pos1 =  (currAnimTimer * 0.1)%720;

            g.drawImage(B1,-(int) (pos1),0,720,480,null);
            g.drawImage(B1,-(int) (pos1 - 720),0,720,480,null);

            g.drawImage(B2,-(int) (pos2),0,720,480,null);
            g.drawImage(B2,-(int) (pos2 - 720),0,720,480,null);

            g.drawImage(B3,-(int) (pos3),0,720,480,null);
            g.drawImage(B3,-(int) (pos3 - 720),0,720,480,null);

            g.drawImage(B4,-(int) (pos4),0,720,480,null);
            g.drawImage(B4,-(int) (pos4 - 720),0,720,480,null);

            g.drawImage(B5, -(int) (pos5),0,720,480,null);
            g.drawImage(B5, -(int) (pos5-720),0,720,480,null);


        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void drawPlayer(int x, int y, int width, int height, String texture,Graphics g){

        File player = new File(texture);
        try {
            Image PLAYER = ImageIO.read(player);


            int currentPositionInAnimation = ((int) ((currAnimTimer%60)/10))*48;
            g.drawImage(PLAYER, x ,y ,x+width ,y+height ,currentPositionInAnimation ,0,currentPositionInAnimation+47,48, null);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void drawPlatform2(int x, int y,int length, String texture,Graphics g){
        File platLeft = new File(texture+"/tiles/IndustrialTile_04.png");
        File platMid = new File(texture+"/tiles/IndustrialTile_05.png");
        File platRight = new File(texture+"/tiles/IndustrialTile_06.png");
        try {
            Image l = ImageIO.read(platLeft);
            Image m = ImageIO.read(platMid);
            Image r = ImageIO.read(platRight);

            int X = x;
            g.drawImage(l, X,y,32,32,  null);
            X+= 16;

            for(int i=0;i<length;i++) {
                g.drawImage(m, X, y,32,32, null);
                X += 16;
            }
            g.drawImage(r,X,y,32,32, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void drawObstacle(int x,int y,  int width, int height, String texture,Graphics g){
        File obs = new File(texture);

        try {
            Image OBS = ImageIO.read(obs);
            g.drawImage(OBS,x,y,width, height,null);
        }catch (IOException e){
            e.printStackTrace();
        }

    }
    private void drawMoney(int x, int y, int width, int height, String texture,Graphics g){
        File TextureToLoad = new File(texture);
        try {
            Image myImage = ImageIO.read(TextureToLoad);

            //The spirte is 32x32 pixel wide and 4 of them are placed together so we need to grab a different one each time
            //remember your training :-) computer science everything starts at 0 so 32 pixels gets us to 31

            //slows down animation so every 10 frames we get another frame so every 100ms
            int currentPositionInAnimation= ((int) ((currAnimTimer%60)/10))*24;
            g.drawImage(myImage, x,y, x+width, y+height,currentPositionInAnimation,0,currentPositionInAnimation+23,24, null);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private void drawMoneyUI(int m, Graphics g){

        File texture = new File("res/sprites/Money.png");
        String texture2 = "res/tiles";
        File bg = new File("res/tiles/IndustrialTile_73.png");

        int i = 0;
        int start = String.valueOf(m).length();
        while (m>0) {
            String digitScore = String.valueOf(m % 10);
            File obs1 = new File(texture2 +"/"+digitScore + ".png");
            int x = 530- i + start*42;
            int y = 395;
            try {
                Image DIGIT = ImageIO.read(obs1);
                Image BG = ImageIO.read(bg);
                Image UI = ImageIO.read(texture);

                g.drawImage(BG, x -42,400-y,42,42,null);
                g.drawImage(UI, x-43,397-y,x-1,437-y,0,0,20,20,null);
                g.drawImage(BG, x  ,400-y,42, 42,null);
                g.drawImage(DIGIT,x + 5 , 402-y,null);

            }catch (IOException e){
                e.printStackTrace();
            }
            i += 42;
            m = m/10;

        }
    }
    private void drawScore(int s, int hs, Graphics g){
        String texture = "res/tiles";
        String textureBg = "res/tiles/IndustrialTile_73.png";
        int i = 0;
        File bg = new File(textureBg);
        int start = String.valueOf(s).length();
        while (s>0) {
            String digitScore = String.valueOf(s % 10);
            File obs1 = new File(texture +"/"+digitScore + ".png");

            try {
                Image DIGIT = ImageIO.read(obs1);
                Image BG = ImageIO.read(bg);
                g.drawImage(BG,  - i + start*42,400,42, 42,null);
                g.drawImage(DIGIT,5 - i + start*42, 402,null);

            }catch (IOException e){
                e.printStackTrace();
            }
            i += 42;
            s = s/10;

        }
        start = String.valueOf(hs).length() + 1;
        i = 42;
        while (hs>0) {
            String digitHighScore = String.valueOf(hs % 10);
            File obs2 = new File(texture +"/"+digitHighScore + ".png");
            try {
                Image DIGIT = ImageIO.read(obs2);
                Image BG = ImageIO.read(bg);
                g.drawImage(BG, 450 - i + start*42,400,42, 42,null);
                g.drawImage(DIGIT,455 - i  + start*42 , 402,null);

            }catch (IOException e){
                e.printStackTrace();
            }
            i += 42;
            hs = hs/10;
        }

    }
}
