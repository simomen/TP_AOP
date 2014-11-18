package fr.ujf.prop.file1;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.junit.Test;

public class JTestFile {

	@Test
	public void testFileReq1_1() {
try {

			
			File f1 = new File("myFile");
			File f2 = new File("myFile");
			File f3 = new File("myFile3");
					
			FileInputStream inStr = new FileInputStream(f1);
			FileInputStream inStr2 = new FileInputStream(f2);
//			FileInputStream intStr3 = new FileInputStream(f3);
						
//			inStr.read();
//			inStr2.read();
//			intStr3.read();
//			
//			inStr.close();
			inStr.close();
//			intStr3.close();
				
			System.exit(1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	@Test
	public void testFileReq1() {
try {

			
			File f1 = new File("myFile");
			File f3 = new File("myFile3");
					
			FileInputStream inStr = new FileInputStream(f1);
			FileInputStream intStr3 = new FileInputStream(f3);
						
			inStr.read();
			intStr3.read();
			
			inStr.close();
			intStr3.close();
			intStr3.close();
						
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	@Test
	public void testFileReq2() {
try {

			
			File f1 = new File("myFile");
			File f2 = new File("myFile2");
			File f3 = new File("myFile3");
					
			FileInputStream inStr = new FileInputStream(f1);
			FileOutputStream outStr = new FileOutputStream(f2);
			FileInputStream intStr2 = new FileInputStream(f3);
						
			inStr.read();
			outStr.write(1);
			intStr2.read();
			
			inStr.close();
			//outStr.close();
			intStr2.close();
						
			System.exit(1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
