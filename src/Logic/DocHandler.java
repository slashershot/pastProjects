package Logic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFTable;

public class DocHandler {
	
	public static void extract(File wordDoc,File excelDoc){
		try {
			FileInputStream word = new FileInputStream(wordDoc);
			//FileInputStream excel = new FileInputStream(excelDoc);
			XWPFDocument doc = new XWPFDocument(word);
			LinkedList<XWPFParagraph> test = new LinkedList<XWPFParagraph>(doc.getParagraphs());
			LinkedList<XWPFTable> test1 = new LinkedList<XWPFTable>(doc.getTables());
			for(int i=0; test.size()>i;i++){
				System.out.println(test.get(i).getParagraphText());
			}
			System.out.println(test1.size());
			for(int i=0; test1.size()>i;i++){
				System.out.println(test1.get(i).getText());
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
