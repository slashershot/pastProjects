package Logic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import Entity.Open_Member;
import Entity.Schools;

public class ExcelHandler {
	XSSFWorkbook excel;
	FileInputStream xcel;
	File xcel1;
	Schools school;
	public ExcelHandler(XSSFWorkbook excel,FileInputStream xcel,File xcel1){
		this.excel = excel;
		this.xcel = xcel;
		this.xcel1 = xcel1;
	}
	public void printEverything(){
		for(int i=0; excel.getNumberOfSheets()>i;i++){
			XSSFSheet sheet = excel.getSheetAt(i);
			Iterator<Row> rowIterator = sheet.iterator();
			while(rowIterator.hasNext()){
				Row row = rowIterator.next();
				Iterator<Cell> cellIterator = row.cellIterator();
				while(cellIterator.hasNext()){
					Cell cell = cellIterator.next();
		            switch(cell.getCellType()) {
	                case Cell.CELL_TYPE_BOOLEAN:
	                    System.out.print(cell.getBooleanCellValue() + "\t\t");
	                    break;
	                case Cell.CELL_TYPE_NUMERIC:
	                    System.out.print(cell.getNumericCellValue() + "\t\t");
	                    break;
	                case Cell.CELL_TYPE_STRING:
	                    System.out.print(cell.getStringCellValue() + "\t\t");
	                    break;
	            }
				}
			}
		}
	}
	public void writeToSheet0(Schools schools){
		school = schools;
		XSSFSheet sheet = excel.getSheetAt(0);
		int nextRowToWrite = (sheet.getLastRowNum()-sheet.getFirstRowNum())+1;
		sheet.createRow(nextRowToWrite);
		Cell cell = sheet.getRow(nextRowToWrite).createCell(0);
		cell.setCellValue(school.getSchool_Name());
		cell = sheet.getRow(nextRowToWrite).createCell(1);
		cell.setCellValue(school.getT_In_Charge());
		cell = sheet.getRow(nextRowToWrite).createCell(2);
		cell.setCellValue(school.getContact());
		cell = sheet.getRow(nextRowToWrite).createCell(3);
		cell.setCellValue(school.getEmail());
		//cell = sheet.getRow(nextRowToWrite).createCell(4);
		//cell.setCellValue(school.getEmail());
		//cell 5 later
		cell = sheet.getRow(nextRowToWrite).createCell(5);
		cell.setCellFormula("COUNTIF('Team Open Members'!A2:'Team Open Members'!A5,\""+school.getSchool_Name()+"\")");
		cell = sheet.getRow(nextRowToWrite).createCell(6);
		cell.setCellFormula("SUM(15*F2)");
		try {
			xcel.close();
			FileOutputStream outFile = new FileOutputStream(xcel1);
			excel.write(outFile);
			outFile.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void writeToSheet1(ArrayList<Open_Member> memArr){
		XSSFSheet sheet = excel.getSheetAt(1);
		int startRow=1;
		//4>0,1,2,3
		//row = 1,2,3,4
		for(int i=0; memArr.size()>i;i++){
			Row row = sheet.createRow(i+1);
			Cell cell = row.createCell(0);
			cell.setCellValue(school.getSchool_Name());
			cell = row.createCell(1);
			cell.setCellValue(memArr.get(i).getTeam_ID());
			cell = row.createCell(2);
			cell.setCellValue(memArr.get(i).getName());
			cell = row.createCell(3);
			cell.setCellValue(memArr.get(i).getNRIC());
			cell = row.createCell(4);
			cell.setCellValue(memArr.get(i).getShirt_Size());
			cell = row.createCell(5);
			cell.setCellValue(memArr.get(i).getContact());
			cell = row.createCell(6);
			cell.setCellValue(memArr.get(i).getEmail());
			cell = row.createCell(7);
			cell.setCellValue(memArr.get(i).getPlayer_Pos());
			cell = row.createCell(8);
			cell.setCellValue(memArr.get(i).getCategory());
			cell = row.createCell(9);
			cell.setCellValue(memArr.get(i).getChess());
		}
		try {
			xcel.close();
			FileOutputStream outFile = new FileOutputStream(xcel1);
			excel.write(outFile);
			outFile.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
