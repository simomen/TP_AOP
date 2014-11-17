package fr.ujf.prop.collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class TestCollection {

	public static void main(String[] args) {

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
}
