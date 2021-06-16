package conjuntistas.dinamicas;

public class ArbolAVL {

    private NodoAVL raiz;

    public ArbolAVL() {
        this.raiz = null;
    }

    public boolean insertar(Comparable elemento) {
        boolean exito = false;
        /**
         * Recibe un elemento y lo inserta de forma ordenada.
         */

        // Caso en que el elemento a insertar sea la raiz.
        if (this.raiz == null) {
            this.raiz = new NodoAVL(elemento);
            exito = true;
        } else {
            // LLamado al método recursivo.
            exito = insertarRecursivo(this.raiz, elemento);

        }

        if (exito) {
            this.raiz.recalcularAltura();
            balancearRaiz(this.raiz);
        }
        return exito;
    }

    private boolean insertarRecursivo(NodoAVL nodo, Comparable elemento) {
        boolean exito = true;
        // Caso en que ya se encuentre el elemento en el arbol
        if (elemento.compareTo(nodo.getElemento()) == 0) {
            exito = false;
        } else {
            // Caso en el que el elemento sea menor
            if (elemento.compareTo(nodo.getElemento()) < 0) {
                if (nodo.getIzquierdo() != null) {
                    // Recorremos por el subarbol izquierdo
                    exito = insertarRecursivo(nodo.getIzquierdo(), elemento);
                } else {
                    // En el caso de que sea nulo se inserta.
                    nodo.setIzquierdo(new NodoAVL(elemento));
                    nodo.recalcularAltura();
                }
            } else {
                // Caso en el que sea mayor
                if (nodo.getDerecho() != null) {
                    // Recorremos por el subarbol derecho
                    exito = insertarRecursivo(nodo.getDerecho(), elemento);
                } else {
                    // En el caso de que sea nulo se inserta.
                    nodo.setDerecho(new NodoAVL(elemento));
                    nodo.recalcularAltura();
                }
            }

        }
        if (exito) {
            nodo.recalcularAltura();
            balancearNodo(nodo, elemento);
        }

        return exito;
    }

    public boolean eliminar(Comparable elemento) {
        /**
         * Recibe un elemento a eliminar y procede a eliminarlo.
         */
        boolean exito = false;
        if (this.raiz != null) {
            exito = eliminarAux(elemento, this.raiz, null);
        }
        return exito;
    }

    private boolean eliminarAux(Comparable elemento, NodoAVL nodo, NodoAVL padre) {
        boolean exito = false;
        // Mientras que nodo no sea null.
        if (nodo != null) {
            int temp = elemento.compareTo(nodo.getElemento());
            // Caso en que el elemento a eliminar se encuentre por el subarbol izquierdo.
            if (temp < 0) {
                exito = eliminarAux(elemento, nodo.getIzquierdo(), nodo);
            } else {
                // Caso en que el elemento a eliminar se encuentre por el subarbol derecho.
                if (temp > 0) {
                    exito = eliminarAux(elemento, nodo.getDerecho(), nodo);
                } else {
                    // Caso en que encuentra el elemento a eliminar.
                    // Se procede segun los casos de eliminar.
                    if (nodo.getIzquierdo() == null && nodo.getDerecho() == null) {
                        primerCaso(padre, elemento);
                        padre.recalcularAltura();
                        aplicarRotaciones(padre);
                    } else {
                        if (nodo.getIzquierdo() == null || nodo.getDerecho() == null) {
                            segundoCaso(elemento, nodo, padre);
                            padre.recalcularAltura();
                            aplicarRotaciones(padre);
                        } else {
                            tercerCaso(nodo);
                            padre.recalcularAltura();
                            aplicarRotaciones(padre);
                        }
                    }
                    exito = true;
                }
            }
        }
        return exito;
    }

    private void primerCaso(NodoAVL nodo, Comparable elem) {
        // El nodo a eliminar es una hoja
        if (nodo == null) {
            // Caso especial al intentar eliminar la raiz.
            this.raiz = null;
        } else {
            int aux = elem.compareTo(nodo.getElemento());
            // Si el nodo a eliminar se encuentra a la izquierda.
            // Seteamos en null el enlace al padre.
            if (aux < 0) {
                nodo.setIzquierdo(null);
            } else {
                // Caso contrario.
                nodo.setDerecho(null);
            }
        }
    }

    private void segundoCaso(Comparable elem, NodoAVL hijo, NodoAVL padre) {
        // Caso en el que el nodo tiene un solo hijo
        NodoAVL derecho = hijo.getDerecho();
        NodoAVL izquierdo = hijo.getIzquierdo();

        if (padre == null) {
            // Caso especial.
            if (derecho == null) {
                this.raiz = izquierdo;
            } else {
                this.raiz = derecho;
            }

        } else {
            int aux = elem.compareTo(padre.getElemento());
            // Si el elemento es menor
            if (aux < 0) {
                // Caso por subarbol izquierdo
                if (izquierdo == null) {
                    // Seteamos el HD del nodo como HI del padre.
                    padre.setIzquierdo(derecho);
                } else {
                    // Seteamos el HI del nodo como HI del padre.
                    padre.setIzquierdo(izquierdo);
                }
            } else {
                // Caso contrario.
                if (izquierdo == null) {
                    // Seteamos el HD del nodo como HD del padre.
                    padre.setDerecho(derecho);
                } else {
                    // Seteamos el HI del nodo como HD del padre.
                    padre.setDerecho(izquierdo);
                }
            }
        }
    }

