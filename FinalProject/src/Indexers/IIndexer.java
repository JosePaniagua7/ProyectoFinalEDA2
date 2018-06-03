package Indexers;


import Parser.IParser;
import Readers.IReader;
import dataContainer.dataBank;

public interface IIndexer {
	dataBank Index(String path,IReader reader,IParser parser,dataBank db);
}
