import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.TreeMap;

public class SpanningTree {
	private TreeMap<String, TreeMap<String, Integer>> tree;
	private int i;
	private HashMap<String, Node> nodes;
	private PriorityQueue<Node> pQueue;

	public SpanningTree() throws IOException {
		tree = new TreeMap<String, TreeMap<String, Integer>>();
//		 readCitiesFromFile("USA-highway-miles.txt");
		readCitiesFromFile("tinyEWG-alpha.txt");
		System.out.println(tree);
		i = Integer.MAX_VALUE;
		makeMST(tree);
		System.out.println(getWeight());
		print();

	}

	public static void main(String[] args) throws IOException {
		new SpanningTree();
	}

	public void makeMST(TreeMap<String, TreeMap<String, Integer>> cities) {
		String rootKey = cities.firstKey();
		TreeMap<String, Integer> rootValue = cities.get(cities.firstKey());
		String rootName = cities.pollFirstEntry().getKey();
		Node root = new Node(rootName, 0, new Node(rootName, 0, null));
		pQueue = new PriorityQueue<Node>();
		nodes = new HashMap<String, Node>();
		for (Map.Entry<String, TreeMap<String, Integer>> entry : cities.entrySet()) {
			if (!entry.getKey().equals(root.getName())) {
				Node n = new Node(entry.getKey(), i, null);
				pQueue.add(n);
				nodes.put(n.getName(), n);
			}
		}
		cities.put(rootKey, rootValue);
		pQueue.add(root);
		nodes.put(root.getName(), root);
		while (!pQueue.isEmpty()) {
			HashSet<Node> update = new HashSet<Node>(pQueue);
			pQueue = new PriorityQueue<Node>(update);
			Node current = pQueue.poll();
			TreeMap<String, Integer> temp = cities.get(current.getName());
			for (Map.Entry<String, Integer> e : temp.entrySet()) {
				int weight = e.getValue();
				Node neighBour = nodes.get(e.getKey());
				if (neighBour.getWeight() > weight && pQueue.contains(neighBour)) {
					neighBour.setWeight(weight);
					neighBour.setParent(current);
				}
			}
		}
	}

	public int getWeight() {
		int size = 0;
		for (Map.Entry<String, Node> entry : nodes.entrySet()) {
			size += entry.getValue().getWeight();
		}
		return size;
	}

	public void readCitiesFromFile(String path) throws FileNotFoundException {
		Scanner scan = new Scanner(new File(path));
		while (scan.hasNextLine()) {
			String s = scan.nextLine();
			if (!s.contains("--")) {
				String newS = s.replaceAll("\"", "");
				tree.put(newS.trim(), new TreeMap<String, Integer>());
			} else {
				String[] cities = s.split("--");
				String city1 = cities[0];
				String cityAndDist = cities[1];
				String[] cityDistance = cityAndDist.split("\\[");
				String city2 = cityDistance[0].replaceAll("\"", "");
				int dist = Integer.parseInt(cityDistance[cityDistance.length - 1].substring(0,
						cityDistance[cityDistance.length - 1].length() - 1));
				city1 = city1.replaceAll("\"", "");
				TreeMap<String, Integer> cityDist = tree.get(city1.trim());
				TreeMap<String, Integer> cityDist1 = tree.get(city2.trim());
				cityDist.put(city2.trim(), dist);
				cityDist1.put(city1.trim(), dist);
			}
		}
		scan.close();
	}

	private class Node implements Comparable<Node> {
		private int weight;
		private Node parent;
		private String name;

		public Node(String name, int weight, Node parent) {
			this.name = name;
			this.weight = weight;
			this.parent = parent;
		}

		public String getName() {
			return name;
		}

		public int getWeight() {
			return weight;
		}

		public void setWeight(int newWeight) {
			this.weight = newWeight;
		}

		public void setParent(Node newParent) {
			this.parent = newParent;
		}

		@Override
		public String toString() {
			return name + weight;
		}

		@Override
		public int compareTo(Node o) {
			return weight - o.weight;
		}

	}

	public void print() throws IOException {
		File outFile = new File("tiny-out.txt");
		FileWriter fWriter = new FileWriter(outFile);
		PrintWriter pWriter = new PrintWriter(fWriter);
		for (Map.Entry<String, Node> entry : nodes.entrySet()) {
			pWriter.println(entry.getKey() + "--" + entry.getValue().parent);
		}
		pWriter.close();
	}
}
