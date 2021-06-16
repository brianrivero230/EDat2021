/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lineales.tests;

import lineales.dinamicas.Pila;

/**
 *
 * @author brian
 */
public class TestDinamicaClone {
    public static void main(String[] args) {
        Pila pila1 = new Pila();
        Pila pila2;
        
        pila1.apilar(1);
        pila1.apilar(2);
        pila1.apilar(3);
        pila1.apilar(4);
        pila1.apilar(5);
        
        System.out.println("Pila Original: " + pila1.toString());
        System.out.println("Tope: "+pila1.obtenerTope());
        pila2 = pila1.clone();
        System.out.println("Pila Clon: " + pila2.toString());
        pila1.vaciar();
        System.out.println("Pila Original vaciar 1: " + pila1.toString());
        pila2.apilar(6);
        pila2.apilar(7);
        pila2.apilar(8);
        pila2.apilar(9);
        System.out.println("Pila Clon: " + pila2.toString());
        pila1.apilar("a");
        pila1.apilar("b");
        pila1.apilar("c");
        pila1.apilar("d");
        System.out.println("Pila Original: "+pila1.toString());
        System.out.println("Pila Clone: "+pila2.toString());
        
        
    }
}
