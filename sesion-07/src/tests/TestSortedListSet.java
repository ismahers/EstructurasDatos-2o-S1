package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import estdatos.SortedListSet;

@SuppressWarnings("unused")
public class TestSortedListSet {

	@Test
	public void testAdd() {
		Set<Integer> set = new SortedListSet<Integer>();
		set.add(3);
		set.add(5);
		set.add(1);
		set.add(2);
		set.add(4);
		
		assertEquals(5, set.size());
		assertEquals("[1, 2, 3, 4, 5]",set.toString());
	
		set = new SortedListSet<Integer>((o1, o2) -> o2.compareTo(o1)); //inverse order
		set.add(3);
		set.add(5);
		set.add(1);
		set.add(2);
		set.add(4);
		
		assertEquals(5, set.size());
		assertEquals("[5, 4, 3, 2, 1]",set.toString());
	}
	
	@Test
	public void testEquals() {
		Set<Integer> set1 = new SortedListSet<Integer>();
		set1.add(3);
		set1.add(5);
		set1.add(1);
		set1.add(2);
		set1.add(4);

		Set<Integer> set2 = new SortedListSet<Integer>();
		set2.add(4);
		set2.add(3);
		set2.add(1);
		set2.add(5);
		set2.add(2);
		
		assertTrue(set1.equals(set2));
		
		Set<Integer> set3 = new SortedListSet<Integer>();
		set3.add(4);
		set3.add(3);
		set3.add(1);
		set3.add(5);
		set3.add(8);

		assertFalse(set1.equals(set3));
	}

	@Test
	public void testConatins() {
		Set<Integer> set = new SortedListSet<Integer>();
		set.add(3);
		set.add(5);
		set.add(1);
		set.add(2);
		set.add(4);
		set.add(9);
		set.add(7);
		set.add(8);
		set.add(6);
		
		assertTrue(set.contains(3));
	}
	
	@Test
	public void testAddAll() {
		Set<Integer> list = new SortedListSet<Integer>();
		list.add(3);
		list.add(5);
		list.add(1);
		list.add(2);
		list.add(4);
		list.add(9);
		list.add(7);
		list.add(8);
		list.add(6);
		
		Set<Integer> set = new SortedListSet<Integer>();
		assertTrue(set.addAll(list));
		assertEquals("[1, 2, 3, 4, 5, 6, 7, 8, 9]", set.toString());
		assertFalse(set.addAll(list));
	}
}
