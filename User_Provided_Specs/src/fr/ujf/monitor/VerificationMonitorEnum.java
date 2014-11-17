package fr.ujf.monitor;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

public class VerificationMonitorEnum {

	private static final int DEFAULT_ID = -1;
	private int id;

	private State currentState = State.NextElement;

	Map<Integer, Integer> dsState = new HashMap<Integer, Integer>();
	Map<Integer, Integer> enumState = new HashMap<Integer, Integer>();
	Map<Integer, Integer> enumDs = new HashMap<Integer, Integer>();

	public VerificationMonitorEnum() {
		this.id = DEFAULT_ID;
	}

	public VerificationMonitorEnum(int id) {
		this.id = id;
	}

	public void updateState(Event e, Vector vect, Enumeration en) {

		int idVect = System.identityHashCode(vect);
		int idEnum = System.identityHashCode(en);

		switch (e) {
		case elements:
			enumDs.put(idEnum, idVect);
			enumState.put(idEnum, dsState.get(idVect));
			this.currentState = State.Elements;

			break;

		case nextElement:
			enumState.put(idEnum, en.hashCode());
			this.currentState = State.NextElement;
			break;

		case hasMoreElements:
			enumState.remove(idEnum);
			enumDs.remove(idEnum);
			this.currentState = State.Update;
			break;

		case add:
			dsState.put(idVect, vect.hashCode());
			this.currentState = State.Update;

			Iterator it = enumDs.keySet().iterator();
			while (it.hasNext()) {
				Integer keyEnum = (Integer) it.next();
				if (enumDs.get(keyEnum) == idVect
						&& (dsState.get(idVect) != enumState.get(keyEnum))) {

					this.currentState = State.Error;
				}
			}

			if (enumDs.containsKey(idEnum)) {
				if (dsState.get(idVect) != enumState.get(idEnum)) {
					this.currentState = State.Error;
				}
			}
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

	public void emitVerdict(Vector vect, Enumeration en) {
		System.out.println("Monitor " + this.id + ": " + currentVerdict());
	}

	public Verdict receiveEvent(Event e, Vector vect, Enumeration en) {
		System.out.println("=> Monitor " + this.id + ": received event " + e
				+ ", vect : " + System.identityHashCode(vect) + " , enum : "
				+ System.identityHashCode(en));
		updateState(e, vect, en);
		emitVerdict(vect, en);
		return currentVerdict();
	}
}
