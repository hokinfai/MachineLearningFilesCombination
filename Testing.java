package MachineLearningFilesCombination;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import NERsuiteDictionaryTagger.FormatDictionary;
import NERsuiteDictionaryTagger.FormatTSV;

public class Testing {
	public static void main(String args[]) throws IOException {
		String path = "/Users/AlanHo/Documents/DissertationLibrary/gold standard/with chunk1";
		String tsv="/Users/AlanHo/Documents/DissertationLibrary/gold standard/entities1";
		// new Combination("1", "2").combine();

		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();
		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				if (listOfFiles[i].getName().endsWith(".txt")) {
					String tsvPath = tsv + "/" + listOfFiles[i].getName().replace(".txt", ".tsv");
					String txtPath = path + "/" + listOfFiles[i].getName();
					System.out.println(txtPath);
					System.out.println(tsvPath);
					new Combination(tsvPath, txtPath).combine();

				}

			}
		}

	}

}
