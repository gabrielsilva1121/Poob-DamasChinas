package Domain;

public class GestorFicha {

    private FichaNegra inicioN;

    private FichaRoja inicioR;

    private int fichasN,fichasR;

    private Ficha fichaSeleccionada;

    private boolean turnoR;

    public GestorFicha(){
        inicioN = null;
        inicioR = null;
        agregarNegras();
        agregarRojas();
        fichasR = 12;
        fichasN = 12;
    }

    public boolean getTurnoR() {return (this.turnoR);}

    public void setTurnoR(boolean turnoR) {this.turnoR = turnoR;}

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

    public  int fichasNR(){
        int i, cont = 0;
        for (i = 0; i < fichasN; i++){
            if(fichasNegras()[i].getCorona()){
                cont++;
            }
        }
        return cont;
    }

    public  int fichasRR(){
        int i, cont = 0;
        for (i = 0; i < fichasR; i++){
            if(fichasRojas()[i].getCorona()){
                cont++;
            }
        }
        return cont;
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
            //fichita.isSeleccion();
            if(fichita.isSeleccion()){
                fichita.deseleccionar();
                fichaSeleccionada = null;
            }else{
                if(!posibilidadComer()) {
                    if (puedeMoverse(fichita)) {
                        if (fichaSeleccionada == null) {
                            fichita.seleccionar();
                            fichaSeleccionada = fichita;
                        } else {
                            fichaSeleccionada.deseleccionar();
                            fichita.seleccionar();
                            fichaSeleccionada = fichita;
                        }
                    }
                }else{
                    if(puedeComer(fichita)){
                        if (fichaSeleccionada == null){
                            fichita.seleccionar();
                            fichaSeleccionada = fichita;
                        }else{
                            fichaSeleccionada.deseleccionar();
                            fichita.seleccionar();
                            fichaSeleccionada = fichita;
                        }
                    }
                }
            }
        }

    }

    public int signo(int x){
        if(x < 0){
            x = x * -1;
        }
        return x;
    }

    public  void comerNegra(FichaNegra comida){
        if(comida.getPrev() != null) {
            comida.getPrev().setNext(comida.getNext());
        }else{
            inicioN = inicioN.getNext();
        }
        if(comida.getNext() != null){
            comida.getNext().setPrev(comida.getPrev());
        }
        fichasN--;
    }

    public void comerRoja(FichaRoja comida){
        if(comida.getPrev() != null){
            comida.getPrev().setNext(comida.getNext());
        }else{
            inicioR = inicioR.getNext();
        }
        if (comida.getNext() != null){
            comida.getNext().setPrev(comida.getPrev());
        }
        fichasR --;
    }

    public boolean moverPieza(int x, int y){
        int a, b;
        a = (int) (x - fichaSeleccionada.getXP());
        b = (int) (y - fichaSeleccionada.getYP());
        if(signo(a)<=2&&signo(b)<=2) {
            if (!fichaSeleccionada.esNegra()) {
                if (!puedeComer(fichaSeleccionada)) {
                    if (signo(a) == 1 && signo(b) == 1) {

                        if (fichaSeleccionada.getCorona() || fichaSeleccionada.getNinja()) {
                            fichaSeleccionada.setLocation((x - 1) * 60, (8 - y) * 60);
                            fichaSeleccionada.moverFicha(x, y);
                            fichaSeleccionada.deseleccionar();
                            fichaSeleccionada = null;
                            return true;
                        }


                        if (y > fichaSeleccionada.getYP()) {
                            fichaSeleccionada.setLocation((x - 1) * 60, (8 - y) * 60);
                            fichaSeleccionada.moverFicha(x, y);
                            fichaSeleccionada.deseleccionar();
                            if( y == 8){
                                fichaSeleccionada.coronar();
                            }
                            if(y == 7){
                                fichaSeleccionada.ninjar();
                            }
                            fichaSeleccionada = null;
                            return true;
                        }
                        return false;

                    }
                }
                if (a > 0 && b > 0) {
                    if (buscarFicha(x - 1, y - 1) != null && buscarFicha(x - 1, y - 1).esNegra()) {
                        comerNegra((FichaNegra) buscarFicha(x - 1, y - 1));
                        fichaSeleccionada.setLocation((x - 1) * 60, (8 - y) * 60);
                        fichaSeleccionada.moverFicha(x, y);
                        fichaSeleccionada.deseleccionar();
                        if( y  == 8){
                            fichaSeleccionada.coronar();
                        }
                        if(y == 7 ){
                            fichaSeleccionada.ninjar();
                        }
                        if (puedeComer(fichaSeleccionada)) {
                            turnoR = !turnoR;
                        }
                        fichaSeleccionada = null;
                        return true;
                    }
                    return false;
                }
                if (a < 0 && b > 0) {
                    if (buscarFicha(x + 1, y - 1) != null && buscarFicha(x + 1, y - 1).esNegra()) {
                        comerNegra((FichaNegra) buscarFicha(x + 1, y - 1));
                        fichaSeleccionada.setLocation((x - 1) * 60, (8 - y) * 60);
                        fichaSeleccionada.moverFicha(x, y);
                        fichaSeleccionada.deseleccionar();
                        if( y == 8){
                            fichaSeleccionada.coronar();
                        }
                        if(y == 7 ){
                            fichaSeleccionada.ninjar();
                        }
                        if (puedeComer(fichaSeleccionada)) {
                            turnoR = !turnoR;
                        }
                        fichaSeleccionada = null;
                        return true;
                    }
                    return false;

                }
                if(fichaSeleccionada.getCorona() || fichaSeleccionada.getNinja()){
                    if( a > 0 && b < 0){
                        if(buscarFicha(x-1,y+1) != null && buscarFicha(x-1,y+1).esNegra()){
                            comerNegra((FichaNegra)buscarFicha(x-1,y+1));
                            fichaSeleccionada.setLocation((x-1)*60,(8-y)*60);
                            fichaSeleccionada.moverFicha(x,y);
                            fichaSeleccionada.deseleccionar();
                            if(puedeComer(fichaSeleccionada)){
                                turnoR = !turnoR;
                            }
                            fichaSeleccionada = null;
                            return true;
                        }
                        return false;
                    }
                    if(a < 0 && b < 0){
                        if(buscarFicha(x+1,y+1) != null && buscarFicha(x+1,y+1).esNegra()){
                            comerNegra((FichaNegra)buscarFicha(x+1,y+1));
                            fichaSeleccionada.setLocation((x-1)*60,(8-y)*60);
                            fichaSeleccionada.moverFicha(x,y);
                            fichaSeleccionada.deseleccionar();
                            if(puedeComer(fichaSeleccionada)){
                                turnoR = !turnoR;
                            }
                            fichaSeleccionada = null;
                            return true;
                        }
                        return false;

                    }
                }



            } else {
                if (!puedeComer(fichaSeleccionada)) {
                    if (signo(a) == 1 && signo(b) == 1) {
                        if(fichaSeleccionada.getCorona() || fichaSeleccionada.getNinja()){
                            fichaSeleccionada.setLocation((x-1)*60,(8-y)*60);
                            fichaSeleccionada.moverFicha(x,y);
                            fichaSeleccionada.deseleccionar();
                            fichaSeleccionada = null;

                        }
                        if (y < fichaSeleccionada.getYP()) {
                            fichaSeleccionada.setLocation((x - 1) * 60, (8 - y) * 60);
                            fichaSeleccionada.moverFicha(x, y);
                            fichaSeleccionada.deseleccionar();
                            if(y == 1){
                                fichaSeleccionada.coronar();
                            }
                            if(y == 2 ){
                                fichaSeleccionada.ninjar();
                            }
                            fichaSeleccionada = null;
                            return true;
                        }
                        return false;
                    }
                }
                if (a < 0 && b < 0) {
                    if (buscarFicha(x + 1, y + 1) != null && !buscarFicha(x + 1, y + 1).esNegra()) {
                        comerRoja((FichaRoja) buscarFicha(x + 1, y + 1));
                        fichaSeleccionada.setLocation((x - 1) * 60, (8 - y) * 60);
                        fichaSeleccionada.moverFicha(x, y);
                        fichaSeleccionada.deseleccionar();
                        if(y == 1){
                            fichaSeleccionada.coronar();
                        }
                        if(y == 2 ){
                            fichaSeleccionada.ninjar();
                        }
                        if (puedeComer(fichaSeleccionada)) {
                            turnoR = !turnoR;
                        }
                        fichaSeleccionada = null;
                        return true;
                    }
                    return false;
                }
                if (a > 0 && b < 0) {
                    if (buscarFicha(x - 1, y + 1) != null && !buscarFicha(x - 1, y + 1).esNegra()) {
                        comerRoja((FichaRoja) buscarFicha(x - 1, y + 1));
                        fichaSeleccionada.setLocation((x - 1) * 60, (8 - y) * 60);
                        fichaSeleccionada.moverFicha(x, y);
                        fichaSeleccionada.deseleccionar();
                        if( y == 1){
                            fichaSeleccionada.coronar();
                        }
                        if(y == 2 ){
                            fichaSeleccionada.ninjar();
                        }
                        if (puedeComer(fichaSeleccionada)) {
                            turnoR = !turnoR;
                        }
                        fichaSeleccionada = null;
                        return true;
                    }
                    return false;

                }
                if(fichaSeleccionada.getCorona() || fichaSeleccionada.getNinja()){
                    if(a > 0 && b > 0){
                        if(buscarFicha(x-1,y-1)!=null&& !buscarFicha(x-1,y-1).esNegra()){
                            comerRoja((FichaRoja)buscarFicha(x-1,y-1));
                            fichaSeleccionada.setLocation((x-1)*60,(8-y)*60);
                            fichaSeleccionada.moverFicha(x,y);
                            fichaSeleccionada.deseleccionar();
                            if(puedeComer(fichaSeleccionada)){
                                turnoR =! turnoR;
                            }
                            fichaSeleccionada = null;
                            return true;
                        }
                        return false;

                    }
                    if(a < 0 && b > 0){
                        if(buscarFicha(x+1,y-1)!=null&& !buscarFicha(x+1,y-1).esNegra()){
                            comerRoja((FichaRoja)buscarFicha(x+1,y-1));
                            fichaSeleccionada.setLocation((x-1)*60,(8-y)*60);
                            fichaSeleccionada.moverFicha(x,y);
                            fichaSeleccionada.deseleccionar();
                            if(puedeComer(fichaSeleccionada)){
                                turnoR =! turnoR;
                            }
                            fichaSeleccionada = null;
                            return true;
                        }
                        return false;

                    }
                }


            }
            return false;


        }
        return false;
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

    public  boolean puedeComer(Ficha ficha){
        if (ficha.getXP() < 7 && ficha.getYP() < 7){
            if(buscarFicha(ficha.getXP()+ 1,ficha.getYP()+1) != null && !buscarFicha(ficha.getXP()+ 1,ficha.getYP()+1).esNegra()!= !ficha.esNegra()){
                if(buscarFicha(ficha.getXP()+2,ficha.getYP()+2) == null){
                    if(!ficha.esNegra()){
                        return true;
                    } else if (ficha.getCorona() || ficha.getNinja()) {
                        return true;

                    }
                }

            }

        }
        if (ficha.getXP() > 2 && ficha.getYP() < 7){
            if(buscarFicha(ficha.getXP() -1,ficha.getYP()+1) != null && !buscarFicha(ficha.getXP() - 1,ficha.getYP()+1).esNegra()!= !ficha.esNegra()){
                if(buscarFicha(ficha.getXP() - 2,ficha.getYP()+2) == null){
                    if(!ficha.esNegra()){
                        return true;
                    } else if (ficha.getCorona() || ficha.getNinja()) {
                        return true;

                    }
                }

            }

        }
        if (ficha.getXP() < 7 && ficha.getYP() > 2){
            if(buscarFicha(ficha.getXP() + 1,ficha.getYP() - 1) != null && !buscarFicha(ficha.getXP() + 1,ficha.getYP()-1).esNegra()!= !ficha.esNegra()){
                if(buscarFicha(ficha.getXP() + 2,ficha.getYP()-2) == null){
                    if(ficha.esNegra()){
                        return true;
                    }else if (ficha.getCorona() || ficha.getNinja()){
                        return true;
                    }
                }

            }

        }
        if (ficha.getXP() > 2 && ficha.getYP() > 2){
            if(buscarFicha(ficha.getXP() - 1,ficha.getYP() - 1) != null && !buscarFicha(ficha.getXP() - 1,ficha.getYP()-1).esNegra()!= !ficha.esNegra()){
                if(buscarFicha(ficha.getXP() - 2,ficha.getYP()-2) == null){
                    if(ficha.esNegra()){
                        return true;
                    } else if (ficha.getCorona() || ficha.getNinja()) {
                        return true;

                    }
                }

            }

        }


        return false;
    }

    public  boolean posibilidadComer(){
        Ficha[] ficha;
        int i;
        if(turnoR){
            ficha = fichasRojas();
            for (i = 0; i < getFichasR(); i++){
                if(this.puedeComer(ficha[i])){
                    return  true;
                }
            }
        }else{
            ficha = fichasNegras();
            for (i = 0; i < getFichasN(); i++){
                if(this.puedeComer(ficha[i])){
                    return  true;
                }
            }
        }
        return false;
    }

    public boolean hayMoviento(){
        Ficha[] ficha;
        int i;
        if (turnoR){
            ficha = this.fichasRojas();
            for(i = 0; i < ficha.length; i++){
                if (puedeMoverse(ficha[i])){
                    return  true;
                }
            }
        }else{
            ficha = this.fichasNegras();
            for (i = 0; i < ficha.length; i++){
                if(puedeMoverse(ficha[i])){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean puedeMoverse(Ficha ficha){

        Ficha fichaNegra = new FichaNegra(1,1);

        if(ficha.getCorona() || ficha.getNinja()){
            if(!ficha.esNegra()){
                if(ficha.getYP()>1&&ficha.getXP()<8&&(fichaNegra = buscarFicha(ficha.getXP()+1,ficha.getYP()-1))==null)
                    return true;
                if(fichaNegra.esNegra()&&ficha.getXP()<7&&ficha.getYP()>2&&buscarFicha(ficha.getXP()+2,
                        ficha.getYP()-2)==null)
                    return true;

                if(ficha.getYP()>1&&ficha.getXP()>1&&(fichaNegra= buscarFicha(ficha.getXP()-1,ficha.getYP()-1))==null)
                    return true;
                if(fichaNegra.esNegra()&&ficha.getXP()>2&&ficha.getYP()>2&&buscarFicha(ficha.getXP()-2,
                        ficha.getYP()-2)==null)
                    return true;

            }
            else{
                boolean b = buscarFicha(ficha.getXP() - 2, ficha.getYP() + 2) == null;
                if(ficha.getYP() < 8 && ficha.getXP() > 1 &&(fichaNegra = buscarFicha(ficha.getXP()-1,ficha.getYP()+1)) == null ){
                    return true;
                }
                if (!fichaNegra.esNegra() && ficha.getXP() > 2 && ficha.getYP() < 7 && b) {
                    return true;
                }
                if (ficha.getYP() < 8 && ficha.getXP() < 8 && (fichaNegra = buscarFicha(ficha.getXP()+1,ficha.getYP()+1)) == null){
                    return true;
                }
                if (!fichaNegra.esNegra() && ficha.getXP() < 7 && ficha.getYP() < 7 && buscarFicha(ficha.getXP()+2,ficha.getYP()+2) == null){
                    return  true;
                }

            }
        }
        if (!ficha.esNegra()){
            boolean b = buscarFicha(ficha.getXP() - 2, ficha.getYP() + 2) == null;
            if(ficha.getYP() < 8 && ficha.getXP() > 1 &&(fichaNegra = buscarFicha(ficha.getXP()-1,ficha.getYP()+1)) == null ){
                return true;
            }
            if (fichaNegra.esNegra() && ficha.getXP() > 2 && ficha.getYP() < 7 && b) {
                return true;
            }
            if (ficha.getYP() < 8 && ficha.getXP() < 8 && (fichaNegra = buscarFicha(ficha.getXP()+1,ficha.getYP()+1)) == null){
                return true;
            }
            if (fichaNegra.esNegra() && ficha.getXP() < 7 && ficha.getYP() < 7 && buscarFicha(ficha.getXP()+2,ficha.getYP()+2) == null){
                return  true;
            }


        }else{
            if(ficha.getYP()>1&&ficha.getXP()<8&&(fichaNegra = buscarFicha(ficha.getXP()+1,ficha.getYP()-1))==null)
                return true;
            if(!fichaNegra.esNegra()&&ficha.getXP()<7&&ficha.getYP()>2&&buscarFicha(ficha.getXP()+2,
                    ficha.getYP()-2)==null)
                return true;

            if(ficha.getYP()>1&&ficha.getXP()>1&&(fichaNegra= buscarFicha(ficha.getXP()-1,ficha.getYP()-1))==null)
                return true;
            if(!fichaNegra.esNegra()&&ficha.getXP()>2&&ficha.getYP()>2&&buscarFicha(ficha.getXP()-2,
                    ficha.getYP()-2)==null)
                return true;

        }

        return  false;
    }

    public int getFichasR(){
        return fichasR;
    }
    public int getFichasN(){
        return  fichasN;
    }

    public boolean validarTurno(int x,int y){
        x = (int) (x/60 + 1);
        y = (int) (8 - y / 60);
        Ficha fichaMovida = buscarFicha(x,y);
        if(fichaMovida != null){
            if(fichaMovida.esNegra() && !turnoR){
                this.seleccionarFicha(x,y);
            }else if (!fichaMovida.esNegra() && turnoR){
                this.seleccionarFicha(x,y);
            }
            return  false;
        }
        if (fichaSeleccionada != null){
            if (moverPieza(x,y)){
                turnoR = !turnoR;
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
