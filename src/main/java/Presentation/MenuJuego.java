package Presentation;

import Domain.FichaNegra;
import Domain.TableroDamas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MenuJuego extends JFrame  {
    public TableroDamas tableroDamas ;
    public FichaNegra fichaNegra;

    public MenuJuego(){
        tableroDamas = new TableroDamas();
        add(tableroDamas, BorderLayout.CENTER);
        tableroDamas.setSize(600,600);





        //tableroDamas.addMouseListener(tableroDamas);

        this.pack();
        this.setSize(510,530);
        this.setVisible(true);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //this.addWindowListener(new WindowAdapter(){
        /*    @Override
            public void windowClosing(WindowEvent e){
                System.exit(0);
            }
       */
        }
        //repaint();
    }



