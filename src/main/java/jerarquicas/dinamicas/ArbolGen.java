package jerarquicas.dinamicas;

import lineales.dinamicas.Cola;
import lineales.dinamicas.Lista;

/**
 *
 * Brian Rivero [FAI-2205] Leonel Llancaqueo [FAI-2964].
 */
public class ArbolGen {

    private NodoGen raiz;

    // Constructores

    public ArbolGen() {
        this.raiz = null;
    }

    // Métodos

    public boolean insertar(Object elemNuevo, Object elemPadre) {
        boolean exito = false;
        // Caso en que el padre sea la raiz.
        if (this.raiz == null) {
            this.raiz = new NodoGen(elemNuevo);
            exito = true;

        } else {
            // Si no es el padre, se busca el padre en el arbol.
            NodoGen nodoPadre = obtenerNodo(this.raiz, elemPadre);
            if (nodoPadre != null) {

                // Si el padre no tiene HI inserta a elemNuevo como HI.
                if (nodoPadre.getHijoIzquierdo() == null) {
                    nodoPadre.setHijoIzquierdo(new NodoGen(elemNuevo));
                    exito = true;
                } else {

                    // En el caso contrario, crea un nuevo nodo con el HI.
                    NodoGen hijo = nodoPadre.getHijoIzquierdo();

                    // Se recorren los HE hasta encontrar el último y se inserta el nuevo HD.
                    while (hijo != null && exito != true) {
                        if (hijo.getHermanoDerecho() == null) {
                            hijo.setHermanoDerecho(new NodoGen(elemNuevo));
                            exito = true;
                        } else {
                            hijo = hijo.getHermanoDerecho();

                        }
                    }
                }
            }
        }
        return exito;
    }

    private NodoGen obtenerNodo(NodoGen nodo, Object elem) {
        // Se crea una variable auxiliar para recorrer el arbol.
        NodoGen aux = null;
        if (nodo != null) {

            // En el caso de encontrarla se lo asigna a aux.
            if (nodo.getElem().equals(elem)) {
                aux = nodo;
            } else {

                // Si no, busca en sus HD.
                aux = obtenerNodo(nodo.getHermanoDerecho(), elem);

                // Y por último en el HI.
                if (aux == null) {
                    aux = obtenerNodo(nodo.getHijoIzquierdo(), elem);
                }
            }
        }
        return aux;
    }

    public boolean pertenece(Object elem) {
        /**
         * Devuelve verdadero si el elemento pasado por parámetro está en el árbol, y
         * falso en caso contrario.
         */
        return perteneceRecursivo(elem, this.raiz);
    }

    private boolean perteneceRecursivo(Object aux, NodoGen padre) {
        boolean control = false;
        if (padre != null) {

            // Caso en que lo encuentra.
            if (padre.getElem().equals(aux)) {
                control = true;
            } else {

                // De lo contrario lo busca por los HD.
                control = perteneceRecursivo(aux, padre.getHermanoDerecho());

                // Si no lo encontro sigue con HI.
                if (!control) {
                    control = perteneceRecursivo(aux, padre.getHijoIzquierdo());
                }
            }
        }
        return control;
    }

    public boolean esVacio() {
        return (this.raiz == null);
    }

    public Object padre(Object elem) {
        /**
         * Dado un elemento devuelve el valor almacenado en su nodo padre busca la
         * primera aparición de elemento de forma recursiva.
         */
        Object aux = null;
        if (!this.esVacio() && !this.raiz.getElem().equals(elem)) {
            aux = padreRecursivo(this.raiz, elem, this.raiz.getElem());
        }
        return aux;
    }

    private Object padreRecursivo(NodoGen raiz, Object elem, Object padre) {
        Object retorno = null;

        // Mientras que la raiz no sea null
        if (raiz != null) {

            // Caso en que coincida raiz con el elemento.
            if (raiz.getElem().equals(elem)) {
                retorno = padre;
            } else {

                // Recorremos los HD
                retorno = padreRecursivo(raiz.getHermanoDerecho(), elem, padre);

                // En caso de no ser ninguno, pasamos al HI
                if (retorno == null) {
                    retorno = padreRecursivo(raiz.getHijoIzquierdo(), elem, raiz.getElem());
                }
            }
        }
        return retorno;

    }

