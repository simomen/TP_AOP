package fr.ujf.prop.file1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class TestFileReq1 {

	public static void main(String[] args) {
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

}
