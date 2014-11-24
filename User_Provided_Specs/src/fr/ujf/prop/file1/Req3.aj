package fr.ujf.prop.file1;

import java.io.File;

import fr.ujf.monitor.Event;
import fr.ujf.monitor.Verdict;
import fr.ujf.monitor.VerificationMonitorFile;

public aspect Req3 {

	VerificationMonitorFile monitor = new VerificationMonitorFile();

	public Verdict dispatchEvent(String concreteEventName, File f, Object stream) {

		Verdict v = null;

		switch (concreteEventName) {
		case "checkOpenRead":
			v = this.monitor.receiveEvent(Event.checkOpenRead, f, stream);
			break;
		case "checkOpenWrite":
			v = this.monitor.receiveEvent(Event.checkOpenWrite, f, stream);
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

	pointcut openFileRead(File f): call(java.io.FileInputStream.new(File)) && args(f) ;

	pointcut openFileWrite(File f): call(java.io.FileOutputStream.new(File)) && args(f) ;

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

	Object around(File f) :  openFileRead(f){
		System.out.println(thisJoinPointStaticPart);

		Verdict v = dispatchEvent("checkOpenRead", f, null);
		if (v == Verdict.CURRENTLY_TRUE || v == Verdict.TRUE) {
			Object stream = proceed(f);
			System.out.println("Allowing the execution of OpenRead on " + f
					+ " with stream " + stream);

			dispatchEvent("afterOpen", f, stream);
			return stream;
		} else {
			System.out.println("Preventing the execution of OpenRead on " + f);
			return null;
		}
	}

	Object around(File f) :  openFileWrite(f){
		System.out.println(thisJoinPointStaticPart);

		Verdict v = dispatchEvent("checkOpenWrite", f, null);
		if (v == Verdict.CURRENTLY_TRUE || v == Verdict.TRUE) {
			Object stream = proceed(f);
			System.out.println("Allowing the execution of OpenWrite on " + f
					+ " with stream " + stream);

			dispatchEvent("afterOpen", f, stream);
			return stream;
		} else {
			System.out.println("Preventing the execution of OpenWrite on " + f);
			return new Object();
		}
	}

	void around() :  endProgram(){
		System.out.println(thisJoinPointStaticPart);
		dispatchEvent("end", null, null);
		proceed();
	}
}