    private void tercerCaso(NodoAVL nodo) {
        /**
         * Caso 3, el nodo tiene 2 hijos. Segun candidato B.
         */

        // Bajamos por el nodo Derecho.
        NodoAVL derecho = nodo.getDerecho();

        // Buscamos el nodo que no tiene hijo izquierdo.
        while (derecho.getIzquierdo() != null) {
            derecho = derecho.getIzquierdo();
        }
        // Realizamos el cambio
        nodo.setElemento(derecho.getElemento());
        if (derecho.getDerecho() != null) {
            nodo.setDerecho(derecho.getDerecho());
        }
    }

    public boolean balancearNodo(NodoAVL nodo, Comparable elto) {
        // evaluamos el balande de nodo
        int balance = balance(nodo);
        boolean exito = true;

        if (balance < -1 || balance > 1) {
            // hay que balancear el nodo
            NodoAVL balanceado = aplicarRotaciones(nodo);
            if (this.raiz.getElemento().compareTo(elto) == 0) {
                exito = false;
            } else {
                if (this.raiz.getElemento().compareTo(elto) > 0) {
                    nodo.setIzquierdo(balanceado);
                } else {
                    nodo.setDerecho(balanceado);
                }
            }

            nodo.recalcularAltura();
        }

        return exito;
    } // Métodos de balanceo

    private NodoAVL aplicarRotaciones(NodoAVL nodo) {
        // System.out.println("Balance raiz " + balance(raiz));
        NodoAVL balanceado = null;
        int balanceR = balance(nodo);
        int balanceD = balance(nodo.getDerecho());
        int balanceI = balance(nodo.getIzquierdo());

        System.out.println("Raiz:" + balanceR + " Izq: " + balanceI + " Der: " + balanceD);
        if (balanceR == -1 || balanceR == 1 || balanceR == 0) {
            System.out.println("Esta balanceado");

        } else {
            System.out.println("No esta balanceado");

            if (balanceR == 2 && (balanceI == 1 || balanceI == 0)) {
                System.out.println("Usó Rotacion simple por Derecha.");

                balanceado = rotacionSimpleDerecha(nodo);
            } else {

                if (balanceR == -2 && balanceD == 1) {
                    System.out.println("Usó Rotacion Doble por Derecha-Izquieda.");
                    nodo.setDerecho(rotacionSimpleDerecha(nodo.getDerecho()));
                    balanceado = rotacionSimpleIzquierda(nodo);
                } else {

                    if (balanceR == -2 && (balanceD == -1 || balanceD == 0)) {
                        System.out.println("Usó Rotacion simple por Izquierda.");
                        balanceado = rotacionSimpleIzquierda(nodo);
                    } else {

                        if (balanceR == 2 && balanceI == -1) {
                            System.out.println("Usó Rotacion Doble por Izquierda-derecha.");
                            nodo.setIzquierdo(rotacionSimpleIzquierda(nodo.getIzquierdo()));
                            balanceado = rotacionSimpleDerecha(nodo);
                        }
                    }
                }
            }
        }
        return balanceado;
    }

    private int balance(NodoAVL nodo) {
        int balance = 0;
        int alturaDer = -1, altureaIzq = -1;
        if (nodo != null) {
            if (nodo.getDerecho() != null) {
                alturaDer = nodo.getDerecho().getAltura();
            }
            if (nodo.getIzquierdo() != null) {
                altureaIzq = nodo.getIzquierdo().getAltura();
            }
        }

        balance = altureaIzq - alturaDer;
        return balance;
    }

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

    // Rotaciones simples
    private NodoAVL rotacionSimpleIzquierda(NodoAVL raiz) {
        // r es mi pivote
        NodoAVL hijo, temp;
        hijo = raiz.getDerecho();
        temp = hijo.getIzquierdo();
        hijo.setIzquierdo(raiz);
        raiz.setDerecho(temp);
        raiz.recalcularAltura();
        return hijo;
    }

    private NodoAVL rotacionSimpleDerecha(NodoAVL r) {
        NodoAVL h, temp;
        h = r.getIzquierdo();
        temp = h.getDerecho();
        h.setDerecho(r);
        r.setIzquierdo(temp);
        r.recalcularAltura();

        return h;
    }

    public String toString() {
        /**
         * Metodo solo para fines de debugging.
         */
        String retorno = "Árbol vacio";

        if (this.raiz != null) {
            retorno = toStringRec(this.raiz);
        }
        return retorno;
    }

    private String toStringRec(NodoAVL nodo) {
        String cadena = "";
        if (nodo != null) {
            cadena = nodo.getElemento().toString() + ": ";
            NodoAVL izquierdo = nodo.getIzquierdo();
            NodoAVL derecho = nodo.getDerecho();

            if (izquierdo != null) {
                cadena += " HI: " + izquierdo.getElemento().toString();
            } else {
                cadena += " HI: - ";
            }

            if (derecho != null) {
                cadena += " HD: " + derecho.getElemento().toString();
            } else {
                cadena += " HD: - ";
            }

            cadena += "\n";

            if (izquierdo != null) {
                cadena += toStringRec(izquierdo);
            }

            if (derecho != null) {
                cadena += toStringRec(derecho);
            }
        }
        return cadena;
    }

}
