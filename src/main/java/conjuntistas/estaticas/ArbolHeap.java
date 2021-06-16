package conjuntistas.estaticas;

/**
 *
 * @author brian
 */
public class ArbolHeap {
    private Comparable[] heap;
    private int ultimo;
    private int TAM = 20;

    public ArbolHeap() {
        this.heap = new Comparable[TAM];
        this.ultimo = 0;
    }

    public boolean insertar(Object elemento) {
        boolean exito = false;

        if (this.ultimo + 1 < this.heap.length) {
            this.heap[this.ultimo + 1] = (Comparable) elemento;
            this.ultimo++;
            ordenarHaciaArriba();
            exito = true;
        }
        return exito;
    }

    private void ordenarHaciaArriba() {
        int i = this.ultimo;
        int padre = this.ultimo / 2;
        boolean control = true;
        while (padre >= 1 && control) {
            if (this.heap[i].compareTo(this.heap[padre]) < 0) {
                int aux = (int) this.heap[i];
                this.heap[i] = this.heap[padre];
                this.heap[padre] = aux;
                i = padre;
                padre = i / 2;
            } else {
                control = false;
            }
        }
    }

    private void ordenarHaciaAbajo(int posPadre) {
        int posH;
        Comparable temp = this.heap[posPadre];
        boolean salir = false;

        while (!salir) {
            posH = posPadre * 2;

            if (posH <= this.ultimo) {

                if (posH < this.ultimo) {

                    if (this.heap[posH + 1].compareTo(this.heap[posH]) < 0) {
                        posH++;
                    }
                }
                if (this.heap[posH].compareTo(temp) < 0) {
                    this.heap[posPadre] = this.heap[posH];
                    this.heap[posH] = temp;
                    posPadre = posH;

                } else {
                    salir = true;
                }
            } else {
                salir = true;
            }
        }
    }

    public boolean eliminarCima() {
        boolean exito;
        if (this.ultimo == 0) {
            exito = false;
        } else {
            this.heap[1] = this.heap[ultimo];
            this.ultimo--;
            ordenarHaciaAbajo(1);
            exito = true;
        }
        return exito;
    }

    public boolean esVacio() {
        return this.ultimo == 0;
    }

    public Object recuperarCima() {
        Object cima = null;
        if (this.ultimo > 0) {
            cima = (Object) this.heap[1];
        }
        return cima;
    }

    public String toString() {
        String cadena = "Arbol Vacío";
        int i = 1;
        if (this.ultimo > 0) {
            cadena = "";
            while (i <= this.ultimo) {
                cadena += this.heap[i] + "-> HI: ";
                if (i * 2 <= this.ultimo) {
                    cadena += this.heap[i * 2];

                } else {
                    cadena += "-";

                }
                cadena += " HD: ";
                if (i * 2 <= this.ultimo) {
                    cadena += this.heap[i * 2 + 1];
                } else {
                    cadena += "-";

                }

                cadena += "\n";
                i++;
            }
        }
        return cadena;
    }

    public ArbolHeap clone() {
        ArbolHeap heapClone = new ArbolHeap();
        heapClone.heap = this.heap.clone();
        heapClone.ultimo = this.ultimo;
        return heapClone;
    }
}
