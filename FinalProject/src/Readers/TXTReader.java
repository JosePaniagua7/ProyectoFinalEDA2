/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Readers;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jose Paniagua
 */
public class TXTReader implements IReader{
    private ArrayList<String> tokens;
    public TXTReader(){
        tokens=new ArrayList<String>();
    }
    
    @Override
    public ArrayList<String> readFile(String path) {    
		//Limpio mi array de tokens para asegurarnos que los index 
		//de otra iteración no se mezclen 
		tokens.clear();
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;               
            while((line = br.readLine()) != null) {		
                if (!(line.trim().isEmpty())) {                    
                    addWords(line);
                }
                
            }
                
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TXTReader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(TXTReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tokens;
    }
    
    private void addWords(String l){       
       String[] palabras=l.split("[^\\w+'*|’]+");
       for(int i=0;i<palabras.length;i++){
    	   if(!(palabras[i].equals(""))){
    		   tokens.add(palabras[i].toLowerCase());
    	   }           
       }
    }
}
