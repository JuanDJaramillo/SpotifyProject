/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.uees.spotifyproject;

import java.awt.Color;
import java.awt.Dimension;
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToggleButton;
import static ec.edu.uees.spotifyproject.SongsScrollPanel.*;
import java.util.ListIterator;
import javax.sound.sampled.LineEvent;

import static ec.edu.uees.spotifyproject.MyFrame.panel;
import javax.swing.JPanel;

/**
 *
 * @author Asus
 */
public class Botones {
    public static JToggleButton btnStatus = btnPlay();
    public static JButton btnRewind = btnAtras();
    public static JButton btnSkip = btnSiguiente();
    public static boolean playing = false;
    public static Clip clip;
    public static long clipTimePosition = 0;
    public static String pathActual;
    static boolean fuePausado = false;
    static Songs cancionActual;
    static JPanel musicPanel;
    
    public static JButton crearBotonCancion(Songs song) {
        
        JButton boton = new JButton();
        boton.setBounds(0,0,1200,62); 
        boton.setText(song.getSongName());
        boton.setForeground(new Color(0, 0, 0,1));
        ImageIcon statusStop = new ImageIcon("Imagenes/Stop.png");
        
        boton.addActionListener(
                (e)->{
                    cancionActual = song;
                    btnStatus.setIcon(statusStop);
                    if(playing){                       
                        clip.stop();
                        playing = false;
                        playMusic(song);
                    }
                    else playMusic(song);
                                    
                }
        
        );
        
        
        boton.setBackground(new Color(0, 0, 0,1));
        boton.setFocusPainted(false);
        
        boton.setBorderPainted(false);
        boton.setContentAreaFilled(false);       

        return boton;
    }
    
    public static void playMusic(Songs song){

        try { 
            cancionActual = song;
            File file = new File(song.getAudioPath());
            pathActual = song.getAudioPath();
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            cancionActual = song;
            clip.open(audioInputStream);
            clip.start();
            playing = true;
            
            clip.addLineListener(new LineListener() {
                        @Override
                        public void update(LineEvent event) {
                            if (clip.getMicrosecondPosition() == clip.getMicrosecondLength()) {
                                siguienteCancion(song);
                            }
                        }
                    });
            
            panel.removeAll();
            musicPanel = new MusicPlayerPanel();
            panel.add(musicPanel);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    public static void stopMusic(){
        if (clip != null && clip.isRunning()) {
            clipTimePosition = clip.getMicrosecondPosition();
            clip.stop();
            playing = false;
            fuePausado = true;
        }
    }
    
    public static void resumeMusic(){
        try {
            clip.setMicrosecondPosition(clipTimePosition);
            clip.start();
            playing = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    public static JButton btnAtras(){
        JButton btnRewind = new JButton();
        
        btnRewind.setPreferredSize(new Dimension(50,50));
        btnRewind.setBackground(null);
        btnRewind.setBorderPainted(false);
        ImageIcon rewind = new ImageIcon("Imagenes/Rewind.png");
        btnRewind.setIcon(rewind);
        
        // --- Functionalities Rewind ---
        btnRewind.addActionListener(
                (e) -> {
                    if(playing||fuePausado){
                        if(clip.getMicrosecondPosition()/1000000 <= 1){
                            anteriorCancion(cancionActual);
                        }else{
                            clip.stop();
                            playMusic(cancionActual);
                        }
                        
                    }                
                }
        );
        
        return btnRewind;
    }
        
    public static JToggleButton btnPlay(){
        JToggleButton btnStatus = new JToggleButton();
        btnStatus.setPreferredSize(new Dimension(50,50));
        btnStatus.setBackground(null);
        btnStatus.setBorderPainted(false);
        ImageIcon statusStop = new ImageIcon("Imagenes/Stop.png");
        ImageIcon statusPlay = new ImageIcon("Imagenes/Play.png");
        btnStatus.setIcon(statusPlay);
        
        // --- Functionalities btnStatus ---
        btnStatus.addActionListener(
              (e) ->{ 
                  
                  if(playing){
                      btnStatus.setIcon(statusPlay);
                      stopMusic();
                      playing = false;
                  }
                  else {
                      if (clip != null){
                        btnStatus.setIcon(statusStop);
                        resumeMusic();
                        playing = true;
                      }
                  }
                  
              }
        );
        
        return btnStatus;
    }
    
    public static JButton btnSiguiente(){
        
        JButton btnSkip = new JButton();
        btnSkip.setPreferredSize(new Dimension(50,50));
        btnSkip.setBackground(null);
        btnSkip.setBorderPainted(false);
        ImageIcon skip = new ImageIcon("Imagenes/Skip.png");
        btnSkip.setIcon(skip);
        
        // --- Functionalities btnSkip ---
        btnSkip.addActionListener(
                (e)->{
                    if(playing||fuePausado)siguienteCancion(cancionActual);
                }
        );
        return btnSkip;
    }
    
    public static void siguienteCancion(Songs song){
        clip.close();
        fuePausado = true;
        Songs s = null;
        ListIterator<Songs> liIt = listSongs.listIterator(song.getNumbSong()-1);
        
        int cont = 0;
        while(cont < 1){
            s = liIt.next();
            cont++;
        }
        playMusic(s);
        
    }
    
    public static void anteriorCancion(Songs song){
        clip.close();
        fuePausado = true;
        Songs s = null;
        ListIterator<Songs> liIt = listSongs.listIterator(song.getNumbSong()-1);
        
        
        int cont = 0;
        while(cont < 1){
            s = liIt.previous();
            cont++;
        }
        
        playMusic(s);
        
    }
    
}
