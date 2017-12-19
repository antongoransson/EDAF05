import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Matching {
	private String input;
	private int nbrOfPairs;
	private List<List<Integer>> men;
	private List<List<Integer>> women;
	private Map<String, List<Integer>> m;
	private Map<String, List<Integer>> w;

	public Matching(String input) {
		this.input = input;
	}

	public void readFromeFile() {
		File f = new File(input);
		Scanner s = null;
		try {
			s = new Scanner(f);
		} catch (FileNotFoundException e) {
			System.out.println("filen ej hittad");
			return;
		}
		// while (s.hasNextLine()) {
		m = new HashMap<String, List<Integer>>();
		w = new HashMap<String, List<Integer>>();
		men = new ArrayList<List<Integer>>();
		women = new ArrayList<List<Integer>>();
		s.nextLine();
		s.nextLine();
		nbrOfPairs = Integer.parseInt(s.nextLine().substring(2));
		System.out.println(nbrOfPairs);
		for (int i = 0; i < nbrOfPairs * 2; i++) {
			String person = s.nextLine();
			System.out.println(person.substring(1).trim());
			if (i % 2 == 0) {
				w.put(person.substring(1).trim(), new ArrayList<Integer>());
				men.add(new ArrayList<Integer>());
				System.out.println(person);
			} else {
				women.add(new ArrayList<Integer>());
				m.put(person.substring(1).trim(), new ArrayList<Integer>());
			}
		}
		s.nextLine();
		for (int i = 0; i < nbrOfPairs * 2; i++) {
			if (s.hasNextLine()) {
				String rating = s.nextLine().substring(3);
				rating.trim();
				String[] r1 = rating.split(("\\s+"));
				System.out.println(men.size() + "\n" + women.size());
				for (int j = 0; j < r1.length; j++) {
					int r = Integer.parseInt(r1[j]);
					men.get(i).add(r);
				}
				String ratingW = s.nextLine().substring(3);
				ratingW.trim();
				String[] r2 = rating.split(("\\s+"));
				for (int j = 0; j < r1.length; j++) {
					int r = Integer.parseInt(r2[j]);
					women.get(i).add(r);
				}
			}
		}
		System.out.println(men.get(2).get(2));
	}
	public void printResult(){
		System.out.println(men.get(0));
	}
}
