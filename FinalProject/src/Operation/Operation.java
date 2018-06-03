package Operation;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet;

import Enumerados.fileType;
import Indexers.IIndexer;
import Indexers.Indexer;
import Parser.IParser;
import Parser.Parser;
import Readers.IReader;
import Searchers.ISearch;
import Searchers.Searcher;
import dataContainer.dataBank;
import dataContainer.searchResponse;
import factories.fileTypeFactory;

public class Operation implements IOperation {
	//Instanciamos una clase dataBank que es en donde tenemos almacenada 
	//toda nuestra información previamente guardada de nuestros archivos/palabras
	//Esta variable será global por que tanto el Indexer como el searh 
	//utilizarán nuestro banco de datos
	private dataBank db;
	//Nombre del archivo en el que almacenaremos toda nuestra información 
	String fileName;
	public Operation() {
		fileName="data.ser";
		loadData();
	}

	@Override
	public void saveData() {
		// TODO Auto-generated method stub
		try {
			//Creamos un FileOutPutStream y le pasamos el nombre del file
			FileOutputStream file=new FileOutputStream(fileName);
			//Creamos un ObjectOutputStream y le pasamos el FileOuputStream
			ObjectOutputStream out=new ObjectOutputStream(file);			
			//Metodo para serializar 
			out.writeObject(db);
			out.close(); 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Problemas guardando la data");
			e.printStackTrace();
		}
	}

	@Override
	public void loadData() {
		// TODO Auto-generated method stub		
		try {
			FileInputStream fileIn = new FileInputStream(fileName);
			ObjectInputStream in=new ObjectInputStream(fileIn);
			db=(dataBank) in.readObject();
			in.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Problemas cargando la data");
			e.printStackTrace();
		} catch(IOException n){
			//Sino encuentra el archivo, entonces cargamos uno nuevo en memoria
			db=new dataBank();
		}
			
		
	}
	
	@Override
	public ArrayList<String> search(String s) {
		// TODO Auto-generated method stub
		ArrayList<String> response=new ArrayList<String>();
		ISearch searcher=new Searcher(); 
		TreeSet<searchResponse> searcherResponse=searcher.search(s, this.db);
		Iterator<searchResponse> itr=searcherResponse.iterator();
		while(itr.hasNext()){
		    searchResponse c=itr.next();
		    //Code to add a new element to the TreeSet ts
		    System.out.println("Aparece en: "+c.getPath());
		    response.add(c.getPath());
		}		
		return response;
	}

	@Override
	public void index(String path) {
		// TODO Auto-generated method stub
		// Creamos una referencia hacia nuesta interfaz indexer
		IIndexer indexer = new Indexer();
		// Creamos una referencia a nuestra interfaz reader, y con el patrón
		// factory, le asignamos una clase, dependiendo del tipo de archio a
		// leer
		IReader reader = fileTypeFactory.getReader(getTypeOfFileEnum(path));
		//Intanciamos una interfaz de tipo Parser, la cual posteriormente 
		//Resolveremos con depencence inyection 
		IParser parser=new Parser();
		
		//Llamamos al método index de nuestra interfaz indexer 
		indexer.Index(path, reader, parser, db);

	}

	private fileType getTypeOfFileEnum(String p) {
		String extention = p.split("\\.")[1];
		switch (extention) {
		case "pdf":
			return fileType.pdf;
		case "txt":
			return fileType.txt;
		case "doc":
			return fileType.doc;
		case "docx":
			return fileType.docx;
		case "xml":
			return fileType.xml;
		default:
			return null;
		}

	}

	

	

}
