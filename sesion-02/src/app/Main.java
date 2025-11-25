package app;

import estdatos.*;
import java.util.Comparator;

public class Main {

    public static void main(String[] args) {
    	
    	// Pruebas con BagNoMod
    	System.out.println("\nPruebas con BagNoMod");
        BagNoMod<Double> bolsaNoMod = new BagNoMod<>(3.14, 2.71, 1.41, 1.73);
        System.out.println("Elementos en bolsaNoMod:");
        for (Double num : bolsaNoMod) {
            System.out.println(num);
        }

        System.out.println("Tamaño: " + bolsaNoMod.size());
        
        
        // Pruebas con BagMod
        System.out.println("\nPruebas con BagMod");
        BagMod<String> bolsa1 = new BagMod<>("manzana", "pera", "uva");
        System.out.println("Elementos iniciales en bolsa1:");
        for (String fruta : bolsa1) {
            System.out.println(fruta);
        }

        bolsa1.remove("pera");
        System.out.println("Después de eliminar 'pera':");
        for (String fruta : bolsa1) {
            System.out.println(fruta);
        }
        
        bolsa1.add("naranja");
        System.out.println("Después de añadir 'naranja':");
        for (String fruta : bolsa1) {
            System.out.println(fruta);
        }

        System.out.println("Tamaño: " + bolsa1.size());
        System.out.println("¿Está vacía?: " + bolsa1.isEmpty());

        bolsa1.clear();
        System.out.println("Después de clear(): ¿vacía?: " + bolsa1.isEmpty());

        
        // Pruebas con BagSorted (orden natural)
        System.out.println("\nPruebas con BagSorted (orden natural)");
        BagSorted<Integer> bolsaOrdenada = new BagSorted<>(10);
        bolsaOrdenada.add(5);
        bolsaOrdenada.add(2);
        bolsaOrdenada.add(9);
        bolsaOrdenada.add(1);

        System.out.println("Elementos ordenados:");
        for (Integer num : bolsaOrdenada) {
            System.out.println(num);
        }

        
        // Pruebas con BagSorted (con Comparator)
        System.out.println("\nPruebas con BagSorted (con Comparator)");
        Comparator<String> porLongitud = Comparator.comparingInt(String::length);
        BagSorted<String> bolsaConComparador = new BagSorted<>(porLongitud);
        bolsaConComparador.add("cereza");
        bolsaConComparador.add("kiwi");
        bolsaConComparador.add("banana");
        bolsaConComparador.add("uva");

        System.out.println("Elementos ordenados por longitud:");
        for (String fruta : bolsaConComparador) {
            System.out.println(fruta);
        }
        

        // Pruebas con BagSorted y Person
        System.out.println("\nPruebas con BagSorted y Person");
        BagSorted<Person> personas = new BagSorted<>();
        personas.add(new Person("Ana", 30));
        personas.add(new Person("Isma", 25));
        personas.add(new Person("Dani", 40));
        personas.add(new Person("Miguel", 35));

        System.out.println("Personas ordenadas por nombre (orden natural):");
        for (Person p : personas) {
            System.out.println(p.getName() + " - edad: " + p.getAge());
        }

        
        // Pruebas con BagSorted y Person (por edad con Comparator)
        System.out.println("\nPruebas con BagSorted y Person (por edad con Comparator)");
        Comparator<Person> porEdad = Comparator.comparingInt(Person::getAge);
        BagSorted<Person> personasPorEdad = new BagSorted<>(porEdad);
        personasPorEdad.add(new Person("Ana", 30));
        personasPorEdad.add(new Person("Isma", 25));
        personasPorEdad.add(new Person("Dani", 40));
        personasPorEdad.add(new Person("Miguel", 35));

        System.out.println("Personas ordenadas por edad:");
        for (Person p : personasPorEdad) {
            System.out.println(p.getName() + " - edad: " + p.getAge());
        }
    }
}
