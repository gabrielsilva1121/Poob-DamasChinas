package Domain;

import javax.swing.*;
import javax.swing.ImageIcon;

public abstract class Ficha extends JLabel {
    ImageIcon Negra_N = new ImageIcon("fichaNegra2.png");

    // crear la ficha NEGRA_S Y NEGRA_SN

    ImageIcon Negra_S = new ImageIcon("fichaNegra2Sombreada.png");

    ImageIcon Roja_N = new ImageIcon("fichaRoja.png");

    ImageIcon Roja_S = new ImageIcon("fichaRoja2Sombreada.png");

    ImageIcon Negra_RN = new ImageIcon("FichaNegraRN.png");
    ImageIcon Negra_RS = new ImageIcon("FichaNegraRS.png");

    ImageIcon Roja_RN = new ImageIcon("FichaRojaRN.png");

    ImageIcon Roja_RS = new ImageIcon("FichaRojaRS.png");




    private double x,y;

    // hago una variable booleana
    private  boolean corona;

    private boolean seleccion;
    public Ficha(double x, double y){
        this.x = x;
        this.y = y;
        // inicializar en constructo
        corona = false;
    }
    public double getXP() {
        return this.x;
    }


    public double getYP() {
        return this.y;
    }

    protected void select(boolean b){seleccion = b;}

    public boolean isSeleccion() {
        return seleccion;
    }

    public void moverFicha(double x, double y){
        this.x = x;
        this.y = y;
    }

    public abstract  void seleccionar();

    protected void corona(){this.corona = true;}
    protected boolean getCorona(){return corona;}

    public  abstract  void coronar();

    public abstract  void deseleccionar();

    public abstract  boolean esNegra();
}
