import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeSet;

public class WordLadder {
	private String fileName;
	private Map<String, LinkedList<String>> adjacentVertices;
	private Map<String, HashSet<String>> endings;

	public WordLadder(String[] a) throws IOException {
		this.fileName = a[0].substring(0, a[0].length() - 4) + "myOutput.txt";
		buildGraph(readFromFile(a[0]));
		readPairFromFileAndWriteToFile(a[1]);
	}

	public void buildGraph(String[] words) {
		endings = new HashMap<String, HashSet<String>>();
		adjacentVertices = new HashMap<String, LinkedList<String>>();
		for (int j = 0; j < words.length; j++) {
			adjacentVertices.put(words[j], new LinkedList<String>());
			addSubstringsOfWord(words[j]);
		}
		for (int i = 0; i < words.length; i++) {
			findNeighbours(words[i]);
		}
	}

	private void addSubstringsOfWord(String word) {
		String[] substrings = sortedSubstrings(word);
		for (int i = 0; i < 5; i++) {
			HashSet<String> vertices = endings.get(substrings[i]);
			if (vertices != null) {
					vertices.add(word);
			} else {
				vertices = new HashSet<String>();
				vertices.add(word);
				endings.put(substrings[i], vertices);
			}
		}
	}

	private void findNeighbours(String word) {
		String t = getLastFourLetters(word);
		for (String s : endings.get(t)) {
			if (!word.equals(s)) {
				adjacentVertices.get(word).add(s);
			}
		}
	}

	private String[] sortedSubstrings(String word) {
		String one = word.substring(1);
		String two = word.substring(0, 1) + word.substring(2, 5);
		String three = word.substring(0, 2) + word.substring(3, 5);
		String four = word.substring(0, 3) + word.substring(4, 5);
		String five = word.substring(0, 4);
		String[] substringArray = { one, two, three, four, five };
		for (int i = 0; i < 5; i++) {
			char[] tempCharArray = substringArray[i].toCharArray();
			Arrays.sort(tempCharArray);
			substringArray[i] = new String(tempCharArray);
		}
		return substringArray;
	}

	private String getLastFourLetters(String word) {
		String lastFourLetters = word.substring(1);
		char[] tempCharArray = lastFourLetters.toCharArray();
		Arrays.sort(tempCharArray);
		lastFourLetters = new String(tempCharArray);
		return lastFourLetters;
	}

	public int checkDistance(String root, String target) {
		LinkedList<String> queue = new LinkedList<String>();
		HashMap<String, Integer> checked = new HashMap<String, Integer>();
		checked.put(root, 0);
		queue.add(root);
		while (!queue.isEmpty()) {
			String current = queue.removeFirst();
			if (current.equals((target))) {
				return checked.get(target);
			}
			for (String childNodes : adjacentVertices.get((current))) {
				if (!checked.containsKey(childNodes)) {
					queue.addLast(childNodes);
					checked.put(childNodes, checked.get(current) + 1);
				}
			}
		}
		return -1;
	}

	public String[] readFromFile(String path) throws FileNotFoundException {
		List<String> wordsFromFile = new ArrayList<String>();
		Scanner scan = new Scanner(new File(path));
		while (scan.hasNextLine()) {
			wordsFromFile.add(scan.nextLine());
		}
		return wordsFromFile.toArray(new String[wordsFromFile.size()]);
	}

	public void readPairFromFileAndWriteToFile(String path) throws IOException {
		Scanner scan = new Scanner(new File(path));
		File outFile = new File(fileName);
		FileWriter fWriter = new FileWriter(outFile);
		PrintWriter pWriter = new PrintWriter(fWriter);
		while (scan.hasNextLine()) {
			String[] s = scan.nextLine().split("\\s+");
			pWriter.println(checkDistance(s[0], s[1]));
		}
		pWriter.close();
	}
}
