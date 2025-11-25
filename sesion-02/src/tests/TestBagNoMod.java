package tests;

import static org.junit.Assert.*;

import java.util.Collection;

import org.junit.Test;

import estdatos.BagNoMod;

public class TestBagNoMod {

	@Test(expected = UnsupportedOperationException.class)
	public void test() {
		Collection<Integer> b1=new BagNoMod<>();
		assertTrue(b1.isEmpty());
		assertEquals(0, b1.size());
		
		Collection<Integer> b2=new BagNoMod<>(5,10,48,30);
		assertFalse(b2.isEmpty());
		assertEquals(4, b2.size());
		assertTrue(b2.contains(5));
		assertTrue(b2.contains(10));
		assertTrue(b2.contains(48));
		assertTrue(b2.contains(30));
		
		b2.add(10); //An exception should be raised
	}

}
