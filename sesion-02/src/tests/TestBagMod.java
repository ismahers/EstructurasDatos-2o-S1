package tests;

import static org.junit.Assert.*;

import java.nio.BufferOverflowException;
import java.util.Collection;

import org.junit.Test;

import estdatos.BagMod;
import estdatos.BagNoMod;

public class TestBagMod {

	@Test(expected = BufferOverflowException.class)
	public void test() {
        Collection<Integer> bm1=new BagMod<>(100);
        assertTrue(bm1.isEmpty());
        assertEquals(0, bm1.size());

        Collection<Integer> b1=new BagNoMod<>(5,10,48,30);
        Collection<Integer> bm2=new BagMod<>(b1);
        assertFalse(bm2.isEmpty());
        assertEquals(4, bm2.size());

        bm2.remove(48);
        assertEquals(3, bm2.size());
        assertFalse(bm2.contains(48));

        bm2.add(666);
        assertEquals(4, bm2.size());
        assertTrue(bm2.contains(10));
        assertTrue(bm2.contains(30));
        assertTrue(bm2.contains(666));
        
        bm2.add(48); //exception
	}

}
