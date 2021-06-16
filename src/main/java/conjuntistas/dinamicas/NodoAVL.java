package conjuntistas.dinamicas;

public class NodoAVL {
    private int altura;
    private Comparable elemento;
    private NodoAVL izquierdo;
    private NodoAVL derecho;

    public NodoAVL(Comparable elem, NodoAVL izq, NodoAVL der) {
        this.izquierdo = izq;
        this.derecho = der;
        this.elemento = elem;
        this.altura = 0;
    }

    public NodoAVL(Comparable elem) {
        this.elemento = elem;
        this.derecho = null;
        this.izquierdo = null;
        this.altura = 0;
    }

    public void recalcularAltura() {
        int alturaDer = -1, altureaIzq = -1;
        if (this.derecho != null) {
            alturaDer = this.derecho.getAltura();
        }
        if (this.izquierdo != null) {
            altureaIzq = this.izquierdo.getAltura();
        }
        // this.altura = Math.max(altureaIzq, alturaDer) + 1;
        if (altureaIzq >= alturaDer) {
            this.altura = altureaIzq + 1;
        } else {
            this.altura = alturaDer + 1;
        }
    }

    public int getAltura() {

        return this.altura;
    }

    public Comparable getElemento() {
        return elemento;
    }

    public void setElemento(Comparable elemento) {
        this.elemento = elemento;
    }

    public NodoAVL getIzquierdo() {
        return this.izquierdo;
    }

    public void setIzquierdo(NodoAVL izquierdo) {
        this.izquierdo = izquierdo;
    }

    public NodoAVL getDerecho() {
        return this.derecho;
    }

    public void setDerecho(NodoAVL derecho) {
        this.derecho = derecho;
    }

}
