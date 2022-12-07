package Domain;

public class GestorFicha {

    private FichaNegra inicioN;

    private FichaRoja inicioR;

    private int fichasN,fichasR;

    private Ficha fichaSeleccionada;

    public GestorFicha(){
        inicioN = null;
        inicioR = null;
        agregarNegras();
        agregarRojas();
    }

    public void agregarNegras(){
        int i,j;
        for (i = 6; i < 9; i++){
            if ( i % 2 == 0)
                for (j = 2; j < 9; j = j +2)
                    agregarNegra(j,i);
            else{
                for (j = 1; j < 8; j = j + 2){
                    agregarNegra(j,i);
                }

            }
        }
    }

    private void agregarRojas(){
        int i,j;
        for (i = 1; i < 4; i++){
            if (i % 2 == 0) {
                for (j = 2; j < 9; j = j + 2){
                    agregarRoja(j,i);
                }
            }else {
                for ( j = 1 ; j < 8; j = j + 2){
                    agregarRoja(j,i);

                }
            }

        }
    }

    public void agregarRoja(int x, int y){
        FichaRoja ficha = new FichaRoja(x,y);

        if(inicioR == null){
            inicioR = ficha;
        }else{
            inicioR.setPrev(ficha);
            ficha.setNext(inicioR);
            inicioR = ficha;
        }

        fichasR ++;

    }

    private void agregarNegra(int x, int y){
        FichaNegra ficha = new FichaNegra(x,y);

        if(inicioN == null){
            inicioN = ficha;
        }else{
            inicioN.setPrev(ficha);
            ficha.setNext(inicioN);
            inicioN = ficha;
        }
        fichasN ++;

    }

    public Ficha[] getFichas(){
        Ficha[] todas = new Ficha[fichasN + fichasR];
        FichaRoja temp = inicioR;
        FichaNegra temp2 = inicioN;
        int ind = 0;

        while(temp != null){
            todas[ind++] = temp;
            temp = temp.getNext();

        }


        while(temp2!=null){
            todas[ind++] = temp2;
            temp2 = temp2.getNext();
        }

        return todas;
    }

    public void  seleccionarFicha(double x, double y){
        Ficha  fichita;
        fichita = buscarFicha(x,y);
        if(fichita != null){
            fichita.isSeleccion();
            if(fichita.isSeleccion()){
                fichita.deseleccionar();
                fichaSeleccionada = null;
            }else{
                if(fichaSeleccionada == null){
                    fichita.seleccionar();
                    fichaSeleccionada = fichita;
                }
            }
        }

    }

    public boolean moverPieza(int x, int y){
        int a, b;
        a = (int) (x - fichaSeleccionada.getXP());
        b = (int) (y - fichaSeleccionada.getYP());
        if(!fichaSeleccionada.esNegra()){
            if( y > fichaSeleccionada.getYP()){
                fichaSeleccionada.setLocation((x-1) * 60, (8-y) * 60);
                fichaSeleccionada.moverFicha(x,y);
                fichaSeleccionada.deseleccionar();
                fichaSeleccionada = null;
                return true;


            }

            return false;

        }else{
            if(y < fichaSeleccionada.getYP()){
                fichaSeleccionada.setLocation((x-1) * 60, (8-y) * 60);
                fichaSeleccionada.moverFicha(x,y);
                fichaSeleccionada.deseleccionar();
                fichaSeleccionada = null;
                return true;

            }
            return false;
        }



    }

    public Ficha buscarFicha(double x, double y){
        int i;
        FichaNegra tempN = inicioN;
        FichaRoja tempR = inicioR;

        for (i = 0; i < (fichasN + fichasR) ; i++){
            //(double valorXp =  tempN.getXP();
            //double valorYp =  tempN.getYP();
            if(tempR != null){
                if (tempR.getXP() == x && tempR.getYP() == y){
                    return tempR;
                }
                tempR = tempR.getNext();

            }else{
                if(tempN.getXP() == x  && tempN.getYP() == y){
                    return tempN;
                }
                tempN = tempN.getNext();

            }

        }
        return  null;
    }

    public boolean validarTurno(int x,int y){
        x = (int) (x/60 + 1);
        y = (int) (8 - y / 60);
        Ficha fichaMovida = buscarFicha(x,y);
        if(fichaMovida != null){
            if(fichaMovida.esNegra()){
                this.seleccionarFicha(x,y);
            }else if (!fichaMovida.esNegra()){
                this.seleccionarFicha(x,y);
            }
        }
        if (fichaSeleccionada != null){
            if (moverPieza(x,y)){
                return true;
            }
        }
        return  false;
    }

    public Ficha[] fichasNegras(){
        Ficha[] negras = new Ficha[fichasN];
        FichaNegra temp = inicioN;

        int i = 0;

        while (temp != null){
            negras[i++] = temp;
            temp = temp.getNext();
        }

        return negras;
    }

    public  Ficha[] fichasRojas(){
        Ficha[] rojas = new Ficha[fichasR];
        FichaRoja temp = inicioR;

        int i = 0;
        while (temp != null){
            rojas[i++] = temp;
            temp = temp.getNext();
;        }

        return rojas;
    }

}
