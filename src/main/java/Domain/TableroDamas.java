package Domain;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TableroDamas extends JPanel implements MouseListener {
    private JLabel fondoJuego;
    private boolean comenzado;

    private GestorFicha ficha;

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

    /*public void definirGanador(){
        if(fichas.getFichasR() == fichas.getFichasN()){
            if(fichas.fichasNR()==fichas.fichasRR()){
                JOptionPane.showMessageDialog(this,"Empate!!!");
            }else if(fichas.fichasNR()<fichas.fichasRR())
                JOptionPane.showMessageDialog(this,"Rojas Ganan!");
            else
                JOptionPane.showMessageDialog(this,"Negras Ganan!");
        }else if(fichas.getFichasN()<fichas.getFichasR()){
            JOptionPane.showMessageDialog(this,"Rojas Ganan!");
        }else
            JOptionPane.showMessageDialog(this,"Negras Ganan!");
        comenzado = false;
    }*/


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
            System.out.println(e.getX());
            System.out.println(e.getY());
            if (ficha.validarTurno(e.getX(),e.getY())){
                pintar();
            }
            if (!ficha.hayMoviento()){
                //definirGanador();
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
