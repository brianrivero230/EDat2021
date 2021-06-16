/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jerarquicas.TestJerarquicas.TestPropios;

import jerarquicas.dinamicas.ArbolGen;
import lineales.dinamicas.Lista;

/**
 * s
 *
 * @author brian
 */
public class TestGenerico {

    public static void main(String[] args) {
        ArbolGen arbolito = new ArbolGen();
        /*
         * arbolito.insertar('A', 2); arbolito.insertar('B', 'A');
         * arbolito.insertar('C', 'A'); arbolito.insertar('D', 'A');
         * arbolito.insertar('E', 'B'); arbolito.insertar('F', 'B');
         * arbolito.insertar('G', 'B'); arbolito.insertar('H', 'D');
         * 
         * System.out.println("Arbolito: \n" + arbolito.toString());
         * System.out.println("ansestro de G" + arbolito.ancestros('G'));
         * 
         * System.out.println("Pertenece: " + arbolito.pertenece('n'));
         * System.out.println("Ansestros de H: \n " + arbolito.ancestros('H'));
         * 
         * ArbolGen arbolito2 = arbolito.clone(); arbolito2.insertar('I', 'F');
         * System.out.println("Arbolito 2: \n" + arbolito2.toString());
         * 
         * Object padre = arbolito.padre('H'); System.out.println("Padre es: " + padre);
         * 
         * Lista list = arbolito.listarPosorden(); System.out.println("Lista Pos: " +
         * list.toString());
         * 
         * list = arbolito.listarPreorden(); System.out.println("Lista Pre: " +
         * list.toString());
         * 
         * list = arbolito.listarInorden(); System.out.println("Lista In: " +
         * list.toString());
         * 
         * list = arbolito.fronntera(); System.out.println("Frontera: \n" +
         * list.toString()); System.out.println("Verifica lista anterior: " +
         * arbolito.verificarPatron(list));
         * 
         * list.vaciar(); list.insertar('H', 1); list.insertar('D', 1);
         * list.insertar('A', 1); //list.insertar('A', 1); System.out.println("Patron: "
         * + list.toString()); System.out.println("Verificar Patron: " +
         * arbolito.verificarPatron(list));
         * 
         * Lista poroto = new Lista(); poroto = arbolito.listaQueJustificaLaAltura(); if
         * (poroto.esVacia()) { System.out.println("MAL"); } else {
         * System.out.println("listaQueJustificaLaAltura: " + poroto.toString()); }
         */

        arbolito.insertar(20, 1);
        arbolito.insertar(13, 20);
        arbolito.insertar(15, 13);
        arbolito.insertar(12, 13);
        arbolito.insertar(54, 20);
        arbolito.insertar(11, 54);
        arbolito.insertar(27, 54);
        arbolito.insertar(4, 54);
        arbolito.insertar(17, 27);
        Lista list = new Lista();
        Lista list2 = new Lista();

        list = arbolito.listarEntreNiveles(1, 2);

        System.out.println(list.toString());
        System.out.println("\n" + list2.toString());
        // System.out.println(arbolito.toString());
        // arbolito.eliminar(54);
        // System.out.println(arbolito.toString());

    }

}
