/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.uees.spotifyproject;

import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import static ec.edu.uees.spotifyproject.Botones.musicPanel;
import javax.swing.JPanel;

/**
 *
 * @author Eduardo Yaguar
 */
public class MyFrame extends JFrame{
    
    static ContentPanel panel = new ContentPanel();
    
    
    MyFrame(){
        
        
        
        setTitle("Spotify");
        this.setDefaultCloseOperation(MyFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setBackground(new Color(30, 30, 30));
        
        this.add(panel); 
        this.pack();
        this.setLocationRelativeTo(null);
        ImageIcon icon = new ImageIcon("Imagenes/spotify-240.png");
        this.setIconImage(icon.getImage());
        JPanel panel2 = musicPanel;
        
        
        this.setVisible(true);
    }
    
    
}
