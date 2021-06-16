/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lineales.tests.EjerciciosDeParcial;

import lineales.dinamicas.Cola;
import lineales.dinamicas.Lista;
import lineales.dinamicas.Pila;

public class TestLineales {

    public static Lista formarLista(Pila pila1) {
        Lista lista = new Lista();
        Pila auxP = pila1.clone();
        Pila pila = new Pila();
        Cola cola = new Cola();
        Object aux = null;
        int cont = 0;
        while (!auxP.esVacia()) {
            aux = auxP.obtenerTope();
            pila.apilar(aux);
            auxP.desapilar();
        }
        while (!pila.esVacia()) {
            aux = pila.obtenerTope();

            if (aux.equals("@") && (cont % 2) != 0) {
                cola.poner(aux);
                pila.desapilar();

            } else {
                if (aux.equals("@")) {
                    aux = cola.obtenerFrente();
                    cola.sacar();
                    lista.insertar(aux, 1);
                    cont++;
                } else {
                    lista.insertar(aux, 1);
                    pila.desapilar();
                }
            }

        }
        return lista;

    }

    public static void main(String[] args) {
        Pila p1 = new Pila();

        p1.apilar("X");
        p1.apilar("Y");
        p1.apilar("W");
        p1.apilar("@");
        p1.apilar("R");
        p1.apilar("Z");
        p1.apilar("@");

        p1.apilar("Y");
        p1.apilar("T");
        p1.apilar("@");
        p1.apilar("Z");

        p1.apilar("T");
        p1.apilar("R");

        Lista lis = new Lista();
        lis = formarLista(p1);

        System.out.println("Lista: " + lis.toString());
        System.out.println("Pila: " + p1.toString());
    }
}
