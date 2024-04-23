/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.uees.spotifyproject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import javax.sound.sampled.*;
import javax.swing.*;

/**
 *
 * @author Asus
 */
public class PlayerTimer extends Thread{
    DateFormat dateF = new SimpleDateFormat("HH:mm:ss");
    boolean running = false;
    boolean paused = false;
    boolean reset = false;
    long startT;
    long pauseT;
    
    private Clip audio;
    JLabel recordTime;
    JLabel duration;
    JSlider slider;
    
    public void setAudio (Clip audio){
        this.audio = audio;
    }
    void resumeT(){
        paused = false;
    }
    
    PlayerTimer(JLabel recordTime, JSlider sliderT, JLabel duration){
        this.recordTime = recordTime;
        this.slider = sliderT;
        this.duration = duration;
    }

    public void run(){
        running = true;
        startT = System.currentTimeMillis();
        
        while(running){
            try{
                Thread.sleep(100);
                if(!paused){
                    if(audio!=null && audio.isRunning()){
                        recordTime.setText(timeString());
                        duration.setText(durationString());
                        int currentSec = (int) audio.getMicrosecondPosition()/1000000;
                        slider.setValue(currentSec);
                    }
                } else{
                    pauseT += 100;
                }
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
    }
    public String timeString(){
        long now = System.currentTimeMillis();
        Date current = new Date(now - startT - pauseT);
        dateF.setTimeZone(TimeZone.getTimeZone("GMT"));
        String time = dateF.format(current);
        return time;
    }
    public String durationString(){
        long now = System.currentTimeMillis();
        long timePassed = now - startT;
        Date current = new Date((audio.getMicrosecondLength()/1000) - timePassed);
        dateF.setTimeZone(TimeZone.getTimeZone("GMT"));
        return dateF.format(current);
    }
}
