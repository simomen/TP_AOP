package fr.ujf.monitor;

import java.io.File;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import fr.ujf.prop.file1.FileState;

public class VerificationMonitorFile {

	private static final int DEFAULT_ID = -1;
	private int id;

	private State currentState = State.NextElement;

	Map<String, FileState> fileState = new HashMap<String, FileState>();
	Map<String, String> streamFile = new HashMap<String, String>();

	public VerificationMonitorFile() {
		this.id = DEFAULT_ID;
	}

	public VerificationMonitorFile(int id) {
		this.id = id;
	}

	public void updateState(Event e, File f, Object stream) {

		String path;
		FileState s;

		switch (e) {
		case checkOpenRead:
			path = f.getAbsolutePath();
			s = fileState.get(path);

			if (s != FileState.WRITE) {
				fileState.put(path, FileState.READ);
				this.currentState = State.OK;
			} else {
				this.currentState = State.Error;
			}
			break;

		case checkOpenWrite:
			path = f.getAbsolutePath();
			s = fileState.get(path);

			if (s != FileState.READ) {
				fileState.put(path, FileState.WRITE);
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

			switch (s) {
			case OPEN:
			case READ:
			case WRITE:
				this.currentState = State.OK;
				fileState.put(path, FileState.CLOSE);

				break;

			default:
				this.currentState = State.Error;

				break;
			}

			break;

		case end:

			if (fileState.containsValue(FileState.OPEN)
					|| fileState.containsValue(FileState.READ)
					|| fileState.containsValue(FileState.WRITE))
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
