package lineales.tests;


import lineales.estaticas.Cola;

public class TestColaEstatica {

    public static void main(String[] args) {
        Cola cola1 = new Cola();
        Cola cola2 = new Cola();
        int op;
        char op2 ;
        Object elemento;
        do{
            System.out.println("|---Métodos de Cola Estática---");
            System.out.println("| 1- poner()      2- sacar()  |");
            System.out.println("| 3- esVacia()    4- vaciar() |");
            System.out.println("| 5- toString()   6- clone()  |");
            System.out.println("| 0- Terminar la ejecución    |");
            System.out.println("|-----------------------------|");
            op = TecladoIn.readLineInt();
            switch(op){
                case 1: 
                    System.out.println("Introducir elemento: ");
                    elemento = TecladoIn.readLineWord();
                    System.out.println("Apiló: " + cola1.poner(elemento));
                    break;
                case 2:
                    System.out.println("Sacó: "+cola1.sacar());
                    break;
                case 3:
                    System.out.println("Es Vacía: "+cola1.esVacia());
                    break;
                case 4:
                    System.out.println("Vaciado.");
                    cola1.vaciar();
                    break;
                case 5:
                    System.out.println("Cola: "+cola1.toString());
                    break;
                case 6:
                    cola2 = cola1.clone();
                    System.out.println("Cola 1: "+cola1.toString());
                    System.out.println("Cola 2: "+cola2.toString());
                    break;
                default: 
                    System.out.println("Opción invalida.");       
            }
            System.out.println("Desea Continuar? Y/N");
            op2 = TecladoIn.readLineNonwhiteChar();
        }while(op2 == 'y');
       
    }
}
