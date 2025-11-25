package estdatos;

import java.util.AbstractList;
import java.util.Collection;

public class MyArrayList<E> extends AbstractList<E> {
	protected E[] data;
	protected int size;
	
	@SuppressWarnings("unchecked")
	public MyArrayList() {
		this.data = (E[]) new Object[10];
		this.size = 0;
	}
	
	@SuppressWarnings("unchecked")
	public MyArrayList(Collection<? extends E> c) {
		this.data = (E[]) new Object[c.size()];
		this.size = c.size();
		int i = 0;
		for (E e: c) {
			data[i] = e;
			i++;
		}
	}
	
	@SuppressWarnings("unchecked")
	public MyArrayList(int initialCapacity) {
		if (initialCapacity < 0) {
			throw new IllegalArgumentException();
		}
		this.data = (E[]) new Object[initialCapacity];
		this.size = 0;
	}
	
	
	public int size() {
		return this.size;
	}
	
	public int capacity() {
		return data.length;
	}

	
	@Override
	public E get(int index) {
		if (index < 0 || index>=size()) {
			throw new IndexOutOfBoundsException();
		}
		return data[index];
	}
	
	@Override
	public E set(int index, E e) {
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}
		E aux = data[index];
		data[index] = (E)e;
		return aux;
	}
	
	
	@Override
	public void add(int index, E e) {
		if (index < 0 || index > size()) {
			throw new IndexOutOfBoundsException();
		}	
		if (size()==capacity()) {
			ensureCapacity();
		}
		System.arraycopy(data, index, data, index + 1, size() - index);
		data[index] = (E)e;
		size++;
	}
	
	
	@Override
	public E remove(int index) {
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}
		E aux = data[index];
		System.arraycopy(data, index+1, data, index, size()-index);
		size--;
		return aux;
		
	}
	
	
	@SuppressWarnings("unchecked")
	protected void ensureCapacity() {
		if (size()==capacity()) {
			E[] newData = (E[]) new Object[capacity()*2 + 1];
			System.arraycopy(data, 0, newData, 0, size());
			data=newData;
		}
	}
	
}

