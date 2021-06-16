package lineales.tests.TestingClase;


import lineales.estaticas.Cola;

public class TestCola {
    public static void main(String[] args) {
        //Tests Cola o algo parecido
        
       Cola cola1 = new Cola();
       Cola cola2 = new Cola();
       
        for (int i = 0; i < 10; i++) {
            cola1.poner(i);
        }
        
        System.out.println("Cola 1: "+cola1.toString());
        System.out.println("Frente Cola1: "+ cola1.obtenerFrente());
        for (int i = 0; i < 5; i++) {
            cola1.sacar();
        }
        
        System.out.println("Cola 1 sin 5 elementos: "+ cola1.toString());
        
        System.out.println("Agregando 7 elementos...");
        for (int i = 0; i < 7; i++) {
            cola1.poner(i);
        }
        
        System.out.println("Cola 1: "+cola1.toString());
        
        System.out.println("Frente Cola1: "+ cola1.obtenerFrente());
        
        System.out.println("Clonando cola... ");
        cola2 = cola1.clone();
        
        System.out.println("Cola 2: "+cola2.toString());
        
        System.out.println("Quitaando 10 elementos de cola1... ");
        for (int i = 0; i < 10; i++) {
            cola1.sacar();
        }
        System.out.println("Frente Cola1: "+ cola1.obtenerFrente());
        
        System.out.println("Cola 1: "+cola1.toString());
        System.out.println("Cola 2: "+cola2.toString());
        
        System.out.println("Vaciando cola 2...");
        cola2.vaciar();
        System.out.println("Frente Cola2: "+ cola2.obtenerFrente());
        System.out.println("cola 2 vacia: "+cola2.esVacia());
        System.out.println("Cola 1 vacía: "+ cola1.esVacia());
    }
}
