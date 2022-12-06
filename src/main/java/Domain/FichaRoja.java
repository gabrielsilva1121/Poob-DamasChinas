package Domain;

import javax.swing.*;




public class FichaRoja extends Ficha{
    private JLabel fichaRoja;



    private FichaRoja prev,next;

    public FichaRoja(int x,int y){
        super(x,y);
        setIcon(Roja_N);
        prev = next = null;
    }

    @Override
    public void seleccionar() {
        super.select(true);
        super.setIcon(Roja_S);

    }

    @Override
    public void deseleccionar() {
        super.select(false);
        super.setIcon(Roja_N);

    }

    @Override
    public boolean esNegra() {
        return false;
    }

    public FichaRoja getNext() {
        return next;
    }

    public FichaRoja getPrev() {
        return prev;
    }

    public void setPrev(FichaRoja prev){
        this.prev = prev;
    }

    public void setNext(FichaRoja next){
        this.next = next;
    }





}
