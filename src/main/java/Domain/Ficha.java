package Domain;

import javax.swing.*;
import javax.swing.ImageIcon;

public abstract class Ficha extends JLabel {
    ImageIcon Negra_N = new ImageIcon("fichaNegra2.png");
    ImageIcon Negra_S = new ImageIcon("fichaNegra2Sombreada.png");

    ImageIcon Roja_N = new ImageIcon("fichaRoja.png");

    ImageIcon Roja_S = new ImageIcon("fichaRoja2Sombreada.png");

    ImageIcon Negra_RN = new ImageIcon("FichaNegraRN.png");
    ImageIcon Negra_RS = new ImageIcon("FichaNegraRS.png");

    ImageIcon Roja_RN = new ImageIcon("FichaRojaRN.png");

    ImageIcon Roja_RS = new ImageIcon("FichaRojaRS.png");

    ImageIcon Negra_JN = new ImageIcon("FichaNegraJN.png");

    ImageIcon Negra_JS = new ImageIcon("FichaNegraJS.png");

    ImageIcon Roja_JN = new ImageIcon("FichaRojaJN.png");

    ImageIcon Roja_JS = new ImageIcon("FichaRojaJS.png");




    private double x,y;

    // hago una variable booleana
    private  boolean corona;
    private  boolean ninja;

    private boolean seleccion;
    public Ficha(double x, double y){
        this.x = x;
        this.y = y;
        // inicializar en constructo
        corona = false;
        ninja = false;
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

    protected  void ninja(){this.ninja = true;}
    protected  boolean getNinja(){return  ninja;}
    public  abstract void ninjar();

    public abstract  void deseleccionar();

    public abstract  boolean esNegra();
}
