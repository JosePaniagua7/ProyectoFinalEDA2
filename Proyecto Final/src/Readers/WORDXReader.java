package Readers;

import java.io.FileInputStream;
import java.util.ArrayList;

import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;


public class WORDXReader implements IReader{
	private ArrayList<String> tokens;
	public WORDXReader(){
		tokens=new ArrayList<String>();
	}
	
	@Override
	public ArrayList<String> readFile(String path) {
		try{           
            XWPFDocument docx = new XWPFDocument(new FileInputStream(path));
            //using XWPFWordExtractor Class
            XWPFWordExtractor we = new XWPFWordExtractor(docx);
            addWords(we.getText());
        }
        catch (Exception exep){
            exep.printStackTrace();
        }
		return tokens;
	}
	
	private void addWords(String l){
	       //String[] palabras=l.split("\\W+|\\n+|\\s+");
	       String[] palabras=l.split("[^\\w+'*]+");
	       for(int i=0;i<palabras.length;i++){
	           tokens.add(palabras[i]);
	       }
	    }
	
}
