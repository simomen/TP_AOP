package fr.ujf.prop.collection;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.junit.Test;

public class JTestCollection {

	@Test
	public void testCollection() {
		Collection<String> c1 = new ArrayList<String>();

		c1.add("OK1");
		c1.add("OK2");

		printCollection(c1);
	}

	public static void printCollection(Collection c) {

		Iterator<String> it = c.iterator();
		while (it.hasNext()) {
			String string = (String) it.next();
			System.out.println(string);
		}
	}
	
		@Test
		public void testCollection3() {
			Set<Collection<String>> s = new HashSet<Collection<String>>();
			Collection<String> c1 = new ArrayList<String>();

			c1.add("OK1");
			c1.add("OK2");
			c1.add("OK3");
			s.add(c1);
			c1.add("KO");
			s.remove(c1);
			c1.add("OK4");

			printCollection(c1);
			}
		
		@Test
		public void testCollection2() {
			Set<Collection<String>> s = new HashSet<Collection<String>>();
			Collection<String> c1 = new ArrayList<String>();

			c1.add("OK1");
			c1.add("OK2");
			c1.add("OK3");
			s.add(c1);
			c1.add("KO");

			printCollection(c1);
		
		}
		
		@Test
		public void testCollection4() {

			Set<Collection<String>> s1 = new HashSet<Collection<String>>();
			Set<Collection<String>> s2 = new HashSet<Collection<String>>();
			Collection<String> c1 = new ArrayList<String>();

			c1.add("OK1");
			c1.add("OK2");
			c1.add("OK3");
			s1.add(c1);
			s2.add(c1);
			c1.add("KO");
			s1.remove(c1);
			c1.add("OK4");

			printCollection(c1);
			
		}
		
		@Test
		public void testCollection5() {
			
			Set<Collection<String>> s1 = new HashSet<Collection<String>>();
			Set<Collection<String>> s2 = new HashSet<Collection<String>>();
			Collection<String> c1 = new ArrayList<String>();

			c1.add("OK1");
			c1.add("OK2");
			c1.add("OK3");
			s1.add(c1);
			s2.add(c1);
			c1.add("KO1");
			s1.remove(c1);
			c1.add("KO2");
			s2.remove(c1);
			c1.add("OK4");

			printCollection(c1);
		}
}
