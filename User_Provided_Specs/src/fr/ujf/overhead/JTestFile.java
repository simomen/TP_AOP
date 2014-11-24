package fr.ujf.overhead;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.junit.Test;

public class JTestFile {

	@Test
	public void testFileReq1_1() throws IOException{

		File f1 = new File("myFile");
		FileInputStream inp =  new FileInputStream(f1);;
		
		for (int i = 0; i < 10000; i++) {
			inp = new FileInputStream(f1);
		}
		
		for (int i = 0; i < 10000; i++) {
			inp.close();
		}
		//System.exit(1);

	}
}
