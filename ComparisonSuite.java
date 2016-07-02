package MachineLearningFilesCombination;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ComparisonSuite {

	StringBuilder output = new StringBuilder();

	public void compare(String gold, String predict) {
		String[] goldSp = gold.split("\t");
		String[] preSp = predict.split("\t");
		String goldAnno = goldSp[0];
		String goldBeIn = goldSp[1];
		String goldEnIn = goldSp[2];
		String goldName = goldSp[3];
		String goldPOS = goldSp[5];
		String preAnno = preSp[6];
		String preBeIn = preSp[0];
		String preEnIn = preSp[1];
		String preName = preSp[2];
		String prePOS = preSp[4];
		if (goldBeIn.equals(preBeIn) && goldEnIn.equals(preEnIn) && preName.contains(goldName)
				&& goldPOS.equals(prePOS)) {
			System.out.println(goldName + " " + goldPOS + " " + goldAnno + " " + preAnno);
			output.append(goldName + " " + goldPOS + " " + goldAnno + " " + preAnno + "\n");
		}

	}

	public void addSpace() {
		output.append("\n");
	}

	public void writeFile(String path) throws IOException {
		String printing = output.toString();
		printing = printing.replaceAll("\n\n+", "\n");
		File file = new File(path);
		// creates the file
		file.createNewFile();
		// creates a FileWriter Object
		FileWriter writer = new FileWriter(file);
		// Writes the content to the file
		writer.write(printing.toString());
		writer.flush();
		writer.close();
	}

	public static void main(String args[]) throws IOException {
		List<String> goldlist = new ArrayList<String>();
		List<String> prelist = new ArrayList<String>();
		// You need to change the path to the files correspondingly
		String path = "/Users/AlanHo/Documents/DissertationLibrary/COMPARISONsuite/Machine Learning/First Version (5 Files)/";
		BufferedReader br = new BufferedReader(new FileReader(path + "19701_English_2376386_1958.txt"));
		BufferedReader br2 = new BufferedReader(new FileReader(path + "5FileResult.txt"));
		try {

			String line = br.readLine();
			while (line != null) {
				goldlist.add(line);
				line = br.readLine();
			}
			String line2 = br2.readLine();
			while (line2 != null) {
				prelist.add(line2);
				line2 = br2.readLine();
			}
		} finally {
			br.close();
			br2.close();
			goldlist.removeAll(Arrays.asList(null, ""));
			prelist.removeAll(Arrays.asList(null, ""));
		}

		ComparisonSuite com = new ComparisonSuite();

		System.out.println(goldlist.size() + " " + prelist.size());
		for (int i = 0; i < prelist.size(); i++) {
			for (int j = i; j <= i + (goldlist.size() - prelist.size()); j++) {
				com.compare(goldlist.get(j), prelist.get(i));

			}
		}
		// You need to change the path to the files correspondingly
		com.writeFile(path + "ComparisonSuite(5files).txt");
	}
}
