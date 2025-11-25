package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import estdatos.BagMap;

public class TestBagMap {

	@Test
	public void testConstructors() {
		Set<Character> bag = new BagMap<Character>();
		assertTrue(bag.isEmpty());
		
		bag = new BagMap<Character>((c1, c2) -> c2.compareTo(c1));
		assertTrue(bag.isEmpty());
		
		List<Character> list = Arrays.asList('a', 'b', 'c');
		bag=new BagMap<Character>(list);
		assertFalse(bag.isEmpty());
		assertEquals(3, bag.size());
		assertTrue(bag.contains('a'));
		assertTrue(bag.contains('b'));
		assertTrue(bag.contains('c'));
	}
	
	@Test
	public void testAdd() {
		BagMap<Character> bag = new BagMap<Character>();
		assertTrue(bag.add('a'));
		assertTrue(bag.add('a'));
		assertTrue(bag.add('b'));
		assertEquals(2, bag.count('a'));
		assertEquals(1, bag.count('b'));
		assertEquals(3, bag.size());
	}
	
	@Test
	public void testRemove() {
		List<Character> list = Arrays.asList('a', 'b', 'b');
		BagMap<Character> bag=new BagMap<Character>(list);

		assertEquals(3, bag.size());
		assertFalse(bag.remove('c'));

		assertTrue(bag.remove('a'));
		assertEquals(0, bag.count('a'));
		assertEquals(2, bag.size());

		assertTrue(bag.remove('b'));
		assertEquals(1, bag.count('b'));
		assertEquals(1, bag.size());

		assertTrue(bag.remove('b'));
		assertEquals(0, bag.count('b'));
		assertEquals(0, bag.size());
	}

	@Test
	public void testIterator() {
		Set<Character> bag=new BagMap<Character>((c1, c2) -> c2.compareTo(c1));
		bag.add('a');
		bag.add('b');
		bag.add('a');
		
		Iterator<Character> itr = bag.iterator();
		assertTrue(itr.hasNext());
		assertEquals(Character.valueOf('b'), itr.next());
		assertTrue(itr.hasNext());
		assertEquals(Character.valueOf('a'), itr.next());
		assertTrue(itr.hasNext());
		assertEquals(Character.valueOf('a'), itr.next());
		assertFalse(itr.hasNext());
	}
	
	@Test
	public void testRetainAll() {
		List<Character> list = Arrays.asList('a', 'b', 'b', 'c', 'd', 'd');
		BagMap<Character> bag=new BagMap<Character>(list);
		
		list = Arrays.asList('a', 'b'); 
		assertTrue(bag.retainAll(list));
		assertTrue(bag.contains('a'));
		assertEquals(1, bag.count('a'));
		assertTrue(bag.contains('b'));
		assertEquals(2, bag.count('b'));
		assertEquals(3, bag.size());
	}

	@Test
	public void testRemoveAll() {
		List<Character> list = Arrays.asList('a', 'b', 'b', 'c', 'd', 'd');
		BagMap<Character> bag=new BagMap<Character>(list);
		
		list = Arrays.asList('c', 'd'); 
		assertTrue(bag.removeAll(list));
		assertTrue(bag.contains('a'));
		assertEquals(1, bag.count('a'));
		assertTrue(bag.contains('b'));
		assertEquals(2, bag.count('b'));
		assertEquals(3, bag.size());
	}
}
