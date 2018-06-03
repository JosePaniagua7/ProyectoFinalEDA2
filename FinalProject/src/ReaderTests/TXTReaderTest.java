package ReaderTests;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import Readers.TXTReader;


public class TXTReaderTest {
	@Test
	public void readTest() {
		// Construimos una palabra con todas mis palabras que deben estar dentro
		// de mi arrayList devuelto por mi método Read de los readers
		ArrayList<String> expected = new ArrayList<String>();
		expected.add("prueba");
		expected.add("palabra");
		expected.add("con");
		expected.add("apostrofe");
		expected.add("la");
		expected.add("palabra");
		expected.add("es");
		expected.add("can’t");
		expected.add("hola");
		expected.add("mundo");
		
		//Intanciamos un nuevo Reader 
		TXTReader pdf=new TXTReader(); 
		ArrayList<String> received=pdf.readFile("C:\\Users\\Jose Paniagua\\workspace\\Proyecto Final\\src\\ToTestReadersFiles\\prueba.txt");
		received.forEach((String s)->{
			System.out.println(s);
		});
		
		Assert.assertEquals(expected, received);
	}

}
