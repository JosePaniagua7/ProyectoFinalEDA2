package IndexersTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Test;
import org.mockito.Mockito;

import Indexers.Indexer;
import Parser.IParser;
import Parser.Parser;
import Readers.IReader;
import Readers.WORDXReader;
import dataContainer.dataBank;
import dataContainer.wordInformation;
import junit.framework.Assert;


public class IndexerTest {
	//Declaramos 3 arrayLists los cuales nos ayudarán con el mocking a simular la respuesta de la clase IReader
	ArrayList<String> doc1=new ArrayList<String>();
	ArrayList<String> doc2=new ArrayList<String>();
	ArrayList<String> doc3=new ArrayList<String>();

	@Test 
	public void indexerTest(){
		
		//Lo primero que haremos para probar el indexer es que vamos a crear nuestro dataBank 
		//esperado 
		//Para ello necesitamos crear objectos del tipo dataBank 
		dataBank expected=new dataBank();
		
		//De igual manera necesitamos llenar nuestro banco de datos esperado		
		populateDataBank(expected);		
		
		//Llenamos nuestros ArrayLists auxiliares para simular la respuesta de la clase IReader
		//populateArrays();
		
		//Creamos nuestro mock, probaremos con una supuesta clase de lector de docx		
		IReader readerMock=Mockito.mock(WORDXReader.class);
		Mockito.when(readerMock.readFile("C:\\Users\\Jose Paniagua\\Desktop\\ToIndexFiles\\doc1.docx")).thenReturn(doc1);
		
		IReader readerMock1=Mockito.mock(WORDXReader.class);
		Mockito.when(readerMock.readFile("C:\\Users\\Jose Paniagua\\Desktop\\ToIndexFiles\\doc1.docx")).thenReturn(doc2);
		
		IReader readerMock2=Mockito.mock(WORDXReader.class);
		Mockito.when(readerMock.readFile("C:\\Users\\Jose Paniagua\\Desktop\\ToIndexFiles\\doc1.docx")).thenReturn(doc3);
		
		IReader reader=new WORDXReader();
		//Instanciamos un parser 
		IParser parser=new Parser(); 
		//Instanciamos nuestro indexer que será el que vamos a probar 
		Indexer i=new Indexer(); 
		//Declaramos un nuevo dataBank que será el recivido y será igual 
		//a lo que indexe nuestro indexer
		dataBank received=new dataBank();
		//Mandamos a indexar nuestros 3 documentos
		
		received=i.Index("C:\\Users\\Jose Paniagua\\Desktop\\ToIndexFiles\\doc1.docx", reader, parser, received);
		received=i.Index("C:\\Users\\Jose Paniagua\\Desktop\\ToIndexFiles\\doc2.docx", reader, parser, received);
		received=i.Index("C:\\Users\\Jose Paniagua\\Desktop\\ToIndexFiles\\doc3.docx", reader, parser, received);
		
		System.out.println("archivos expected: "+expected.getNumberOfIndexedFiles());
		HashMap<String,wordInformation> dataExpected=expected.getData();
		for(String s:dataExpected.keySet()){
			System.out.println("---------------------");
			System.out.println("Las claves son: "+s);
			HashMap<String,Integer> wordData=dataExpected.get(s).getWordData();
			for(String m:wordData.keySet()){
				System.out.println("Fichero: "+m);
				System.out.println("tokens: "+wordData.get(m));
			}
		}
		
		System.out.println("archivos received: "+received.getNumberOfIndexedFiles());
		HashMap<String,wordInformation> dataReceived=received.getData();
		for(String s:dataReceived.keySet()){
			System.out.println("---------------------");
			System.out.println("Las claves son: "+s);
			HashMap<String,Integer> wordData2=dataReceived.get(s).getWordData();
			for(String m:wordData2.keySet()){
				System.out.println("Fichero: "+m);
				System.out.println("tokens: "+wordData2.get(m));
			}
		}
		
		//Ahora comparamos los data Bank recibidos vs el esperado
		Assert.assertEquals(expected.getClass(), received.getClass());
		
	}
	
	

	private void populateDataBank(dataBank expected){
		
		expected.addIndexedFile("C:\\Users\\Jose Paniagua\\Desktop\\ToIndexFiles\\doc1.docx");
		expected.addIndexedFile("C:\\Users\\Jose Paniagua\\Desktop\\ToIndexFiles\\doc2.docx");
		expected.addIndexedFile("C:\\Users\\Jose Paniagua\\Desktop\\ToIndexFiles\\doc3.docx");
		
		wordInformation wI=new wordInformation();		
		wI.addIndexedFile("C:\\Users\\Jose Paniagua\\Desktop\\ToIndexFiles\\doc1.docx", 1);
		wI.addIndexedFile("C:\\Users\\Jose Paniagua\\Desktop\\ToIndexFiles\\doc3.docx", 1);
		wI.setIdf(expected.getNumberOfIndexedFiles());
		expected.addWord("hola", wI);
		
		wI=new wordInformation();
		wI.addIndexedFile("C:\\Users\\Jose Paniagua\\Desktop\\ToIndexFiles\\doc1.docx", 1);
		wI.setIdf(expected.getNumberOfIndexedFiles());
		expected.addWord("mundo", wI);
		
		wI=new wordInformation();
		wI.addIndexedFile("C:\\Users\\Jose Paniagua\\Desktop\\ToIndexFiles\\doc2.docx", 1);
		wI.addIndexedFile("C:\\Users\\Jose Paniagua\\Desktop\\ToIndexFiles\\doc3.docx", 1);
		wI.setIdf(expected.getNumberOfIndexedFiles());
		expected.addWord("jose", wI);
		
		wI=new wordInformation();
		wI.addIndexedFile("C:\\Users\\Jose Paniagua\\Desktop\\ToIndexFiles\\doc2.docx", 1);
		wI.setIdf(expected.getNumberOfIndexedFiles());
		expected.addWord("paniagua", wI);
	}
	
	private void populateArrays(){
		doc1.add("hola");
		doc1.add("mundo");
		
		doc2.add("jose");
		doc2.add("paniagua");

		doc3.add("hola");
		doc3.add("jose");
	}
}
