package Domain;

import javax.swing.*;
import javax.swing.ImageIcon;

public abstract class Ficha extends JLabel {
    ImageIcon Negra_N = new ImageIcon("fichaNegraNatural.png");
    private int x,y;
    public Ficha(int x, int y){
        this.x = x;
        this.y = y;
    }
}
