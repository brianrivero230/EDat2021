package lineales.estaticas;

public class Cola {

    private Object[] arreglo;
    private int frente;
    private int fin;
    private static final int TAMANIO = 10;

    public Cola() {
        this.arreglo = new Object[Cola.TAMANIO];
        this.frente = 0;
        this.fin = 0;

    }

    public boolean poner(Object elem) {
        boolean exito = false;
        /*
         * Verifica que el frente de la cola no sea igual a final+1, lo que significa
         * que la cola esta llena.
         */
        if ((this.frente + 1) % TAMANIO != fin) {

            this.arreglo[this.frente] = elem;

            this.frente = (this.frente + 1) % TAMANIO;

            exito = true;
        }

        return exito;
    }

    public boolean estaLlena() {
        boolean retorno = false;
        // en caso de que el frente y el fin+1 son el mismo valor
        if (((this.fin + 1) % TAMANIO) == this.frente) {
            retorno = true;
        }
        return retorno;
    }

    public boolean sacar() {
        boolean exito = false;
        /*
         * Si no está vacía, aumenta el frente.
         */
        if (this.frente != this.fin) {
            this.arreglo[this.fin] = null;
            this.fin = (this.fin + 1) % TAMANIO;
            exito = true;
        }

        return exito;
    }

    public boolean esVacia() {
        /* Si la cola esta vaciá, frente = fin */
        return (this.frente == this.fin);

    }

    public void vaciar() {
        // Ya que esVacía es true si frente = fin.
        this.arreglo = new Object[Cola.TAMANIO];
        this.frente = 0;
        this.fin = 0;
    }

    public Object obtenerFrente() {
        Object aux = null;
        if (this.frente != this.fin) {
            aux = this.arreglo[fin];
        }
        return aux;
    }

    @Override
    public String toString() {

        String cadena;
        cadena = "[";
        for (int i = 0; i < TAMANIO; i++) {
            if (this.arreglo[i] != null) {
                cadena += this.arreglo[i].toString() + ",";
            } else {
                cadena += "-,";
            }
        }

        cadena += "] Frente: " + this.frente + " Fin: " + this.fin;
        return cadena;
    }

    @Override
    public Cola clone() {
        Cola clon = new Cola();
        int i = 0;
        while (i < TAMANIO) {
            clon.arreglo[i] = this.arreglo[i];
            i++;
        }
        clon.frente = this.frente;
        clon.fin = this.fin;
        return clon;
    }
}
