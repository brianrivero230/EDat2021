package grafos;

public class NodoAdy {
    private NodoVert vertice;
    private NodoAdy sigAdyacente;

    public NodoAdy(NodoVert vert, NodoAdy nod) {
        this.vertice = vert;
        this.sigAdyacente = nod;
    }

    public NodoVert getVertice() {
        return vertice;
    }

    public void setVertice(NodoVert vertice) {
        this.vertice = vertice;
    }

    public NodoAdy getSigAdyacente() {
        return sigAdyacente;
    }

    public void setSigAdyacente(NodoAdy sigAdyacente) {
        this.sigAdyacente = sigAdyacente;
    }

}
