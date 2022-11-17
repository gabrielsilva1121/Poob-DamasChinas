package Domain;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TableroDamas extends JPanel implements MouseListener {
    private JLabel fondoJuego;

    public TableroDamas(){
        this.setSize(1000,1000);
        this.setLayout(null);
        this.setVisible(true);
        this.setOpaque(false);
        fondoJuego = new JLabel();
        fondoJuego.setIcon(new ImageIcon("tableroJuego.png"));
        this.add(fondoJuego);
        fondoJuego.setSize(1000,1000);

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

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
