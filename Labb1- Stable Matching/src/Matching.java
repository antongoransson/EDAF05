
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Matching {
	private String input;
	private int nbrOfPairs;
	private List<List<Integer>> men;
	private List<List<Integer>> women;
	private TreeMap<String, Integer> m;
	private TreeMap<Integer, String> w;
	private TreeMap<Integer, List<Integer>> men1;
	private TreeMap<Integer, List<Integer>> women1;
	private TreeMap<String, String> pairs;

	public Matching(String input) throws IOException {
		this.input = input;
		readFromeFile();
		CalculateResult();
		printResult();
	}
	public void readFromeFile() {
		File f = new File(input);
		Scanner s = null;
		try {
			s = new Scanner(f);
		} catch (FileNotFoundException e) {
			System.out.println("Filen ej hittad");
			return;
		}

		m = new TreeMap<String, Integer>();
		w = new TreeMap<Integer, String>();
		men = new ArrayList<List<Integer>>();
		System.out.println(w.get("amanda"));
		women = new ArrayList<List<Integer>>();
		men1 = new TreeMap<Integer, List<Integer>>();
		women1 = new TreeMap<Integer, List<Integer>>();
		String p = s.nextLine();
		while ((p.contains("#"))) {
			p = s.nextLine();
		}
		nbrOfPairs = Integer.parseInt(p.substring(2));
		for (int i = 0; i < nbrOfPairs * 2; i++) {
			String person = s.nextLine();
			String[] row = person.split("\\s+");
			if (Integer.parseInt(row[0]) % 2 != 0) {
				m.put(row[1].trim(), (Integer.parseInt(row[0])));
				men1.put((Integer.parseInt(row[0])), new ArrayList<Integer>());
			} else {
				w.put((Integer.parseInt(row[0])), row[1].trim());
				women1.put((Integer.parseInt(row[0])), new ArrayList<Integer>());
			}
		}
		s.nextLine();
		for (int i = 0; i < nbrOfPairs * 2; i++) {
			if (s.hasNextLine()) {

				String[] rating = s.nextLine().split(":");
				String[] r1 = rating[1].split(("\\s+"));
				if (Integer.parseInt(rating[0]) % 2 != 0) {
					for (int j = 1; j < r1.length; j++) {
						int r = Integer.parseInt(r1[j]);
						men1.get(Integer.parseInt(rating[0])).add(r);
					}
				} else {
					for (int j = 1; j < r1.length; j++) {
						int r = Integer.parseInt(r1[j]);
						women1.get(Integer.parseInt(rating[0])).add(r);
					}

				}
			}
		}
	}


	public void CalculateResult() {
		pairs = new TreeMap<String, String>();
		TreeMap<String, Integer> tempManMap = new TreeMap<String, Integer>(m);
		while (pairs.size() < nbrOfPairs && men1.size() > 0) {
			Map.Entry<String, Integer> man = m.pollFirstEntry();
			List<Integer> menPrefs = men1.get(man.getValue());
			Integer preferedWoman = menPrefs.remove(0);
			String woman = w.get(preferedWoman);
			if (!pairs.containsKey(woman)) {
				pairs.put(woman, man.getKey());
			} else if (women1.get(preferedWoman).indexOf(man.getValue()) < women1.get(preferedWoman)
					.indexOf(tempManMap.get(pairs.get(woman)))) {
				String dissadMan = pairs.get(woman);
				m.put(dissadMan, tempManMap.get(pairs.get(woman)));
				pairs.put(woman, man.getKey());
			} else {
				m.put(man.getKey(), man.getValue());
			}
		}
	}

	public void printResult() throws IOException {
		File outFile = new File("output.txt");
		FileWriter fWriter = new FileWriter(outFile);
		PrintWriter pWriter = new PrintWriter(fWriter);
		for (Map.Entry<String, String> set : pairs.entrySet()) {
			System.out.println(set);
			pWriter.println(set.getKey() + " -- " + set.getValue());
		}
		pWriter.close();
	}
}