package conjuntistas.dinamicas;

public class Avl {
    private NodoAVL raiz;

    public Avl(){
        this.raiz = null;
    }


    public boolean insertar(Comparable elemento) {
        boolean retorno = false;

        if (this.raiz == null) {
            // en caso de no tener raiz la insertamos y retornamos true
            this.raiz = new NodoAVL(elemento, null, null);
            retorno = true;
        } else {
            if (!this.raiz.getElemento().equals(elemento)) {
                if (this.raiz.getElemento().compareTo(elemento) > 0) {
                    // vamos a relizar la insercion por la izquierda de la raiz
                    retorno = insertarAuxInsertarHijos(this.raiz, raiz.getIzquierdo(), 'I', elemento);
                } else {
                    // vamos a realizar la insercion por la derecha de la raiz
                    retorno = insertarAuxInsertarHijos(this.raiz, raiz.getDerecho(), 'D', elemento);
                }
            }
        }

        if (retorno) {
            this.raiz.recalcularAltura();
            balancearRaiz(this.raiz);
        }
        //
        return retorno;
    }

    private boolean insertarAuxInsertarHijos(NodoAVL padre, NodoAVL subRaiz, char hijo, Comparable elemento) {
        // comparamos con el elemento actual
        boolean retorno = false;

        if (subRaiz != null) {
            if (!subRaiz.getElemento().equals(elemento)) {
                if (subRaiz.getElemento().compareTo(elemento) > 0) {
                    // nos movemos a la izquierda de la subRaiz
                    retorno = insertarAuxInsertarHijos(subRaiz, subRaiz.getIzquierdo(), 'I', elemento);
                } else {
                    // nos movemos a la derecha de la subRaiz
                    retorno = insertarAuxInsertarHijos(subRaiz, subRaiz.getDerecho(), 'D', elemento);
                }
            }
        } else {
            // creamos y enlazamos en nodo
            NodoAVL nuevoHIjo = new NodoAVL(elemento, null, null);
            nuevoHIjo.recalcularAltura();
            subRaiz = nuevoHIjo;
            // enlazamos el padre con el hijo
            if (hijo == 'I') {
                padre.setIzquierdo(nuevoHIjo);
            } else {
                padre.setDerecho(nuevoHIjo);
            }

            retorno = true;
        }

        if (retorno) {
            subRaiz.recalcularAltura();
            balancear(padre, subRaiz, hijo);
        }

        return retorno;
    }

    // metodos de AVL

    public boolean balancear(NodoAVL padre, NodoAVL nodo, char hijo) {
        // evaluamos el balande de nodo
        int balance = balance(nodo);

        if (balance < -1 || balance > 1) {
            // hay que balancear el nodo
            NodoAVL balanceado = aplicarRotaciones(nodo);
            if (hijo == 'I') {
                padre.setIzquierdo(balanceado);
            } else {
                padre.setDerecho(balanceado);
            }
            padre.recalcularAltura();
        }

        return true;
    }

    /**
     * caso especial de balanceo del nodo raiz
     * 
     * @param nodo
     * @return
     */
    public boolean balancearRaiz(NodoAVL nodo) {
        // evaluamos el balande de nodo
        int balance = balance(nodo);
        if (balance < -1 || balance > 1) {
            // hay que balancear el nodo
            this.raiz = aplicarRotaciones(nodo);
        }
        this.raiz.recalcularAltura();
        return true;
    }

    /***
     * retornamos el balance de un nodo
     * 
     * @param nodo del cual queremos sacar el balance
     * @return el balance del nodo dado
     */
    public int balance(NodoAVL nodo) {
        int alturaIzquierdo = -1;
        int alturaDerecho = -1;

        if (nodo.getIzquierdo() != null) {
            alturaIzquierdo = nodo.getIzquierdo().getAltura();
        }

        if (nodo.getDerecho() != null) {
            alturaDerecho = nodo.getDerecho().getAltura();
        }

        return alturaIzquierdo - alturaDerecho;
    }

    /***
     * aplicamos las rotacion necesaria para ese nodo en caso de tener que aplicarla
     * 
     * @param nodo        al cual hay que aplicar una rotacion
     * @param balanceNodo es el balance del nodo a apĺicar la rotacion
     */
    private NodoAVL aplicarRotaciones(NodoAVL nodo) {
        NodoAVL balanceado = null;
        int balanceNodo = balance(nodo);
        if (balanceNodo == -2 && nodo.getDerecho() != null) {
            // esta caido hacia la derecha
            int balanceHijoDerecho = balance(nodo.getDerecho());
            if (balanceHijoDerecho == -1) {
                // giro simple a la izquierda
                balanceado = giroIzquierda(nodo);
            } else {
                // giro doble izquierda-derecha
                balanceado = dobleDerechaIzquierda(nodo);
            }
        } else {
            if (balanceNodo == 2 && nodo.getIzquierdo() != null) {
                // esta caido hacia la izquierda
                int balanceHijoIzquierda = balance(nodo.getIzquierdo());
                if (balanceHijoIzquierda == 1) {
                    // giro a la derecha simple
                    balanceado = giroDerecha(nodo);
                } else {
                    // giro doble derecha-izquierda
                    balanceado = dobleIzquierdaDerecha(nodo);
                }
            }
        }
        return balanceado;
    }

