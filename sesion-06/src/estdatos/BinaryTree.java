package estdatos;

public interface BinaryTree<E> {
	
	/**
	 * Retorna cierto si este árbol binario es vacío.
	 * 
	 * @return {@code true} si es el árbol vacío
	 */
	boolean isEmpty();

	/**
	 * Retorna la etiqueta de la raíz del árbol.
	 * 
	 * @return la etiqueta de la raíz
	 * @throws IllegalStateException si este árbol está vacío
	 */
	E label();

	/**
	 * Retorna el subárbol izquierdo de este árbol.
	 * 
	 * @return el subárbol izquierdo del árbol
	 * @throws IllegalStateException si este árbol está vacío
	 */
	BinaryTree<E> left();
	
	/**
	 * Retorna el subárbol derecho de este árbol.
	 * 
	 * @return el subárbol derecho del árbol
	 * @throws IllegalStateException si este árbol está vacío
	 */
	BinaryTree<E> right();
		
	/**
	 * Cambia la etiqueta de la raíz de este árbo por la
	 * especificada (operación opcional).
	 * 
	 * @param e la nueva etiqueta de la raíz
	 * @throws IllegalStateException si este árbol está vacío
	 * @throws NullPointerException si el árbol no admite etiquetas
	 * de valor {@code null}
	 */
	default void setLabel(E e) {
		throw new UnsupportedOperationException();
	}
	
	/**
	 * Cambia el subárbol izquierdo de este árbol por el
	 * especificado (operación opcional).
	 * 
	 * @param left el nuevo subárbol izquierdo
	 * @throws IllegalStateException si este árbol está vacío
	 */
	default void setLeft(BinaryTree<E> left) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Cambia el subárbol derecho de este árbol por el
	 * especificado (operación opcional).
	 * 
	 * @param left el nuevo subárbol derecho
	 * @throws IllegalStateException si este árbol está vacío
	 */
	default void setRight(BinaryTree<E> right) {
		throw new UnsupportedOperationException();
	}
		
}
