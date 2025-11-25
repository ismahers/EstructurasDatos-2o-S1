package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.CopyOnWriteArrayList;

import org.junit.Test;

import estdatos.SList;
import estdatos.IList;

public class TestLinkedList {
	@Test
	public void testConstructors() {
		IList<Character> l1 = new SList<Character>();
		assertEquals(0, l1.size()); // isEmpty

		Character[] chars = { 'r', 'e' };
		l1 = new SList<Character>(new CopyOnWriteArrayList<Character>(chars));

	}

	@Test
	public void testAdd() {
		SList<Character> l1 = new SList<Character>();
		assertEquals(0, l1.size()); // isEmpty

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
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testGet() {
		Character[] chars = { 'a', 'b', 'c' };
		IList<Character> l1 = new SList<Character>(new CopyOnWriteArrayList<Character>(chars));

		assertEquals(Character.valueOf('a'), l1.get(0));
		assertEquals(Character.valueOf('b'), l1.get(1));
		assertEquals(Character.valueOf('c'), l1.get(2));

		l1.get(3); // exception
	}

	@Test
	public void testRemove() {
		Character[] chars = { 'a', 'b', 'c' };
		IList<Character> l1 = new SList<Character>(new CopyOnWriteArrayList<Character>(chars));

		assertTrue(l1.remove('b'));
		assertFalse(l1.contains('b'));
		assertEquals(2, l1.size());

		assertFalse(l1.remove('b'));
	}

	@Test
	public void testEquals() {
		SList<Character> l1 = new SList<Character>();

		l1.add('a');
		l1.add('b');
		l1.add('c');
		l1.add('d');
		l1.add('e');
		assertEquals(5, l1.size());

		SList<Character> l2 = new SList<Character>();

		l2.add('a');
		l2.add('b');
		l2.add('c');
		l2.add('d');
		l2.add('e');
		assertEquals(5, l2.size());
		
		assertTrue(l1.equals(l2));
		l1.remove('a');
		assertFalse(l1.equals(l2));
	}
	
	@Test
	public void testToString() {
		SList<Character> l1 = new SList<Character>();

		l1.add('a');
		l1.add('b');
		l1.add('c');
		l1.add('d');
		l1.add('e');
		
		assertEquals("[ a b c d e ]", l1.toString());
	}
}
