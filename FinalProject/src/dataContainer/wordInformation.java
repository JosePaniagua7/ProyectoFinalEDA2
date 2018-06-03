package dataContainer;

import java.io.Serializable;
import java.util.HashMap;

public class wordInformation implements Serializable{
	//En este caso la clave es el path del documento y la clave es la cantidad de veces que aparece allí
	private HashMap<String,Integer> wordByFile;
	private double Idf;
	public wordInformation(){
		this.wordByFile=new HashMap<String,Integer>();
		this.Idf=0;
	}
	
	public boolean appearInFile(String p){
		if(wordByFile.containsKey(p)){
			return true;
		}else{
			return false;
		}
	}
	
	public void addIndexedFile(String p,Integer i){
		//Cada vez que se añada un Fichero indexado 
		//Tendremos que volver a calcular el IDF		
		this.wordByFile.put(p,i);		
	}
	
	public double getIdf() {
		return Idf;
	}
	
	public void setIdf(int s) {
		double toCalculate=(s/wordByFile.size())+1;
		 this.Idf=Math.log10(toCalculate);
	}
	
	public HashMap<String,Integer> getWordData(){
		return this.wordByFile;
	}
	
}
