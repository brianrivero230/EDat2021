package lineales.estaticas;

public class Pila {

    private static final int TAM = 10;
    private Object[] arreglo;
    private int tope;

    // Constructor
    public Pila() {

        this.arreglo = new Object[TAM];
        this.tope = -1;

    }

    // Métodos
    public boolean apilar(Object nuevoElemento) {

        /*
         * Coloca el elemento nuevoElemento en el tope de la pila. Retorna True si el
         * elemento se apiló o False en el caso contrario.
         */
        boolean exito;

        if (this.tope + 1 == TAM) {

            // Error: Pila llena.
            exito = false;

        } else {

            // En el caso que no esté llena
            this.tope++;
            this.arreglo[tope] = nuevoElemento;
            exito = true;

        }

        return exito;
    }

    public boolean desapilar() {

        /*
         * Saca el elemento del tope de la Pila. Retorna True si desapiló con exito o
         * False del caso contrerio
         */
        boolean exito;

        if (this.esVacia()) {

            // Error: la pila esta vacía.
            exito = false;

        } else {

            this.tope = tope - 1;
            exito = true;

        }

        return exito;

    }

    public boolean esVacia() {
        // Si es vacia retorna True, caso contrario false.
        boolean res;

        res = this.tope == -1;

        return res;
    }

    public Object obtenerTope() {

        // Devuelve el elemento en el tope de la Pila.
        Object elem = null;

        if (!this.esVacia()) {

            elem = this.arreglo[this.tope];

        }

        return elem;

    }

    @Override
    public String toString() {

        // Devuelve una cadena de carácteres con los elementos de la pila.
        String res;

        if (this.esVacia()) {

            res = "Pila vacía";

        } else {

            res = "[-";

            for (int i = 0; i <= this.tope; i++) {

                res += arreglo[i] + "-";

            }

            res += "-]";

        }

        return res;

    }

    public void vaciar() {

        // Vacía la pila colocando el tope en -1.
        int i = 0;
        while (i <= this.tope) {
            this.arreglo[i] = null;
            i++;
        }
        this.tope = -1;

    }

    @Override
    public Pila clone() {

        // Devuelve una copia de la Pila original.
        Pila pilaClon = new Pila();
        pilaClon.tope = this.tope;
        // pilaClon.arreglo = this.arreglo;
        int i = 0;
        while (i <= pilaClon.tope) {
            pilaClon.arreglo[i] = this.arreglo[i];
            i++;
        }

        return pilaClon;

    }

}
