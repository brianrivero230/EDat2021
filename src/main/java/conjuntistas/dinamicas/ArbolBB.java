package conjuntistas.dinamicas;

import lineales.dinamicas.Lista;

public class ArbolBB {
    private NodoBB raiz;

    public ArbolBB() {
        this.raiz = null;
    }

    public boolean insertar(Comparable elemento) {
        boolean exito = true;
        /**
         * Recibe un elemento y lo inserta de forma ordenada.
         */

        // Caso en que el elemento a insertar sea la raiz.
        if (this.raiz == null) {
            this.raiz = new NodoBB(elemento);
        } else {
            // LLamado al método recursivo.
            exito = insertarRecursivo(this.raiz, elemento);
        }

        return exito;
    }

    private boolean insertarRecursivo(NodoBB nodo, Comparable elemento) {
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
                    nodo.setIzquierdo(new NodoBB(elemento));
                }
            } else {
                // Caso en el que sea mayor
                if (nodo.getDerecho() != null) {
                    // Recorremos por el subarbol derecho
                    exito = insertarRecursivo(nodo.getDerecho(), elemento);
                } else {
                    // En el caso de que sea nulo se inserta.
                    nodo.setDerecho(new NodoBB(elemento));
                }
            }
        }
        return exito;
    }

    public boolean esVacio() {
        /**
         * Retorna false si hay al menos un elemento en el arbol.
         */
        return this.raiz == null;
    }

    public boolean pertenece(Comparable elemento) {
        /**
         * Retorna true si el elemento se encuentra en el arbol.
         */
        boolean exito = false;
        if (this.raiz != null && elemento != null) {
            exito = perteneceRecursivo(this.raiz, elemento);
        }
        return exito;
    }

    private boolean perteneceRecursivo(NodoBB nodo, Comparable elemento) {
        boolean exito = false;
        // Mientras que el nodo no sea nulo.
        if (nodo != null) {
            // Caso en que se encuenta el elemento.
            if (nodo.getElemento().compareTo(elemento) == 0) {
                exito = true;

            } else {
                // Caso de busqueda
                if (nodo.getElemento().compareTo(elemento) < 0) {
                    // Busqueda por el subarbol derecho.
                    exito = perteneceRecursivo(nodo.getDerecho(), elemento);
                } else {
                    // Busqueda por el subarbol izquierdo.
                    exito = perteneceRecursivo(nodo.getIzquierdo(), elemento);
                }
            }
        }
        return exito;

    }

    public Comparable minimoElem() {
        /**
         * Recorre la rama correspondiente y retorna el elemento minimo del arbol.
         */
        Comparable elem = null;
        // Caso en que la rama no es nula.
        if (this.raiz != null) {
            NodoBB hijo = this.raiz;
            // Recorremos los HI en busca del minimo hasta encontrar al que tiene HI en
            // null.
            while (hijo.getIzquierdo() != null) {
                hijo = hijo.getIzquierdo();
            }
            // Asignamos el minimo.
            elem = hijo.getElemento();
        }
        // En caso de que el arbol sea vacío retorna null.
        return elem;
    }

    public Comparable maximoElem() {
        /**
         * Recorre la rama correspondiente y retorna el elemento maximo del arbol.
         */
        Comparable elem = null;
        // Caso en que la rama no es nula.
        if (this.raiz != null) {
            NodoBB hijo = this.raiz;
            // Recorremos los HD en busca del minimo hasta encontrar al que tiene HI en
            // null.
            while (hijo.getDerecho() != null) {
                hijo = hijo.getDerecho();
            }
            // Asignamos el maximo.
            elem = hijo.getElemento();
        }
        // En caso de que el arbol sea vacío retorna null.
        return elem;
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

    private String toStringRec(NodoBB nodo) {
        String cadena = "";
        if (nodo != null) {
            cadena = nodo.getElemento().toString() + ": ";
            NodoBB izquierdo = nodo.getIzquierdo();
            NodoBB derecho = nodo.getDerecho();

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

    private boolean eliminarAux(Comparable elemento, NodoBB nodo, NodoBB padre) {
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
                    } else {
                        if (nodo.getIzquierdo() == null || nodo.getDerecho() == null) {
                            segundoCaso(elemento, nodo, padre);
                        } else {
                            tercerCaso(nodo);
                        }
                    }
                    exito = true;
                }
            }
        }
        return exito;
    }

    private void primerCaso(NodoBB nodo, Comparable elem) {
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

    private void segundoCaso(Comparable elem, NodoBB hijo, NodoBB padre) {
        // Caso en el que el nodo tiene un solo hijo
        NodoBB derecho = hijo.getDerecho();
        NodoBB izquierdo = hijo.getIzquierdo();

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

    private void tercerCaso(NodoBB nodo) {
        /**
         * Caso 3, el nodo tiene 2 hijos. Segun candidato B.
         */

        // Bajamos por el nodo Derecho.
        NodoBB derecho = nodo.getDerecho();

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

    public Lista listar() {
        /**
         * Recorre el arbol completo y retorna una lista ordenada en Inorden.
         */
        Lista list = new Lista();
        if (this.raiz != null) {
            listarRecursivo(this.raiz, list);
        }
        return list;
    }

    private void listarRecursivo(NodoBB nodo, Lista lis) {
        // Listar recursivo en inorden.
        if (nodo != null) {
            listarRecursivo(nodo.getIzquierdo(), lis);
            lis.insertar(nodo.getElemento(), lis.longitud() + 1);
            listarRecursivo(nodo.getDerecho(), lis);
        }
    }

    public Lista listarRango(Comparable minimo, Comparable maximo) {
        /**
         * Recorre parte del arbol y retorna una lista ordenada con los elementos dentro
         * de intervalo pasado por parámetro.
         */
        Lista list = new Lista();
        if (minimo != null && maximo != null) {
            listarRangoRecursivo(this.raiz, list, minimo, maximo);
        }
        return list;
    }

    private void listarRangoRecursivo(NodoBB nodo, Lista list, Comparable min, Comparable max) {

        if (nodo != null) {
            Comparable elem = nodo.getElemento();
            // Busca del lado derecho.
            if (elem.compareTo(max) < 0) {
                listarRangoRecursivo(nodo.getDerecho(), list, min, max);
            }
            // Si esta dentro del intervalo no inserta.
            if (elem.compareTo(min) >= 0 && elem.compareTo(max) <= 0) {
                list.insertar(elem, 1);
            }
            // Busca por el lado izquierdo.
            if (elem.compareTo(min) > 0) {
                listarRangoRecursivo(nodo.getIzquierdo(), list, min, max);
            }
        }
    }

    public ArbolBB clone() {
        /**
         * Genera y devuelve recursivamente un árbol binario que ese quivalente (igual
         * estructura y contenido de los nodos) que el árbol original.
         */
        ArbolBB arbolClone = new ArbolBB();
        arbolClone.raiz = cloneRecursivo(this.raiz);
        return arbolClone;
    }

    private NodoBB cloneRecursivo(NodoBB nodo) {
        // Igual que el clone de ArbolBin.
        NodoBB hijo = null;
        if (nodo != null) {
            hijo = new NodoBB(nodo.getElemento(), cloneRecursivo(nodo.getDerecho()),
                    cloneRecursivo(nodo.getIzquierdo()));
        }
        return hijo;
    }

    /**
     * El método eliminarMinimo() que elimine el elemento más pequeño del árbol en
     * un sólo recorrido y visitando lo mínimo indispensable
     */

    public void eliminarMinimo() {

        if (this.raiz != null) {
            eliminarMin(this.raiz);
        }
    }

    private void eliminarMin(NodoBB hijo) {
        if (hijo.getIzquierdo() != null) {
            NodoBB padre = hijo;
            hijo = hijo.getIzquierdo();

            while (hijo.getIzquierdo() != null) {
                padre = padre.getIzquierdo();
                hijo = hijo.getIzquierdo();
            }

            if (hijo.getIzquierdo() == null && hijo.getDerecho() == null) {
                padre.setIzquierdo(null);
            } else {
                if (hijo.getIzquierdo() == null || hijo.getDerecho() == null) {
                    if (hijo.getIzquierdo() == null) {
                        padre.setIzquierdo(hijo.getDerecho());
                    } else {
                        if (hijo.getDerecho() == null) {
                            padre.setIzquierdo(hijo.getIzquierdo());
                        }
                    }
                } else {
                    tercerCaso(hijo);
                }
            }

        }
    }

    // ...........................

    public String concatenarPreordenDesde(char car, int n) {
        String retorno = "###";
        if (this.raiz != null) {
            retorno = concatenarPreordenDesdeAux(this.raiz, car, n);
        }
        return retorno;
    }

    private String concatenarPreordenDesdeAux(NodoBB nodo, Comparable car, int n) {
        String cadena = "";
        if (nodo != null) {
            int tem = car.compareTo(nodo.getElemento());
            System.out.println(tem + "tem");
            if (tem == 0) {
                System.out.println("Lo encontro");
                cadena += recorre(nodo, car, n, 0);
                if (cadena.length() < n) {
                    cadena = "#" + cadena;
                }
            } else {
                if (tem < 0) {
                    concatenarPreordenDesdeAux(nodo.getIzquierdo(), car, n);
                } else {
                    concatenarPreordenDesdeAux(nodo.getDerecho(), car, n);
                }
            }
        }
        return cadena;
    }

    private String recorre(NodoBB nodo, Comparable car, int n, int puntero) {
        String cadena = "";
        if (nodo != null) {

            cadena += nodo.getElemento();
            puntero++;
            System.out.println("Puntero " + puntero);
            if (puntero < n) {
                cadena += recorre(nodo.getIzquierdo(), car, n, puntero);
                puntero++;
                cadena += recorre(nodo.getDerecho(), car, n, puntero);
            }

        }
        System.out.println(cadena);
        return cadena;
    }
}
