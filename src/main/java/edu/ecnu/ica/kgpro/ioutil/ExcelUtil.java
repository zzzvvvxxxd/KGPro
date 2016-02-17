package edu.ecnu.ica.kgpro.ioutil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

import org.apache.jena.atlas.iterator.Iter;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import edu.ecnu.ica.kgpro.base.Triple;
import edu.ecnu.ica.kgpro.util.TripleIterator;

public class ExcelUtil {
	
	public static TripleIterator TripleReader(String filename) {
		HSSFWorkbook wookbook;
		try {
			wookbook = new HSSFWorkbook(new FileInputStream(new File(filename)));
			HSSFSheet sheet = wookbook.getSheetAt(0);
			Iterator<Row> iterator = sheet.iterator();
			return new TripleIterator(iterator);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
