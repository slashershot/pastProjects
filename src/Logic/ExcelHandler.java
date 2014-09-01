package Logic;

import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelHandler {
	XSSFWorkbook excel;
	public ExcelHandler(XSSFWorkbook excel){
		this.excel = excel;
	}
	public void writeToExcel(){
		XSSFSheet sheet = excel.getSheetAt(0);
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
