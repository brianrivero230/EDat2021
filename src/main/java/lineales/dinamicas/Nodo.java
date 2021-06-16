package lineales.dinamicas;

public class Nodo {
    
    private Object elem;
    private Nodo enlace;
    
    //Constructor
    
    public Nodo (Object elem, Nodo enlace){
        this.elem = elem;
        this.enlace = enlace;
    }
    
    //Métodos get
    
    public Object getElem(){
        return elem;
    }
    
    public Nodo getEnlace(){
        return enlace;
    }
    
    //Métodos set
    
    public void setElem(Object elem){
        this.elem = elem;
    }
    
    public void setEnlace(Nodo enlace){
        this.enlace = enlace;
    }
}
