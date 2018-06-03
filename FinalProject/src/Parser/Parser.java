package Parser;

import java.util.ArrayList;
import java.util.HashMap;

public class Parser implements IParser {
	HashMap<String, Integer> data;

	// Constructor
	public Parser() {

	}

	@Override
	public HashMap<String, Integer> Parse(ArrayList<String> s) {
		this.data = new HashMap<String, Integer>();

		s.forEach((String word) -> {
			// Si HashMap de datos ya contiene la a palabra entoneces suma uno a
			// los tokens, sino, coloca los tokens en 1 y añade la palabra

			if (data.containsKey(word)) {
				int tokens=data.get(word)+1;
				data.put(word, tokens);
			}else{
				data.put(word, 1);
			}
		});
		return data;
	}

}
