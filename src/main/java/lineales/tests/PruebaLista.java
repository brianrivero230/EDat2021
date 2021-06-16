/*concatenar:recibe dos listas L1 y L2 y devuelve una lista nueva con los 
elementos de L1 y L2 concatenados. Ej: si L1=[2,4,6] y L2=[5,1,6,7] debe 
devolver [2,4,6,5,1,6,7]
 */
package lineales.tests;

import lineales.dinamicas.Lista;

public class PruebaLista {
    public static void main(String[] args) {
        Lista lista1 = new Lista();
        Lista lista2;
        for (int i = 1; i <= 3; i++) {
            lista1.insertar(i, i);
        }
        lista1.insertar(2, 1);
        lista1.insertar(1, 5);
        lista1.insertar(2, 6);
        System.out.println("Lista Original: " + lista1.toString());
        lista1.insertarPosPrevia(2, '#');
        System.out.println("Lista 1: " + lista1.toString());
        lista2 = lista1.clone();
        lista1.eliminarApariciones(2);

        System.out.println("Lista 2: " + lista2.toString());
        System.out.println("Lista 1: " + lista1.toString());
        lista2.insertar(5, 1);
        lista2.insertar(1, 2);
        lista2.insertar(6, 3);
        lista2.insertar(7, 4);
        System.out.println("Lista 2: " + lista2.toString());

    }

    public static Lista concatenar(Lista l1, Lista l2) {
        Lista res = new Lista();
        Object aux;
        int i = 1;
        if (l1.esVacia()) {
            res = l2.clone();
        } else {
            // Vemos que onda despues

        }
        while (i <= l1.longitud()) {
            aux = l1.recuperar(i);
            res.insertar(aux, i);
            i++;
        }

        int j = 1;

        while (j <= l2.longitud()) {
            aux = l2.recuperar(j);
            res.insertar(aux, i);
            i++;
            j++;
        }
        System.out.println("Pila Concatenada: " + res.toString());
        return res;
    }
}
