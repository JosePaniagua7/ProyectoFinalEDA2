package Searchers;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

import Comparators.responseComparator;
import dataContainer.dataBank;
import dataContainer.searchResponse;
import dataContainer.wordInformation;

public class Searcher implements ISearch{
	private TreeSet<searchResponse> response;
	public Searcher(){
		this.response=new TreeSet<searchResponse>(new responseComparator()); 
	}
	@Override
	public TreeSet<searchResponse> search(String word,dataBank db) {
		// TODO Auto-generated method stub
		//Limpiamos nuestro array de respueastas para evitar que se mezclen búsquedas
		response.clear();
		HashMap<String,wordInformation> data=db.getData();
		if(data.containsKey(word)){
			wordInformation temp=data.get(word);
			for(Map.Entry<String, Integer> entry : temp.getWordData().entrySet()) {
				String key = entry.getKey();
			    Integer value = entry.getValue();
			    //System.out.println("Aparece en: "+key);
			    double tfidf=value*temp.getIdf();
			    //System.out.println("El tfidf es: "+tfidf);
			    searchResponse res=new searchResponse(key,tfidf);
			    response.add(res);			    
			}
		}
		return response;
	}

}
