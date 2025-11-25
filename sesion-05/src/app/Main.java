package app;

import estdatos.*;
import java.util.function.Predicate;

public class Main {

    // Función recursiva genérica que cuenta etiquetas según predicado
    public static <E> int contarSi(Tree<E> t, Predicate<? super E> condicion) {
        if (t == null)
            return 0;
        int contador = 0;

        if (condicion.test(t.label()))
            contador++;

        ChildrenIterator<Tree<E>> it = t.childrenIterator();
        while (it.hasNext()) {
            contador += contarSi(it.next(), condicion);
        }
        return contador;
    }

    // Ejemplo de uso
    public static void main(String[] args) {
        Tree<Integer> t1 = new ListTree<>(1,
                new ListTree<>(2),
                new ListTree<>(4,
                        new ListTree<>(3),
                        new ListTree<>(5)));

        int pares = contarSi(t1, e -> e % 2 == 0);
        System.out.println("Nodos con etiqueta par: " + pares);
    }
}