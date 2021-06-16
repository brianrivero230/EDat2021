/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jerarquicas.TestJerarquicas.TestPropios;

import jerarquicas.dinamicas.ArbolBin;

/**
 *
 * @author brian
 */
public class TestArbolBIn {
    public static void main(String[] args) {
        ArbolBin arbol = new ArbolBin();
        arbol.insertar(6, null, 'r');

        System.out.println("Arbol Original: ");
        System.out.println(arbol.toString());

        arbol.cambiarHijos(0, 2, 10);

        System.out.println("Arbol 2: ");
        System.out.println(arbol.toString());
        // ArbolBin arbolInvertido = arbol.cloneInvertido();

        // System.out.println("Arbol Invertido: ");
        // System.out.println(arbolInvertido.toString());
    }
}
