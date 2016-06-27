package MachineLearningFilesCombination;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
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
				// System.out.println("line: " + line);
				// System.out.println("checking: " + checking.toString());

				// System.out.println("new term: " + line);
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
			txtlist.removeAll(Arrays.asList(null, ""));
			// for (String ele : txtlist)
			// System.out.println(ele);
			// for (String ele : tsvlist)
			// System.out.println(ele);
		}
	}

	public void combine() {
		if (tsvCount != tsvlist.size() - 1 || tsvCount != tsvlist.size() - 1) {

			String[] annoSpilt = tsvlist.get(tsvCount).split("\t");
			String[] txtSpilt = txtlist.get(txtCount).split("\t");
			// System.out.println(txtlist.get(txtCount));
			// System.out.println(tsvlist.get(tsvCount));
			if (Integer.parseInt(txtSpilt[0]) - Integer.parseInt(annoSpilt[2]) >= 0
					&& Integer.parseInt(annoSpilt[3]) - Integer.parseInt(txtSpilt[1]) >= 0) {
				System.out.println(annoSpilt[1] + "\t" + txtlist.get(txtCount));
				txtCount++;
				if (annoSpilt[3].equals(txtSpilt[1])) {
					tsvCount++;
					combine();
				}
				combine();
			} else {
				txtCount++;
				combine();
			}
		}
	}
}
