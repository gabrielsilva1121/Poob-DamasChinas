package Domain;

import javax.swing.*;
import javax.swing.ImageIcon;

public abstract class Ficha extends JLabel {
    ImageIcon Negra_N = new ImageIcon("fichaNegra2.png");

    ImageIcon Negra_S = new ImageIcon("fichaNegra2Sombreada.png");

    ImageIcon Roja_N = new ImageIcon("fichaRoja.png");

    ImageIcon Roja_S = new ImageIcon("fichaRoja2Sombreada.png");


    private double x,y;

    private boolean seleccion;
    public Ficha(double x, double y){
        this.x = x;
        this.y = y;
    }
    public double getXP() {
        return this.x;
    }


    public double getYP() {
        return this.y;
    }

    public void select(boolean b){seleccion = b;}

    public boolean isSeleccion() {
        return seleccion;
    }

    public void moverFicha(double x, double y){
        this.x = x;
        this.y = y;
    }

    public abstract  void seleccionar();

    public abstract  void deseleccionar();

    public abstract  boolean esNegra();
}
