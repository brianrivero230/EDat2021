package jerarquicas.dinamicas;

import lineales.dinamicas.Lista;
import lineales.estaticas.Cola;

/**
 * Brian Rivero [FAI-2205] Leonel Llancaqueo [FAI-2964].
 */
public class ArbolBin {

    private NodoArbol raiz;

    // Constructor vacío.
    public ArbolBin() {
        this.raiz = null;
    }

    public boolean insertar(Object elemNuevo, Object elemPadre, char lugar) {
        /**
         * Inseramos elemeNuevo como uno de los hijos (I/D) indicados por lugar,
         * elemPadre es el elemento que vamos a buscar por arbol en caso de que no este
         * retorna falso, si el nodo indicado esta ocupado retorna falso.
         */

        boolean retorno = true;

        // si el arbol esta vacio, se guarda como raiz
        if (this.raiz == null) {
            this.raiz = new NodoArbol(elemNuevo);

        } else {
            // si el arbol no esta vacio, vamos se busca a su padre por preorden
            retorno = auxInsertarPreorden(this.raiz, elemNuevo, elemPadre, lugar);
        }

        return retorno;
    }

    private boolean auxInsertarPreorden(NodoArbol raiz, Object nuevoElemento, Object elementoPadre, char posicion) {
        // Buscamos en la raiz el elemento padre, pero si no es igual, recorremos en
        // preorden
        boolean retorno = false;

        if (raiz.getElem().equals(elementoPadre)) {
            if (posicion == 'I' && raiz.getIzquierdo() == null) {
                raiz.setIzquierdo(new NodoArbol(nuevoElemento));
                retorno = true;
            }

            if (posicion == 'D' && raiz.getDerecho() == null) {
                raiz.setDerecho(new NodoArbol(nuevoElemento));
                retorno = true;
            }
        } else {
            if (raiz.getIzquierdo() != null) {
                // si el hijo izquierdo es distinto null, entonces hacemos el llamado recursivo
                retorno = retorno || auxInsertarPreorden(raiz.getIzquierdo(), nuevoElemento, elementoPadre, posicion);
            }

            if (raiz.getDerecho() != null) {
                // si el hijo derecho es distinto de null, entonces hacemos el llamado recursivo
                retorno = retorno || auxInsertarPreorden(raiz.getDerecho(), nuevoElemento, elementoPadre, posicion);
            }
        }

        return retorno;
    }

    // Esta comentado ya que no se utiliza.
    /*
     * private NodoArbol obtenerNodo(NodoArbol nodo, Object elem) { /** Busca un
     * elemento y retorna el nodo que lo contiene, en caso de no encontrarlo retorna
     * null.
     */
    /*
     * NodoArbol aux = null;
     * 
     * if (aux != null) { if (aux.getElem().equals(elem)) { // si el elemento
     * buscado es igual a aux, lo devuelve. aux = nodo; } else { // Caso contrario
     * busca en el HI. aux = obtenerNodo(aux.getIzquierdo(), elem); // Si no lo
     * encuentra en el HI busca en el HD. if (aux == null) { aux =
     * obtenerNodo(aux.getDerecho(), elem); } } } return aux; }
     */

    public boolean esVacio() {
        /**
         * Devuelve falso si hay al menos un elemento cargado en el árbol y verdadero en
         * caso contrario.
         */
        return (this.raiz == null);
    }

    public int altura() {
        /**
         * Devuelve la altura del árbol, es decir la longitud del camino más largo desde
         * la raíz hasta una hoja, Haciendo uso del método alturaRecursivo.
         */
        return alturaRecursivo(this.raiz) - 1;
    }

    private int alturaRecursivo(NodoArbol aux) {
        // Utilizo dos variables auxiliares como contadores.
        int alturaDer = 0, alturaIzq = 0;

        // Mientras que el nodo aux no sea nulo se cuenta recursivamente la altura.
        if (aux != null) {

            alturaDer = alturaRecursivo(aux.getDerecho()) + 1;
            alturaIzq = alturaRecursivo(aux.getIzquierdo()) + 1;
            /*
             * En el caso de que la altura izquierda sea mayor se le asigna a alturaDer la
             * cual va a ser la variable retornada
             */
            if (alturaIzq >= alturaDer) {
                alturaDer = alturaIzq;
            }
        }

        return alturaDer;
    }

    public void vaciar() {
        // Quita todos los elementos de la estructura colocando la raiz en null
        this.raiz = null;
    }

    public int nivel(Object elem) {
        /**
         * Devuelve el nivel de un elemento de forma recursiva en el árbol, Si el
         * elemento no existe en el árbol devuelve -1.
         */
        int nivel = -1;

        if (this.raiz != null) {
            nivel = nivelRecursivo(this.raiz, elem, 0);
        }

        return nivel;
    }

