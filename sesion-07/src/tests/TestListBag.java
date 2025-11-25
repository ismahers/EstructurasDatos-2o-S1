package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.Test;

import estdatos.ListBag;

public class TestListBag {

	@Test
	public void testAdd() {
		Set<Integer> set = new ListBag<Integer>();
		assertTrue(set.add(3));
		assertEquals(1, set.size());
		assertTrue(set.contains(3));
		
		assertTrue(set.add(3));
		assertEquals(2, set.size());
	}
}
