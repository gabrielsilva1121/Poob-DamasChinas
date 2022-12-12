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

    public  static Movimiento[] movimientoFicha(GestorFicha gf, Ficha f){
        ArrayList<Movimiento> m = new ArrayList<>();
        Movimiento[] mov;
        if(!f.esNegra()){
            if(!gf.puedeComer(f)){
                if(gf.buscarFicha(f.getXP()+1,f.getYP()+1)==null&&f.getXP()!=8&&
                        f.getYP()!=8)
                    m.add(new Movimiento(f, (int) (f.getXP()+1), (int) (f.getYP()+1)));

                if(gf.buscarFicha(f.getXP()-1,f.getYP()+1)==null&&f.getXP()>1&&f.getYP()<8)
                    m.add(new Movimiento(f, (int) (f.getXP()-1), (int) (f.getYP()+1)));

                if(gf.buscarFicha(f.getXP()+1,f.getYP()-1)==null&&f.getCorona()&&f.getXP()<8&&f.getYP()>1)
                    m.add(new Movimiento(f, (int) (f.getXP()+1), (int) (f.getYP()-1)));

                if(gf.buscarFicha(f.getXP()-1,f.getYP()-1)==null&&f.getCorona()&&f.getXP()>1&&f.getYP()>1)
                    m.add(new Movimiento(f, (int) (f.getXP()-1), (int) (f.getYP()-1)));

            }else
            {
                if(gf.buscarFicha(f.getXP()+1,f.getYP()+1)!=null&&
                        !gf.buscarFicha(f.getXP()+1,f.getYP()+1).esNegra()!= !f.esNegra()&&
                        gf.buscarFicha(f.getXP()+2,f.getYP()+2)==null&&f.getXP()<7&&f.getYP()<7)
                    m.add(new Movimiento(f, (int) (f.getXP()+2), (int) (f.getYP()+2)));

                if(gf.buscarFicha(f.getXP()-1,f.getYP()+1)!=null&&
                        !gf.buscarFicha(f.getXP()-1,f.getYP()+1).esNegra()!= !f.esNegra()&&
                        gf.buscarFicha(f.getXP()-2,f.getYP()+2)==null&&f.getXP()>2&&f.getYP()<7)
                    m.add(new Movimiento(f, (int) (f.getXP()-2), (int) (f.getYP()+2)));

                if(gf.buscarFicha(f.getXP()+1,f.getYP()-1)!=null&&
                        !gf.buscarFicha(f.getXP()+1,f.getYP()-1).esNegra()!= !f.esNegra()&&
                        gf.buscarFicha(f.getXP()+2,f.getYP()-2)==null&&
                        f.getCorona()&&f.getXP()<7&&f.getYP()>2)
                    m.add(new Movimiento(f, (int) (f.getXP()+2), (int) (f.getYP()-2)));

                if(gf.buscarFicha(f.getXP()-1,f.getYP()-1)!=null&&
                        !gf.buscarFicha(f.getXP()-1,f.getYP()-1).esNegra()!= !f.esNegra()&&
                        gf.buscarFicha(f.getXP()-2,f.getYP()-2)==null&&
                        f.getCorona()&&f.getXP()>2&&f.getYP()>2)
                    m.add(new Movimiento(f, (int) (f.getXP()-2), (int) (f.getYP()-2)));
            }

        }else{
            if(!gf.puedeComer(f)){
                if(gf.buscarFicha(f.getXP()+1,f.getYP()-1)==null&&f.getXP()<8&&f.getYP()>1)
                    m.add(new Movimiento(f, (int) (f.getXP()+1), (int) (f.getYP()-1)));

                if(gf.buscarFicha(f.getXP()-1,f.getYP()-1)==null&&f.getXP()>1&&f.getYP()>1)
                    m.add(new Movimiento(f, (int) (f.getXP()-1), (int) (f.getYP()-1)));

                if(gf.buscarFicha(f.getXP()+1,f.getYP()+1)==null&&f.getCorona()&&f.getXP()!=8&&
                        f.getYP()!=8)
                    m.add(new Movimiento(f, (int) (f.getXP()+1), (int) (f.getYP()+1)));

                if(gf.buscarFicha(f.getXP()-1,f.getYP()+1)==null&&f.getCorona()&&f.getXP()>1&&f.getYP()<8)
                    m.add(new Movimiento(f, (int) (f.getXP()-1), (int) (f.getYP()+1)));

            }else
            {
                if(gf.buscarFicha(f.getXP()+1,f.getYP()-1)!=null&&
                        !gf.buscarFicha(f.getXP()+1,f.getYP()-1).esNegra()!= !f.esNegra()&&
                        gf.buscarFicha(f.getXP()+2,f.getYP()-2)==null&&f.getXP()<7&&f.getYP()>2)
                    m.add(new Movimiento(f, (int) (f.getXP()+2), (int) (f.getYP()-2)));

                if(gf.buscarFicha(f.getXP()-1,f.getYP()-1)!=null&&
                        !gf.buscarFicha(f.getXP()-1,f.getYP()-1).esNegra()!= !f.esNegra()&&
                        gf.buscarFicha(f.getXP()-2,f.getYP()-2)==null&&f.getXP()>2&&f.getYP()>2)
                    m.add(new Movimiento(f, (int) (f.getXP()-2), (int) (f.getYP()-2)));

                if(gf.buscarFicha(f.getXP()+1,f.getYP()+1)!=null&&
                        !gf.buscarFicha(f.getXP()+1,f.getYP()+1).esNegra()!= !f.esNegra()&&
                        gf.buscarFicha(f.getXP()+2,f.getYP()+2)==null&&
                        f.getCorona()&&f.getXP()<7&&f.getYP()<7)
                    m.add(new Movimiento(f, (int) (f.getXP()+2), (int) (f.getYP()+2)));

                if(gf.buscarFicha(f.getXP()-1,f.getYP()+1)!=null&&
                        !gf.buscarFicha(f.getXP()-1,f.getYP()+1).esNegra()!= !f.esNegra()&&
                        gf.buscarFicha(f.getXP()-2,f.getYP()+2)==null&&
                        f.getCorona()&&f.getXP()>2&&f.getYP()<7)
                    m.add(new Movimiento(f, (int) (f.getXP()-2), (int) (f.getYP()+2)));
            }

        }

        mov = new Movimiento[m.size()];
        for(int i = 0; i < mov.length; i++){
            mov[i] = m.get(i);
        }
        return mov;
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