    // rotaciones

    /***
     * tenemos que el subarbol esta caido hacia la derecha (-2), y su hijo derecho
     * esta caido hacia la izquierda (1)
     * 
     * @param padre es nodo raiz del subarbol desbalanceado hacia la derecha
     */
    private NodoAVL dobleDerechaIzquierda(NodoAVL padre) {
        // hacemos girar hacia la derecha el hijo
        NodoAVL hijoDerecha = giroDerecha(padre.getDerecho());
        padre.setDerecho(hijoDerecha);
        // hacemos girar padre a la izquierda
        NodoAVL nuevoPadre = giroIzquierda(padre);
        // recalculamos alturas
        padre.recalcularAltura();
        hijoDerecha.recalcularAltura();
        return nuevoPadre;
    }

    /***
     * tenemos que el subarbol esta caido hacia la izquierda (2), y su hijo
     * izquierdo esta caido hacia la derecha (-1)
     * 
     * @param padre es nodo raiz del subarbol desbalanceado hacia la izquierda
     */
    private NodoAVL dobleIzquierdaDerecha(NodoAVL padre) {
        System.out.println("Aplicamos un giro izquierda-derecha");
        // hacemos rotar hacia la izquierda al hijo izquierdo de padre
        NodoAVL hijoIzqueirdo = giroIzquierda(padre.getIzquierdo());
        System.out.println("El nuevo hijo Izquierdo " + hijoIzqueirdo.getElemento().toString());
        padre.setIzquierdo(hijoIzqueirdo);
        // hacemos rotar hacia la derecha a el padre
        NodoAVL nuevoPadre = giroDerecha(padre);
        System.out.println("El nuevo padre: " + nuevoPadre.getElemento().toString());

        // recalculasmos alturas
        padre.recalcularAltura();
        hijoIzqueirdo.recalcularAltura();
        return nuevoPadre;
    }

    /***
     * En esta rotacion lo que pasa es que se intercambian los enlaces de padre e
     * hijo derecho Esto es el HI del HD de padre pasa a ser el HD de padre y el HI
     * de el HD de padre pasa a aser padre
     * 
     * @param padre
     * @return
     */
    private NodoAVL giroDerecha(NodoAVL padre) {
        NodoAVL hijoIzquierdo = padre.getIzquierdo();
        // guardamos el hijo derecho de hijoIzquierdo en un tmp
        NodoAVL temp = null;
        if (hijoIzquierdo.getDerecho() != null) {
            temp = hijoIzquierdo.getDerecho();
        }
        // hacemos que hijo tenga como derecho a su padre
        hijoIzquierdo.setDerecho(padre);
        // hacemos que tmp sea hijo izquierdo de padre
        padre.setIzquierdo(temp);

        padre.recalcularAltura();
        hijoIzquierdo.recalcularAltura();
        return hijoIzquierdo;
    }

    /***
     * Este metodo retorna rota el subarbol hacia la derecha, esto para mantener el
     * balance del arbol, esto se aplica cuando el arbol esta caido hacia la
     * izquierda (balance 2)
     * 
     * @param padre         es la raiz del subarbol a rotar
     * @param hijoIzquierdo es el hijo hacia donde esta caido el subarbol que
     *                      tambien esta un poco caido hacia la izquierda (balance
     *                      1)
     * @return el nuevo sobarbol balanceado
     */
    private NodoAVL giroIzquierda(NodoAVL padre) {
        // tomamos al hijo derecho de padre
        NodoAVL hijoDerecho = padre.getDerecho();
        // tomamos el hijo izquiedo de nuetro hijo derecho
        NodoAVL temp = null;
        if (hijoDerecho.getIzquierdo() != null) {
            temp = hijoDerecho.getIzquierdo();
        }

        // hacemos que padre sea hijo derecho de su hijo derecho
        hijoDerecho.setIzquierdo(padre);
        // hacemos que el hijo derecho de padre sea el HI de su hijo derecho
        padre.setDerecho(temp);

        // recalculamos las alturas
        padre.recalcularAltura();
        hijoDerecho.recalcularAltura();
        return hijoDerecho;
    }

}