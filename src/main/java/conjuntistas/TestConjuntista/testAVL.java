package conjuntistas.TestConjuntista;

import conjuntistas.dinamicas.ArbolAVL;

public class testAVL {
    public static void main(String[] args) {
        ArbolAVL arbolito1 = new ArbolAVL();
        System.out.println("\nInserto 75");
        arbolito1.insertar(75);
        System.out.println("Arbol\n" + arbolito1.toString());

        System.out.println("\nInserto 20");
        arbolito1.insertar(20);
        System.out.println("Arbol\n" + arbolito1.toString());

        System.out.println("\nInserto 15");
        arbolito1.insertar(15);
        System.out.println("Arbol\n" + arbolito1.toString());

        System.out.println("\nInserto 80");
        arbolito1.insertar(80);
        System.out.println("Arbol\n" + arbolito1.toString());

        System.out.println("\nInserto 93");
        arbolito1.insertar(93);
        System.out.println("Arbol\n" + arbolito1.toString());

        // arbolito1.insertar(10);
        // System.out.println("\nArbol\n" + arbolito1.toString());
    }

}
