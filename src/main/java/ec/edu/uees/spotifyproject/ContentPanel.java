/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.uees.spotifyproject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.plaf.basic.BasicSliderUI;
import static ec.edu.uees.spotifyproject.Botones.btnRewind;
import static ec.edu.uees.spotifyproject.Botones.btnSkip;
import static ec.edu.uees.spotifyproject.Botones.btnStatus;


/**
 *
 * @author Eduardo Yaguar
 */
public class ContentPanel extends JPanel{
   
    SongsScrollPanel scrollPanel;
    JPanel pnSouth;
    
    ContentPanel(){
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(1200,800));
        
        
        JPanel pnNorth = new JPanel();
        pnNorth.setLayout(new BorderLayout());
        pnNorth.setPreferredSize(new Dimension(1200,200));
        
        
        ImageIcon icon = new ImageIcon("Imagenes/Top25.png");
        JLabel label = new JLabel();
        pnNorth.add(label, BorderLayout.WEST);
        
        label.setText("Top 25 de Ecuador");
        label.setFont(new Font("Sans Serif", Font.BOLD,60));
        label.setForeground(Color.WHITE);
        label.setIcon(icon);
        pnNorth.setBackground(Color.BLACK);
        
        
        // --- Center Panel & ScrollPane ---
        
        scrollPanel = new SongsScrollPanel();
        
        
        this.add(scrollPanel, BorderLayout.CENTER);
        
                
        
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

        
        pnSlider.add(slider);
       
        
        pnSlider.setBackground(new Color(10, 10, 10));
        pnSouth.add(pnSlider, BorderLayout.SOUTH);
        
        // ------------------------------
        
        
       
        this.add(pnNorth, BorderLayout.NORTH);
        this.add(pnSouth, BorderLayout.SOUTH);
        
        
    }
   
    
}
