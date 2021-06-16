package conjuntistas.TestConjuntista;

import conjuntistas.dinamicas.ArbolBB;
import lineales.dinamicas.Lista;

public class testBinario {
    // static String sOk = "OK!", sErr = "ERROR";
    static String sOk = "\u001B[32m OK! \u001B[0m";
    static String sErr = " \u001B[31m ERROR \u001B[0m";

    public static void main(String[] args) {
        ArbolBB arbolito = new ArbolBB();
        arbolito.insertar(45);
        arbolito.insertar(34);
        arbolito.insertar(65);
        arbolito.insertar(13);
        arbolito.insertar(55);
        arbolito.insertar(47);
        arbolito.insertar(73);
        arbolito.insertar(96);
        System.out.println("****************************************");
        System.out.println("*      Pruebas sobre esVacio()         *");
        System.out.println("****************************************");

        System.out.println("Arbol 1, prueba esVacio():");
        System.out.println("Tiene que dar ERROR --> " + ((arbolito.esVacio() == true) ? sOk : sErr));

        ArbolBB arbolito2 = new ArbolBB();
        System.out.println("Arbol 2, prueba esVacio():");
        System.out.println("Tiene que dar OK! --> " + ((arbolito2.esVacio() == true) ? sOk : sErr));

        arbolito2.insertar(1);
        System.out.println("****************************************");
        System.out.println("*       Pruebas sobre listar()         *");
        System.out.println("*        y sobre listarRango()         *");
        System.out.println("****************************************");

        Lista lista = new Lista();
        Lista lista2 = new Lista();

        System.out.println("Listar Arbol 1:");
        lista = arbolito.listar();
        System.out.println(lista.toString());

        System.out.println("Listar Arbol 2:");
        lista2 = arbolito2.listar();
        System.out.println(lista2.toString());

        System.out.println("ListarRango Arbol 1: 65 - 96");
        lista = arbolito.listarRango(65, 96);
        System.out.println(lista.toString());

        System.out.println("ListarRango Arbol 1: 12 - 40");
        lista = arbolito.listarRango(12, 40);
        System.out.println(lista.toString());

        System.out.println("ListarRango Arbol 1: 1 - 10");
        lista = arbolito.listarRango(1, 10);
        System.out.println(lista.toString());

        System.out.println("\n Arbol 1: \n" + arbolito.toString());
        System.out.println("****************************************");
        System.out.println("*      Pruebas sobre eliminar()        *");
        System.out.println("****************************************");
        System.out.println("Primer Caso: Nodo hoja 96");
        System.out.println("Tiene que dar Ok! --> " + ((arbolito.eliminar(96) == true) ? sOk : sErr));
        System.out.println("Caso Especial: elemento a elminar un arbol solo con raiz");
        System.out.println("Tiene que dar Ok! --> " + ((arbolito2.eliminar(1) == true) ? sOk : sErr));
        System.out.println("Caso 2: el nodo tiene un solo hijo, elimina 13");
        System.out.println("Tiene que dar Ok! --> " + ((arbolito.eliminar(13) == true) ? sOk : sErr));
        System.out.println("Caso 3: El nodo tiene 2 hijos eliminar 65");
        System.out.println("Tiene que dar Ok! --> " + ((arbolito.eliminar(73) == true) ? sOk : sErr));
        System.out.println("\n Arbol 1: \n" + arbolito.toString());

        System.out.println("****************************************");
        System.out.println("*      Pruebas sobre pertenece()       *");
        System.out.println("****************************************");
        System.out.println("Elemento que no pertenece al arbol: 0");
        System.out.println("Tiene que dar" + sErr + " --> " + ((arbolito.pertenece(0) == true) ? sOk : sErr));
        System.out.println("Elemento que pertenece al arbol: 47");
        System.out.println("Tiene que dar " + sOk + " --> " + ((arbolito.pertenece(47) == true) ? sOk : sErr));
        System.out.println("Elemento que no pertenece al arbol: 96");
        System.out.println("Tiene que dar" + sErr + " --> " + ((arbolito.pertenece(96) == true) ? sOk : sErr));
        System.out.println("Elemento que pertenece al arbol: 65");
        System.out.println("Tiene que dar " + sOk + " --> " + ((arbolito.pertenece(65) == true) ? sOk : sErr));

    }
}
