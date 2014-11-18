package fr.ujf.monitor;

public enum Event {
	hasNext("hasNext"), next("next"), hasMoreElements("hasMoreElements"), nextElement(
			"nextElement"), add("add"), elements("elements"), addToSet(
			"addToSet"), addToCollection("addToCollection"), removeFromSet(
			"removeFromSet"), open("open"), close("close"), end("end");

	private String name;

	Event(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}
}
