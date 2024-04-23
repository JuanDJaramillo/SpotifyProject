/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.uees.spotifyproject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.plaf.basic.BasicScrollBarUI;

import static ec.edu.uees.spotifyproject.Botones.*;
import java.util.Iterator;
import javax.swing.SwingConstants;
/**
 *
 * @author Eduardo Yaguar
 */
public class SongsScrollPanel extends JScrollPane{
    
    JScrollBar scrollBar;
    String nombreArchivo = "Archivos/canciones.txt";
    
    static CircularDoublyLinkedList<Songs> listSongs = new CircularDoublyLinkedList<>();
    
    SongsScrollPanel(){
        this.setBorder(null);
        
        listSongs = crearLista(nombreArchivo);
        JPanel pnSongs = new JPanel();
        pnSongs.setPreferredSize(new Dimension(1200, listSongs.size()*60));
        pnSongs.setLayout(new GridLayout(listSongs.size(),1,5,5));
        pnSongs.setBackground(new Color(30, 30, 30));
        
        this.setViewportView(pnSongs);
        
        
        scrollBar = diseñoBarra();
       
        this.setVerticalScrollBar(scrollBar);
        this.setHorizontalScrollBar(null);
        
        
        Iterator<Songs> it = listSongs.iterator();
        int cont = 0;
        while(cont < listSongs.size()){
            Songs s = it.next();
            JLayeredPane layeredPane = new JLayeredPane();
            layeredPane.setPreferredSize(new Dimension(1200,62));
            JButton boton =  crearBotonCancion(s);
            JPanel panelBoton = crearPanelCanciones(s);
            layeredPane.add(panelBoton,10);
            layeredPane.add(boton,20);
            pnSongs.add(layeredPane);
            cursorEncima(boton, panelBoton);
            cont++;
        }
        
    }
    
    private CircularDoublyLinkedList<Songs> crearLista(String nombreArchivo){
        CircularDoublyLinkedList<Songs> Songs = new CircularDoublyLinkedList<>();
        
        try {
            File archivo = new File(nombreArchivo);
            Scanner scanner = new Scanner(archivo);
            int i = 1;

            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                String[] partes = linea.split(",");
                if(partes.length >=5){
                    int numbSong = i;
                    String songName = partes[0];
                    String artistName = partes[1];
                    String audioPath = partes[2];
                    String imagePath = partes[3];
                    String timeDuration = partes[4];
                    String album = partes[5];
                    String year = partes[6];
                    Songs songs = new Songs(numbSong, songName, artistName, audioPath, imagePath, timeDuration,album,year);
                    Songs.addLast(songs);
                }
                i++;
            }
            
            scanner.close();
        }catch(FileNotFoundException e){
            System.err.println("Error: El archivo no se encontró.");
            e.printStackTrace();
        }
        
