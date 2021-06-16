package lineales.dinamicas;

/**
 * Brian Rivero [FAI-2205] Leonel Llancaqueo [FAI-2964].
 */

public class Cola {

    private Nodo frente;
    private Nodo fin;

    // Constructores
    public Cola() {
        this.frente = null;
        this.fin = null;

    }

    public boolean poner(Object elem) {
        // Se crea el Nodo nodoNuevo.
        Nodo nodoNuevo = new Nodo(elem, null);
        // Enganchamos el nodo .
        if (this.frente == null) {
            // Caso especial cuando la cola está vacía.
            // Enganchamos el fin y luego el frente.
            this.fin = nodoNuevo;
            this.frente = nodoNuevo;

        } else {
            // Caso General O(1).
            this.fin.setEnlace(nodoNuevo);
            this.fin = nodoNuevo;
        }

        return true;
    }

    public boolean sacar() {
        boolean exito = true;

        if (this.frente == null) {
            exito = false;

        } else {
            // En el caso de que frente no sea null.
            // Enganchamos el frente al segundo nodo.
            this.frente = this.frente.getEnlace();

            // Caso especial
            if (this.frente == null) {
                // se coloca el fin en null para que se lo lleve el GarbageCollector
                this.fin = null;

            }
        }
        return exito;
    }

    public Object obtenerFrente() {
        // Devuelve
        Object aux = null;
        if (this.frente != null) {
            aux = frente.getElem();
        }
        return aux;
    }

    public boolean esVacia() {
        // Si el frente es nulo retorna True.
        return this.frente == null;
    }

    public void vaciar() {
        // Asignamos tanto a frente como fin el valor null.
        this.fin = null;
        this.frente = null;
    }

    @Override
    public Cola clone() {
        Cola colaClone = new Cola();
        // Este nodo obtiene el frente de la cola actual y se asigna al frente de la
        // colaClon.
        if (!this.esVacia()) {

            Nodo auxActual = this.frente;
            colaClone.frente = new Nodo(auxActual.getElem(), null);

            // Se obtiene el frente de colaClone y se lo enlaza a el nodo auxActual de la
            // Cola Actual.
            Nodo auxClone = colaClone.frente;
            auxActual = auxActual.getEnlace();

            while (auxActual != null) {
                // Enlaza el siguiente elemento con el que esta al frente.
                auxClone.setEnlace(new Nodo(auxActual.getElem(), null));
                // Movemos los punteros aux.
                auxActual = auxActual.getEnlace();
                auxClone = auxClone.getEnlace();
            }

            colaClone.fin = auxClone;
        } else {
            colaClone.fin = null;
            colaClone.frente = null;
        }
        return colaClone;
    }

    @Override
    /*
     * public String toString() { String cadena = "[ "; //Creamos un puntero Nodo
     * aux = this.frente;
     * 
     * while (aux != null) { cadena += aux.getElem().toString(); if (aux.getEnlace()
     * != null) { cadena += ","; } //Actualizamos el puntero aux = aux.getEnlace();
     * } cadena += " ]"; return cadena; }
     */
    public String toString() {
        String contenido = "Cola vacia";

        if (!this.esVacia()) {
            contenido = "";
            // recorremos los enlaces de forma recursiva
            contenido = "[" + toStringRecursivoPaso(this.frente) + "]";
        }
        return contenido;
    }

    private String toStringRecursivoPaso(Nodo actual) {
        String contenido = "";
        if (actual != null) {
            contenido = actual.getElem() + " " + toStringRecursivoPaso(actual.getEnlace());
        }
        return contenido;
    }
}
