package Domain;

public class Movimiento {

    private Ficha ficha;
    private int x, y;
    private Movimiento next,prev;

    public  Movimiento(Ficha ficha, int x, int y){
        this.ficha = ficha;
        this.x = x;
        this.y = y;
        next = null;
        prev = null;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Ficha getFicha() {
        return ficha;
    }

    public void setNext(Movimiento next) {
        this.next = next;
    }

    public void setPrev(Movimiento prev) {
        this.prev = prev;
    }

    public Movimiento getNext() {
        return this.next;
    }

    public Movimiento getPrev() {
        return this.prev;
    }

    public void realizarMovimiento(){ficha.moverFicha(x,y);}
}