        return Songs;
    }
    
    public JScrollBar diseñoBarra(){
        scrollBar = new JScrollBar(JScrollBar.VERTICAL);
        scrollBar.setUI(new BasicScrollBarUI(){
            @Override
            protected void configureScrollBarColors() {
                    thumbColor = new Color(180, 180, 180, 200); 
                    trackColor = new Color(30, 30, 30);
                }
            @Override
            protected JButton createDecreaseButton(int orientation) {
                    return createZeroButton();
                }
            @Override
                protected JButton createIncreaseButton(int orientation) {
                    return createZeroButton();
                }

                private JButton createZeroButton() {
                    JButton button = new JButton();
                    button.setPreferredSize(new Dimension(0, 0)); // Hacer el botón invisible
                    button.setMinimumSize(new Dimension(0, 0));
                    button.setMaximumSize(new Dimension(0, 0));
                    return button;
                }
            
        });
        
        return scrollBar;
    }
    
    public JPanel crearPanelCanciones(Songs song){
        JPanel panelBoton = new JPanel(new BorderLayout());
        panelBoton.setBounds(0,0,1200,62); 
        panelBoton.setBackground(new Color(30, 30, 30));
                
        JPanel panelIzquierda = new JPanel(new BorderLayout());
        panelIzquierda.setOpaque(false);
        
        JPanel panelCentro = new JPanel(new BorderLayout());
        panelCentro.setOpaque(false);
        
        JPanel panelDerecha = new JPanel(new BorderLayout());
        panelDerecha.setOpaque(false);
    
        JLabel labelNumero = new JLabel(String.valueOf(song.getNumbSong()));
        labelNumero.setBackground(new Color(30, 30, 30)); 
        labelNumero.setFont(new Font("SansSerif", Font.BOLD, 16)); 
        labelNumero.setForeground(new Color(192, 192, 192, 200));
        labelNumero.setBorder(BorderFactory.createEmptyBorder(0, 40, 0, 5)); 
        panelIzquierda.add(labelNumero, BorderLayout.WEST);
        

        try {
            BufferedImage imagen = ImageIO.read(new File(song.getImagePath()));
            Image imagenRedimensionada = imagen.getScaledInstance(45, 45, Image.SCALE_SMOOTH);
            ImageIcon iconoRedimensionado = new ImageIcon(imagenRedimensionada);
            JLabel labelIcono = new JLabel(iconoRedimensionado);
            labelIcono.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 0));
            labelIcono.setBackground(new Color(225, 225, 225, 200)); 
            panelIzquierda.add(labelIcono,BorderLayout.EAST);
        } catch (IOException e) {
            e.printStackTrace();
        }

        JPanel panelNombre = new JPanel(new BorderLayout());
        panelNombre.setOpaque(false);
        panelNombre.setPreferredSize(new Dimension(450,62)); 
        
        JLabel labelNombreCancion = new JLabel(song.getSongName());
        labelNombreCancion.setFont(new Font("SansSerif", Font.BOLD, 16));
        labelNombreCancion.setForeground(Color.WHITE);
        labelNombreCancion.setBorder(BorderFactory.createEmptyBorder(11, 20, 0, 0));
        panelNombre.add(labelNombreCancion,BorderLayout.NORTH);
        
        JLabel labelNombreArtista = new JLabel(song.getArtistName());
        labelNombreArtista.setFont(new Font("SansSerif", Font.BOLD, 13));
        labelNombreArtista.setForeground(new Color(225, 225, 225, 200));
        labelNombreArtista.setBorder(BorderFactory.createEmptyBorder(0, 20, 11, 0));
        panelNombre.add(labelNombreArtista, BorderLayout.SOUTH);
        
        JPanel panelAlbum = new JPanel(new BorderLayout());
        panelAlbum.setOpaque(false);
        
        JLabel labelAlbum = new JLabel(song.getAlbum());
        labelAlbum.setFont(new Font("SansSerif", Font.BOLD, 16));
        labelAlbum.setForeground(new Color(225, 225, 225, 200));
        labelAlbum.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 150));
        labelAlbum.setHorizontalAlignment(SwingConstants.LEFT);
        panelAlbum.add(labelAlbum,BorderLayout.CENTER);
        
        JPanel panelYear = new JPanel(new BorderLayout());
        panelYear.setOpaque(false);
        
        JLabel labelYear = new JLabel(song.getYear());
        labelYear.setFont(new Font("SansSerif", Font.BOLD, 16));
        labelYear.setForeground(new Color(225, 225, 225, 200));
        labelYear.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 150));
        panelYear.add(labelYear, BorderLayout.CENTER);
        
        panelCentro.add(panelNombre,BorderLayout.WEST);
        panelCentro.add(panelAlbum,BorderLayout.CENTER);
        panelCentro.add(panelYear,BorderLayout.EAST);


        JLabel labelDuracion = new JLabel(song.getTimeDuration());
        labelDuracion.setFont(new Font("SansSerif", Font.BOLD, 16));
        labelDuracion.setForeground(new Color(225, 225, 225, 200));
        labelDuracion.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 42));
        panelDerecha.add(labelDuracion,BorderLayout.CENTER);
        
        panelBoton.add(panelIzquierda, BorderLayout.WEST);
        panelBoton.add(panelCentro,BorderLayout.CENTER);
        panelBoton.add(panelDerecha, BorderLayout.EAST);
        
        
        return panelBoton;
        
    }
    
    public void cursorEncima(JButton boton, JPanel panel){
                
        boton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
               panel.setBackground(new Color(105, 95, 80,50));
               panel.repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                panel.setBackground(new Color(30, 30, 30)); 
                panel.repaint();
            }
            
            @Override
            public void mousePressed(MouseEvent e){
                panel.setBackground(new Color(105, 95, 80,200)); 
                panel.repaint();
            }
            @Override
            public void mouseReleased(MouseEvent e){
                panel.setBackground(new Color(105, 95, 80,50));
                panel.repaint();
            }
           
        });
    }
    
    
}
