package Searchers;

import java.util.TreeSet;

import dataContainer.dataBank;
import dataContainer.searchResponse;

public interface ISearch {
	public TreeSet<searchResponse> search(String word,dataBank db);
}
