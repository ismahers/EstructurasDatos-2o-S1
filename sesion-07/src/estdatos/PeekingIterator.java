package estdatos;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class PeekingIterator<E> implements PeekIterator<E> {
	private Iterator<E> itr;		// el iterador de un objeto iterable
	private E current;				// el elemento que retornará la operación next()	
	private boolean setCurrent;		// cierto si current contiene el siguiente elemento

	public PeekingIterator(final Iterable<E> obj) {
		itr = obj.iterator();
		setCurrent = itr.hasNext();
		if (setCurrent) {
			current = itr.next();
		}
	}
	
	public E peek() {
		if (!hasNext()) {
			throw new NoSuchElementException();
		}
		
		return current; 
	}
	
	/**
	 * @see java.util.Iterator#hasNext()
	 */
	@Override
	public boolean hasNext() {
		return setCurrent;
	}
	
	/**
	 * @see java.util.Iterator#next()
	 */
	@Override
	public E next() {
		if (!hasNext()) {
			throw new NoSuchElementException();
		}
		
		E temp = current;		
		setCurrent = false;
		if (itr.hasNext()) {
			current = itr.next();
			setCurrent = true;
		}
		
		return temp;
	}

}