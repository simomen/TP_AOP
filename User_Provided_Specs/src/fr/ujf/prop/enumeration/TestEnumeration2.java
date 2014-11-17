package fr.ujf.prop.enumeration;

import java.util.Enumeration;
import java.util.Vector;

public class TestEnumeration2 {

	public static void main(String[] args) {

		Vector<Integer> v = new Vector<>();

		v.add(1);
		v.add(2);
		v.add(5);

		Enumeration<Integer> en = v.elements();
		while (en.hasMoreElements()) {
			Integer integer = (Integer) en.nextElement();
			if (integer == 2) {
				v.add(33);
			}
			System.out.println(integer);
		}
		
		v.add(4);
	}
}
