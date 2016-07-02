package MachineLearningFilesCombination;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Combination {
	String annotationFile;
	String literature;
	List<String> tsvlist = new ArrayList<String>();
	List<String> txtlist = new ArrayList<String>();
	int tsvCount = 0;
	int txtCount = 0;
	StringBuilder finalResult = new StringBuilder();
	StringBuilder checking = new StringBuilder();

	public Combination(String anno, String lit) throws IOException {
		annotationFile = anno;
		literature = lit;
		BufferedReader br = new BufferedReader(new FileReader(annotationFile));
		BufferedReader br2 = new BufferedReader(new FileReader(literature));

		try

		{

			// insert tsv file record into arraylist
			String line = br.readLine();

			while (line != null) {
				String[] seg = line.split("\t");
				tsvlist.add(line);

				checking.append("b" + seg[2] + " e" + seg[3] + " ");
				line = br.readLine();
			}
			// }

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

			txtlist.removeAll(Arrays.asList(null, ""));
			txtlist.add("-1	-1	0	0	0	0");
			// for (String ele : txtlist)
			// System.out.println(ele);
			// for (String ele : tsvlist)
			// System.out.println(ele);
		}
	}

	public void combine() {
		if (tsvCount <= tsvlist.size() - 1) {

			String[] annoSpilt = tsvlist.get(tsvCount).split("\t");

			// System.out.println("tsv file: " + tsvlist.get(tsvCount));
			for (int i = 0; i < txtlist.size(); i++) {
				// System.out.println(txtlist.get(i));
				String[] txtSpilt = txtlist.get(i).split("\t");
				String annotation;
				if (Integer.parseInt(txtSpilt[0]) - Integer.parseInt(annoSpilt[2]) == 0)
					annotation = "B-" + annoSpilt[1];

				else
					annotation = "I-" + annoSpilt[1];
				if (Integer.parseInt(txtSpilt[0]) - Integer.parseInt(annoSpilt[2]) >= 0
						&& Integer.parseInt(annoSpilt[3]) - Integer.parseInt(txtSpilt[1]) >= 0) {
					String verify = txtSpilt[1] + "\t" + ((Integer.parseInt(txtSpilt[1]) + 1));

					finalResult.append(annotation + "\t" + txtlist.get(i) + "\n");
					System.out.println(annotation + "\t" + txtlist.get(i));

					if (annoSpilt[3].equals(txtSpilt[1])
							|| ((Integer.parseInt(annoSpilt[3]) - Integer.parseInt(txtSpilt[1]) == 1)
									&& !txtlist.get(i + 1).startsWith(verify))) {
						// System.out.println("verify: " + verify);
						tsvCount++;
						combine();
					}

				} else if (Integer.parseInt(txtSpilt[0]) - Integer.parseInt(annoSpilt[2]) >= 0
						&& (Integer.parseInt(annoSpilt[3]) - Integer.parseInt(txtSpilt[1]) == -1)
						&& !txtlist.get(i).startsWith(annoSpilt[3] + "\t" + txtSpilt[1])) {
					// System.out.println("txtSpilt[0]: " + txtSpilt[0] + "
					// annoSpilt[2]: " + annoSpilt[2]
					// + "annoSpilt[3]: " + annoSpilt[3] + " txtSpilt[1]: " +
					// txtSpilt[1]);
					String extra1word = annoSpilt[1] + "\t" + txtSpilt[0] + "\t" + annoSpilt[3] + "\t"
							+ txtSpilt[2].substring(0, txtSpilt[2].length() - 1) + "\t" + txtSpilt[3] + "\t"
							+ txtSpilt[4] + "\t" + txtSpilt[5] + "\n";
					finalResult.append(extra1word);
					System.out.println("extra: " + extra1word);
					tsvCount++;
					combine();
				} else if (Integer.parseInt(txtSpilt[0]) - Integer.parseInt(annoSpilt[2]) >= 0
						&& (Integer.parseInt(annoSpilt[3]) - Integer.parseInt(txtSpilt[1]) == -2)
						&& !txtlist.get(i)
								.startsWith("" + (Integer.parseInt(annoSpilt[3]) + 1) + "\t"
										+ Integer.parseInt(txtSpilt[1]))
						&& !txtlist.get(i).startsWith(
								"" + Integer.parseInt(annoSpilt[3]) + "\t" + Integer.parseInt(txtSpilt[1]))) {
					System.out.println();
					System.out.println("txtSpilt[0]: " + txtSpilt[0] + " annoSpilt[2]: " + annoSpilt[2]
							+ "annoSpilt[3]: " + annoSpilt[3] + " txtSpilt[1]: " + txtSpilt[1]);
					System.out.println(txtlist.get(i));
					String extra2word = annoSpilt[1] + "\t" + txtSpilt[0] + "\t" + annoSpilt[3] + "\t"
							+ txtSpilt[2].substring(0, txtSpilt[2].length() - 2) + "\t" + txtSpilt[3] + "\t"
							+ txtSpilt[4] + "\t" + txtSpilt[5] + "\n";
					finalResult.append(extra2word);
					System.out.println("extra 2 words");
					System.out.println(extra2word);
					tsvCount++;
					combine();
				} else if (Integer.parseInt(txtSpilt[0]) - Integer.parseInt(annoSpilt[2]) >= 0
						&& (Integer.parseInt(annoSpilt[3]) - Integer.parseInt(txtSpilt[1]) == -3)
						&& !txtlist.get(i)
								.startsWith("" + (Integer.parseInt(annoSpilt[3]) + 1) + "\t"
										+ (Integer.parseInt(txtSpilt[1]) - 1))
						&& !txtlist.get(i)
								.startsWith("" + (Integer.parseInt(txtSpilt[1]) - 1) + "\t"
										+ (Integer.parseInt(txtSpilt[1])))
						&& !txtlist.get(i).startsWith(
								"" + (Integer.parseInt(annoSpilt[3])) + "\t" + (Integer.parseInt(txtSpilt[1])))
						&& !txtlist.get(i).startsWith(
								"" + (Integer.parseInt(annoSpilt[3]) + 1) + "\t" + (Integer.parseInt(txtSpilt[1])))) {
					System.out.println();
					System.out.println("txtSpilt[1]-1: " + (Integer.parseInt(txtSpilt[1]) - 1) + " annoSpilt[3]+1: "
							+ (Integer.parseInt(annoSpilt[3]) + 1) + " annoSpilt[3]: " + annoSpilt[3] + " txtSpilt[1]: "
							+ txtSpilt[1] + " "
							+ (Integer.parseInt(annoSpilt[3]) + 1 + "\t" + (Integer.parseInt(txtSpilt[1]))));
					System.out.println(txtlist.get(i));
					String extra3word = annoSpilt[1] + "\t" + txtSpilt[0] + "\t" + annoSpilt[3] + "\t"
							+ txtSpilt[2].substring(0, txtSpilt[2].length() - 3) + "\t" + txtSpilt[3] + "\t"
							+ txtSpilt[4] + "\t" + txtSpilt[5] + "\n";
					finalResult.append(extra3word);
					System.out.println("extra 3 words");
					System.out.println(extra3word);
					tsvCount++;
					combine();
				} else if (Integer.parseInt(txtSpilt[0]) - Integer.parseInt(annoSpilt[2]) <= 0
						&& Integer.parseInt(annoSpilt[3]) - Integer.parseInt(txtSpilt[1]) <= 0
						&& txtSpilt[2].matches("\\d\\d\\d\\d-\\d\\d\\d\\d")) {
					int index = Integer.parseInt(annoSpilt[2]) - Integer.parseInt(txtSpilt[0]);
					String TemporalExpression = "B-TemporalExpression\t" + annoSpilt[2] + "\t" + annoSpilt[3] + "\t"
							+ txtSpilt[2].substring(index,
									Integer.parseInt(annoSpilt[3]) - Integer.parseInt(txtSpilt[0]))
							+ "\t"
							+ txtSpilt[2].substring(index,
									Integer.parseInt(annoSpilt[3]) - Integer.parseInt(txtSpilt[0]))
							+ "\tCD\t" + txtSpilt[5];
					System.out.println(TemporalExpression);
					finalResult.append(TemporalExpression + "\n");
					System.out.println();
					tsvCount++;
					combine();
				} else {
					System.out.println(txtlist.get(i));
				}

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
}
