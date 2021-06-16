package grafos;

public class Grafo {
    private NodoVert inicio = null;

    public Grafo(NodoVert ini) {
        this.inicio = ini;
    }

    public boolean insertarVertice(Object vertice) {
        boolean exito = false;
        NodoVert aux = this.ubicarVertice(vertice);
        if (aux != null) {
            this.inicio = new NodoVert(vertice, this.inicio, null);
            exito = true;
        }
        return exito;
    }

    private NodoVert ubicarVertice(Object buscado) {
        NodoVert aux = this.inicio;
        while (aux != null && !aux.getElem().equals(buscado)) {
            aux = aux.getSigVertice();
        }
        return aux;
    }
}
