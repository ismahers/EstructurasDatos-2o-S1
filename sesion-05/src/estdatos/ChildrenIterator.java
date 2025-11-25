package estdatos;

import java.util.Iterator;

/**
 * Extensión de la interfaz Iterator<E> que se utilizará para
 * para obtener, uno a uno, los subárboles cuyas raíces son los
 * nodos hijos de la raíz de un árbol ordenado. 
 * @param <E> el tipo de los ítems
 */
public interface ChildrenIterator<E> extends Iterator<E> {
	
	/**
	 * Reemplaza el último elemento retornado por {@code next()} por
	 * el elemento especificado (opcional).
	 * @param e el elemeto de reemplazo
	 * @throws IllegalStateException si no se ha llamado a {@code next()}
	 * o se ha reemplazado, borrado, o añadido un elemento después de la
	 * última llamada a {@code next()}
	 * @throws NullPointerException si el árbol no admite etiquetas
	 * de valor {@code null}
	 */
	default void set(E e) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Añade el elemento especificado antes del elemento que será
	 * retornado por la operación {@code next()}
	 * @param e
	 */
	default void add(E e) {
		throw new UnsupportedOperationException();
	}
}
