package fr.ujf.prop.collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class TestCollection5 {

	public static void main(String[] args) {

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

	public static void printCollection(Collection c) {

		Iterator<String> it = c.iterator();
		while (it.hasNext()) {
			String string = (String) it.next();
			System.out.println(string);
		}
	}

}