    public int altura() {
        /**
         * Devuelve la altura del árbol, es decir la longitud del camino más largo desde
         * la raíz hasta una hoja.
         */
        int altura = -1;
        if (this.raiz != null) {
            altura = alturaRecursivo(this.raiz);
        }
        return altura;
    }

    private int alturaRecursivo(NodoGen n) {
        // Utilizo la variable como contador.
        int altura = 0;
        // Mientras que el nodo no sea nulo recorro el arbol.
        if (n != null) {
            // Utilizo a alturaAux como auxiliar para contar la altura de los HI.
            int alturaAux;
            NodoGen nodoAux = n.getHijoIzquierdo();
            // Se recorren los HD.
            while (nodoAux != null) {
                alturaAux = alturaRecursivo(nodoAux) + 1;
                // En el caso de que se encuentre una altura mayor, se cambia.
                if (alturaAux > altura) {//
                    altura = alturaAux;
                }
                nodoAux = nodoAux.getHermanoDerecho();
            }
        }
        return altura;
    }

    public int nivel(Object elem) {
        /**
         * Devuelve el nivel de un elemento en el árbol.
         */
        // Caso base en que no exista.
        int retorno = -1;
        if (this.raiz != null) {
            retorno += nivelRecursivo(this.raiz, elem);
        }
        return retorno;
    }

    private int nivelRecursivo(NodoGen nodo, Object elemento) {
        int retorno = 0;
        if (nodo != null) {
            // Si lo encuenta se le suma 1.
            if (nodo.getElem().equals(elemento)) {
                retorno = 1;
            } else {

                // Sino se crea un aux para recorrer los HI.
                NodoGen aux = nodo.getHijoIzquierdo();
                // Y un contador para los hijos.
                int retornoHijos;
                // Se busca en los HD.
                while (aux != null && retorno == 0) {
                    retornoHijos = nivelRecursivo(aux, elemento);
                    if (retornoHijos > 0) {
                        retorno = retornoHijos + 1;
                    }
                    aux = aux.getHermanoDerecho();
                }
            }
        }
        return retorno;
    }

    public Lista ancestros(Object elemento) {
        // Devuelve una lista con los ansestros del elemento pasado por parametro
        Lista list = new Lista();
        if (this.raiz != null) {
            ancestrosRecursivo(this.raiz, list, elemento);
        }
        return list;
    }

    private boolean ancestrosRecursivo(NodoGen nodo, Lista list, Object elemento) {
        boolean retorno = false;
        if (nodo != null) {
            // Verificar si coincide con el elemento y lo inserta.
            if (nodo.getElem().equals(elemento)) {
                retorno = true;
            } else {

                // Caso contrario recorre por el HI.
                if (nodo.getHijoIzquierdo() != null) {
                    retorno = ancestrosRecursivo(nodo.getHijoIzquierdo(), list, elemento);
                }
                // Recorre con los HD.
                NodoGen aux = nodo.getHijoIzquierdo();
                while (aux != null && !retorno) {
                    retorno = retorno || ancestrosRecursivo(aux, list, elemento);
                    aux = aux.getHermanoDerecho();
                }

                if (retorno) {
                    list.insertar(nodo.getElem(), list.longitud() + 1);
                }
            }
        }
        return retorno;
    }

    @Override
    public ArbolGen clone() {
        /**
         * Genera y devuelve recursivamente un árbol binario que ese quivalente (igual
         * estructura y contenido de los nodos)que el árbol original.
         */
        ArbolGen arbolClone = new ArbolGen();
        arbolClone.raiz = cloneRecursivo(this.raiz);
        return arbolClone;
    }

