package com.utility;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections.iterators.ArrayIterator;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.ui.pojos.User;

public class ExcelReaderUtility {

	public static Iterator<User> readExcelFile(String fileName) {

		File xlxsFile = new File(System.getProperty("user.dir") + "/testData/"+fileName);
		XSSFWorkbook xssfworkbook = null;
		List<User> userList = null;
		Row row;
		Cell emailAddressCell;
		Cell passwordCell;
		User user;
		Iterator<Row> rowIterator;
		XSSFSheet xssfSheet;

		try {
			xssfworkbook = new XSSFWorkbook(xlxsFile);
			userList = new ArrayList<User>();

			xssfSheet = xssfworkbook.getSheet("LoginTestData");
			rowIterator = xssfSheet.iterator();
			rowIterator.next();// skipping the col name
			while (rowIterator.hasNext()) {

				row = rowIterator.next();
				emailAddressCell = row.getCell(0);
				passwordCell = row.getCell(1);
				user = new User(emailAddressCell.toString(), passwordCell.toString());
				userList.add(user);
			}
			xssfworkbook.close();

			
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return userList.iterator();

	}
}
