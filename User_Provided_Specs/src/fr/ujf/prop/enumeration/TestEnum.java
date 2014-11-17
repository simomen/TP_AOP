package fr.ujf.prop.enumeration;

import java.util.Enumeration;
import java.util.Vector;

public class TestEnum {

	public static void main(String[] args) {

		Vector<Integer> v = new Vector<>();
//		Vector<Integer> v1 = new Vector<>();

		v.add(1);
		v.add(2);
		v.add(5);
		
//		v1.add(1);
//		v1.add(44);
//		v1.add(6);

		Enumeration<Integer> en = v.elements();
		Enumeration<Integer> en1 = v.elements();
		while (en.hasMoreElements()) {
			Integer integer = (Integer) en.nextElement();
			if (integer == 2) {
				v.add(33);	
			}
			System.out.println(integer);

		}

	}

}
