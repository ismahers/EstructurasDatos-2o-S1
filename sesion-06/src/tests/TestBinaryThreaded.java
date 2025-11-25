package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;

import org.junit.Test;

import estdatos.BinaryThreaded;
import estdatos.BinaryTree;

public class TestBinaryThreaded {

	@Test
	public void testBasicConstructors() {
		BinaryTree<Integer> bt = new BinaryThreaded<Integer>();
		assertTrue(bt.isEmpty());
		assertNull(bt.label());
		assertNull(bt.right());
		assertNull(bt.left());
		
		bt = new BinaryThreaded<Integer>(Integer.valueOf(0));
		assertEquals(Integer.valueOf(0), bt.label());
		assertTrue(bt.right().isEmpty());
		assertTrue(bt.left().isEmpty());
	}
	
	@Test 
	public void testCopyConstructors() {
		BinaryTree<Integer> bt8 = new BinaryThreaded<Integer>(25);
		BinaryTree<Integer> bt7 = new BinaryThreaded<Integer>(35);
		BinaryTree<Integer> bt6 = new BinaryThreaded<Integer>(45);
		BinaryTree<Integer> bt5 = new BinaryThreaded<Integer>(20, null, bt8);
		BinaryTree<Integer> bt4 = new BinaryThreaded<Integer>(35, null, bt7);
		BinaryTree<Integer> bt3 = new BinaryThreaded<Integer>(30, bt5, bt4);
		BinaryTree<Integer> bt2 = new BinaryThreaded<Integer>(50, bt6, null);
		BinaryTree<Integer> bt1 = new BinaryThreaded<Integer>(40, bt3, bt2);

		
		assertEquals("20 25 30 35 35 40 45 50 ", 
				((BinaryThreaded<Integer>) bt1).inorder());

	}
	
	@Test
	public void testIterator() {
		BinaryTree<Integer> bt8 = new BinaryThreaded<Integer>(25);
		BinaryTree<Integer> bt7 = new BinaryThreaded<Integer>(35);
		BinaryTree<Integer> bt6 = new BinaryThreaded<Integer>(45);
		BinaryTree<Integer> bt5 = new BinaryThreaded<Integer>(20, bt8, null);
		BinaryTree<Integer> bt4 = new BinaryThreaded<Integer>(35, null, bt7);
		BinaryTree<Integer> bt3 = new BinaryThreaded<Integer>(30, bt5, bt4);
		BinaryTree<Integer> bt2 = new BinaryThreaded<Integer>(50, bt6, null);
		BinaryTree<Integer> bt1 = new BinaryThreaded<Integer>(40, bt3, bt2);
		
		Iterator<Integer> itr = 
				((BinaryThreaded<Integer>) bt1).iterator();
		
		assertTrue(itr.hasNext());
		assertEquals(Integer.valueOf(25), itr.next());
		assertTrue(itr.hasNext());
		assertEquals(Integer.valueOf(20), itr.next());
		assertTrue(itr.hasNext());
		assertEquals(Integer.valueOf(30), itr.next());
		assertTrue(itr.hasNext());
		assertEquals(Integer.valueOf(35), itr.next());
		assertTrue(itr.hasNext());
		assertEquals(Integer.valueOf(35), itr.next());
		assertTrue(itr.hasNext());
		assertEquals(Integer.valueOf(40), itr.next());
		assertTrue(itr.hasNext());
		assertEquals(Integer.valueOf(45), itr.next());
		assertTrue(itr.hasNext());
		assertEquals(Integer.valueOf(50), itr.next());
		assertFalse(itr.hasNext());
	}

}
