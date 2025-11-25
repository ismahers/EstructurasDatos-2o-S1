package app;

import estdatos.SList;
import estdatos.IList;
import java.util.Arrays;
import java.util.Iterator;


public class Main {

    public static void main(String[] args) {

        // Creo una lista vacía
        IList<String> lista1 = new SList<>();
        System.out.println("Lista1 creada: " + lista1);

        // Añado elementos individuales
        lista1.add("A");
        lista1.add("B");
        lista1.add("C");
        System.out.println("Después de añadir A, B, C: " + lista1);

        // Inserto en una posición específica
        lista1.add(1, "X");
        System.out.println("Después de insertar X en la posición 1: " + lista1);

        // Obtengo un elemento por índice
        System.out.println("Elemento en la posición 2: " + lista1.get(2));

        // Compruebo si contiene elementos
        System.out.println("¿Contiene 'B'? " + lista1.contains("B"));
        System.out.println("¿Contiene 'Z'? " + lista1.contains("Z"));

        // Elimino un elemento
        lista1.remove("B");
        System.out.println("Después de eliminar B: " + lista1);

        // Añado varios elementos de una colección
        lista1.addAll(Arrays.asList("D", "E", "F"));
        System.out.println("Después de añadir [D, E, F]: " + lista1);

        // Creo otra lista y compruebo containsAll
        IList<String> lista2 = new SList<>();
        lista2.add("A");
        lista2.add("C");
        lista2.add("D");

        System.out.println("Lista2: " + lista2);
        System.out.println("¿Lista1 contiene todos los elementos de Lista2? " + lista1.containsAll(lista2));

        // Compruebo equals
        IList<String> lista3 = new SList<>(lista1);
        System.out.println("Lista3 (copia de Lista1): " + lista3);
        System.out.println("¿Lista1 y Lista3 son iguales? " + lista1.equals(lista3));

        // Pruebo el iterador simple
        System.out.print("Recorriendo lista1 con iterador: ");
        for (String s : lista1) {
            System.out.print(s + " ");
        }
        System.out.println();

        // Pruebo el iterador con remove()
        System.out.println("\nUsando el iterador para eliminar los elementos que empiezan por 'D' o 'E'...");
        Iterator<String> it = lista1.iterator();
        while (it.hasNext()) {
            String val = it.next();
            if (val.equals("D") || val.equals("E")) {
                it.remove();
            }
        }
        System.out.println("Lista1 tras remove() en iterador: " + lista1);

        // Comprobación final
        System.out.println("\nTamaño final de lista1: " + lista1.size());
        System.out.println("Contenido final: " + lista1);
    }
}