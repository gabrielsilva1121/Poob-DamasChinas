package Domain;

import java.util.ArrayList;

public class GestorMovimiento {

    private Movimiento inicio;
    private int n;

    public GestorMovimiento(GestorFicha gestorFicha, Ficha[] fichas){
        Movimiento[] move;
        n = 0;
        for (int i = 0; i < fichas.length; i ++){
            move = this.movimientoFicha(gestorFicha,fichas[i]);
            for (int j = 0; j < move.length; j++){
                agregarMovimiento(move[j]);

            }
        }
    }

    public void agregarMovimiento(Movimiento movimiento){
        movimiento.setNext(inicio);
        if (inicio != null){
            inicio.setPrev(movimiento);
        }
        inicio = movimiento;
        n++;
    }

    public  static Movimiento[] movimientoFicha(GestorFicha gestorFicha, Ficha ficha){
        ArrayList<Movimiento> listaMovimientos = new ArrayList<>();
        Movimiento[] movimiento;
        if(!ficha.esNegra()){
            listaMovimientos.add(new Movimiento(ficha, (int) ficha.getXP(),(int )ficha.getYP()));
        }

        movimiento = new Movimiento[listaMovimientos.size()];
        for(int i = 0; i < movimiento.length; i++){
            movimiento[i] = listaMovimientos.get(i);
        }
        return movimiento;
    }

    public Movimiento[] getMovimientos(){
        Movimiento[] movimientos = new Movimiento[n];
        Movimiento temp = inicio;
        int i = 0;
        while(temp!=null){
            movimientos[i++] = temp;
            temp = temp.getNext();
        }
        return movimientos;
    }

}