    private int nivelRecursivo(NodoArbol aux, Object elem, int altura) {

        int nivel = -1;
        // Mientas que el arbol no este vacío.
        if (aux != null) {
            // Buscamos el nodo
            if (aux.getElem().equals(elem)) {
                nivel = altura;
            } else {
                // Caso que recorre el camino derecho.
                nivel = nivelRecursivo(aux.getIzquierdo(), elem, altura + 1);
                if (nivel == -1) {
                    // Caso que recorre el camino izquierdo.
                    nivel = nivelRecursivo(aux.getDerecho(), elem, altura + 1);
                }
            }
        }
        return nivel;
    }

    public Object padre(Object elemento) {
        /**
         * Dado un elemento devuelve el valor almacenado en su nodo padre busca la
         * primera aparición de elemento de forma recursiva.
         */

        Object aux = null;

        if (!this.esVacio()) {
            if (!this.raiz.getElem().equals(elemento)) {
                aux = padreRecursivo(this.raiz, elemento);
            }
        }

        return aux;
    }

    private Object padreRecursivo(NodoArbol raiz, Object elemento) {

        Object retorno = null;
        boolean aux = true;

        // mientras que el hijo izquierdo no sea null
        if (raiz.getIzquierdo() != null) {

            if (raiz.getIzquierdo().getElem().equals(elemento)) {
                retorno = raiz.getElem();
                aux = false;
            } else {
                retorno = padreRecursivo(raiz.getIzquierdo(), elemento);
                if (retorno != null) {
                    aux = false;
                }
            }
        }
        // mientras que el hijo derecho no sea null, y el aux sea verdadero
        if (aux && raiz.getDerecho() != null) {

            if (raiz.getDerecho().getElem().equals(elemento)) {
                retorno = raiz.getElem();
            } else {
                retorno = padreRecursivo(raiz.getDerecho(), elemento);
            }
        }

        return retorno;
    }

    @Override
    public ArbolBin clone() {
        /**
         * Genera y devuelve recursivamente un árbol binario que ese quivalente (igual
         * estructura y contenido de los nodos)que el árbol original.
         */
        ArbolBin arbolBinClone = new ArbolBin();
        arbolBinClone.raiz = cloneRecursivo(this.raiz);

        return arbolBinClone;
    }

    private NodoArbol cloneRecursivo(NodoArbol nodo) {
        // Creamos el nodo que nos va a servir para enlazar el arbol.
        NodoArbol hijo = null;
        if (nodo != null) {
            // El paso recursivo va enlazando nodo por nodo en el lugar que corresponden
            hijo = new NodoArbol(nodo.getElem(), cloneRecursivo(nodo.getIzquierdo()),
                    cloneRecursivo(nodo.getDerecho()));
        }
        return hijo;
    }

    public ArbolBin cloneInvertido() {
        /**
         * Genera y devuelve recursivamente un árbol binario que ese quivalente (igual
         * estructura y contenido de los nodos)que el árbol original.
         */
        ArbolBin arbolBinClone = new ArbolBin();
        arbolBinClone.raiz = cloneInvertidoRecursivo(this.raiz);

        return arbolBinClone;
    }

    private NodoArbol cloneInvertidoRecursivo(NodoArbol nodo) {
        // Creamos el nodo que nos va a servir para enlazar el arbol.
        NodoArbol hijo = null;
        if (nodo != null) {
            // El paso recursivo va enlazando nodo por nodo en el lugar que corresponden
            hijo = new NodoArbol(nodo.getElem(), cloneInvertidoRecursivo(nodo.getDerecho()),
                    cloneInvertidoRecursivo(nodo.getIzquierdo()));
        }
        return hijo;
    }

    @Override
    public String toString() {
        /**
         * Genera y devuelve recursivamente una cadena de caracteres que indica cuál es
         * la raíz del árbol y quienes son los hijos de cada nodo.
         */
        String cadena;
        if (this.raiz != null) {
            cadena = toStringRecursivo(this.raiz);
        } else {
            cadena = "Árbol vacío";
        }
        return cadena;
    }

    private String toStringRecursivo(NodoArbol nodo) {

        String cadena = "";
        if (nodo != null) {
            cadena = "";

            if (nodo.getDerecho() != null || nodo.getIzquierdo() != null) {
                cadena += "Raiz: " + nodo.getElem();
                if (nodo.getIzquierdo() != null) {
                    cadena += "[ HI: " + nodo.getIzquierdo().getElem() + " ]";
                } else {
                    cadena += "[ HI: null ]";
                }
                if (nodo.getDerecho() != null) {
                    cadena += "[ HD: " + nodo.getDerecho().getElem() + " ]" + "\n";
                } else {
                    cadena += "[ HD: null ] \n";
                }

                cadena += toStringRecursivo(nodo.getIzquierdo());
                cadena += toStringRecursivo(nodo.getDerecho());
            }
        }
        return cadena;
    }

