package fr.ujf.overhead;

import static org.junit.Assert.*;

import java.util.Enumeration;
import java.util.Vector;

import org.junit.Test;

public class JTestEnumeration {
	@Test
	public void testEnumeration() {
		Vector<Integer> v = new Vector<>();
		for (int i = 0; i < 10000; i++)
			v.add(1);

		Enumeration<Integer> en = v.elements();
		while (en.hasMoreElements()) {
			en.nextElement();
		}
	}
}