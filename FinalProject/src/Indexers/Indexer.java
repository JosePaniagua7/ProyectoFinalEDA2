package Indexers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Parser.IParser;
import Readers.IReader;
import dataContainer.dataBank;
import dataContainer.wordInformation;

public class Indexer implements IIndexer {
	public Indexer() {

	}

	@Override
	public dataBank Index(String path,IReader reader, IParser parser, dataBank db) {
		// TODO Auto-generated method stub
		ArrayList<String> words=reader.readFile(path);
		HashMap<String,Integer> toIndex=parser.Parse(words);
		HashMap<String,wordInformation> data=db.getData();
		wordInformation wI;
		//Añado un file a mi lista de indexed files de mi dataBank
		db.addIndexedFile(path);
		
		for (Map.Entry<String, Integer> entry : toIndex.entrySet()) {
			if(data.containsKey(entry.getKey())){
				wI=data.get(entry.getKey());
			}else{
				wI=new wordInformation();
			}
			
			//Añadimos un nuevo Indexed file con su respectivo token
			wI.addIndexedFile(path, entry.getValue());
			//Cada vez que se añada un nuevo fichero indexado tendremos 
			//que volver a setear el IDF
			wI.setIdf(db.getNumberOfIndexedFiles());
			//Hago un push nuevamente al map de datos para actulizar su 
			//valor 
			data.put(entry.getKey(), wI);				
		}
		
		//Hago un push a mi banco de datos con los datos acualizados 
		db.setData(data);
		return db;
	}

}