    public Lista listarInorden() {
        /**
         * Retorna una lista con el formato In Orden Izquierdo - Raiz - Derecho.
         */
        Lista lista = new Lista();
        inOrdenRecursivo(this.raiz, lista);
        return lista;
    }

    private void inOrdenRecursivo(NodoArbol nodo, Lista lista) {
        // Mientas que el nodo no sea nulo.
        if (nodo != null) {
            // Recorre el arbol recursivamente del lado Izq.
            inOrdenRecursivo(nodo.getIzquierdo(), lista);

            // Inserta la raiz.
            lista.insertar(nodo.getElem(), lista.longitud() + 1);

            // Luego del der.
            inOrdenRecursivo(nodo.getDerecho(), lista);
        }
    }

    public Lista listarPreorden() {
        /**
         * Retorna una lista con el formato Pre-Orden Raiz - Izquierdo - Derecho.
         */
        Lista lista = new Lista();
        preOrdenRecursivo(this.raiz, lista);

        return lista;
    }

    private void preOrdenRecursivo(NodoArbol nodo, Lista lista) {
        // Mientras que el nodo no sea nulo.
        if (nodo != null) {
            // Se inserta la raiz.
            lista.insertar(nodo.getElem(), lista.longitud() + 1);
            // Se recorre la parte izquierda.
            preOrdenRecursivo(nodo.getIzquierdo(), lista);
            // Y luego la derecha.
            preOrdenRecursivo(nodo.getDerecho(), lista);
        }
    }

    public Lista listarPosorden() {
        /**
         * Retorna una lista con el formato Pos-Orden Izquierdo - Derecho - Raiz.
         */
        Lista lista = new Lista();
        posOrdenRecursivo(this.raiz, lista);
        return lista;
    }

    private void posOrdenRecursivo(NodoArbol nodo, Lista lista) {
        // Mientas que el nodo no sea nulo
        if (nodo != null) {
            // Recorre recursivamente por el lado Izquierdo
            posOrdenRecursivo(nodo.getIzquierdo(), lista);
            // Luego por el Derecho
            posOrdenRecursivo(nodo.getDerecho(), lista);
            // Y al final inserta la raiz.
            lista.insertar(nodo.getElem(), lista.longitud() + 1);
        }
    }

    public Lista listarPorNiveles() {
        Lista lista = new Lista();
        // Miertras que la raiz no este en null.
        if (this.raiz != null) {
            // Crea la cola y los punteros.
            Cola cola = new Cola();
            NodoArbol nodo, hijoIzq, hijoDer;
            // inserta la raiz en la cola
            cola.poner(this.raiz);
            int aux = 1;
            // Mientras que la cola no este vacía.
            while (!cola.esVacia()) {
                // Se le asigna a nodo el frente de la cola.
                nodo = (NodoArbol) cola.obtenerFrente();
                // Li inserta en la lita.
                lista.insertar(nodo.getElem(), aux);
                aux++;
                // Elimina el elmento que ya se inserto en la lista
                cola.sacar();
                // Actualiza los punteros
                hijoIzq = nodo.getIzquierdo();
                hijoDer = nodo.getDerecho();
                // Mientras que los nodos no sean nulos los inserta en la cola.
                if (hijoIzq != null) {
                    cola.poner(hijoIzq);
                }
                if (hijoDer != null) {
                    cola.poner(hijoDer);
                }
            }
        }
        // Retorna la lista
        return lista;
    }

    public Lista frontera() {
        /**
         * Retorna una lista con las hojas del arbol tomadas de izq a der.
         */
        Lista lista = new Lista();
        if (this.raiz != null) {
            fronteraRecursivo(lista, this.raiz);
        }
        return lista;
    }

    private void fronteraRecursivo(Lista lista, NodoArbol nodo) {
        // Mientras que el nodo no sea null.
        if (nodo != null) {
            // En el caso de que no posea hijos se inserta en la lista.
            if (nodo.getIzquierdo() == null && nodo.getDerecho() == null) {
                lista.insertar(nodo.getElem(), lista.longitud() + 1);// Ver listar in orden
            } else {
                // De lo contrario busca recursivamente.
                fronteraRecursivo(lista, nodo.getIzquierdo());
                fronteraRecursivo(lista, nodo.getDerecho());
            }
        }

    }

