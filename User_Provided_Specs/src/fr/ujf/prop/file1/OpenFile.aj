package fr.ujf.prop.file1;

import java.io.File;

import fr.ujf.monitor.Event;
import fr.ujf.monitor.Verdict;
import fr.ujf.monitor.VerificationMonitorOpenClose;

public aspect OpenFile {

	VerificationMonitorOpenClose monitor = new VerificationMonitorOpenClose();

	public Verdict dispatchEvent(String concreteEventName, File f, Object stream) {

		Verdict v = null;

		switch (concreteEventName) {
		case "open":
			v = this.monitor.receiveEvent(Event.open, f, stream);
			break;
		case "close":
			v = this.monitor.receiveEvent(Event.close, f, stream);
			break;
		case "end":
			v = this.monitor.receiveEvent(Event.end, f, stream);
			break;

		default:
			// unrecognized event => do nothing
			v = this.monitor.currentVerdict();
			break;
		}
		return v;
	}

	pointcut endProgram(): call(void java.lang.System.exit(int));

	pointcut openFile(File f): call(java.io.File*Stream.new(File)) && args(f) ;

	pointcut closeFile(Object stream): call(void java.io.*Stream.close()) && target(stream) ;

	after(Object stream) : closeFile(stream){
		System.out.println(thisJoinPointStaticPart);
		dispatchEvent("close", null, stream);

	}

	after(File f) returning(Object stream):  openFile(f){
		System.out.println(thisJoinPointStaticPart);
		dispatchEvent("open", f, stream);
	}

	before() :  endProgram(){
		System.out.println(thisJoinPointStaticPart);
		dispatchEvent("end", null, null);
	}
}
