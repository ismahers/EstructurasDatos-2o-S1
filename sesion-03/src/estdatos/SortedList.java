package estdatos;

import java.nio.BufferOverflowException;
import java.util.Comparator;

public class SortedList<E> extends MyArrayList<E> {
	private final Comparator<? super E> cmp;
	
	public SortedList() {
		super();
		this.cmp = null;
	}

	public SortedList(int size) {
		super(size);
	    this.cmp = null;
	}

	public SortedList(Comparator<? super E> comparator) {
	    super();
	    this.cmp = comparator;
	}


	@Override
    public boolean add(E e) {
		if (size() == data.length) { 
	           throw new BufferOverflowException();
	    }
	       
		int posicion = 0;
		int i = 0;
		for (; i < size(); i++) {
			if (myCompare((E)data[i], e) > 0) {
				posicion = i;
				break;
	       	}
		}
		
		if (i>=size()) {
			posicion =size();
		}
		
		if (size - posicion >= 0) {
            System.arraycopy(data, posicion, data, posicion + 1, size() - posicion);
        }
		
		data[posicion] = e;
		size++;
		return true;
	}
	

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private int myCompare(E e1, E e2) {
		if (cmp!=null) {
			return cmp.compare(e1, e2);
		} else {
			if (!(e1 instanceof Comparable)) {
				throw new RuntimeException();
			}
			return ((Comparable) e1).compareTo(e2);
		}
	}
}