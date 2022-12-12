package Domain;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TableroDamas extends JPanel implements MouseListener {
    private JLabel fondoJuego;
    private boolean comenzado;

    private GestorFicha ficha;

    public static final int MODO_HVH = 0,
                            MODO_PVH = 2;


    public TableroDamas(){
        this.setSize(500,500);
        this.setLayout(null);
        this.setVisible(true);
        this.setOpaque(false);
        comenzado = false;
        fondoJuego = new JLabel();
        fondoJuego.setIcon(new ImageIcon("tableroJuego.png"));
        this.add(fondoJuego);
        fondoJuego.setSize(500,500);
        //fondoJuego.setBounds(0,0,490,490);


    }

    public void comenzarPartida(){
        ficha = new GestorFicha();
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



    public void pintar(){
        Ficha[] f = ficha.getFichas();
        removeAll();
        for(int i= 0; i<f.length; i++){
            add(f[i]);
            //f[i].setBounds((int) ((f[i].getXP()-0.8)*60), (int) ((8.1-f[i].getYP())*60),60,60);
            f[i].setBounds((int) ((f[i].getXP()-1)*60), (int) ((8-f[i].getYP())*60),60,60);
        }

        add(fondoJuego);

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(comenzado){
            if (ficha.validarTurno(e.getX(),e.getY())){
                pintar();
            }
            if (!ficha.hayMoviento()){
                definirGanador();
            }
        }

        return;

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
