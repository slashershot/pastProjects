package Logic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFTable;

public class DocHandler {
	//DECLARION OF CONSTANTS TO CHECK!
	private static final String T_I_C = "Teacher-in-charge:";
	private static final String T_CONTACT = "Contact:";
	private static final String SCHOOL = "School:";
	private static final String EMAIL = "E-mail Address:";
	private static final String T_NUM ="Team Number:";
	private static final String CATEGORY = "Category(Secondary School,Tertiary):";
	private static final String CHESS = "Chess:";
	private static final String[] CONSTANTSARR = {T_I_C,T_CONTACT,SCHOOL,EMAIL,T_NUM,CATEGORY,CHESS};
	//gets Strings of the entire document
	public  void extract(File wordDoc,File excelDoc){
		try {
			FileInputStream word = new FileInputStream(wordDoc);
			//FileInputStream excel = new FileInputStream(excelDoc);
			XWPFDocument doc = new XWPFDocument(word);
			LinkedList<XWPFParagraph> test = new LinkedList<XWPFParagraph>(doc.getParagraphs());
			LinkedList<XWPFTable> test1 = new LinkedList<XWPFTable>(doc.getTables());
			long startTime = System.nanoTime();
			for(int i=0; test.size()>i;i++){
				String line = test.get(i).getParagraphText();
				//System.out.println(line);
				evaulateText(line);
			}
			long endTime = System.nanoTime();
			//System.out.println("This portion run for "+ TimeUnit.SECONDS.convert((endTime-startTime),TimeUnit.NANOSECONDS)+" ms");
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//checking of variables to see if they match what we are looking for
	/**
	 * 				  line2 = line.substring(endIndex);
		  line = line.substring(beginIndex,endIndex);
		  				  if(line2.contains(CONSTANTSARR[j])){
			  line2 = line2.substring(line2.indexOf(":")+1);
			  System.out.println(line2);
		  }
		  line = line.substring(line.indexOf(":" )+1).trim();
		  System.out.println(line);
	 */
	//check how many constants a line has
	public void evaulateText(String line){
		ArrayList<Integer> arr = new ArrayList<Integer>();
		ArrayList<String>  cons = new ArrayList<String>();
		int index;
		for(int i=0; CONSTANTSARR.length>i;i++){
			if(line.contains(CONSTANTSARR[i])){
				index = line.indexOf(CONSTANTSARR[i]);
				arr.add(index);
				cons.add(CONSTANTSARR[i]);
			}
		}
		if(cons.size()>1&&arr.size()>1){
			for(int i=1; cons.size()>i;i++){
				
				System.out.println(line.substring(line.indexOf(":")+1,arr.get(i)).trim());
				String line2 = line.substring(arr.get(i));
				System.out.println(line2.substring(line2.indexOf(":")+1).trim());
			}
		}else if(cons.size()==1 && arr.size()==1){
			System.out.println(line.substring(line.indexOf(":")+1).trim().replaceAll("_", ""));
		}else{
			
		}
	}
}
