package jerarquicas.TestJerarquicas.TestPropios;

import jerarquicas.dinamicas.ArbolGen;

public class TestParcial2Gen {
    public static void main(String[] args) {
        ArbolGen arbolito = new ArbolGen();
        arbolito.insertar("G", "G");

        arbolito.insertar("P", "G");
        arbolito.insertar("H", "P");
        arbolito.insertar("K", "P");

        arbolito.insertar("P", "G");
        arbolito.insertar("H", "P");
        arbolito.insertar("M", "P");
        arbolito.insertar("K", "M");
        arbolito.insertar("T", "P");

        arbolito.insertar("F", "G");

        arbolito.insertar("P", "G");
        arbolito.insertar("J", "P");

        System.out.println(arbolito.toString());
        System.out.println(arbolito.esPadreDe("P", "T"));

    }
}
