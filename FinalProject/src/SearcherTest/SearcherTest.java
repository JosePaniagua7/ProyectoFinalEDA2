package SearcherTest;

import java.util.Iterator;
import java.util.TreeSet;

import org.junit.Test;

import Comparators.responseComparator;
import Indexers.Indexer;
import Parser.IParser;
import Parser.Parser;
import Readers.IReader;
import Readers.WORDXReader;
import Searchers.ISearch;
import Searchers.Searcher;
import dataContainer.dataBank;
import dataContainer.searchResponse;
import junit.framework.Assert;

public class SearcherTest {
	@Test 
	public void searchTest(){
		//Lo primero que haré para mi test es indexar algunos documentos en un dataBank 
		//Creamos nuestro mock, probaremos con una supuesta clase de lector de docx		
		//IReader readerMock=mock(WORDXReader.class);
		IReader reader=new WORDXReader();
		//Instanciamos un parser 
		IParser parser=new Parser(); 
		//Instanciamos nuestro indexer que será el que vamos a probar 
		Indexer i=new Indexer(); 
		//Declaramos un nuevo dataBank que será el recivido y será igual 
		//a lo que indexe nuestro indexer
		dataBank db=new dataBank();
		//Mandamos a indexar nuestros 3 documentos
		
		db=i.Index("C:\\Users\\Jose Paniagua\\Desktop\\ToIndexFiles\\doc1.docx", reader, parser, db);
		db=i.Index("C:\\Users\\Jose Paniagua\\Desktop\\ToIndexFiles\\doc2.docx", reader, parser, db);
		db=i.Index("C:\\Users\\Jose Paniagua\\Desktop\\ToIndexFiles\\doc3.docx", reader, parser, db);
		
		//Ahora que ya tenemos un banco de datos, vamos a proceder a la búsqueda de mi palabra 
		//Instanciamos nuestro Searcher 
		ISearch searcher=new Searcher(); 
		TreeSet<searchResponse> received=searcher.search("hola",db);
	
		
		//Instanciamos el objeto que esperamos recibir
		TreeSet<searchResponse> expected=new TreeSet<searchResponse>(new responseComparator());
		//Construimos nuestro expected paramet
		searchResponse res=new searchResponse("C:\\Users\\Jose Paniagua\\Desktop\\ToIndexFiles\\doc1.docx",0.3010299956639812);
		//Añadimos nuestro res 
		expected.add(res);
		//Construimos nuestro expected paramet
		searchResponse res2=new searchResponse("C:\\Users\\Jose Paniagua\\Desktop\\ToIndexFiles\\doc3.docx",0.3010299956639812);
		//Añadimos nuestro res 
		expected.add(res2);
		
		
		
		Iterator<searchResponse> itr=expected.iterator();
		while(itr.hasNext()){
		    searchResponse c=itr.next();
		    //Code to add a new element to the TreeSet ts
		    System.out.println("Aparece en: "+c.getPath());
		    System.out.println("Con TF-IDF: "+c.getTfidf());
		}
		System.out.println("-------------------------");
		Iterator <searchResponse> receivedIterator=received.iterator();
		while(receivedIterator.hasNext()){			
			searchResponse m=receivedIterator.next();
			System.out.println("Aparece en: "+m.getPath());
		    System.out.println("Con TF-IDF: "+m.getTfidf());
		}
		
		//Comparamos el received con el expected 
		Assert.assertEquals(expected.getClass(), received.getClass());
		
	}
}
