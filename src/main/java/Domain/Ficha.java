package Domain;

import javax.swing.*;
import javax.swing.ImageIcon;

public abstract class Ficha extends JLabel {
    ImageIcon Negra_N = new ImageIcon("fichaNegraNatural.png");

    ImageIcon Negra_S = new ImageIcon("fichaNegraSombreada.png");
    private int x,y;

    private boolean seleccion;
    public Ficha(int x, int y){
        this.x = x;
        this.y = y;
    }
    public int getXP() {
        return this.x;
    }


    public int getYP() {
        return this.y;
    }

    public void select(boolean b){seleccion = b;}

    public boolean isSeleccion() {
        return seleccion;
    }

    public void moverFicha(int x, int y){
        this.x = x;
        this.y = y;
    }

    public abstract  void seleccionar();

    public abstract  void deseleccionar();
}
