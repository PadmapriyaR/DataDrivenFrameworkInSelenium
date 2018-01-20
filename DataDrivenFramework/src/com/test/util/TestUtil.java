package com.test.util;

import java.util.ArrayList;

import com.excel.utility.XlsReader;

public class TestUtil {

	static XlsReader reader; 
	
	public static ArrayList<Object[]> getDataFromExcel(){
		
		ArrayList<Object[]> myData = new ArrayList<Object[]>();
		try {
			reader = new XlsReader("D:\\\\Studies\\\\Selenium with Java1.0\\\\Hybrid Framework with POM\\\\DataDrivenFramework\\\\src\\\\com\\\\excel\\\\testdata\\\\HalfEBayTestData.xlsx");
		} catch (Exception e) {
			e.printStackTrace();
		}
		int rowCount = reader.getRowCount("RegTestData");
		for(int rowNum=2;rowNum<=rowCount;rowNum++) {
			String firstName = reader.getCellData("RegTestData", "Firstname", rowNum);
			String lastName = reader.getCellData("RegTestData", "Lastname", rowNum);
			String email = reader.getCellData("RegTestData", "Emailaddress", rowNum);
			String password = reader.getCellData("RegTestData", "Password", rowNum);
			Object ob[] = {firstName,lastName,email,password};
			myData.add(ob);
		}
		return myData;
	}
}
