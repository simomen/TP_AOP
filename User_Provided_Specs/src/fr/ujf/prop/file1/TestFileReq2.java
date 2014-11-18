package fr.ujf.prop.file1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class TestFileReq2 {

	public static void main(String[] args) {
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
