package Comparators;

import java.util.Comparator;

import dataContainer.searchResponse;

public class responseComparator implements Comparator{

	@Override
	public int compare(Object arg0, Object arg1) {
		// TODO Auto-generated method stub
		searchResponse first=(searchResponse) arg0;
		searchResponse second=(searchResponse) arg1;
		if(first.getTfidf()>=second.getTfidf()){
			return 1;
		}else if(first.getTfidf()<second.getTfidf()){
			return -1;
		}else{
			return 0;
		}
		
	}

}
