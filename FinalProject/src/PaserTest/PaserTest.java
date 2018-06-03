package PaserTest;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;

import Parser.Parser;
import junit.framework.Assert;

public class PaserTest {
	@Test
	public void parserTest(){
		ArrayList<String> parametersParser=new ArrayList<String>();			
		parametersParser.add("Hola");
		parametersParser.add("Mundo");
		parametersParser.add("Hola");
		parametersParser.add("Jose");
		parametersParser.add("Hola");
		parametersParser.add("Fernando");
		
		HashMap<String,Integer> expected=new HashMap<String,Integer>();
		expected.put("Hola",3);
		expected.put("Mundo",1);
		expected.put("Jose",1);
		expected.put("Fernando",1);
		
		//Instancaimos nuestra clase a probar
		Parser p=new Parser();		
		HashMap<String,Integer> received=p.Parse(parametersParser);	

		//Comparamos el recivido con el esperado
		Assert.assertEquals(expected, received);
	}
}
