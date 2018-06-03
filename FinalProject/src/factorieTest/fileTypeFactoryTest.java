package factorieTest;

import org.junit.Test;

import Enumerados.fileType;
import Readers.IReader;
import Readers.PDFReader;
import Readers.TXTReader;
import Readers.WORDReader;
import Readers.WORDXReader;
import Readers.XMLReader;
import factories.fileTypeFactory;
import junit.framework.Assert;

public class fileTypeFactoryTest {
	IReader expected;
	IReader received;
	@Test
	public void getPDFReader(){
		//Instanciamos una clase que esperamos recibir
		expected=new PDFReader(); 
		received=fileTypeFactory.getReader(fileType.pdf);
		Assert.assertEquals(expected.getClass(), received.getClass());
		
	}
	
	@Test
	public void getTXTReader(){
		//Instanciamos una clase que esperamos recibir
		expected=new TXTReader(); 
		received=fileTypeFactory.getReader(fileType.txt);
		Assert.assertEquals(expected.getClass(), received.getClass());
	}
	
	@Test
	public void getWORDReader(){
		//Instanciamos una clase que esperamos recibir
		expected=new WORDReader(); 
		received=fileTypeFactory.getReader(fileType.doc);
		Assert.assertEquals(expected.getClass(), received.getClass());
	}
	
	@Test
	public void getWORDXReader(){
		//Instanciamos una clase que esperamos recibir
		expected=new WORDXReader(); 
		received=fileTypeFactory.getReader(fileType.docx);
		Assert.assertEquals(expected.getClass(), received.getClass());
	}
	
	@Test
	public void getXMLReader(){
		//Instanciamos una clase que esperamos recibir
		expected=new XMLReader(); 
		received=fileTypeFactory.getReader(fileType.xml);
		Assert.assertEquals(expected.getClass(), received.getClass());
	}
}
