package estdatos;

import java.nio.BufferOverflowException;
import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;

public class BagMod<E> extends AbstractCollection<E> {

	protected E[] data;
	protected int size;


	public BagMod(Collection<? extends E> c) {
	    @SuppressWarnings("unchecked")
	    E[] temp = (E[]) new Object[c.size()];
	    this.data = temp;
	    this.size = c.size();

	    Iterator<? extends E> it = c.iterator();
	    int i = 0;
	    while (it.hasNext()) {
	        data[i++] = it.next();
	    }
	}
	
	@SuppressWarnings("unchecked")
	public BagMod(E... elements) {
		data = (E[]) new Object[elements.length];
		this.size = elements.length;
		for(int i=0; i < size; i++) {
			data[i] = elements[i];
		}
	}
	
	@SuppressWarnings("unchecked")
	public BagMod(int size) {
		this.data = (E[]) new Object [size];
		this.size = 0;
	}
	
	@Override 
	public boolean add(E element) { 
		if (size == data.length) { 
			throw new BufferOverflowException(); 
		} 
		data[size++] = element;
		return true; 
	}

    @Override
    public boolean remove(Object element) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(element)) {
                data[i] = data[size - 1];  
                data[size - 1] = null;      
                size--;  
                return true;
            }
        }
        return false;
    }

	
	@Override
	public Iterator<E> iterator() {	
		return new BagIterator();
	}

	@Override
	public int size() {
		return size;
	}
	
	@Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            data[i] = null;  
        }
        size = 0;
    }
	
	@SuppressWarnings("unused")
	private class BagIterator implements Iterator<E> {
		private int index = 0;

		@Override
		public boolean hasNext() {
			return index < size;
		}

		@Override
		public E next() {
			if(!hasNext()) {
				throw new BufferOverflowException();
			}
			
			E element = data[index];
			index++;
			return element;
		}
		
		@Override
        public void remove() {

            for (int i = index - 1; i < size - 1; i++) {
                data[i] = data[i + 1];
            }
            data[size - 1] = null; 
            size--; 
            index--; 
        }
	}

}
