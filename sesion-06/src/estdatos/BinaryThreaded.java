package estdatos;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Arbol binario con hilos inorden (Threaded Binary Tree)
 */
@SuppressWarnings("unused")
public class BinaryThreaded<E> extends BinaryTreeImp<E> implements Iterable<E> {
	
	// Área de datos
	protected boolean isLeftThread; // indica si left es un hilo o un enlace 
	protected boolean isRightThread; // indica si rightes un hilo o un enlace 
	
	// arbol vacio compartido para evitar crear multiples instancias 
	private static final BinaryThreaded<?> EMPTY = new BinaryThreaded<>(); 
	
	@SuppressWarnings("unchecked")
	private static <T> BinaryThreaded<T> empty() { 
		return (BinaryThreaded<T>) EMPTY; 
	}

		
	// Constructor árbol vacío
	public BinaryThreaded() {
	    super();
	}

	// Constructor hoja con etiqueta
	public BinaryThreaded(E e) {
	    this(e, new BinaryThreaded<E>(), new BinaryThreaded<E>());
	}

	// Constructor con subárboles
	public BinaryThreaded(E e, BinaryTree<E> leftSubtree, BinaryTree<E> rightSubtree) {
	    this.label = e;
	    this.left = leftSubtree == null ? empty() : leftSubtree;
	    this.right = rightSubtree == null ? empty() : rightSubtree;

	    // Si no hay subárbol izquierdo -> hilo
	    this.isLeftThread = this.left.isEmpty();
	    // Si no hay subárbol derecho -> hilo
	    this.isRightThread = this.right.isEmpty();

	    // Si hay subárbol izquierdo, enlazar su nodo más derecho hacia this
	    if (!this.left.isEmpty()) {
	        setRightmostThread((BinaryThreaded<E>) this.left, this);
	    }

	    // Si hay subárbol derecho, enlazar su nodo más izquierdo hacia this
	    if (!this.right.isEmpty()) {
	        setLeftmostThread((BinaryThreaded<E>) this.right, this);
	    }
	}

	// Constructor copia desde un BinaryTree
	public BinaryThreaded(BinaryTree<E> bt) {
	    if (bt.isEmpty()) {
	        return;
	    } 
	    
	    this.label = bt.label();
	    this.isRightThread = false;
	    this.isLeftThread = false;
	    
	    if(bt.left() != null && !bt.left().isEmpty()) {
	    	var left = new BinaryThreaded<E>(bt.left());
	    	this.left = left;
	    	this.setRightmostThread(left, this);
	    } else {
	    	this.left = empty();
	    }
	    
	    if(bt.right() != null && !bt.right().isEmpty()) {
	    	var right = new BinaryThreaded<E>(bt.left());
	    	this.right = right;
	    	this.setLeftmostThread(right, this);
	    } else {
	    	this.right = empty();
	    }
	}
	
	
	/*
	Algoritmo seguido en el constructor copia desde un BinaryTree:
	
	1. Copiar la etiqueta.
	2. Si tiene hijo izquierdo:
		2.1. Copiarlo (llamada recursiva al constructor)
		2.2. Asignarlo al atributo left.
		2.3. Buscar el nodo más a la derecha y enhebrarlo.
	3. Si no tiene hijo izquierdo:
		3.1. Establecer el hijo como EMPTY.
	4. Si tiene hijo derecho:
		4.1. Copiarlo (llamada recurisva al constructor).
		4.2. Asignarlo al atributo right.
		4.3. Buscar el nodo más a la izquierda y enhebrarlo.
	*/

	
	@Override
	public BinaryTree<E> left() {
		return isLeftThread ? empty() : this.left;
	}
	
	@Override
	public BinaryTree<E> right() {
		return isRightThread ? empty() : this.right;
	}
	
	
	/**
	 * For a given node, finds the right-most node in subtree and threads it back to
	 * node
	 * 
	 * @param subtree for which we will find the right-most node
	 * @param node    to be threaded
	 */
	private void setRightmostThread(BinaryThreaded<E> subtree, BinaryThreaded<E> node) {
		BinaryThreaded<E> rightmost = subtree;
		while (!rightmost.isRightThread && !rightmost.right().isEmpty()) {
			rightmost = (BinaryThreaded<E>) rightmost.right;
		}
		rightmost.right = node;
		rightmost.isRightThread = true;
	}

	/**
	 * For a given node, finds the left-most node in subtree and threads it back to
	 * node
	 * 
	 * @param subtree for which we will find the left-most node
	 * @param node    to be threaded
	 */
	private void setLeftmostThread(BinaryThreaded<E> subtree, BinaryThreaded<E> node) {
		BinaryThreaded<E> leftmost = subtree;
		while (!leftmost.isLeftThread && !leftmost.left().isEmpty()) {
			leftmost = (BinaryThreaded<E>) leftmost.left;
		}
		leftmost.left = node;
		leftmost.isLeftThread = true;
	}

	/**
	 * Returns the string representation of the nodes of the tree, traversed in
	 * inorder using threads and separated by a whitespace
	 * 
	 * @return the string representation of the tree
	 */
	public String inorder() {
		StringBuilder sb = new StringBuilder();
		BinaryThreaded<E> current = slideLeft();
		while (!current.isEmpty()) {
			sb.append(current.label + " ");
			if (current.isRightThread)
				current = (BinaryThreaded<E>) current.right;
			else {
				current = (BinaryThreaded<E>) current.right;
				if (!current.isEmpty())
					current = current.slideLeft();
			}
		}
		return sb.toString();
	}

	private BinaryThreaded<E> slideLeft() {
		BinaryThreaded<E> current = this;
		while (!current.left().isEmpty() && !current.isLeftThread) {
			current = (BinaryThreaded<E>) current.left;
		}
		return current;
	}


	@Override
	public Iterator<E> iterator() {
	    return new ThreadedInorderIterator();
	}

	private class ThreadedInorderIterator implements Iterator<E> {

	    private BinaryThreaded<E> current;

	    public ThreadedInorderIterator() {
	        // Comenzamos en el nodo más a la izquierda del árbol
	        this.current = BinaryThreaded.this.slideLeft();
	    }

	    @Override
	    public boolean hasNext() {
	        return current != empty();
	    }

	    @Override
	    public E next() {
	        if (!hasNext()) {
	            throw new NoSuchElementException();
	        }

	        E result = current.label;

	        // Paso al siguiente nodo siguiendo hilos
	        if (current.isRightThread) {
	            // Avanzamos al hilo
	            current = (BinaryThreaded<E>) current.right;
	        } else {
	            // Bajamos al subárbol derecho y luego al más izquierdo
	            current = (BinaryThreaded<E>) current.right;
	            if (current != empty()) {
	                current = current.slideLeft();
	            }
	        }

	        return result;
	    }
	}

}
