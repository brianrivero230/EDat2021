package jerarquicas.dinamicas;

/**
 *
 * @author brian riverp
 */
public class NodoGen {

    private Object elem;
    private NodoGen hijoIzquierdo;
    private NodoGen hermanoDerecho;

    //Constructores
    public NodoGen(Object elem, NodoGen izquierdo, NodoGen derecho) {
        this.elem = elem;
        this.hijoIzquierdo = izquierdo;
        this.hermanoDerecho = derecho;
    }

    public NodoGen(Object elem) {
        this.elem = elem;
        this.hijoIzquierdo = null;
        this.hermanoDerecho = null;
    }

    //Observadores
    public Object getElem() {
        return this.elem;
    }

    public NodoGen getHijoIzquierdo() {
        return this.hijoIzquierdo;
    }

    public NodoGen getHermanoDerecho() {
        return this.hermanoDerecho;
    }

    //Modificadores
    public void setElem(Object elemento) {
        this.elem = elemento;
    }

    public void setHijoIzquierdo(NodoGen izquierdo) {
        this.hijoIzquierdo = izquierdo;
    }

    public void setHermanoDerecho(NodoGen derecho) {
        this.hermanoDerecho = derecho;
    }
}
