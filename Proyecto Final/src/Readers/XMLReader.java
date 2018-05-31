package Readers;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class XMLReader implements IReader{	
	private ArrayList<String> tokens;
	private  int vuelta=0;
	public XMLReader(){
		tokens=new ArrayList<String>();
	}
	
	@Override
	public ArrayList<String> readFile(String path) {
		try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;               
            while((line = br.readLine()) != null) {		
                if (!(line.trim().isEmpty())) {                    
                    addWords(line.split("[<][^>]*[>]"));                	
                }                
            }
                
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TXTReader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(TXTReader.class.getName()).log(Level.SEVERE, null, ex);
        }
		return tokens;
	}
	  
	  private void addWords(String [] s){		 
		for(int i=0;i<s.length;i++){
			String [] wordsOnly=s[i].split("[^\\w+'*]+");
			for(int j=0;j<wordsOnly.length;j++){				
				if(!(wordsOnly[j].equals(""))){
					tokens.add(wordsOnly[j]);
				}
			}
		}		
	  }
	  
}
