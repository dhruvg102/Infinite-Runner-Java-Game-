//Infinite Runner by Dhruv Gupta
//Student Number 20200897



package com.company;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;

public class MainWindow {

    private static JFrame frame =new JFrame("Endless Runner");
    private static GameModel gameworld = new GameModel();
    private static Renderer renderer = new Renderer(gameworld);

    private static int TargetFPS = 100;

    private static boolean startGame = false;
    private static boolean gameEnded = false;
    private   JLabel BackgroundImageForStartMenu ;
    private JLabel BackgroundImageForEndGame;

    private String savedDataPath;
    private String fileName = "Saved";

    private static int highscore;
    private static int score = 0;

    public MainWindow() {
        frame.setSize(720, 480);  // you can customise this later and adapt it to change on size.
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   //If exit // you can modify with your way of quitting , just is a template.
        frame.setLayout(null);
        frame.add(renderer);
        renderer.setBounds(0, 0, 720, 480);
        renderer.setBackground(new Color(0,0,0)); //black background  replaced by Space background but if you remove the background method this will draw a white screen
        renderer.setVisible(false);   // this will become visible after you press the key.


        JButton startMenuButton = new JButton("Start Game");  // start button
        startMenuButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                startMenuButton.setVisible(false);
                BackgroundImageForStartMenu.setVisible(false);
                renderer.setVisible(true);
                renderer.addKeyListener(gameworld.getPlayer().PlayerController);    //adding the controller to the renderer
                renderer.requestFocusInWindow();   // making sure that the renderer is in focus so keyboard input will be taking in .
                startGame=true;
            }});
        startMenuButton.setBounds(300, 280, 100, 50);

        //loading background image
        File BackroundToLoad = new File("res/background/startgame.png");  //should work okay on OSX and Linux but check if you have issues depending your eclipse install or if your running this without an IDE
        try {

            BufferedImage myPicture = ImageIO.read(BackroundToLoad);
            BackgroundImageForStartMenu = new JLabel(new ImageIcon(myPicture));
            BackgroundImageForStartMenu.setBounds(0, 0, 720, 480);
            frame.add(BackgroundImageForStartMenu);
        }  catch (IOException e) {
            e.printStackTrace();
        }

        frame.add(startMenuButton);
        frame.setVisible(true);

        createPath();

    }
    public void endGame(){
        File BackroundToLoad = new File("res/background/endgame.png");  //should work okay on OSX and Linux but check if you have issues depending your eclipse install or if your running this without an IDE
        try {

            BufferedImage myPicture = ImageIO.read(BackroundToLoad);
            BackgroundImageForEndGame = new JLabel(new ImageIcon(myPicture));
            BackgroundImageForEndGame.setBounds(0, 0, 720, 480);
            frame.add(BackgroundImageForEndGame);
        }  catch (IOException e) {
            e.printStackTrace();
        }
        renderer.setVisible(false);
        frame.setVisible(true);

    }
    private void createPath(){
        try{
            savedDataPath = MainWindow.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void createSavedData(){
        try{

            File file = new File(savedDataPath,fileName);
            FileWriter out = new FileWriter(file);

            BufferedWriter writer = new BufferedWriter(out);
            writer.write("" + 0);
            writer.close();

        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void loadHighScore(){
        try{
            File file = new File(savedDataPath,fileName);
            if(!file.exists()){
                createSavedData();
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            highscore = Integer.parseInt(reader.readLine());
            reader.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void setHigScore(){
        if (score > highscore) {
            FileWriter out = null;
            try {

                File f = new File(savedDataPath, fileName);
                out = new FileWriter(f);

                BufferedWriter writer = new BufferedWriter(out);
                    writer.write("" + score);

                    writer.close();

                }catch(Exception e){
                    e.printStackTrace();
                }
            }
    }


    public static void main(String[] args) {



        MainWindow hello = new MainWindow();  //sets up environment

        hello.loadHighScore();
        gameworld.oldHighScore(highscore);


        while(true)   //not nice but remember we do just want to keep looping till the end.  // this could be replaced by a thread but again we want to keep things simple
        {
            //swing has timer class to help us time this but I'm writing my own, you can of course use the timer, but I want to set FPS and display it

            int TimeBetweenFrames =  1000 / TargetFPS;
            long FrameCheck = System.currentTimeMillis() + (long) TimeBetweenFrames;

            //wait till next time step
            while (FrameCheck > System.currentTimeMillis()){}




            if(startGame)
            {
                // GAMELOOP


                // model update
                gameworld.gameLogic();

                // view update
                renderer.updateView();

                startGame = !gameworld.getEndGame();
                gameEnded = gameworld.getEndGame();

            }
            if(gameEnded) {
                score = gameworld.getHighScore();
                //System.out.println(score);
                hello.setHigScore();
                hello.endGame();
            }
//            //UNIT test to see if framerate matches
//            UnitTests.CheckFrameRate(System.currentTimeMillis(),FrameCheck, TargetFPS);

        }

    }



}