    public Lista ObtenerAnsestros(Object elem) {
        // Devuelve una lista con los ansestros del elemento pasado por parametro
        Lista listaAnsestros = new Lista();

        if (this.raiz != null) {
            ansestrosRecursivo(this.raiz, listaAnsestros, elem);
        }
        return listaAnsestros;
    }

    private boolean ansestrosRecursivo(NodoArbol nodo, Lista ansestros, Object elemento) {
        boolean retorno = false;
        // Mientras que el nodo no sea nulo.
        if (nodo != null) {
            // Si el nodo coincide con el elemento lo insertamos en la lista
            if (nodo.getElem().equals(elemento)) {
                retorno = true;
                ansestros.insertar(elemento, 1);
            } else {
                // Se busca el ansestro por la izquierda recursivamente
                if (nodo.getIzquierdo() != null) {
                    retorno = ansestrosRecursivo(nodo.getIzquierdo(), ansestros, elemento);
                }
                // Se busca el ansestro por la derecha recursivamente
                if (!retorno && nodo.getDerecho() != null) {
                    retorno = ansestrosRecursivo(nodo.getDerecho(), ansestros, elemento);
                }
                // Si lo encuentra lo inserta
                if (retorno) {
                    ansestros.insertar(nodo.getElem(), 1);
                }
            }
        }
        return retorno;
    }

    public Lista obtenerDescendientes(Object elemento) {
        /**
         * Devuelve una lista con todos los descendientes de un elemento pasado por
         * parámetro.
         */
        Lista unaLista = new Lista();
        if (this.raiz != null) {
            descendientesRecursivo(this.raiz, unaLista, elemento, false);
        }
        return unaLista;
    }

    private void descendientesRecursivo(NodoArbol unNodoArbol, Lista unaLista, Object elemento, boolean encontrado) {
        boolean esEncontrado = encontrado;
        // Mientras que el nodo no sea null.
        if (unNodoArbol != null) {
            // Compara el elemento con un elemento del arbol
            if (unNodoArbol.getElem().equals(elemento)) {
                esEncontrado = true;
            }
            // En el caso de que lo halla encontrado lo inserta en la lista
            if (esEncontrado && unNodoArbol.getIzquierdo() != null) {
                unaLista.insertar(unNodoArbol.getIzquierdo().getElem(), unaLista.longitud() + 1);
            }
            if (esEncontrado && unNodoArbol.getDerecho() != null) {
                unaLista.insertar(unNodoArbol.getDerecho().getElem(), unaLista.longitud() + 1);
            }
            // Caso contrario lo busca recursivamente.
            descendientesRecursivo(unNodoArbol.getIzquierdo(), unaLista, elemento, esEncontrado);
            descendientesRecursivo(unNodoArbol.getDerecho(), unaLista, elemento, esEncontrado);
        }
    }

    public boolean verificarPatron(Lista lisPatron) {
        /**
         * Recibe una lista por parametro y retorna true en caso de que coincida con al
         * menos un camino del arbol, caso contrario retorna false.
         */
        boolean exito;
        if (lisPatron.esVacia() && this.raiz == null) {
            exito = true;
        } else {
            exito = verificarPatronRecursivo(lisPatron, this.raiz, 1);
        }
        return exito;
    }

    private boolean verificarPatronRecursivo(Lista lista, NodoArbol nodo, int pos) {
        boolean controlIzq = false, control = true;

        if (pos <= lista.longitud()) {

            if (nodo != null) {
                control = nodo.getElem().equals(lista.recuperar(pos));
                if (control) {
                    controlIzq = verificarPatronRecursivo(lista, nodo.getIzquierdo(), pos + 1);
                }
                if (!controlIzq && control) {
                    control = verificarPatronRecursivo(lista, nodo.getDerecho(), pos + 1);
                }
            } else {
                control = false;
            }
        }
        return control;
    }

    // ------------------------------------------
    public void cambiarHijos(Object izq, Object p, Object der) {

        if (this.raiz.getIzquierdo() == null && this.raiz.getDerecho() == null) {
            this.raiz.getIzquierdo().setElem(izq);
            this.raiz.getDerecho().setElem(der);
        } else {
            cambiarHijosRec(izq, p, der, this.raiz);
        }
    }

    private void cambiarHijosRec(Object izq, Object p, Object der, NodoArbol nodo) {
        if (nodo != null) {
            if (nodo.getElem().equals(p)) {
                nodo.getIzquierdo().setElem(izq);
                nodo.getDerecho().setElem(der);
            } else {
                cambiarHijosRec(izq, p, der, nodo.getIzquierdo());
                cambiarHijosRec(izq, p, der, nodo.getDerecho());
            }
        }
    }

}
