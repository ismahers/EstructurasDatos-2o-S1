package estdatos;

import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class ListSet<E> extends AbstractSet<E> {

    protected List<E> data;

    public ListSet() {
        data = new ArrayList<>();
    }

    public ListSet(int capacidadInicial) {
        data = new ArrayList<>(capacidadInicial);
    }

    public ListSet(Collection<? extends E> c) {
        this();
        addAll(c);     
    }

    @Override
    public int size() {
        return data.size();
    }

    @Override
    public boolean contains(Object o) {
        return data.contains(o);
    }

    @Override
    public Iterator<E> iterator() {
        return data.iterator();
    }

    @Override
    public boolean add(E e) {
        if (data.contains(e))
            return false;      // no permite repetidos
        data.add(e);
        return true;         
    }

    @Override
    public boolean remove(Object o) {
        return data.remove(o); // elimina si existe
    }

    @Override
    public void clear() {
        data.clear();
    }
}
