package fr.ujf.overhead;

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
		Set<Collection<String>> s = new HashSet<Collection<String>>();
		Collection<String> c1 = new ArrayList<String>();

		for (int i = 0; i < 10000000; i++) {
			c1.add("OK1");
		}
		s.add(c1);

	}
}
