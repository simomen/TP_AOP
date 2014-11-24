package fr.ujf.prop.file1;

import java.io.File;

import fr.ujf.monitor.Event;
import fr.ujf.monitor.Verdict;
import fr.ujf.monitor.VerificationMonitorOpenClose;

public aspect Req1_2 {

	VerificationMonitorOpenClose monitor = new VerificationMonitorOpenClose();

	public Verdict dispatchEvent(String concreteEventName, File f, Object stream) {

		Verdict v = null;

		switch (concreteEventName) {
		case "checkOpen":
			v = this.monitor.receiveEvent(Event.checkOpen, f, stream);
			break;
		case "afterOpen":
			v = this.monitor.receiveEvent(Event.afterOpen, f, stream);
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

	void around(Object stream) : closeFile(stream){
		System.out.println(thisJoinPointStaticPart);

		Verdict v = dispatchEvent("close", null, stream);
		if (v == Verdict.CURRENTLY_TRUE || v == Verdict.TRUE) {
			System.out.println("Allowing the execution of Close on " + stream);
			proceed(stream);
		} else {
			System.out
					.println("Preventing the execution of Close on " + stream);
			return;
		}
	}

	Object around(File f) :  openFile(f){
		System.out.println(thisJoinPointStaticPart);

		Verdict v = dispatchEvent("checkOpen", f, null);
		if (v == Verdict.CURRENTLY_TRUE || v == Verdict.TRUE) {
			Object stream = proceed(f);
			System.out.println("Allowing the execution of Open on " + f
					+ " with stream " + stream);

			dispatchEvent("afterOpen", f, stream);
			return stream;
		} else {
			System.out.println("Preventing the execution of Open on " + f);
			return null;
		}
	}

	void around() :  endProgram(){
		System.out.println(thisJoinPointStaticPart);
		dispatchEvent("end", null, null);
		proceed();
	}
}
