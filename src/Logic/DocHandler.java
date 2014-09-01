package Logic;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.Callable;

import org.apache.poi.xwpf.usermodel.XWPFParagraph;

public class DocHandler implements Callable<ArrayList<String>>{
	//global variables needed(because I am a lousy programmer)
	//FileInputStream excel = new FileInputStream(excelDoc);
	LinkedList<XWPFParagraph> test;
	String[] CONSTANTSARR;
	ArrayList<String> stuff = new ArrayList<String>();
	public DocHandler(LinkedList<XWPFParagraph> zxc,String[] CONSTANTSARR){
		test = zxc;
		this.CONSTANTSARR = CONSTANTSARR;
	}
	@Override
	public ArrayList<String> call() {
		// TODO Auto-generated method stub
		paraEval(test);
		return stuff;
	}
	//gets Strings of the entire document
	//checking of variables to see if they match what we are looking for
	//check how many constants a line has
	public void paraEval(LinkedList<XWPFParagraph> test){
		for(int i=0; test.size()>i;i++){
			String line = test.get(i).getParagraphText();
			//System.out.println(line);
			evaulateText(line);
		}
	}
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
			stuff.add((line.substring(line.indexOf(":")+1).trim().replaceAll("_", "")));
		}else{
			
		}
	}
	public String[] getConstantsArr(){
		return CONSTANTSARR;
	}
}
