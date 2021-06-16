package lineales.dinamicas;

/**
 * Brian Rivero [FAI-2205] Leonel Llancaqueo [FAI-2964].
 */
public class Lista {

    private Nodo cabecera;
    private int longitud;

    // Constructores
    public Lista() {
        this.cabecera = null;
        this.longitud = 0;
    }

    public boolean insertar(Object elem, int pos) {
        /**
         * Agrega un elemento en la pos, aunmentando la longitud de la lista.
         */
        boolean exito = true;
        // Mientaras que la posición este entre 1<= pos < longitud+1
        if (pos < 1 || pos > this.longitud() + 1) {
            exito = false;
        } else {
            // Caso inicial
            if (pos == 1) {
                this.cabecera = new Nodo(elem, this.cabecera);
            } else {
                // Caso general
                Nodo aux = this.cabecera;
                int i = 1;
                // utilizo a aux como puntero
                while (i < pos - 1) {
                    aux = aux.getEnlace();
                    i++;
                }
                // Inserto el nodo en la lista
                Nodo nuevo = new Nodo(elem, aux.getEnlace());
                aux.setEnlace(nuevo);

            }
            // Incrementa la longitud.
            this.longitud++;
        }
        return exito;
    }

    public boolean eliminar(int pos) {
        /**
         * Recibe una posición y elimina ese elemento, disminuyendo la longitud de la
         * lista en 1.
         */
        boolean exito = false;
        Nodo aux = this.cabecera;

        if (pos > 0 && pos < this.longitud() + 1) {
            // Verifica si se coloca en la primera pos.
            if (pos == 1) {
                this.cabecera = this.cabecera.getEnlace();
            } else {
                // recorremos los nodos para encontar la pos
                int i = 1;
                while (i < pos - 1) {
                    aux = aux.getEnlace();
                    i++;
                }
                Nodo enlace = aux.getEnlace().getEnlace();
                aux.setEnlace(enlace);
            }
            // Disminuye la longitud.
            this.longitud--;
            exito = true;
        }
        return exito;
    }

    public Object recuperar(int pos) {
        /**
         * Retorna el elemento en la posición ingresada por parámetro.
         */
        Object elem = null;
        Nodo aux = this.cabecera;

        // Verificamos que la pos sea válida.
        if (pos > 0 && pos <= this.longitud) {
            int i = 1;
            // Utiliza a aux como puntero.
            while (i < pos) {
                aux = aux.getEnlace();
                i++;
            }
            // Asigna el elemento de aux a la variable a retonar.
            elem = aux.getElem();
        }
        return elem;
    }

    public int localizar(Object elemento) {
        /**
         * Retorna la posición del elemento buscado.
         */
        int pos = -1;

        Nodo aux = this.cabecera;
        boolean control = false;
        // recorremos los nodos comparando elementos
        int i = 1;

        while (i <= this.longitud() && !control) {
            // cuando lo encontremos cortamos y retornamos al pocicion donde se encontro
            if (aux.getElem().equals(elemento)) {
                control = true;
                pos = i;
            } else {
                aux = aux.getEnlace();
                i++;
            }
        }
        // retornamos -1 si no lo encuentra y n si lo encuentra
        return pos;
    }

    public int longitud() {
        /**
         * Retorna la longitud de la lista.
         */
        return this.longitud;
    }

    public void vaciar() {
        /**
         * Vacía la lista.
         */
        this.cabecera = null;
        this.longitud = 0;
    }

    public boolean esVacia() {
        /**
         * Verifica si la lista esta vacía.
         */
        return (this.cabecera == null);
    }

    @Override

    public Lista clone() {
        /**
         * Retorna una copia exacta de la lista original de forma recursiva.
         */

        Lista listaClon = new Lista();
        listaClon = cloneRecursivo(listaClon, this.cabecera);
        listaClon.longitud = this.longitud;

        return listaClon;
    }

    private Lista cloneRecursivo(Lista listaClone, Nodo aux) {
        // Recorre la lista hasta el final y inserta nodo a nodo.
        if (aux != null) {
            listaClone = cloneRecursivo(listaClone, aux.getEnlace());
            Nodo nuevo = new Nodo(aux.getElem(), listaClone.cabecera);
            listaClone.cabecera = nuevo;
        }
        return listaClone;
    }

    @Override
    public String toString() {

        String cadena = "Lista Vacía.";

        if (this.cabecera != null) {
            cadena = "[ ";
            Nodo aux = this.cabecera;

            while (aux != null) {
                cadena += aux.getElem().toString();
                aux = aux.getEnlace();
                if (aux != null) {
                    cadena += " - ";
                }

            }
            cadena += " ]";
        }

        return cadena;
    }

    public Lista obtenerMultiplos(int num) {
        Lista lista = new Lista();
        lista = multAux(lista, this.cabecera, num, 1);
        return lista;
    }

    private Lista multAux(Lista list, Nodo nodo, int num, int pos) {

        if (nodo != null && pos <= this.longitud()) {

            list = multAux(list, nodo.getEnlace(), num, pos + 1);
            if ((pos % num) == 0) {
                Nodo aux = new Nodo(nodo.getElem(), list.cabecera);

                list.cabecera = aux;
            }
        }

        return list;
    }

    public void eliminarApariciones(Object x) {
        Nodo nodo = this.cabecera;
        int pos = 1;
        if (nodo != null) {
            while (pos <= this.longitud() && nodo.getEnlace() != null) {
                if (pos == 1) {
                    System.out.println("Saco el primero");
                    this.cabecera = nodo.getEnlace();
                } else {
                    System.out.println("Siguiente " + nodo.getElem());
                    if (pos < this.longitud() && nodo.getEnlace().getElem().equals(x)) {
                        System.out.println("saco " + nodo.getEnlace().getElem());
                        nodo.setEnlace(nodo.getEnlace().getEnlace());
                    } else {
                        if (pos == this.longitud() && nodo.getElem().equals(x)) {
                            nodo.setEnlace(null);
                            System.out.println("saco el final");
                        }
                    }
                }
                nodo = nodo.getEnlace();
                pos++;
            }

        }
    }

    public void insertarPosPrevia(Object val1, Object val2) {
        int pos = 1;
        // revisaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaarrrrrrrrrrrrrrrrrr
        Nodo nodo = this.cabecera;
        Nodo aux1;

        while (pos <= this.longitud() && nodo != null) {

            if (pos == 1 && nodo.getElem().equals(val1)) {
                System.out.println("primero = 2");
                this.cabecera = new Nodo(val2, this.cabecera);
                Nodo nuevo = new Nodo(val2, nodo.getEnlace());
                nodo.setEnlace(nuevo);

            } else {
                aux1 = nodo.getEnlace();
                if (pos == this.longitud() - 1 && aux1.getElem().equals(val1)) {
                    System.out.println("ultimo igual a dos");
                    Nodo nuevo1 = new Nodo(val2, nodo.getEnlace());
                    nodo.setEnlace(nuevo1);

                } else {

                    if (nodo.getElem().equals(val1)) {
                        System.out.println("pos " + pos + " igual a dos");
                        Nodo nuevo2 = new Nodo(val2, nodo.getEnlace());
                        nodo.setEnlace(nuevo2);

                    }
                }
            }
            pos++;
            nodo = nodo.getEnlace();
            this.longitud++;
        }

    }
}
