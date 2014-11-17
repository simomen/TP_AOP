package fr.ujf.prop.collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class TestCollection3 {

	public static void main(String[] args) {

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

	public static void printCollection(Collection c) {

		Iterator<String> it = c.iterator();
		while (it.hasNext()) {
			String string = (String) it.next();
			System.out.println(string);
		}
	}

}
