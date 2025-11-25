package estdatos;

import java.util.Collection;

public class ListBag<E> extends ListSet<E> {

    public ListBag() {
        super();
    }

    public ListBag(int initialCapacity) {
        super(initialCapacity);
    }

    public ListBag(Collection<? extends E> c) {
        super();
        addAll(c);
    }

    @Override
    public boolean add(E e) {
        data.add(e);   
        return true;   
    }

}
