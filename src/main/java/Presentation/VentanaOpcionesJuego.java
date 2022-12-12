package Presentation;

import Domain.NuevoJuego;
import Domain.TableroDamas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class
VentanaOpcionesJuego extends JFrame implements ActionListener {

    public TableroDamas tableroDamas;

    public NuevoJuego nuevo;

    public JMenuBar menuBar;

    public JMenu mArchivo;

    public JMenuItem mNuevoJuego, mSalir;

    public VentanaOpcionesJuego(){
        super("Proyecto Juego Damas");

        //-------------------//
        this.setSize(500,560); //jFrame que contiene el tablero
        this.setLayout(null);
        this.setVisible(true);
        this.setResizable(false);
        this.setLocationRelativeTo(null);


                menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);

            mArchivo = new JMenu("opciones");
            menuBar.add(mArchivo);

                mNuevoJuego = new JMenuItem("Comenzar partida");
                mArchivo.add(mNuevoJuego);
                mNuevoJuego.addActionListener(this);

                mSalir = new JMenuItem("salir");
                mArchivo.add(mSalir);
                mSalir.addActionListener(this);


                tableroDamas = new TableroDamas();
                add(tableroDamas, BorderLayout.CENTER);
                tableroDamas.setBounds(0,-10 ,500,500); // imagen del tablero
                tableroDamas.addMouseListener(tableroDamas);

                nuevo  = new NuevoJuego(tableroDamas);

                repaint();









    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource().equals(mNuevoJuego)){
            nuevo.show();
        } else if (e.getSource().equals(mSalir)) {
            this.dispose();

        }


    }
}
