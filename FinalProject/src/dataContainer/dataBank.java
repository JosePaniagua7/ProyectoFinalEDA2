package dataContainer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class dataBank implements Serializable{
	private HashMap<String,wordInformation> data;
	private ArrayList<String> indexedFiles;
	public dataBank(){
		this.data=new HashMap<String,wordInformation>();
		this.indexedFiles=new ArrayList<String>();
	}
	
	public HashMap<String,wordInformation> getData(){
		return data;
	}
	
	public void addWord(String word,wordInformation w){
		this.data.put(word, w);
	}
	
	public void setData(HashMap<String,wordInformation> s){
		this.data=s;
	}
	
	public void addIndexedFile(String p){
		this.indexedFiles.add(p);
	}
	
	public ArrayList<String> getIndexedFiles(){
		return this.indexedFiles;
	}
	
	public int getNumberOfIndexedFiles(){
		return this.indexedFiles.size();
	}
	
}
