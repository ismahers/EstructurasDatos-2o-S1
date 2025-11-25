package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;

import org.junit.Test;

import estdatos.ChildrenIterator;
import estdatos.ListTree;
import estdatos.Tree;

@SuppressWarnings("unused")
public class TestListTree {

	@Test
	public void testConstructors() {
		Tree<Character> t1=new ListTree<>('B');
		assertTrue(t1.isLeaf());
		assertEquals(Character.valueOf('B'), t1.label());
		
		Tree<Character> t2=new ListTree<>('C');
		Tree<Character> t3=new ListTree<>('D');
		Tree<Character> t4=new ListTree<>('E');
		
		Tree<Character> t0=new ListTree<>('A', t1,t2,t3,t4);
		assertFalse(t0.isLeaf());		
	}
	
	@Test
	public void testChildrenIterator() {
		Tree<Character> t1=new ListTree<>('B');
		Tree<Character> t2=new ListTree<>('C');
		Tree<Character> t3=new ListTree<>('D');
		Tree<Character> t4=new ListTree<>('E');
		Tree<Character> t0=new ListTree<>('A', t1,t2,t3,t4);
		
		ChildrenIterator<Tree<Character>> itr0 = t0.childrenIterator();
		
		// The iterator only iterates the children, no need to check the root
		assertTrue(itr0.hasNext());
		assertEquals(Character.valueOf('B'), itr0.next().label());
		assertTrue(itr0.hasNext());
		assertEquals(Character.valueOf('C'), itr0.next().label());
		assertTrue(itr0.hasNext());
		assertEquals(Character.valueOf('D'), itr0.next().label());
		assertTrue(itr0.hasNext());
		assertEquals(Character.valueOf('E'), itr0.next().label());
		assertFalse(itr0.hasNext());
		
		itr0 = t0.childrenIterator(); //new iterator to check the remove
		assertTrue(itr0.hasNext());
		assertEquals(Character.valueOf('B'), itr0.next().label());
		itr0.remove();
		itr0 = t0.childrenIterator();//new iterator to check the remove succeeded
		assertTrue(itr0.hasNext());
		assertEquals(Character.valueOf('C'), itr0.next().label());

		itr0.set(new ListTree<Character>('Z'));
		itr0 = t0.childrenIterator(); //new iterator to check if the set succeeded
		assertTrue(itr0.hasNext());
		assertEquals(Character.valueOf('Z'), itr0.next().label());

		itr0.add(new ListTree<Character>('X'));
		itr0 = t0.childrenIterator(); //new iterator to check if the add succeeded
		assertTrue(itr0.hasNext());
		assertEquals(Character.valueOf('Z'), itr0.next().label());
		assertTrue(itr0.hasNext());
		assertEquals(Character.valueOf('X'), itr0.next().label());
	}
	
	@Test
	public void testEquals() {
		Tree<Character> t1=new ListTree<>('B');
		Tree<Character> t2=new ListTree<>('C');
		Tree<Character> t3=new ListTree<>('D');
		Tree<Character> t4=new ListTree<>('E');

		Tree<Character> t0=new ListTree<>('A', t1,t2,t3,t4);
		Tree<Character> t5=new ListTree<>('A', t1,t2,t3,t4);
		Tree<Character> t6=new ListTree<>('A', t1,t2,t3);
		
		assertTrue(t0.equals(t5));
		assertFalse(t0.equals(t6));
	}
}
