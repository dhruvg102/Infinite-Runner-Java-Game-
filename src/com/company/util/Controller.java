package com.company.util;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Controller implements KeyListener {

    private static boolean KeyAPressed= false;
    private static boolean KeySPressed= false;
    private static boolean KeyDPressed= false;
    private static boolean KeyWPressed= false;
    private static boolean KeySpacePressed= false;

    private static final Controller instance = new Controller();

    public static Controller getInstance(){
        return instance;
    }

    @Override
    // Key pressed , will keep triggering
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e)
    {
        switch (e.getKeyChar())
        {
            case 'a':setKeyAPressed(true);break;
            case 's':setKeySPressed(true);break;
            case 'w':setKeyWPressed(true);break;
            case 'd':setKeyDPressed(true);break;
            case ' ':setKeySpacePressed(true);break;
            default:
                //System.out.println("Controller test:  Unknown key pressed");
                break;
        }

    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        switch (e.getKeyChar())
        {
            case 'a':setKeyAPressed(false);break;
            case 's':setKeySPressed(false);break;
            case 'w':setKeyWPressed(false);break;
            case 'd':setKeyDPressed(false);break;
            case ' ':setKeySpacePressed(false);break;
            default:
                //System.out.println("Controller test:  Unknown key pressed");
                break;
        }
        //TODO UPPERCASE (INCASE CAPS IS ON)

    }

    public boolean isKeyAPressed() {
        return KeyAPressed;
    }
    public boolean isKeySPressed() {
        return KeySPressed;
    }
    public boolean isKeyDPressed() {
        return KeyDPressed;
    }
    public boolean isKeyWPressed() {
        return KeyWPressed;
    }
    public boolean isKeySpacePressed() {
        return KeySpacePressed;
    }




    public void setKeyAPressed(boolean keyAPressed) {
        KeyAPressed = keyAPressed;
    }
    public void setKeySPressed(boolean keySPressed) {
        KeySPressed = keySPressed;
    }
    public void setKeyDPressed(boolean keyDPressed) {
        KeyDPressed = keyDPressed;
    }
    public void setKeyWPressed(boolean keyWPressed) {
        KeyWPressed = keyWPressed;
    }
    public void setKeySpacePressed(boolean keySpacePressed) {
        KeySpacePressed = keySpacePressed;
    }
}
