package Domain;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NuevoJuego extends JDialog implements ActionListener {

    private TableroDamas tableroDamas;

    private JLabel rojas,negras;

    private JComboBox comboModos;

    private String[] modos;


    private JButton aceptar, cancelar;

    public NuevoJuego(TableroDamas tablero){

        this.modos = new String[]{ "H vs H", "H vs PC"};

        tableroDamas = tablero;

        this.setTitle("Nuevo Juego");
        this.setSize(200,200);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setLayout(new FlowLayout());
        this.setModal(true);

        modos = new String[]{
                "H vs H",
                "H vs PC",
        };

            rojas = new JLabel("rojas - ");
            negras = new JLabel("negras - ");
            comboModos = new JComboBox(modos);
            aceptar = new JButton("comenzar juego ");
            cancelar = new JButton("cancelar ");
            comboModos.setEditable(false);
                this.add(rojas);
               this.add(comboModos);
               this.add(negras);
                this.add(aceptar);
              this.add(cancelar);

            aceptar.addActionListener(this);
            cancelar.addActionListener(this);
            repaint();


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(aceptar)){
            tableroDamas.comenzarPartida(comboModos.getSelectedIndex());
        }
        comboModos.setSelectedIndex(0);
        this.dispose();

    }
}
