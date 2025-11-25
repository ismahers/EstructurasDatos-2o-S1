package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.junit.Test;

import estdatos.MyArrayList;

public class TestArrayList {
	@Test
	public void testConstructors() {
		MyArrayList<Character> l1 = new MyArrayList<Character>();
		assertTrue(l1.isEmpty());

		l1 = new MyArrayList<Character>(10);
		assertTrue(l1.isEmpty());
		assertEquals(10, l1.capacity());

		Character[] chars = { 'r', 'e' };
		l1 = new MyArrayList<Character>(new CopyOnWriteArrayList<Character>(chars));

	}

	@Test
	public void testAdd() {
		MyArrayList<Character> l1 = new MyArrayList<Character>(5);
		assertTrue(l1.isEmpty());

		l1.add('a');
		l1.add('b');
		l1.add('c');
		l1.add('d');
		l1.add('e');

		assertEquals(5, l1.size());
		assertTrue(l1.contains('a'));
		assertTrue(l1.contains('b'));
		assertTrue(l1.contains('c'));
		assertTrue(l1.contains('d'));
		assertTrue(l1.contains('e'));

		l1.add('f');
		assertTrue(l1.capacity() > 5);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testGet() {
		Character[] chars = { 'a', 'b', 'c' };
		List<Character> l1 = new MyArrayList<Character>(new CopyOnWriteArrayList<Character>(chars));

		assertEquals(Character.valueOf('a'), l1.get(0));
		assertEquals(Character.valueOf('b'), l1.get(1));
		assertEquals(Character.valueOf('c'), l1.get(2));

		l1.get(3); // exception
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testSet() {
		Character[] chars = { 'a', 'b', 'c' };
		List<Character> l1 = new MyArrayList<Character>(new CopyOnWriteArrayList<Character>(chars));

		l1.set(1, 'e');
		assertEquals(Character.valueOf('e'), l1.get(1));
		
		l1.set(-1, 'f'); //exception
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void testRemove() {
		Character[] chars = { 'a', 'b', 'c' };
		List<Character> l1 = new MyArrayList<Character>(new CopyOnWriteArrayList<Character>(chars));
		
		assertEquals(Character.valueOf('b'), l1.remove(1));
		assertFalse(l1.contains('b'));
		assertEquals(2, l1.size());
		
		l1.remove(-1); //exception
	}
	
	
}
