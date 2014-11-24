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
			FileInputStream inStr2 = new FileInputStream(f1);
									
			inStr.close();
			inStr.close();
						
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