    private NodoGen cloneRecursivo(NodoGen nodo) {
        // Creamos el nodo que nos va a servir para enlazar el arbol.
        NodoGen hijo = null;
        // El paso recursivo va enlazando nodo por nodo en el lugar que corresponden.
        if (nodo != null) {
            hijo = new NodoGen(nodo.getElem(), cloneRecursivo(nodo.getHijoIzquierdo()),
                    cloneRecursivo(nodo.getHermanoDerecho()));
        }
        return hijo;
    }

    public void vaciar() {
        // Quita todos los elementos de la estructura colocando la raiz en null
        this.raiz = null;
    }

    public Lista listarPreorden() {
        /**
         * Retorna una lista con el formato Pos Orden Raiz - Izquierdo - Derecho.
         */
        Lista salida = new Lista();
        if (this.raiz != null) {
            listarPreordenRecursivo(this.raiz, salida);
        }
        return salida;
    }

    private void listarPreordenRecursivo(NodoGen n, Lista list) {
        if (n != null) {

            // Inserta la Raiz.
            list.insertar(n.getElem(), list.longitud() + 1);

            // LLamado recursivo a HI.
            if (n.getHijoIzquierdo() != null) {
                listarPreordenRecursivo(n.getHijoIzquierdo(), list);
            }

            // LLamados recursivos a los HD.
            if (n.getHijoIzquierdo() != null) {
                NodoGen hijo = n.getHijoIzquierdo().getHermanoDerecho();
                while (hijo != null) {
                    listarPreordenRecursivo(hijo, list);
                    hijo = hijo.getHermanoDerecho();
                }
            }

        }
    }

    public Lista listarInorden() {
        /**
         * Retorna una lista con el formato In Orden Izquierdo - Raiz - Derecho.
         */
        Lista salida = new Lista();
        listarInordenRecursivo(this.raiz, salida);
        return salida;
    }

    private void listarInordenRecursivo(NodoGen n, Lista list) {
        if (n != null) {

            // LLamado al HI.
            if (n.getHijoIzquierdo() != null) {
                listarInordenRecursivo(n.getHijoIzquierdo(), list);
            }

            // Inserta la raiz.
            list.insertar(n.getElem(), list.longitud() + 1);

            // Llamados recursivos a los HD.
            if (n.getHijoIzquierdo() != null) {
                NodoGen hijo = n.getHijoIzquierdo().getHermanoDerecho();
                while (hijo != null) {
                    listarInordenRecursivo(hijo, list);
                    hijo = hijo.getHermanoDerecho();
                }
            }
        }
    }

    public Lista listarPosorden() {
        /**
         * Retorna una lista con el formato Pos Orden Izquierdo - Derecho - Raiz.
         */
        Lista salida = new Lista();
        if (this.raiz != null) {
            listarPosordenRecursivo(this.raiz, salida);
        }
        return salida;
    }

    private void listarPosordenRecursivo(NodoGen n, Lista list) {
        if (n != null) {

            // LLamado recursivo a HI.
            if (n.getHijoIzquierdo() != null) {
                listarPosordenRecursivo(n.getHijoIzquierdo(), list);
            }

            // LLamados recursivos a los HD.
            if (n.getHijoIzquierdo() != null) {
                NodoGen hijo = n.getHijoIzquierdo().getHermanoDerecho();
                while (hijo != null) {
                    listarPosordenRecursivo(hijo, list);
                    hijo = hijo.getHermanoDerecho();
                }
            }

            // Inserta la Raiz.
            list.insertar(n.getElem(), list.longitud() + 1);

        }
    }

