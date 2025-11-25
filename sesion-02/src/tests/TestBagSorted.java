package tests;

import static org.junit.Assert.*;

import java.util.Collection;

import org.junit.Test;

import estdatos.BagSorted;

public class TestBagSorted {

	@Test
	public void test() {
		Collection<Integer> bag = new BagSorted<Integer>(10);
		
		bag.add(10);
		bag.add(5);
		bag.add(-1);
		bag.add(33);
		
		Object[] bagContents = bag.toArray();
		
		assertEquals(Integer.valueOf(-1), bagContents[0]);
		assertEquals(Integer.valueOf(5), bagContents[1]);
		assertEquals(Integer.valueOf(10), bagContents[2]);
		assertEquals(Integer.valueOf(33), bagContents[3]);
	}

}
