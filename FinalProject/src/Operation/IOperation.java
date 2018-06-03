package Operation;

import java.util.ArrayList;

public interface IOperation {
	//M�todo que me servir� para devolver en un arrayList todos los 
	//path de los archivos en los que aparece mi palabra
	ArrayList<String> search(String s);
	//M�todo que indexar� mi informaci�n
	void index(String path);
	//M�todo que guardar� y serializar� mi objeto dataBank que es 
	//en donde se encuentran almacenadas todas mis palabras
	void saveData();
	//Metodo en donde se cargar�n nuevamente todas las palabras 
	//previamente indexadas
	void loadData();
}
