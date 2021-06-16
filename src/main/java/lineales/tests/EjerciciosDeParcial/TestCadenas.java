/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lineales.tests.EjerciciosDeParcial;

import lineales.dinamicas.Cola;
import lineales.dinamicas.Pila;
import lineales.dinamicas.Lista;

/**
 *
 * @author brian
 */
public class TestCadenas {

    public static void main(String[] args) {
        Cola c1 = new Cola();
        Cola c2 = new Cola();
        // AB#C#DEF 
        c1.poner('A');
        c1.poner('B');
        c1.poner('#');
        c1.poner('C');
        c1.poner('#');
        c1.poner('D');
        c1.poner('E');
        c1.poner('F');
        // System.out.println("Cola c1: " + c1.toString());
        System.out.println("c2 " + c2.toString());
        c2 = generar(c1);

        System.out.println("Cola c2: " + c2.toString());
    }

    public static Cola generar(Cola c1) {
        Cola cola = new Cola();
        Cola clone = c1.clone();
        Pila pila = new Pila();
        Lista lista = new Lista();
        char aux;
        int pos = 1;

        while (!clone.esVacia()) {
            aux = (char) clone.obtenerFrente();

            if (aux != '#') {

                lista.insertar(aux, pos);
                pila.apilar(aux);
                cola.poner(aux);
                pos++;

            } else {
                while (!pila.esVacia()) {
                    cola.poner(pila.obtenerTope());
                    pila.desapilar();
                }
                while (!lista.esVacia()) {
                    cola.poner(lista.recuperar(pos - 1));
                    lista.eliminar(pos - 1);
                    pos--;
                }
                cola.poner('#');

            }

            clone.sacar();

        }
        while (!pila.esVacia()) {
            cola.poner(pila.obtenerTope());
            pila.desapilar();
        }

        while (!lista.esVacia()) {
            cola.poner(lista.recuperar(1));
            lista.eliminar(1);
        }

        return cola;
    }
}
