package MachineLearningFilesCombination;

import java.io.File;

import java.io.IOException;

public class Testing {
	public static void main(String args[]) throws IOException {
		String path = "/Users/AlanHo/Documents/DissertationLibrary/documents/gold standard/with chunk";
		String tsv = "/Users/AlanHo/Documents/DissertationLibrary/documents/gold standard/entities";
		String outputPath = "/Users/AlanHo/Documents/DissertationLibrary/documents/gold standard/Combination/";
		System.out.println("abc");
		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();
		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				if (listOfFiles[i].getName().endsWith(".txt")) {

					String tsvPath = tsv + "/" + listOfFiles[i].getName().replace(".txt", ".tsv");
					String txtPath = path + "/" + listOfFiles[i].getName();
					System.out.println();
					System.out.println(txtPath);
					// System.out.println(tsvPath);
					Combination com = new Combination(tsvPath, txtPath);
					com.combine();
					com.fileWriter(outputPath, listOfFiles[i].getName());

				}

			}
		}

	}

}
