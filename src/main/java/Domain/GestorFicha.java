package Domain;

public class GestorFicha {

    private FichaNegra inicioN;

    private int fichasN;

    private Ficha fichaSeleccionada;

    public GestorFicha(){
        inicioN = null;
        agregarNegras();
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

    public void agregarNegra(int x, int y){
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
        Ficha[] todas = new Ficha[fichasN];
        FichaNegra temp2 = inicioN;
        int ind = 0;


        while(temp2!=null){
            todas[ind++] = temp2;
            temp2 = temp2.getNext();
        }

        return todas;
    }

    public void  seleccionarFicha(int x, int y){
        Ficha  fichita;
        fichita = buscarFicha(x,y);
        if(fichita != null){
            if(fichita.isSeleccion()){
                fichita.deseleccionar();
                fichaSeleccionada = fichita;
            }
        }

    }

    public Ficha buscarFicha(int x, int y){

        int i;
        FichaNegra tempN = inicioN;

        for (i = 0; i < fichasN; i++){
            if(tempN.getXP() == x && tempN.getYP() == y){
                return tempN;
            }
            tempN = tempN.getNext();
        }
        return  null;
    }

}
