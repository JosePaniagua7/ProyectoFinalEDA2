/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Readers;

import java.io.FileInputStream;
import java.util.ArrayList;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;



/**
 *
 * @author Jose Paniagua
 */
public class WORDReader implements IReader{
    private ArrayList<String> tokens; 
    public WORDReader(){
        tokens=new ArrayList<String>();
    }

    @Override
    public ArrayList<String> readFile(String path) {   
		//Limpio mi array de tokens para asegurarnos que los index 
		//de otra iteración no se mezclen 
		tokens.clear();
        try{
            FileInputStream file = new FileInputStream(path);
            HWPFDocument doc = new HWPFDocument(file);
            WordExtractor extractor= new WordExtractor(doc);
            String text = extractor.getText();
            addWords(text);
        }catch (Exception exep){
            exep.printStackTrace();
        }
        return tokens;
    }
    
    private void addWords(String l){	       
	       String[] palabras=l.split("[^\\w+'*|’]+");
	       for(int i=0;i<palabras.length;i++){
	           tokens.add(palabras[i].toLowerCase());
	       }
	    } 
}
