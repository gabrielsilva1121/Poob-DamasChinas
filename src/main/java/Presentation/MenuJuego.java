package Presentation;

import Domain.TableroDamas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MenuJuego extends JFrame implements ActionListener {
    public TableroDamas tableroDamas ;

    public MenuJuego(){
        tableroDamas = new TableroDamas();
        add(tableroDamas, BorderLayout.CENTER);
        tableroDamas.setSize(200,200);
        tableroDamas.addMouseListener(tableroDamas);
        this.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e){
                System.exit(0);
            }

        });
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
