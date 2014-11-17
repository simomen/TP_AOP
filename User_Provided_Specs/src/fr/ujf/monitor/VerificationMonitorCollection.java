package fr.ujf.monitor;

import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

public class VerificationMonitorCollection {

	private static final int DEFAULT_ID = -1;
	private int id;

	private State currentState = State.NextElement;

	Map<Integer, HashSet<Integer>> collSet = new HashMap<Integer, HashSet<Integer>>();

	public VerificationMonitorCollection() {
		this.id = DEFAULT_ID;
	}

	public VerificationMonitorCollection(int id) {
		this.id = id;
	}

	public void updateState(Event e, Set s, Collection c) {

		int idSet = System.identityHashCode(s);
		int idCollection = System.identityHashCode(c);

		switch (e) {
		case addToSet:
			if (collSet.containsKey(idCollection)) {
				collSet.get(idCollection).add(idSet);
			} else {
				HashSet<Integer> set = new HashSet<Integer>();
				set.add(idSet);
				collSet.put(idCollection, set);
			}
			this.currentState = State.Update;
			break;

		case addToCollection:
			if (collSet.containsKey(idCollection))
				if (collSet.get(idCollection).isEmpty())
					this.currentState = State.Update;
				else
					this.currentState = State.Error;
			break;
		case removeFromSet:
			collSet.get(idCollection).remove(idSet);
			break;
		}
	}

	public Verdict currentVerdict() {

		switch (this.currentState) {
		case Elements:
			return Verdict.CURRENTLY_TRUE;
		case NextElement:
			return Verdict.CURRENTLY_TRUE;
		case Update:
			return Verdict.CURRENTLY_TRUE;
		case Error:
			return Verdict.FALSE;
		default:
			return Verdict.FALSE;
		}

	}

	public void emitVerdict(Set s, Collection c) {
		System.out.println("Monitor " + this.id + ": " + currentVerdict());
	}

	public Verdict receiveEvent(Event e, Set s, Collection c) {
		System.out.println("=> Monitor " + this.id + ": received event " + e
				+ ", set : " + System.identityHashCode(s) + " , collection : "
				+ System.identityHashCode(c));
		updateState(e, s, c);
		emitVerdict(s, c);
		return currentVerdict();
	}
}
