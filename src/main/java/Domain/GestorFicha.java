package Domain;

public class GestorFicha {

    private FichaNegra inicioN;

    private FichaRoja inicioR;

    private int fichasN,fichasR;

    private Ficha fichaSeleccionada;

    private boolean turnoR;
    private int mode;

    public GestorFicha(){
        inicioN = null;
        inicioR = null;
        agregarNegras();
        agregarRojas();
        fichasR = 12;
        fichasN = 12;
    }

    public boolean getTurnoR() {return (this.turnoR);}

    public int getMode(){return(this.mode);}

    public  void  setMode(int mode){
        this.mode = mode;
    }

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
                        if(!buscarFicha(x - 1, y - 1).getNinja()){
                            comerNegra((FichaNegra) buscarFicha(x - 1, y - 1));
                        }
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
                    if (buscarFicha(x + 1, y - 1) != null && buscarFicha(x + 1, y - 1).esNegra() ) {
                        if(!buscarFicha(x+1,y-1).getNinja()) {
                            comerNegra((FichaNegra) buscarFicha(x + 1, y - 1));
                        }
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
                            return true;


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
                    if (buscarFicha(x + 1, y + 1) != null && !buscarFicha(x + 1, y + 1).esNegra() ) {
                        if(!buscarFicha(x + 1, y + 1).getNinja()){
                            comerRoja((FichaRoja) buscarFicha(x + 1, y + 1));
                        }
                        fichaSeleccionada.setLocation((x - 1) * 60, (8 - y) * 60);
                        fichaSeleccionada.moverFicha(x, y);
                        fichaSeleccionada.deseleccionar();
                        if(y == 1){
                            fichaSeleccionada.coronar();
                        }
                        if(y == 2){
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
                        if(!buscarFicha(x - 1, y + 1).getNinja()){
                            comerRoja((FichaRoja) buscarFicha(x - 1, y + 1));
                        }
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

    public  int cantidadMover(){
        Ficha[] movimiento;
        int i = 0, j;
        if(turnoR){
            movimiento = fichasRojas();
            if(!posibilidadComer())
                for(j=0;j< movimiento.length; j++){
                    if(puedeMoverse(movimiento[j]))
                        i++;
                }
            if(posibilidadComer())
                for(j=0;j< movimiento.length; j++){
                    if(puedeComer(movimiento[j]))
                        i++;
                }


        }else{
            movimiento = fichasNegras();
            if(!posibilidadComer())
                for(j=0;j< movimiento.length; j++){
                    if(puedeMoverse(movimiento[j]))
                        i++;
                }
            if(posibilidadComer())
                for(j=0;j< movimiento.length; j++){
                    if(puedeComer(movimiento[j]))
                        i++;
                }
        }
        return  i;
    }

    public Ficha[] fichasMovimiento(){
        int i,j;
        Ficha[] movimiento = new Ficha[cantidadMover()];
        Ficha[] aux;
        movimiento = new Ficha[1];
        if(turnoR){
            aux = fichasRojas();
            j = 0;
            if(!this.posibilidadComer()){
                for(i = 0; i < aux.length; i++){
                    if(puedeMoverse(aux[i]))
                        movimiento[j++] = aux[i];

                }
            }
            if(this.posibilidadComer()){
                for(i = 0; i < aux.length; i++){
                    if(puedeComer(aux[i]))
                        movimiento[j++] = aux[i];

                }
            }
        }else{
            aux = fichasNegras();
            j = 0;
            if(!this.posibilidadComer()){
                for(i = 0; i < aux.length; i++){
                    if(puedeMoverse(aux[i]))
                        movimiento[j++] = aux[i];
                }
            }
            if(this.posibilidadComer()){
                for(i = 0; i < aux.length; i++){
                    if(puedeComer(aux[i]))
                        movimiento[j++] = aux[i];
                }
            }


        }
        return  movimiento;
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

    public boolean posibilidadComerE(){
        Ficha [] fichas;
        int i;

        if(!turnoR){
            fichas = fichasRojas();
            for(i = 0;i<getFichasR(); i++){
                if(this.puedeComer(fichas[i]))
                    return true;
            }
        }else
        {
            fichas = fichasNegras();

            for(i=0; i<getFichasN(); i++)
                if(this.puedeComer(fichas[i]))
                    return true;
        }

        return false;

    }


    public int cantidadMoverE(){
        Ficha[] movimiento;
        int i = 0, j;
        if(!turnoR){
            movimiento = fichasRojas();
            if(!posibilidadComerE())
                for(j=0;j< movimiento.length; j++){
                    if(puedeMoverse(movimiento[j]))
                        i++;
                }
            if(posibilidadComerE())
                for(j=0;j< movimiento.length; j++){
                    if(puedeComer(movimiento[j]))
                        i++;
                }


        }else{
            movimiento = fichasNegras();
            if(!posibilidadComerE())
                for(j=0;j< movimiento.length; j++){
                    if(puedeMoverse(movimiento[j]))
                        i++;
                }
            if(posibilidadComerE())
                for(j=0;j< movimiento.length; j++){
                    if(puedeComer(movimiento[j]))
                        i++;
                }
        }

        return i;

    }
    public Ficha[] fichasMovimientoE(){
        int i,j;
        Ficha[] mover = new Ficha[this.cantidadMoverE()];
        Ficha[] auxiliar;
        if(!turnoR){
            auxiliar = fichasRojas();
            j = 0;
            if(!this.posibilidadComerE())
                for(i=0;i<auxiliar.length;i++){
                    if(puedeMoverse(auxiliar[i]))
                        mover[j++]=auxiliar[i];
                }

            if(this.posibilidadComerE())
                for(i=0;i<auxiliar.length;i++){
                    if(puedeComer(auxiliar[i]))
                        mover[j++]=auxiliar[i];
                }
        }else{
            auxiliar = fichasNegras();
            j = 0;
            if(!this.posibilidadComerE())
                for(i=0;i< auxiliar.length;i++){
                    if(puedeMoverse(auxiliar[i]))
                        mover[j++]=auxiliar[i];
                }

            if(this.posibilidadComerE())
                for(i=0;i<auxiliar.length;i++){
                    if(puedeComer(auxiliar[i]))
                        mover[j++]=auxiliar[i];
                }

        }
        return mover;
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

    public void moverNegrasPC(){
        Ficha [] fichasMovidas = fichasMovimiento();
        Movimiento [] movimientoE = new GestorMovimiento(this,fichasMovimientoE()).getMovimientos();
        Movimiento [] maquina = new GestorMovimiento(this,fichasMovidas).getMovimientos();
        Movimiento [] aux;
        Ficha auxi;
        int i,x,y,xx,yy;
        if(!posibilidadComer()){
            if(movimientoE.length != 0 && puedeComer(movimientoE[0].getFicha())){
                for(i = 0; i < movimientoE.length; i++){
                    for(int j = 0; j < maquina.length; j++){
                        if(movimientoE[i].getX() == maquina[j].getX() && movimientoE[i].getY() == maquina[j].getY()){
                            fichaSeleccionada = maquina[j].getFicha();
                            if(moverPieza(maquina[j].getX(),maquina[j].getY())){
                                turnoR = !turnoR;

                            }
                            return;

                        }
                    }

                }
                for(i = 0; i < movimientoE.length; i++) {
                    for (int j = 0; j < maquina.length; j++) {
                        x = ((movimientoE[i].getX() - movimientoE[i].getFicha().getXP()) < 0) ? movimientoE[i].getX() + 1 : movimientoE[i].getX() - 1;
                        y = ((movimientoE[i].getY() - movimientoE[i].getFicha().getYP()) < 0) ? movimientoE[i].getY() + 1 : movimientoE[i].getY() - 1;
                        if(buscarFicha(x,y).equals(movimientoE[j].getFicha())){
                            x = (int) maquina[j].getFicha().getXP();
                            y = (int) maquina[j].getFicha().getYP();
                            maquina[j].realizarMovimiento();
                            aux = new GestorMovimiento(this,fichasMovimientoE()).getMovimientos();
                            if(aux.length != 0 && puedeComer(aux[0].getFicha())){
                                maquina[j].getFicha().moverFicha(x,y);
                                break;
                            }
                            maquina[j].getFicha().moverFicha(x,y);
                            fichaSeleccionada = maquina[j].getFicha();
                            if(moverPieza(maquina[j].getX(),maquina[j].getY())){
                                turnoR = !turnoR;
                            }
                            return;
                        }
                    }
                }
                for( i = 0; i < maquina.length; i++){
                    if(maquina[i].getY() == 1 && !maquina[i].getFicha().getCorona()){
                        fichaSeleccionada = maquina[i].getFicha();
                        if(moverPieza(maquina[i].getX(),maquina[i].getY())){
                            turnoR = !turnoR;
                        }
                        return;

                    }

                }

                i = (int)(Math.random()*maquina.length);
                fichaSeleccionada = maquina[i].getFicha();
                if (moverPieza(maquina[i].getX(),maquina[i].getY())){
                    turnoR = !turnoR;
                }
                return;


            }else{
                for(i=0;i<maquina.length;i++){
                    if(maquina[i].getY()==1&&!maquina[i].getFicha().getCorona()){
                        fichaSeleccionada = maquina[i].getFicha();
                        if(moverPieza(maquina[i].getX(),maquina[i].getY()))
                            turnoR = !turnoR;
                        return;
                    }
                }

                for(i=0; i<maquina.length; i++){
                    x = (int) maquina[i].getFicha().getXP();
                    y = (int) maquina[i].getFicha().getYP();
                    maquina[i].realizarMovimiento();
                    aux = new GestorMovimiento(this,fichasMovimientoE()).getMovimientos();
                    if(aux.length!=0&&puedeComer(aux[0].getFicha())){
                        maquina[i].getFicha().moverFicha(x,y);
                        i++;
                        continue;
                    }
                    maquina[i].getFicha().moverFicha(x,y);
                    fichaSeleccionada = maquina[i].getFicha();
                    if(moverPieza(maquina[i].getX(),maquina[i].getY()))
                        turnoR = !turnoR;
                    return;
                }
                i =(int)(Math.random()*maquina.length);

                fichaSeleccionada = maquina[i].getFicha();
                if(moverPieza(maquina[i].getX(),maquina[i].getY()))
                    turnoR = !turnoR;
                return;

            }
        }else {
            for(i = 0; i<maquina.length; i++){
                x = (int) maquina[i].getFicha().getXP();
                y = (int) maquina[i].getFicha().getYP();
                maquina[i].realizarMovimiento();
                aux = GestorMovimiento.movimientoFicha(this,maquina[i].getFicha());
                for(int j=0; j<aux.length; j++){
                    if(aux[j].getX()!=x||aux[j].getY()!=y){
                        xx = (int)aux[j].getFicha().getXP();
                        yy = (int) aux[j].getFicha().getYP();
                        aux[j].realizarMovimiento();
                        if(GestorMovimiento.movimientoFicha(this,aux[j].getFicha()).length<1&&
                                aux[j].getFicha().getCorona())
                        {
                            maquina[i].getFicha().moverFicha(x,y);
                            fichaSeleccionada = maquina[i].getFicha();
                            if(moverPieza(maquina[i].getX(),maquina[i].getY()))
                                turnoR = !turnoR;
                            return;
                        }
                        if(GestorMovimiento.movimientoFicha(this,aux[j].getFicha()).length<0&&
                                !aux[j].getFicha().getCorona()){
                            maquina[i].getFicha().moverFicha(x,y);
                            fichaSeleccionada = maquina[i].getFicha();
                            if(moverPieza(maquina[i].getX(),maquina[i].getY()))
                                turnoR = !turnoR;
                            return;

                        }
                        aux[j].getFicha().moverFicha(xx,yy);
                    }

                }
                maquina[i].getFicha().moverFicha(x,y);
            }

            for(i = 0; i<maquina.length; i++){
                x = (int) maquina[i].getFicha().getXP();
                y = (int) maquina[i].getFicha().getYP();
                xx = ((maquina[i].getX()-maquina[i].getFicha().getXP())<0)?maquina[i].getX()+1:maquina[i].getX()-1;
                yy = ((maquina[i].getY()-maquina[i].getFicha().getYP())<0)?maquina[i].getY()+1:maquina[i].getY()-1;
                maquina[i].realizarMovimiento();
                comerRoja((FichaRoja)buscarFicha(xx,yy));
                aux = new GestorMovimiento(this,fichasMovimientoE()).getMovimientos();
                if(aux.length!=0&&!puedeComer(aux[0].getFicha())){
                    agregarRoja(xx,yy);
                    maquina[i].getFicha().moverFicha(x,y);
                    fichaSeleccionada = maquina[i].getFicha();
                    if(moverPieza(maquina[i].getX(),maquina[i].getY()))
                        turnoR = !turnoR;
                    return;
                }
                agregarRoja(xx,yy);
                maquina[i].getFicha().moverFicha(x,y);
            }
            i =(int)(Math.random()*maquina.length);

            fichaSeleccionada = maquina[i].getFicha();
            if(moverPieza(maquina[i].getX(),maquina[i].getY()))
                turnoR = !turnoR;
            return;




        }
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
