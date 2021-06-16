package conjuntistas.TestConjuntista;

import conjuntistas.estaticas.ArbolHeap;

public class testHeap {
    public static void main(String[] args) {
        ArbolHeap arbol = new ArbolHeap();
        ArbolHeap arbol2 = new ArbolHeap();
        arbol.insertar(3);
        arbol.insertar(4);
        arbol.insertar(5);
        arbol.insertar(6);
        arbol.insertar(7);
        arbol.insertar(2);
        arbol.insertar(1);
        System.out.println(arbol.toString());
        arbol.eliminarCima();
        arbol.eliminarCima();
        System.out.println(arbol.toString());
        System.out.println("Arbol: " + arbol.esVacio());
        System.out.println("Arbol2: " + arbol2.esVacio());
        arbol2 = arbol.clone();
        arbol2.insertar(0);
        System.out.println("Clone: \n" + arbol2.toString());
    }
}
