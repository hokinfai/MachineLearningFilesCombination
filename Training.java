package MachineLearningFilesCombination;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Training {
	String chunk;
	String anno;
	List<String> tsvlist = new ArrayList<String>();
	List<String> txtlist = new ArrayList<String>();
	int tsvCount = 0;
	int txtCount = 0;
	int continuity = 0;
	StringBuilder finalResult = new StringBuilder();

	public Training(String anno, String chunk) throws IOException {
		this.anno = anno;
		this.chunk = chunk;
		BufferedReader br = new BufferedReader(new FileReader(anno));
		BufferedReader br2 = new BufferedReader(new FileReader(chunk));

		try

		{

			// insert tsv file record into arraylist
			String line = br.readLine();

			while (line != null) {

				tsvlist.add(line);

				line = br.readLine();
			}
			// insert txt file record into arraylist
			String line2 = br2.readLine();
			while (line2 != null) {

				txtlist.add(line2);
				line2 = br2.readLine();
			}

		} finally

		{
			br.close();
			br2.close();
			tsvlist.add("checking	0	0	nothing	nothing	NN	B-NP");
			txtlist.removeAll(Arrays.asList(null, ""));
			finalResult.append("");
		}
	}

	public void merging() {
		if (tsvCount < tsvlist.size() && txtCount < txtlist.size()) {
			String[] annoSp = tsvlist.get(tsvCount).split("\t");
			String[] chunkSp = txtlist.get(txtCount).split("\t");

			if (Integer.parseInt(annoSp[1]) - Integer.parseInt(chunkSp[0]) <= 5
					&& (annoSp[3].contains(chunkSp[2]) || chunkSp[2].contains(annoSp[3]))
					&& annoSp[5].equals(chunkSp[4])) {
				System.out.println(tsvlist.get(tsvCount));
				finalResult.append(tsvlist.get(tsvCount) + "\n");
				continuity++;
				txtCount++;
				tsvCount++;
				if (chunkSp[2].matches("\\d\\d\\d\\d-\\d\\d\\d\\d") && annoSp[0].equals("TemporalExpression"))
					txtCount--;
				merging();
			} else {

				txtCount = txtCount - continuity;
				continuity = 0;

				if (!finalResult.toString().contains(txtlist.get(txtCount))) {
					System.out.println("O\t" + txtlist.get(txtCount));
					finalResult.append("O\t" + txtlist.get(txtCount) + "\n");
				}
				txtCount++;
				merging();
			}

		}
	}

	public void fileWriter(String outputPath, String name) throws IOException {
		File file = new File(outputPath + name);
		// creates the file
		file.createNewFile();
		// creates a FileWriter Object
		FileWriter writer = new FileWriter(file);
		// Writes the content to the file
		writer.write(finalResult.toString());
		writer.flush();
		writer.close();
	}

	public static void main(String args[]) throws IOException {
		String chunkPath = "/Users/AlanHo/Documents/DissertationLibrary/gold standard/with chunk";
		String annoPath = "/Users/AlanHo/Documents/DissertationLibrary/gold standard/Combination";
		String outputPath = "/Users/AlanHo/Documents/DissertationLibrary/gold standard/TrainingTestingData/";
		File folder = new File(chunkPath);
		File[] listOfFiles = folder.listFiles();
		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				if (listOfFiles[i].getName().endsWith(".txt")) {
					System.out.println();
					System.out.println(annoPath + "/" + listOfFiles[i].getName());

					Training train = new Training(annoPath + "/" + listOfFiles[i].getName(),
							chunkPath + "/" + listOfFiles[i].getName());
					train.merging();
					train.fileWriter(outputPath, listOfFiles[i].getName());

				}
			}
		}
	}
}
