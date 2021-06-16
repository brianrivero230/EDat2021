package conjuntistas.dinamicas;

public class NodoBB {
    private Comparable elemento;
    private NodoBB izquierdo;
    private NodoBB derecho;

    public NodoBB(Comparable unElem, NodoBB izq, NodoBB der) {
        this.elemento = unElem;
        this.derecho = der;
        this.izquierdo = izq;
    }

    public NodoBB(Comparable unElem) {
        this.elemento = unElem;
        this.derecho = null;
        this.izquierdo = null;
    }

    public Comparable getElemento() {
        return elemento;
    }

    public void setElemento(Comparable elemento) {
        this.elemento = elemento;
    }

    public NodoBB getIzquierdo() {
        return izquierdo;
    }

    public void setIzquierdo(NodoBB izquierdo) {
        this.izquierdo = izquierdo;
    }

    public NodoBB getDerecho() {
        return derecho;
    }

    public void setDerecho(NodoBB derecho) {
        this.derecho = derecho;
    }

}
