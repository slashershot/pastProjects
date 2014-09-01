package Logic;

import java.util.LinkedList;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

public class Table_Handler implements Callable<ArrayList<Object>>{
	ArrayList<XWPFTable> wordTable;
	ArrayList<Object> stuff = new ArrayList<Object>();
	public Table_Handler(ArrayList<XWPFTable> wordTable){
		this.wordTable = wordTable;
	}

	public ArrayList<Object> call() {
		List<XWPFTableRow> rowArr = new ArrayList<XWPFTableRow>();
		List<Object> objArr = new ArrayList<Object>();
		//index 0 should return all the rows of one table with index 0 = row 0 and index x = row x
		for(int i=0; wordTable.size()>i;i++){
			 rowArr = wordTable.get(i).getRows();
			 objArr.add(rowArr);
		}
		//for the amount of tables check if the first row corrisponds the following syntax
		//Player,Name,NRIC,ShirtSize,Contact,Email
		// if it does, it a registration form. check if the amount of team number category and
		//chess tallys with the amoount table
		// else its a blitz form
		for(int i=0; objArr.size()>i;i++){
			//System.out.println(((ArrayList<XWPFTableRow>)objArr.get(i)).get(0).getCell(0).getText());
			//get header row
			XWPFTableRow header = ((ArrayList<XWPFTableRow>)objArr.get(i)).get(0);
			String text = "";
			//amount of cells is header row
			int headerSize = header.getTableCells().size();
			int size = ((ArrayList<XWPFTableRow>)objArr.get(i)).size();
			String[][] tableArr = new String[size][headerSize];
			if(headerSize==6){
				//calls a method(gettablecells) that takes in this tablerow and returns all table cells
				for(int k=0; size>k;k++){
				for(int j=0; headerSize>j;j++){
					text = (getTableCells(((ArrayList<XWPFTableRow>)objArr.get(i)).get(k)).get(j).getText());
					if(!text.isEmpty()||!(text==null)){
						if(text.equals("")){
							
						}
						tableArr[k][j] = text;
						//System.out.println(tableArr[k][j]);
					}
				}
				}
				stuff.add( new Object[]{"Open",tableArr});
			}else if(headerSize==7){
				//calls a method(gettablecells) that takes in this tablerow and returns all table cells
				for(int k=0; size>k;k++){
				for(int j=0; headerSize>j;j++){
					text = (getTableCells(((ArrayList<XWPFTableRow>)objArr.get(i)).get(k)).get(j).getText());
					if(text!=null){
						tableArr[k][j] = text;
						//System.out.println(tableArr[k][j]);

					}
				}
				}
				stuff.add( new Object[]{"Blitz",tableArr});
			}else{
				System.out.println("Exception error,Table recognized");
			}
		}
		return stuff;
	}
	public ArrayList<XWPFTableCell> getTableCells(XWPFTableRow row){
		 return (ArrayList<XWPFTableCell>) row.getTableCells();
	}
}
