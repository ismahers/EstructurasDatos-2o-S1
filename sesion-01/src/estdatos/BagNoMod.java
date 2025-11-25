package estdatos;

import java.nio.BufferOverflowException;
import java.util.AbstractCollection;
import java.util.Iterator;

public class BagNoMod<E> extends AbstractCollection<E> {

	private E[] data;
	private int size;

	
	@SuppressWarnings("unchecked")
	public BagNoMod(E... elements) {
		data = (E[]) new Object[elements.length];
		this.size = elements.length;
		for(int i=0; i < size; i++) {
			data[i] = elements[i];
		}
	}

	
	@Override
	public Iterator<E> iterator() {
		
		return new BagIterator();
	}

	@Override
	public int size() {
		return size;
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
			//devolver el elemento que toca
			//avanzar una posición
			if(!hasNext()) {
				throw new BufferOverflowException();
			}
			
			E element = data[index];
			index++;
			return element;
		}
		
		
	}

}
