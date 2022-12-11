package Domain;

import javax.swing.*;

public class FichaNegra extends Ficha{

    private JLabel fichaNegra;



    private FichaNegra prev,next;

    public FichaNegra(int x,int y){
        super(x,y);
        setIcon(Negra_N);
        prev = next = null;
    }

    @Override
    public void seleccionar() {
        super.select(true);
        if(this.getCorona()){
            super.setIcon(Negra_RS);
        }else if(getNinja()){
            super.setIcon(Negra_JS);
        }else{
            super.setIcon(Negra_S);
        }

    }

    @Override
    public void deseleccionar() {
        super.select(false);


        if(this.getCorona()){
            super.setIcon(Negra_RN);
        } else if(this.getNinja()){
            super.setIcon(Negra_JN);
        }else{
            super.setIcon(Negra_N);
        }

    }

    @Override
    public void coronar() {
        super.corona();
        super.setIcon(Negra_RN);
    }

    @Override
    public void ninjar() {
        super.ninja();
        super.setIcon(Negra_JN);
    }

    public FichaNegra getNext() {
        return next;
    }

    public FichaNegra getPrev() {
        return prev;
    }

    public void setPrev(FichaNegra prev){
        this.prev = prev;
    }

    public void setNext(FichaNegra next){
        this.next = next;
    }

    @Override
    public boolean esNegra() {return  true;}


}