    public Lista listarPorNiveles() {
        Lista lista = new Lista();
        // Mientras que la raiz no sea nula.
        if (this.raiz != null) {

            // Creamos la cola y los punteros.
            Cola cola = new Cola();
            NodoGen nodo, hijo;

            // Insertamos la raiz en la cola.
            cola.poner(this.raiz);
            int aux = 1;

            // Mientras que la cola no sea vacia.
            while (!cola.esVacia()) {

                // Asignamos el frente de la cola al primer puntero.
                nodo = (NodoGen) cola.obtenerFrente();

                // Lo insertamos en la lista.
                lista.insertar(nodo.getElem(), aux);
                aux++;

                // Y eliminamos el elemneto ya insertado en la lista.
                cola.sacar();

                // Asignamos el HI al segindo puntero.
                hijo = nodo.getHijoIzquierdo();

                // Recorremos los HD.
                while (hijo != null) {
                    cola.poner(hijo);
                    hijo = hijo.getHermanoDerecho();
                }
            }
        }
        return lista;
    }

    @Override
    public String toString() {
        return toStringRecursivo(this.raiz);
    }

    private String toStringRecursivo(NodoGen n) {
        String cadena = "";
        if (n != null) {
            // Se visita al nodo n
            cadena += n.getElem().toString() + " -> ";
            NodoGen hijo = n.getHijoIzquierdo();
            while (hijo != null) {
                cadena += hijo.getElem().toString() + ", ";
                hijo = hijo.getHermanoDerecho();
            }

            // Comienza el recorrido de los hijos de n llamando recursivamente
            hijo = n.getHijoIzquierdo();
            while (hijo != null) {
                cadena += "\n" + toStringRecursivo(hijo);
                hijo = hijo.getHermanoDerecho();
            }

        }

        return cadena;
    }

    public Lista fronntera() {
        /**
         * Retorna una lista con las hojas del arbol tomadas de izq a der.
         */
        Lista list = new Lista();
        if (this.raiz != null) {
            fronteraRecursivo(list, this.raiz);
        }
        return list;
    }

    private void fronteraRecursivo(Lista lista, NodoGen nodo) {
        // Mientras que el nodo no sea nulo.
        if (nodo != null) {
            // En el caso de que no posea HI, lo inserta en la lista.
            if (nodo.getHijoIzquierdo() == null) {
                lista.insertar(nodo.getElem(), lista.longitud() + 1);
            } else {
                // De lo contrario busca en los HD recursivamente.
                NodoGen hijo = nodo.getHijoIzquierdo();
                while (hijo != null) {
                    fronteraRecursivo(lista, hijo);
                    hijo = hijo.getHermanoDerecho();
                }
            }
        }
    }

    public boolean verificarPatron(Lista lis) {
        /**
         * Recibe una lista por parametro y retorna true en caso de que coincida con al
         * menos un camino del arbol, caso contrario retorna false.
         */
        boolean exito = true;
        if (!lis.esVacia() && this.raiz != null) {
            exito = verificarPatronRecursivo(lis, this.raiz, 1);
        }
        return exito;
    }

    private boolean verificarPatronRecursivo(Lista list, NodoGen nodo, int pos) {
        boolean controlIzq = false, control = true;

        if (pos <= list.longitud()) {
            if (nodo != null) {
                control = nodo.getElem().equals(list.recuperar(pos));
                if (control) {
                    controlIzq = verificarPatronRecursivo(list, nodo.getHijoIzquierdo(), pos + 1);
                }
                if (!controlIzq && control) {
                    NodoGen hijo = nodo.getHijoIzquierdo();
                    while (hijo != null) {
                        control = verificarPatronRecursivo(list, hijo, pos + 1);
                        hijo = hijo.getHermanoDerecho();
                    }
                }
            }
        }
        return control;
    }

    public int grado() {
        /**
         * Retorna el grado del arbol.
         */
        int grado = -1;
        // Si la raiz es nula, el grado es -1.
        if (this.raiz != null) {
            grado = gradoRecursivo(this.raiz);
        }
        return grado;
    }

    private int gradoRecursivo(NodoGen nodo) {
        int grado = 0;
        // Mientras que el nodo sea distinto de null.
        if (nodo != null) {
            // Cramos dos variables aux para recorrer los HD.
            // gradoAux calcula el grado del HI.
            int gradoAux = 0;
            NodoGen hijo = nodo.getHijoIzquierdo();
            // Recorremos los HD de forma recursiva.
            while (hijo != null) {
                // Hacemos uso del método privado para calcular el grado de los subarboles
                grado = gradoRecursivo(hijo);
                gradoAux++;
                hijo = hijo.getHermanoDerecho();
            }

            if (gradoAux > grado) {
                grado = gradoAux;
            }
        }
        return grado;
    }

