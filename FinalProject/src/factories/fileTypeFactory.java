package factories;

import Enumerados.fileType;
import Readers.IReader;
import Readers.PDFReader;
import Readers.TXTReader;
import Readers.WORDReader;
import Readers.WORDXReader;
import Readers.XMLReader;

public class fileTypeFactory {
	
	public static IReader getReader(fileType f){
		switch(f){
			case pdf:
				return new PDFReader();				
			case txt:
				return new TXTReader(); 
				
			case doc:		
				return new WORDReader(); 
				
			case docx:		
				return new WORDXReader();
				
			case xml:		
				return new XMLReader();
			
			default: 
				return null;						
			}				
		}	
	}


