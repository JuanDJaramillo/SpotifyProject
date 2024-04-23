/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.uees.spotifyproject;

import static ec.edu.uees.spotifyproject.Botones.btnRewind;
import static ec.edu.uees.spotifyproject.Botones.btnSkip;
import static ec.edu.uees.spotifyproject.Botones.btnStatus;
import static ec.edu.uees.spotifyproject.Botones.cancionActual;
import static ec.edu.uees.spotifyproject.Botones.clip;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.plaf.basic.BasicSliderUI;

/**
 *
 * @author Asus
 */
public class MusicPlayerPanel extends JPanel{
    
    JPanel pnSouth;
    public static Thread playbackT;
    public static PlayerTimer timer;
    public static JLabel start = start();
    public static JLabel end = end();
    
    MusicPlayerPanel(){
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(1200,800));
        
        
        JPanel pnNorth = new JPanel();
        pnNorth.setLayout(new BorderLayout());
        pnNorth.setPreferredSize(new Dimension(1200,200));
        
        
        ImageIcon icon = new ImageIcon("./src/main/java/ec/edu/uees/spotifyproject/icons/Top25.png");
        JLabel label = new JLabel();
        pnNorth.add(label, BorderLayout.WEST);
        pnNorth.setBackground(new Color(30, 30, 30));
        
        
        // --- Center Panel & ScrollPane ---
        
        
        JPanel panelCentral = new JPanel();
        panelCentral.setBackground(new Color(30, 30, 30));
        panelCentral.setLayout(new BorderLayout());
        ImageIcon image = new ImageIcon(cancionActual.getImagePath());
        Image scale = image.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        JLabel holder = new JLabel(new ImageIcon(scale));
        
        JLabel titulo = new JLabel(cancionActual.getSongName());
        JLabel artista = new JLabel(cancionActual.getArtistName());
        
        titulo.setFont(new Font("SansSerif", Font.BOLD, 25));
        titulo.setForeground(Color.WHITE);
        artista.setFont(new Font("SansSerif", Font.PLAIN, 16));
        artista.setForeground(Color.WHITE);
        
        JLabel albumYear = new JLabel(cancionActual.getAlbum()+" • "+cancionActual.getYear());
        albumYear.setFont(new Font("SansSerif", Font.BOLD, 14));
        albumYear.setForeground(Color.WHITE);
        
        GridBagConstraints constraintsArriba = new GridBagConstraints();
        constraintsArriba.gridx = 0;
        constraintsArriba.gridy = 0;
        constraintsArriba.insets = new Insets(0, 0, 0, 0);
        constraintsArriba.anchor = GridBagConstraints.CENTER;
    
        GridBagConstraints constraintsMedio = new GridBagConstraints();
        constraintsMedio.gridx = 0;
        constraintsMedio.gridy = 1;
        constraintsMedio.insets = new Insets(0, 0, 0, 0);
        constraintsMedio.anchor = GridBagConstraints.CENTER;
        
        GridBagConstraints constraintsAbajo = new GridBagConstraints();
        constraintsAbajo.gridx = 0;
        constraintsAbajo.gridy = 2;
        constraintsAbajo.insets = new Insets(15, 0, 0, 0);
        constraintsAbajo.anchor = GridBagConstraints.CENTER;
        
        
        JPanel info = new JPanel();
        info.setBackground(new Color(30, 30, 30));
        info.setLayout(new BorderLayout());
        info.setBorder(BorderFactory.createEmptyBorder(0, 0, 50, 0)); 
        JPanel centroInfo = new JPanel();
        centroInfo.setBackground(new Color(30, 30, 30));
        centroInfo.setLayout(new GridBagLayout());
        centroInfo.add(titulo, constraintsArriba);
        centroInfo.add(artista, constraintsMedio);
        centroInfo.add(albumYear, constraintsAbajo);
        info.add(centroInfo, BorderLayout.CENTER); 
        panelCentral.add(holder, BorderLayout.CENTER);
        panelCentral.add(info, BorderLayout.SOUTH);
                
        
        pnSouth = new JPanel();
        pnSouth.setLayout(new BorderLayout());
        pnSouth.setPreferredSize(new Dimension(1200,120));
        
        
        // ---- SUB PANELS ---
        JPanel pnOptions = new JPanel();
        pnOptions.setPreferredSize(new Dimension(1200,70));
        
        JPanel pnSlider = new JPanel();
        pnSlider.setPreferredSize(new Dimension(1200,50));
        
        // --- Options buttons ---
        pnOptions.setLayout(new FlowLayout(FlowLayout.CENTER));
                
        pnOptions.add(btnRewind);
        pnOptions.add(btnStatus);
        pnOptions.add(btnSkip);
        pnOptions.setBackground(new Color(10, 10, 10));
        
        // --- Panel que muestra la cancion actual ---
        
        
        pnSouth.add(pnOptions, BorderLayout.NORTH);
        
        // --- Slider ---
        
        JSlider slider = new JSlider(0,100,0);
        
        slider.setPreferredSize(new Dimension(400,10));
        
        slider.setBorder(BorderFactory.createEmptyBorder());
        slider.setPaintTrack(true);
        slider.setPaintTicks(false);
        slider.setPaintLabels(false);
        
       
        slider.setUI(new BasicSliderUI(slider) {
            
            @Override
            public void paintTrack(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setColor(Color.GRAY);
                g2d.fillRect(trackRect.x, trackRect.y + trackRect.height / 2 - 4, trackRect.width, 8);

                
                g2d.setColor(Color.WHITE);
                int thumbX = thumbRect.x + thumbRect.width / 2;
                g2d.fillRect(trackRect.x, trackRect.y + trackRect.height / 2 - 4, thumbX - trackRect.x, 8);
            }
        });
    
        timer = new PlayerTimer(start, slider, end);
        timer.start();
        timer.setAudio(clip);
        slider.setMaximum((int) clip.getMicrosecondLength()/1000000);

        
        pnSlider.add(start);
        pnSlider.add(slider);
        pnSlider.add(end);
       
        
        pnSlider.setBackground(new Color(10, 10, 10));
        pnSouth.add(pnSlider, BorderLayout.SOUTH);
        
        // ------------------------------
        
        
       
        this.add(pnNorth, BorderLayout.NORTH);
        this.add(panelCentral, BorderLayout.CENTER);
        this.add(pnSouth, BorderLayout.SOUTH);
        
        
    }
    
    public static JLabel start(){
        start = new JLabel("00:00:00");
        start.setForeground(Color.WHITE);
        return start;
    }
    
    public static JLabel end(){
        end = new JLabel("00:00:00");
        end.setForeground(Color.WHITE);
        return end;
    }
    
}
