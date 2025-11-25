package tests;

import static org.junit.Assert.assertEquals;

import java.util.Comparator;
import java.util.List;

import org.junit.Test;

import estdatos.SortedList;

public class TestSortedList {

	@Test
	public void testNaturalOrder() {

		List<Character> l1 = new SortedList<Character>();
		l1.add('c');
		l1.add('v');
		l1.add('a');
		
		assertEquals(Character.valueOf('a'), l1.get(0));
		assertEquals(Character.valueOf('c'), l1.get(1));
		assertEquals(Character.valueOf('v'), l1.get(2));
		
		l1.remove(1);
		
		assertEquals(Character.valueOf('a'), l1.get(0));
		assertEquals(Character.valueOf('v'), l1.get(1));
	}
	
	@Test
	public void testComparator() {
		Comparator<Character> comparator = (c1, c2) -> c2.compareTo(c1);
		
		List<Character> l1 = new SortedList<Character>(comparator);
		l1.add('c');
		l1.add('v');
		l1.add('a');
		
		assertEquals(Character.valueOf('v'), l1.get(0));
		assertEquals(Character.valueOf('c'), l1.get(1));
		assertEquals(Character.valueOf('a'), l1.get(2));
		
		l1.remove(1);
		
		assertEquals(Character.valueOf('v'), l1.get(0));
		assertEquals(Character.valueOf('a'), l1.get(1));
		
	}

}
