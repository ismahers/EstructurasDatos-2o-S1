package estdatos;

import java.util.AbstractSet;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeMap;
import java.util.List;
import java.util.ArrayList;

public class BagMap<E> extends AbstractSet<E> {

    // Área de datos
	
    private TreeMap<E, Integer> data;   // elemento, nº repeticiones
    private int size;                   // nº total de elementos contando repeticiones


    // Constructores

    public BagMap() {
        data = new TreeMap<>();
        size = 0;
    }

    public BagMap(Comparator<? super E> cmp) {
        data = new TreeMap<>(cmp);
        size = 0;
    }

    public BagMap(Collection<? extends E> c) {
        this();
        this.addAll(c);
    }


    // Métodos auxiliares del enunciado

    /** Número de ocurrencias de un elemento */
    public int count(E e) {
        Integer count = data.get(e);
        return (count == null) ? 0 : count;
    }

    /** Devuelve una lista con los elementos únicos (ordenados) */
    public List<E> uniqueElements() {
        return new ArrayList<>(data.keySet());
    }

    @Override
    public String toString() {
        return data.toString() + " (size=" + size + ")";
    }


    // Operaciones heredadas de AbstractSet

    @Override
    public boolean add(E e) {
        Integer cont = data.get(e);
        if (!data.containsKey(e)) {
            data.put(e, 1);
        } else {
            data.put(e, cont + 1);
        }
        size++;
        return true;   
    }

    @Override
    public boolean remove(Object o) {
        @SuppressWarnings("unchecked")
        E e = (E) o;

        Integer cont = data.get(e);
        if (!data.containsKey(e)) return false;

        if (cont == 1) {
            data.remove(e);
        } else {
            data.put(e, cont - 1);
        }
        size--;
        return true;
    }

    @Override
    public int size() {
        return size;
    }


    @Override
    public Iterator<E> iterator() {
        return new BagMapIterator();
    }

    private class BagMapIterator implements Iterator<E> {

        private Iterator<E> keyIterator;  // recorre las claves del TreeMap
        private E currentElement;         // elemento actual
        private int rest;            // repeticiones pendientes del elemento actual

        public BagMapIterator() {
            keyIterator = data.keySet().iterator();
            currentElement = null;
            rest = 0;
        }

        @Override
        public boolean hasNext() {
            return rest > 0 || keyIterator.hasNext();
        }

        @Override
        public E next() {
            // Si no quedan repeticiones del actual -> pasamos al siguiente elemento del mapa
            if (rest == 0) {
                currentElement = keyIterator.next();   // siguiente clave ordenada
                rest = data.get(currentElement);  // cargar nº repeticiones que quedan
            }

            rest--; // actualizar nº repeticiones que quedan
            return currentElement;
        }
    }
    
    
    @Override
    public boolean retainAll(Collection<?> c) {
        boolean modified = false;

        TreeMap<E, Integer> nuevo = new TreeMap<>(data.comparator());
        int newSize = 0;

        for (Object o : c) {
            @SuppressWarnings("unchecked")
            E e = (E) o;

            Integer cont = data.get(e);
            if (cont != null) {
                // se copia elemento y sus repeticiones
                nuevo.put(e, cont);
                newSize += cont;
            }
        }

        // Si cambia algo, hay que actualizarlo
        if (newSize != size) {
            this.data = nuevo;
            this.size = newSize;
            modified = true;
        }

        return modified;
    }
    
    
    @Override
    public boolean removeAll(Collection<?> c) {
        boolean modified = false;

        for (Object o : c) {
            @SuppressWarnings("unchecked")
            E e = (E) o;

            Integer cont = data.get(e);
            if (cont != null) {
                size -= cont;     // se restan TODAS sus repeticiones
                data.remove(e);
                modified = true;
            }
        }

        return modified;
    }


}
