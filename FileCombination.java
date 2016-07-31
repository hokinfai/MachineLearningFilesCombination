package MachineLearningFilesCombination;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileCombination {
	public static void main(String args[]) throws IOException {
		String path = "/Users/AlanHo/Documents/DissertationLibrary/documents/gold standard/TrainingTestingData/Testing Data1";
		StringBuilder output = new StringBuilder();
		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();
		for (int i = 0; i < listOfFiles.length; i++)

		{
			if (listOfFiles[i].isFile()) {
				if (listOfFiles[i].getName().endsWith(".txt")) {

					BufferedReader br = new BufferedReader(new FileReader(path + "/" + listOfFiles[i].getName()));
					try {
						System.out.println(path + "/" + listOfFiles[i].getName());
						System.out.println();
						String line = br.readLine();

						while (line != null) {
							System.out.println(line);
							output.append(line + "\n");
							line = br.readLine();
						}

					} finally

					{
						br.close();
						File file = new File("/Users/AlanHo/Documents/DissertationLibrary/Testing1.txt");
						// creates the file
						file.createNewFile();
						// creates a FileWriter Object
						FileWriter writer = new FileWriter(file);
						// Writes the content to the file
						writer.write(output.toString());
						writer.flush();
						writer.close();
					}
				}
			}
		}

	}
}