package fr.ujf.monitor;

import java.io.File;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import fr.ujf.prop.file1.FileState;

public class VerificationMonitorOpenClose {

	private static final int DEFAULT_ID = -1;
	private int id;

	private State currentState = State.NextElement;

	Map<String, FileState> fileState = new HashMap<String, FileState>();
	Map<String, String> streamFile = new HashMap<String, String>();

	public VerificationMonitorOpenClose() {
		this.id = DEFAULT_ID;
	}

	public VerificationMonitorOpenClose(int id) {
		this.id = id;
	}

	public void updateState(Event e, File f, Object stream) {

		String path;
		FileState s;

		switch (e) {
		case checkOpen:
			path = f.getAbsolutePath();
			s = fileState.get(path);

			if (s != FileState.OPEN) {
				fileState.put(path, FileState.OPEN);
				this.currentState = State.OK;
			} else {
				this.currentState = State.Error;
			}
			break;
		case afterOpen:
			path = f.getAbsolutePath();
			s = fileState.get(path);

			streamFile.put(stream.toString(), path);
			this.currentState = State.OK;

			break;
		case close:
			path = streamFile.get(stream.toString());
			s = fileState.get(path);

			if (s != FileState.OPEN)
				this.currentState = State.Error;
			else {
				fileState.put(path, FileState.CLOSE);
				this.currentState = State.OK;
			}
			break;

		case end:

			if (fileState.containsValue(FileState.OPEN))
				this.currentState = State.Error;
			else {
				this.currentState = State.OK;
			}
			break;
		default:
			break;
		}
	}

	public Verdict currentVerdict() {

		switch (this.currentState) {
		case OK:
			return Verdict.CURRENTLY_TRUE;
		case Error:
			return Verdict.FALSE;
		default:
			return Verdict.FALSE;
		}
	}

	public void emitVerdict(File f, Object stream) {
		System.out.println("Monitor " + this.id + ": " + currentVerdict());
	}

	public Verdict receiveEvent(Event e, File f, Object stream) {
		System.out.println("=> Monitor " + this.id + ": received event " + e
				+ ", file : " + f + " , stream : " + stream);
		updateState(e, f, stream);
		emitVerdict(f, stream);
		return currentVerdict();
	}
}
