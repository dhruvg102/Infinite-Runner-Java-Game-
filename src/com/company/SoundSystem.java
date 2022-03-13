//Infinite Runner by Dhruv Gupta
//Student Number 20200897


package com.company;

import javax.sound.sampled.*;

import java.io.File;
import java.io.IOException;


public class SoundSystem {
    String soundName;
    AudioInputStream audioInputStream;
    Clip clip;

    FloatControl gainControl;


    SoundSystem(String soundName) {
        this.soundName = soundName;

        try {
            audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);

    }
    public void run() {

        clip.setFramePosition(0);
        clip.start();
    }

    public void setGain(double gain){
        float db = (float) (Math.log(gain) / Math.log(10.0) * 20.0);
        gainControl.setValue(db);
    }
}
