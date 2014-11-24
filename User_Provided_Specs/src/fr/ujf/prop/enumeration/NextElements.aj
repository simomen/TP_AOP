package fr.ujf.prop.enumeration;

import java.util.Enumeration;
import java.util.Vector;

import fr.ujf.monitor.Event;
import fr.ujf.monitor.Verdict;
import fr.ujf.monitor.VerificationMonitorEnum;

public aspect NextElements {
	VerificationMonitorEnum monitor = new VerificationMonitorEnum();

	public Verdict dispatchEvent(String concreteEventName, Vector vect,
			Enumeration en) {

		Verdict v = null;

		switch (concreteEventName) {
		case "nextElement":
			v = this.monitor.receiveEvent(Event.nextElement, vect, en);
			break;
		case "add":
			v = this.monitor.receiveEvent(Event.add, vect, en);
			break;
		case "elements":
			v = this.monitor.receiveEvent(Event.elements, vect, en);
			break;
		case "hasMoreElements":
			v = this.monitor.receiveEvent(Event.hasMoreElements, vect, en);
			break;
		default:
			// unrecognized event => do nothing
			v = this.monitor.currentVerdict();
			break;
		}
		return v;
	}

	pointcut nextElement(Enumeration en) : call (Object java.util.Enumeration.nextElement()) && target(en);

	pointcut hasMoreElements(Enumeration en) : call (boolean java.util.Enumeration.hasMoreElements()) && target(en);

	pointcut elements(Vector v) : call (Enumeration java.util.Vector.elements()) && target(v);

	pointcut add(Vector vect) : call (boolean java.util.Vector.add(*)) && target(vect);

	before(Enumeration en) : nextElement(en) {
		dispatchEvent("nextElement", null, en);
	}

	after(Vector v) returning(Enumeration en) : elements(v) {
		dispatchEvent("elements", v, en);
	}

	after(Enumeration en) returning(boolean result) : hasMoreElements(en) {
		if (result == false)
			dispatchEvent("hasMoreElements", null, en);
	}


	boolean around(Vector vect) : add(vect) {
		Verdict v = dispatchEvent("add", vect, null);
		if (v == Verdict.CURRENTLY_TRUE || v == Verdict.TRUE) {
			System.out.println("Allowing the execution of add on "
					+ System.identityHashCode(vect));
			return proceed(vect);
		} else {
			System.out.println("Preventing the execution of add on "
					+ System.identityHashCode(vect));
			return false;
		}
	}

}
