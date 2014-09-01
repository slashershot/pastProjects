package Logic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFTable;

import Entity.Schools;

public class ThreadsHandler {
	//DECLARION OF CONSTANTS TO CHECK!
	private static final String T_I_C = "Teacher-in-charge:";
	private static final String T_CONTACT = "Contact:";
	private static final String SCHOOL = "School:";
	private static final String EMAIL = "E-mail Address:";
	private static final String T_NUM ="Team Number:";
	private static final String CATEGORY = "Category(Secondary School,Tertiary):";
	private static final String CHESS = "Chess:";
	private static final String[] CONSTANTSARR = {T_I_C,T_CONTACT,SCHOOL,EMAIL,T_NUM,CATEGORY,CHESS};
	//Global Variables
	FileInputStream word;
	FileInputStream xcel;
	File xcel1;
	public  ArrayList<String> stuff = new ArrayList<String>();
	public  ArrayList<Object> stuff1 = new ArrayList<Object>();
	XWPFDocument doc;
	LinkedList<XWPFParagraph> test;
	ArrayList<XWPFTable> test1;
	XSSFWorkbook excel;
	ExecutorService excutor = Executors.newCachedThreadPool();
	public ThreadsHandler(File wordDoc,File excelDoc){
		try {
			xcel1= excelDoc;
			word = new FileInputStream(wordDoc);
			XWPFDocument doc = new XWPFDocument(word);
			 xcel = new FileInputStream(excelDoc);
			excel = new XSSFWorkbook(xcel);
			test = new LinkedList<XWPFParagraph>(doc.getParagraphs());
			test1 = new ArrayList<XWPFTable>(doc.getTables());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		DocHandler doc = new DocHandler(test,CONSTANTSARR);
		Table_Handler table = new Table_Handler(test1);
		ExcelHandler excelHandle = new ExcelHandler(excel,xcel,xcel1);
		Future<ArrayList<String>> future = excutor.submit(doc);
		Future<ArrayList<Object>> future1 = excutor.submit(table);
		try {
			stuff = future.get();
			stuff1 = future1.get();
			stuff1.add(0,new Object[]{"Header",stuff.toArray()});
	    	EntityCreation create = new EntityCreation(stuff1);
	    	create.sort();
			excelHandle.writeToSheet0(create.getSchool());
			excelHandle.writeToSheet1(create.getMembers());
			
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			xcel.close();
			word.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		excutor.shutdownNow();
	    try {
			if (!excutor.awaitTermination(100, TimeUnit.MICROSECONDS)) {
			    System.out.println("Still waiting...");
			    System.exit(0);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    System.out.println("Exiting normally...");
	}
}
