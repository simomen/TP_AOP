package fr.ujf.monitor;

public enum State {
	DoHasNext("DoHasNext"), DoNext("DoNext"), Elements("Elements"), NextElement(
			"NextElement"), Update("Update"), Error("Error"), OK("OK");

	private String name;

	State(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}
}
