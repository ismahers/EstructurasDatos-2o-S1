package estdatos;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class ListTree<E> extends AbstractTree<E> {

	
	// ÁREA DE DATOS
    private E labelRoot;                     
    private List<Tree<E>> children;          
    private Tree<E> parent;                  
    
    
    // CONSTRUCTORES
	@SafeVarargs
	public ListTree(E e, Tree<E>... trees) {
        if (e == null)
            throw new NullPointerException("La etiqueta de la raíz no puede ser null");

        this.setLabel(e);
        this.children = new LinkedList<Tree<E>>();
        this.parent = null;

        for (Tree<E> tree : trees) {
        	ListTree<E> listTree = new ListTree<E>(tree);
        	listTree.parent = this;
        	this.children.add(listTree);
        }
    }


    public ListTree(Tree<E> t) {
        if (t == null)
            throw new NullPointerException();
        
        this.setLabel(t.label());
        this.children = new LinkedList<Tree<E>>();
        this.parent = null;

        Iterator<Tree<E>> itr = t.childrenIterator();
        while (itr.hasNext()) {
        	ListTree<E> listTree = new ListTree<E>(itr.next());
        	listTree.parent = this;
        	this.children.add(itr.next());
        }
    }
    
    
    public Tree<E> parent() {
        return parent;
    }
   
    
    @Override
    public boolean isLeaf() {
        return children.isEmpty();
    }

    @Override
    public E label() {
        return labelRoot;
    }

    @Override
    public void setLabel(E e) {
        if (e == null)
            throw new NullPointerException();
        this.labelRoot = e;
    }


	@Override
	public ChildrenIterator<Tree<E>> childrenIterator() {
		return new ListChildrenIterator();
	}
    
	
	@Override
	public boolean equals(Object obj) {
		if(obj == this) {
			return true;
		}
		
		if(obj == null || !(obj instanceof ListTree)) {
			return false;
		}
		@SuppressWarnings("unchecked")
		ListTree<E> tree = (ListTree<E>) obj;
		
		if(!this.label().equals(tree.label())) {
			return false;
		}
		
		var itr1 = this.childrenIterator();
		var itr2 = tree.childrenIterator();
		while(itr1.hasNext() && itr2.hasNext()) {
			if(!itr1.next().equals(itr2.next())) {
				return false;
			}
		}
		
		return itr1.hasNext() == itr2.hasNext();
	}
	
	
	private class ListChildrenIterator implements ChildrenIterator<Tree<E>> {
		
		private ListIterator<Tree<E>> itr;
		
		public ListChildrenIterator() {
			itr = children.listIterator();
		}
		
		@Override
		public boolean hasNext() {
			return itr.hasNext();
		}

		@Override
		public Tree<E> next() {
			return itr.next();
		}
		
		@Override
		public void remove() {
			itr.remove();
		}
		
		@Override
		public void set(Tree<E> e) {
			ListTree<E> listTree = new ListTree<E>(e);
			listTree.parent = ListTree.this;
			itr.set(listTree);
		}
		
		@Override
		public void add(Tree<E> e) {
			ListTree<E> listTree = new ListTree<E>(e);
			listTree.parent = ListTree.this;
			itr.add(listTree);
		}
		
	}
}