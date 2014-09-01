package Logic;

import java.util.LinkedList;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

public class Table_Handler implements Callable<ArrayList<Object>>{
	ArrayList<XWPFTable> wordTable;
	ArrayList<Object> stuff = new ArrayList<Object>();
	public Table_Handler(ArrayList<XWPFTable> wordTable){
		this.wordTable = wordTable;
	}

	public ArrayList<Object> call() {
		// TODO Auto-generated method stub
		List<XWPFTableRow> test = new ArrayList<XWPFTableRow>();
		//test = wordTable.get(0).getRows().get(0).getCell(0).getText();
		//System.out.println(wordTable.get(0).getRows().get(0).getCell(0).getText());
		return stuff;
	}
}