    public int gradoSubarbol(Object n) {
        /**
         * Calcula el grado del subnodo pasado por parametro.
         */
        int grado = -1;
        if (this.raiz != null) {
            // Utilizamos el método privado obtener nodo.
            NodoGen aux = this.obtenerNodo(this.raiz, n);
            if (aux != null) {
                grado = gradoRecursivo(aux);
            }
        }
        return grado;
    }

    /**
     * Implementar el método listarEntreNiveles(int niv1, int niv2) que recibe como
     * parámetro dos elementos niv1 y niv2 y devuelve una lista con los elementos
     * del árbol que están entre los niveles niv1 y niv2 inclusive. El método debe
     * recorrer el árbol en inorden y no debe visitar nodos de más. Ejemplo: Si para
     * el árbol de la derecha se ingresa niv1=1 y niv2=2 debe devolver
     * 15,13,12,11,54,27,4.
     */

    public Lista listarEntreNiveles(int niv1, int niv2) {
        Lista lista = new Lista();

        if (this.raiz != null) {
            lista = listarAux(this.raiz, lista, niv1, niv2, 0);
        }
        return lista;

    }

    private Lista listarAux(NodoGen nodo, Lista list, int nivel1, int nivel2, int puntero) {
        // Modifico el método listar inorden
        if (nodo != null) {
            System.out.println("Paso por " + nodo.getElem());
            // En el caso que cumpla la condición lo lista
            if (puntero >= nivel1 && puntero <= nivel2) {

                // LLamado al HI.
                if (nodo.getHijoIzquierdo() != null) {
                    listarAux(nodo.getHijoIzquierdo(), list, nivel1, nivel2, puntero + 1);
                }

                // Inserta el nodo.
                list.insertar(nodo.getElem(), list.longitud() + 1);

                // Llamados recursivos a los HD.
                if (nodo.getHijoIzquierdo() != null) {
                    NodoGen hijo = nodo.getHijoIzquierdo().getHermanoDerecho();
                    while (hijo != null) {
                        listarAux(hijo, list, nivel1, nivel2, puntero + 1);
                        hijo = hijo.getHermanoDerecho();
                    }
                }
            } else {
                // Sino sigue con los demás hermanos
                listarAux(nodo.getHijoIzquierdo(), list, nivel1, nivel2, puntero + 1);

                // Como solo recorria por izquierda, recorro por derecha
                if (nodo.getHijoIzquierdo() != null) {
                    NodoGen hijo = nodo.getHijoIzquierdo().getHermanoDerecho();
                    while (hijo != null) {
                        listarAux(hijo, list, nivel1, nivel2, puntero + 1);
                        hijo = hijo.getHermanoDerecho();
                    }

                }
            }

        }

        return list;
    }
    // ------------------------------------ MOOD EXAMEN
    // ---------------------------------------------

    public boolean esPadreDe(Object a, Object b) {
        boolean exito = false;
        if (this.raiz != null) {
            exito = esPadreRec(this.raiz, a, b);
        }
        return exito;
    }

    private boolean esPadreRec(NodoGen padre, Object a, Object b) {
        boolean exito = false;

        if (padre != null) {
            NodoGen hijo = padre.getHijoIzquierdo();
            if (hijo != null) {
                System.out.println(padre.getElem() + " & " + hijo.getElem());
                if (padre.getElem().equals(a) && hijo.getElem().equals(b)) {
                    exito = true;
                } else {

                    exito = esPadreRec(padre.getHijoIzquierdo(), a, b);
                    if (!exito) {
                        exito = esPadreRec(padre.getHermanoDerecho(), a, b);
                    }
                }
            }
        }
        return exito;
    }
}
