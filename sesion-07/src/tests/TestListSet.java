package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import estdatos.ListSet;

public class TestListSet {

	@Test
	public void testConstructors() {
		Set<Integer> set = new ListSet<Integer>();
		assertTrue(set.isEmpty());
		assertEquals(0, set.size());
		
		List<Integer> list = Arrays.asList(1, 2, 3, 4);
		set = new ListSet<Integer>(list);
		assertFalse(set.isEmpty());
		assertEquals(4, set.size());
		assertTrue(set.containsAll(list));
	}
	
	@Test
	public void testAdd() {
		Set<Integer> set = new ListSet<Integer>();
		assertTrue(set.add(3));
		assertEquals(1, set.size());
		assertTrue(set.contains(3));
		
		assertFalse(set.add(3));
		assertEquals(1, set.size());
	}

}
