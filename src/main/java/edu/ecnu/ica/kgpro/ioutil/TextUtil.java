package edu.ecnu.ica.kgpro.ioutil;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import edu.ecnu.ica.kgpro.base.Entity;
import edu.ecnu.ica.kgpro.base.Relation;
import edu.ecnu.ica.kgpro.base.Triple;

public class TextUtil {
	public static List<Triple> TripleLoader(String filename) throws Exception {
		List<Triple> list = new ArrayList<>();
		FileReader reader = new FileReader(filename);
		BufferedReader bReader = new BufferedReader(reader);
		String line = bReader.readLine();
		while(line != null) {
			String[] content = line.split("\t");
			Triple t = new Triple(new Entity(content[0]), new Relation(content[1]), new Entity(content[2]));
			list.add(t);
			line = bReader.readLine();
		}
		return list;
	}
}
