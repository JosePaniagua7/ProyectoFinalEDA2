package View;

import java.util.ArrayList;

import Readers.PDFReader;
import Readers.TXTReader;
import Readers.WORDReader;
import Readers.WORDXReader;
import Readers.XMLReader;

public class program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*MainView m=new MainView(); 
        m.setVisible(true);*/
        
        //Voy a probar, a ver que me devuelve mi PDFReader
        /*PDFReader pdfReader=new PDFReader();
        ArrayList<String> res=pdfReader.readFile("C:\\Users\\Jose Paniagua\\Desktop\\Documentos de Prueba\\prueba.pdf");        
        res.forEach((String s) ->{
            System.out.println(s);
        });
        System.out.println("El tamaño es: "+res.size());*/
        
        
        //Prueba de lectura de txt 
        /*TXTReader txtReader=new TXTReader(); 
        ArrayList<String> res=txtReader.readFile("C:\\Users\\Jose Paniagua\\Documents\\NetBeansProjects\\Proyecto Final EDAII\\src\\ToReadFiles\\version_control.txt"); 
        res.forEach((String s) -> {
            System.out.println(s);
        });
        System.out.println("El tamaño es: "+res.size());*/
        
        //Prueba para lctura de .doc 
        /*WORDReader wR=new WORDReader();
        ArrayList<String> res=wR.readFile("C:\\Users\\Jose Paniagua\\Desktop\\Documentos de Prueba\\word2003.doc");
        res.forEach((String s) -> {
            System.out.println(s);
        });
        System.out.println("El tamaño es: "+res.size());*/
		
		//Prueba para la lectura de .docx		
		/*WORDXReader wR=new WORDXReader();
		ArrayList<String> res=wR.readFile("C:\\Users\\Jose Paniagua\\Desktop\\Documentos de Prueba\\prueba.docx");
        res.forEach((String s) -> {
            System.out.println(s);
        });
        System.out.println("El tamaño es: "+res.size());*/		
        
		//Prueba para la lectura de .XML
		/*XMLReader xR=new XMLReader(); 
		ArrayList<String> res=xR.readFile("C:\\Users\\Jose Paniagua\\Desktop\\Documentos de Prueba\\version_control.xml");
		res.forEach((String s) -> {
            System.out.println(s);
        });
        System.out.println("El tamaño es: "+res.size());*/
		
	}

}
