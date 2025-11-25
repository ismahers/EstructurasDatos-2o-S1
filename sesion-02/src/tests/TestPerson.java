package tests;

import static org.junit.Assert.*;

import java.util.Comparator;

import org.junit.Test;

import estdatos.AgeComparator;
import estdatos.Person;

public class TestPerson {

	@Test
	public void testNaturalOrder() {
		Person p1=new Person("Jaime",25);
		Person p2=new Person("Ana",35);
		
		int result1 = p1.compareTo(p2);
		assertTrue(result1 > 0);
		
		p2 = new Person("Jaime", 25);
		int result2 = p2.compareTo(p1);
		assertTrue(result2 == 0);
		
		Person p3 = new Person("Jaime", 28);
		int result3 = p1.compareTo(p3);
		assertEquals(0, result3);
	}
	
	@Test
	public void testAgeComparator() {
		Person p1=new Person("Jaime",25);
		Person p2=new Person("Ana",35);
		
		Comparator<Person> comparator = new AgeComparator();
		
		assertTrue(comparator.compare(p1, p2) < 0);
		assertTrue(comparator.compare(p2, p1) > 0);
		assertEquals(0, comparator.compare(p1, p1));
	}

}
