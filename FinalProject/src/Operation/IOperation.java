package Operation;

import java.util.ArrayList;

public interface IOperation {
	//Método que me servirá para devolver en un arrayList todos los 
	//path de los archivos en los que aparece mi palabra
	ArrayList<String> search(String s);
	//Método que indexará mi información
	void index(String path);
	//Método que guardará y serializará mi objeto dataBank que es 
	//en donde se encuentran almacenadas todas mis palabras
	void saveData();
	//Metodo en donde se cargarán nuevamente todas las palabras 
	//previamente indexadas
	void loadData();
}
