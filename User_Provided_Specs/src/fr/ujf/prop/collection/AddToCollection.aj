package fr.ujf.prop.collection;

import java.util.Collection;
import java.util.Set;

import fr.ujf.monitor.Event;
import fr.ujf.monitor.Verdict;
import fr.ujf.monitor.VerificationMonitorCollection;

public aspect AddToCollection {

	VerificationMonitorCollection monitor = new VerificationMonitorCollection();

	public Verdict dispatchEvent(String concreteEventName, Set s, Collection c) {

		Verdict v = null;

		switch (concreteEventName) {
		case "addToSet":
			v = this.monitor.receiveEvent(Event.addToSet, s, c);
			break;
		case "addToCollection":
			v = this.monitor.receiveEvent(Event.addToCollection, s, c);
			break;
		case "removeFromSet":
			v = this.monitor.receiveEvent(Event.removeFromSet, s, c);
			break;
		case "elements":
			v = this.monitor.receiveEvent(Event.elements, s, c);
			break;
		default:
			// unrecognized event => do nothing
			v = this.monitor.currentVerdict();
			break;
		}
		return v;
	}

	pointcut addToSet(Set s, Collection c) : call(boolean java.util.Set.add(*)) && target(s) && args(c);

	pointcut addCollection(Collection c) : call(boolean java.util.Collection.add*(*)) && target(c) && if (!(c instanceof java.util.Set));

	pointcut removeFromSet(Set s, Collection c) : call(boolean java.util.Set.remove(*)) && target(s) && args(c);

	after(Set s, Collection c) : addToSet(s,c) {
		dispatchEvent("addToSet", s, c);
	}

	boolean around(Collection c) : addCollection(c) {
		Verdict v = dispatchEvent("addToCollection", null, c);
		if (v == Verdict.CURRENTLY_TRUE || v == Verdict.TRUE) {
			System.out.println("Allowing the execution of addToCollection on "
					+ System.identityHashCode(c));
			return proceed(c);
		} else {
			System.out
					.println("Preventing the execution of addToCollection on "
							+ System.identityHashCode(c));
			return false;
		}
	}

	after(Set s, Collection c) : removeFromSet(s,c) {
		dispatchEvent("removeFromSet", s, c);
	}
}