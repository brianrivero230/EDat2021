package lineales.dinamicas;

public class Pila {

    private Nodo tope;

    //Constructor
    public Pila() {

        this.tope = null;

    }

    //Métodos
    public boolean apilar(Object nuevoElem) {

        //Coloca el elemento nuevoElemento en el tope de la pila.
        Nodo nuevo = new Nodo(nuevoElem, this.tope);
        this.tope = nuevo;
        return true;

    }

    public boolean desapilar() {

        /*Saca el elemento del tope de la Pila. Retorna True si desapiló con 
        éxito o False del caso contrerio.*/
        boolean exito;

        if (this.esVacia()) {

            //Error: Pila vacía.
            exito = false;

        } else {

            Nodo aux = this.tope.getEnlace();
            this.tope = aux;
            exito = true;
        }

        return exito;
    }

    public Object obtenerTope() {

        //Devuelve el elemento en el tope de la Pila.
        Object res;

        if (this.esVacia()) {

            res = null;

        } else {

            res = this.tope.getElem();

        }
        return res;
    }

    public boolean esVacia() {

        //Si es vacia retorna True, caso contrario false.
        return this.tope == null;
    }

    public void vaciar() {

        //Vacía la pila colocando el tope en -1.
        this.tope = null;

    }

    @Override
   /* public Pila clone() {
        
        Pila pilaClone = new Pila();
        Nodo aux = this.tope;
        pilaClone.tope = aux;
        
        while (aux != null) {
            
            Nodo nuevo = new Nodo(aux.getElem(), aux.getEnlace());
            aux = aux.getEnlace();
            
        }
        return pilaClone;
    }*/
    public Pila clone(){
        
        Pila pilaClone = new Pila();
        return cloneRecursivo(pilaClone,this.tope);
    }
    
    private Pila cloneRecursivo(Pila pilaClon, Nodo aux){
        
        if (aux != null) {
            pilaClon = cloneRecursivo(pilaClon,aux.getEnlace());
            Nodo nuevo = new Nodo(aux.getElem(), pilaClon.tope);
            pilaClon.tope = nuevo;
        }
        
        return pilaClon;
    }

    @Override
    public String toString() {

        //Devuelve una cadena de carácteres con los elementos de la pila.
        String s = "";

        if (this.tope == null) {

            s = "Pila vacía";

        } else {

            Nodo aux = this.tope;
            s += "[ ";
            while (aux != null) {

                s += aux.getElem().toString();
                aux = aux.getEnlace();

                if (aux != null) {

                    s += "-";

                }
            }

            s += " ]";

        }

        return s;
    }

}
