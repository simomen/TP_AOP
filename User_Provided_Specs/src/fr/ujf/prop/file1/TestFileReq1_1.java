package fr.ujf.prop.file1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.stream.IntStream;

public class TestFileReq1_1 {

	public static void main(String[] args) {
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

}
