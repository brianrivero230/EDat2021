package jerarquicas.dinamicas;

/**
 * @author brian rivero
 */
public class NodoArbol {
    private Object elem;
    private NodoArbol izquierdo;
    private NodoArbol derecho;
    
//Constructores
    public NodoArbol(Object elem, NodoArbol izquierdo, NodoArbol derecho){
        this.elem = elem;
        this.derecho = derecho;
        this.izquierdo = izquierdo;
    }   
    public NodoArbol(Object elem){
        this.elem = elem;
        this.derecho = null;
        this.izquierdo = null;
    }
    
//Observadores
    public Object getElem(){
        return this.elem;
    }
    public NodoArbol getIzquierdo(){
        return this.izquierdo;
    }
    public NodoArbol getDerecho(){
        return this.derecho;
    }
    
//Modificadores
    public void setElem(Object elem){
        this.elem = elem;       
    }
    public void setIzquierdo(NodoArbol izquierdo){
        this.izquierdo = izquierdo;       
    }
    public void setDerecho(NodoArbol derecho){
        this.derecho = derecho;       
    }
}
