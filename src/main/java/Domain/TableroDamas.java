package Domain;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TableroDamas extends JPanel implements MouseListener {
    private JLabel fondoJuego;
    private boolean comenzado;

    private GestorFicha ficha;

    public static final int MODO_HVH = 0,
            MODO_PVH = 1;


    public TableroDamas() {
        this.setSize(500, 500);
        this.setLayout(null);
        this.setVisible(true);
        this.setOpaque(false);
        comenzado = false;
        fondoJuego = new JLabel();
        fondoJuego.setIcon(new ImageIcon("tableroJuego.png"));
        this.add(fondoJuego);
        fondoJuego.setSize(500, 500);
        //fondoJuego.setBounds(0,0,490,490);


    }

    public void comenzarPartida(int modo) {
        ficha = new GestorFicha();
        ficha.setTurnoR(true);
        ficha.setMode(modo);
        comenzado = true;

        pintar();
        repaint();
    }

    public void definirGanador() {
        if (ficha.getFichasR() == ficha.getFichasN()) {
            if (ficha.fichasNR() == ficha.fichasRR()) {
                JOptionPane.showMessageDialog(this, "Empate!!!");
            } else if (ficha.fichasNR() < ficha.fichasRR())
                JOptionPane.showMessageDialog(this, "Rojas Ganan!");
            else
                JOptionPane.showMessageDialog(this, "Negras Ganan!");
        } else if (ficha.getFichasN() < ficha.getFichasR()) {
            JOptionPane.showMessageDialog(this, "Rojas Ganan!");
        } else
            JOptionPane.showMessageDialog(this, "Negras Ganan!");
        comenzado = false;
    }

    public void cambiarModo(int modo) {
        ficha.setMode(modo);
    }


    public void pintar() {
        Ficha[] f = ficha.getFichas();
        removeAll();
        for (int i = 0; i < f.length; i++) {
            add(f[i]);
            //f[i].setBounds((int) ((f[i].getXP()-0.8)*60), (int) ((8.1-f[i].getYP())*60),60,60);
            f[i].setBounds((int) ((f[i].getXP() - 1) * 60), (int) ((8 - f[i].getYP()) * 60), 60, 60);
        }

        add(fondoJuego);

    }

    public boolean getComenzando() {
        return (this.comenzado);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (comenzado) {
            if (ficha.getMode() == MODO_HVH) {
                if (ficha.validarTurno(e.getX(), e.getY())) {
                    pintar();
                    if(!ficha.hayMoviento()){
                        definirGanador();
                    }
                }

                return;
            }
            int mm = ficha.getMode();

            if (ficha.getMode() == MODO_PVH){
                if(ficha.getTurnoR()){
                    if (ficha.validarTurno(e.getX(), e.getY())) {
                        pintar();
                        if(!ficha.hayMoviento()){
                            definirGanador();
                        }
                        return;
                    }

                }
                if(!ficha.getTurnoR()){
                    ficha.moverNegrasPC();
                    pintar();
                    if(!ficha.hayMoviento())
                        this.definirGanador();
                        return;
                }
            }




        }
    }



    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
