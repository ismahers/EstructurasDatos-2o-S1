package estdatos;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Set;


@SuppressWarnings("unused")
public class SortedListSet<E> extends ListSet<E> {

    private Comparator<? super E> cmp;

 
    public SortedListSet() {
        super();
        cmp = null;
    }


    public SortedListSet(Comparator<? super E> cmp) {
        super();
        this.cmp = cmp;
    }


    public SortedListSet(Collection<? extends E> c) {
        this();
        addAll(c);
    }


    public SortedListSet(Collection<? extends E> c, Comparator<? super E> cmp) {
        this(cmp);
        addAll(c);
    }


    // Métodos de comparación y búsqueda

    @SuppressWarnings("unchecked")
    private int compare(E a, E b) {
        if (cmp != null)
            return cmp.compare(a, b);
        Comparable<? super E> ca = (Comparable<? super E>) a;
        return ca.compareTo(b);
    }

    @SuppressWarnings("unchecked")
    private int busquedaBinaria(Object o) {
        int ini = 0;
        int fin = data.size() - 1;
        E key = (E) o;

        while (ini <= fin) {
            int medio = (ini + fin) / 2;
            E elem = data.get(medio);
            int c = compare(elem, key);

            if (c == 0) return medio;
            if (c < 0) ini = medio + 1;
            else fin = medio - 1;
        }
        return -(ini + 1);
    }

    @Override
    public boolean contains(Object o) {
        return busquedaBinaria(o) >= 0;
    }


    // add ordenado
    @Override
    public boolean add(E e) {
        int pos = busquedaBinaria(e);

        if (pos >= 0)
            return false;  // ya estaba

        int posicionInserccion = -(pos + 1);
        data.add(posicionInserccion, e);
        return true;
    }

    
    
    // Método que verifica si los conjuntos están ordenados para ver si podemos usar el algoritmo de mezcla
    
    private boolean compatible(SortedListSet<?> other) {
        if (cmp == null && other.cmp == null) return true;
        if (cmp != null && cmp.equals(other.cmp)) return true;
        return false;
    }


    // Algoritmo de mezcla

    private boolean merge(SortedListSet<? extends E> other) {

        PeekingIterator<E> itr1 = new PeekingIterator<>(this.data);
        PeekingIterator<? extends E> itr2 = new PeekingIterator<>(other.data);

        ArrayList<E> result = new ArrayList<>(this.size() + other.size());
        boolean diferente = false;

        // BUCLE PRINCIPAL: mientras ambas colecciones tienen elementos
        while (itr1.hasNext() && itr2.hasNext()) {

            E val1 = itr1.peek();     
            E val2 = itr2.peek();     

            int comp = compare(val1, val2);

            if (comp < 0) {
                // val1 < val2 -> tratar val1 y avanzar en la colección 1
                result.add(val1);
                itr1.next();
            }
            else if (comp > 0) {
                // val2 < val1 -> tratar val2 y avanzar en la colección 2
                result.add(val2);
                itr2.next();
                diferente = true;      // hay elementos nuevos
            }
            else {
                // val1 == val2 
                result.add(val1);

                itr1.next();   // avanzar ambas
                itr2.next();
            }
        }

        // Si se acaba 1 colección, tratar el resto

        // Resto de colección 1 (c1)
        while (itr1.hasNext()) {
            result.add(itr1.next());
        }

        // Resto de colección 2 (c2)
        while (itr2.hasNext()) {
            result.add(itr2.next());
            diferente = true;
        }


        if (diferente) {
            this.data = result;
        }

        return diferente;
    }

    
    // addAll con algoritmo de mezcla
    @Override
    public boolean addAll(Collection<? extends E> c) {

        if (c == this) return false;
        if (c.isEmpty()) return false;

        // Caso eficiente: sorted + compatible
        if (c instanceof SortedListSet<?>) {
            SortedListSet<? extends E> other = (SortedListSet<? extends E>) c;
            if (compatible(other)) {
                return merge(other);
            }
        }

        // Caso general: inserta uno a uno
        boolean changed = false;
        for (E e : c)
            if (add(e)) changed = true;
        return changed;
    }

 
    // equals con algoritmo de mezcla
    @Override
    public boolean equals(Object o) {

    	if (this == o) return true;
    	if (!(o instanceof Set<?>)) return false;

    	Set<?> s = (Set<?>) o;

        if (s.size() != this.size()) return false;

        // caso eficiente: dos SortedListSet
        if (o instanceof SortedListSet<?>) {

            PeekingIterator<E> it1 = new PeekingIterator<>(this.data);
            PeekingIterator<?> it2 = new PeekingIterator<>(((SortedListSet<?>) o).data);

            while (it1.hasNext() && it2.hasNext()) {
                if (!it1.next().equals(it2.next()))
                    return false;
            }

            return !(it1.hasNext() || it2.hasNext());
        }

        // caso general
        return super.equals(o);
    }
}
