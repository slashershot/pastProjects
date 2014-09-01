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

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFTable;

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
	public  ArrayList<String> stuff = new ArrayList<String>();
	public  ArrayList<Object> stuff1 = new ArrayList<Object>();
	//FileInputStream excel = new FileInputStream(excelDoc);
	XWPFDocument doc;
	LinkedList<XWPFParagraph> test;
	ArrayList<XWPFTable> test1;
	ExecutorService excutor = Executors.newCachedThreadPool();
	public ThreadsHandler(File wordDoc,File excel){
		try {
			word = new FileInputStream(wordDoc);
			XWPFDocument doc = new XWPFDocument(word);
			test = new LinkedList<XWPFParagraph>(doc.getParagraphs());
			test1 = new ArrayList<XWPFTable>(doc.getTables());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//FileInputStream excel = new FileInputStream(excelDoc);
		DocHandler doc = new DocHandler(test,CONSTANTSARR);
		Table_Handler table = new Table_Handler(test1);
		Future<ArrayList<String>> future = excutor.submit(doc);
		Future<ArrayList<Object>> future1 = excutor.submit(table);
		try {
			stuff = future.get();
			stuff1 = future1.get();
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
