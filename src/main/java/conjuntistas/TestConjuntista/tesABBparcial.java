package conjuntistas.TestConjuntista;

import conjuntistas.dinamicas.ArbolBB;

public class tesABBparcial {
    public static void main(String[] args) {
        ArbolBB arbolito = new ArbolBB();
        arbolito.insertar('H');
        arbolito.insertar('F');
        arbolito.insertar('R');
        arbolito.insertar('B');
        arbolito.insertar('M');
        arbolito.insertar('Y');
        arbolito.insertar('E');
        arbolito.insertar('J');
        arbolito.insertar('P');
        System.out.println("\nArbol Original: ");
        System.out.println(arbolito.toString());

        String cadena = arbolito.concatenarPreordenDesde('R', 4);
        System.out.println(cadena);

        /**
         * arbolito.eliminarMinimo(); System.out.println("Arbol sin minimo");
         * System.out.println(arbolito.toString());
         * 
         * arbolito.eliminarMinimo(); System.out.println("Arbol sin minimo");
         * System.out.println(arbolito.toString());
         * 
         * arbolito.eliminarMinimo(); System.out.println("Arbol sin minimo");
         * System.out.println(arbolito.toString());
         * 
         * arbolito.eliminarMinimo(); System.out.println("Arbol sin minimo");
         * System.out.println(arbolito.toString());
         * 
         * arbolito.eliminarMinimo(); System.out.println("Arbol sin minimo");
         * System.out.println(arbolito.toString());
         */
    }
}
