package estdatos;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;


public class SList<E> implements IList<E> {
	
	// Nodo del primer elemento de la lista 
	private Node<E> first;
	
	// Nodo del último elemento de la lista 
	private Node<E> last;
	
	// Número de elementos de la lista 
	private int size;
	

	// Clase interna estática para los nodos
	private static class Node<E> {
		E item;
		Node<E> next;
		
		Node(E e, Node<E> theNext) {
			this.item = e;
			this.next = theNext;
		}
	}

	
	// Constructor por defecto: crea una lista vacía
	public SList() {
		this.first = null;
		this.last = null;
		this.size = 0;
	}
	
	public SList(Collection<? extends E> c) {
		this();
		for (E e: c) {
			add(size, e);
		}
	}
	
	
	// Constructor de conversión: crea una lista a partir de otra IList<E>
	public SList(IList<E> l) {
		this();
		for (E elem : l) {
			add(elem);
		}
	}

	
	// Retorna el número de elementos de la lista
	@Override
	public int size() {
		return size;
	}
	

	// Inserta el elemento en la posición indicada
	@Override
	public void add(int index, E e) {
		if (index < 0 || index > size)
			throw new IndexOutOfBoundsException("Índice fuera de rango");
		
		Node<E> newNode = new Node<>(e, null);
		
		if (index == 0) { // inserción al principio
			newNode.next = first;
			first = newNode;
			if (size == 0)
				last = newNode;
		} 
		else if (index == size) { // inserción al final
			last.next = newNode;
			last = newNode;
		} 
		else { // inserción intermedia
			Node<E> prev = getNode(index - 1);
			newNode.next = prev.next;
			prev.next = newNode;
		}
		
		size++;
	}
	
	// Devuelve el elemento en la posición indicada
	@Override
	public E get(int index) {
		if (index < 0 || index >= size)
			throw new IndexOutOfBoundsException("Índice fuera de rango");
		return getNode(index).item;
	}
	
	
	// Borra la primera aparición del elemento especificado (si existe)
	@Override
	public boolean remove(Object e) {
		if (first == null) return false;
		
		// Caso especial: primer elemento
		if ((e == null && first.item == null) || (e != null && e.equals(first.item))) {
			first = first.next;
			if (first == null) last = null; // si la lista quedó vacía
			size--;
			return true;
		}
		
		Node<E> prev = first;
		Node<E> curr = first.next;
		
		while (curr != null) {
			if ((e == null && curr.item == null) || (e != null && e.equals(curr.item))) {
				prev.next = curr.next;
				if (curr == last) last = prev;
				size--;
				return true;
			}
			prev = curr;
			curr = curr.next;
		}
		return false;
	}
	

	// Devuelve un iterador sobre los elementos de la lista
	@Override
	public Iterator<E> iterator() {
		return new SListIterator();
	}
	

	private class SListIterator implements Iterator<E> {
		private Node<E> current = first;      // nodo en curso
		private Node<E> lastReturned = null;  // último nodo devuelto por next()

		
		@Override
		public boolean hasNext() {
			return current != null;
		}
		
		@Override
		public E next() {
			if (!hasNext()) throw new NoSuchElementException();
			lastReturned = current;
			current = current.next;
			return lastReturned.item;
		}
		
		@Override
		public void remove() {
			if (lastReturned == null) {
				throw new IllegalStateException("Debe llamarse a next() antes de remove()");
			}
			
			// Eliminar el primero
			if (lastReturned == first) {
				first = first.next;
				if (first == null) last = null;
			} 
			else {
				// Encontrar el nodo anterior real
				Node<E> pred = first;
				while (pred != null && pred.next != lastReturned) {
					pred = pred.next;
				}
				if (pred != null) {
					pred.next = lastReturned.next;
					if (lastReturned == last)
						last = pred;
				}
			}
			
			size--;
			lastReturned = null; // solo se puede eliminar una vez por next()
		}
	}
	

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("[ ");
		Node<E> aux = first;
		while (aux != null) {
			sb.append(aux.item);
			if (aux.next != null) sb.append(" ");
			aux = aux.next;
		}
		sb.append(" ]");
		return sb.toString();
	}
	
	
	// Compara dos listas por su contenido (elemento a elemento).
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof SList<?> other)) return false;
		if (this.size != other.size) return false;
		
		Node<E> a = this.first;
		Node<?> b = other.first;
		while (a != null) {
			if (a.item == null ? b.item != null : !a.item.equals(b.item)) return false;
			a = a.next;
			b = b.next;
		}
		return true;
	}
	
	
	// Clase interna para los nodos
	private Node<E> getNode(int index) {
		Node<E> current = first;
		for (int i = 0; i < index; i++) {
			current = current.next;
		}
		return current;
	}

	
}