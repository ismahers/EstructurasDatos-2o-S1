package estdatos;

import java.util.Collection;

public interface IList<E> extends Iterable<E> {
	
	/**
	 * Retorna el número de elementos de la lista
	 * @return tamaño de la lista
	 */
	int size();
	
	/**
	 * Inserta el elemento en la posición de la lista (opcional).
	 * @param indice la posición especificada
	 * @param e el elemento a insertar
	 * @throws IndexOutOfBoundsException si el índice está fuera de rango.
	 * @throws UnsupportedOperationException si la operación no está soportada
	 * para esta lista
	 */
	void add(int index, E e);
	
	/**
	 * Inserta el elemento especificado al final de la lista (opcional).
	 * @param e el elemento a insertar.
	 * @return {@code true} si la lista se modifica y {@code false} en caso
	 * contrario
	 * @throws UnsupportedOperationException si la operación no está soportada
	 * para esta lista
	 */
	default boolean add(E e) {
		// Inserta el elemento al final de la lista
		add(size(), e);
		return true;
	}
	
	/**
	 * Extrae el elemento que ocupa la posición especificada y lo retorna (opcional).
	 * @param indice de la posición de extracción
	 * @return el elemento extraído de la lista
	 * @throws IndexOutOfBoundsException si el índice está fuera de rango
	 * @throws UnsupportedOperationException si la operación no está soportada
	 * para esta lista
	 */
	E get(int index);
	
	/**
	 * Borra el elemento especificado si está en la lista (opcional).
	 * @param e el elemento a borrar
	 * @return {@code true} si el elemento a borrar está en la lista
	 * y {@code false} en caso contrario
	 * @throws UnsupportedOperationException si la operación no está soportada
	 * para esta lista
	 */
	boolean remove(Object e);
	
	/**
	 * Retorna cierto si la lista contiene el elemento especificado y
	 * falso en caso contrario.
	 * @param e el elemento cuya presencia en la lista se comprueba
	 * @return {@code true} si la lista contiene el elemento especificado
	 * y {@code false} en caso contrario
	 */
	default boolean contains(Object e) {
		// Recorre todos los elementos de la lista y compara con equals()
		for (E elem : this) {
			if ((e == null && elem == null) || (e != null && e.equals(elem))) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Añade todos los elementos de la colección especificada a esta lista (opcional).
	 * @param c la colección que contiene los elementos a añadir
	 * @return {@code true} si la lista cambia como resultado de la llamada y
	 * {@code false} en caso contrario
	 * @throws UnsupportedOperationException si la operación no está soportada.
	 */
	default boolean addAll(Collection<E> c) {
		// Añade todos los elementos de la colección al final de la lista
		boolean modified = false;
		for (E elem : c) {
			add(elem);
			modified = true;
		}
		return modified;
	}
	
	/**
	 * Retorna cierto si esta colección contiene todos los elementos de la lista
	 * especificada y falso en caso contrario
	 * @param l la lista para la que se realiza la comprobación
	 * @return {@code true} si la lista contiene todos los elementos de la lista
	 * especificada y {@code false} en caso contrario
	 */
	default boolean containsAll(IList<E> l) {
		// Comprueba si la lista actual contiene todos los elementos de la lista pasada como parámetro
		for (E elem : l) {
			if (!contains(elem)) {
				return false;
			}
		}
		return true;
	}	
}