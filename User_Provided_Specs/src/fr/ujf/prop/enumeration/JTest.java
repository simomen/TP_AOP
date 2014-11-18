package fr.ujf.prop.enumeration;

import static org.junit.Assert.*;

import java.util.Enumeration;
import java.util.Vector;

import org.junit.Test;

public class JTest {
	@Test
	public void testEnum1() {
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
	@Test
	public void testEnumeration1() {
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
	}
	
	@Test
	public void testEnumeration2(){
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
	
	
	@Test
	public void testEnumeration3(){
		
		Vector<Integer> v = new Vector<>();
		v.add(1);
		v.add(2);
		v.add(5);
		Enumeration<Integer> en = v.elements();
		Enumeration<Integer> en1 = v.elements();
		while (en.hasMoreElements()) {
			Integer integer = (Integer) en.nextElement();
			if (integer == 2) {
				v.add(33);
			}
			System.out.println(integer);
		}
	
		v.add(4);
	}
	
	@Test 
	public void testEnumeration4(){

		Vector<Integer> v = new Vector<>();

		v.add(1);
		v.add(2);
		v.add(5);

		Enumeration<Integer> en = v.elements();
		Enumeration<Integer> en1 = v.elements();
		while (en.hasMoreElements()) {
			Integer integer = (Integer) en.nextElement();
			if (integer == 2) {
				v.add(33);
			}
			System.out.println(integer);
		}
	
		v.add(4);
		
		while (en1.hasMoreElements()) {
			Integer integer = (Integer) en1.nextElement();
		}
		
		v.add(6);
	}
}