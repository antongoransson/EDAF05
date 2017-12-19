import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class SequenceAlignment {
	private Map<String, Integer> charValues;
	private String filename;
	public SequenceAlignment(String word1, String word2,String filename) throws IOException {
		this.filename = filename;
		String[][] s = getBlosum("BLOSUM62.txt");
		charValues = getValues(s);
		int[][] M = optSequenceAlignment(11, 4, word1.split(""), word2.split(""), -4, s);
		optWord(M, s, -4, word1, word2);
	}

	private void optWord(int[][] M, String[][] a, int d, String word1, String word2) throws IOException {
		int m = word1.length();
		int n = word2.length();
		int k = m;
		int t = n;
		while (m != 0 || n != 0) {
			int up = m == 0 ? 0 : M[m - 1][n];
			int left = n == 0 ? 0 : M[m][n - 1];
			int current = M[m][n];
			if (m != 0 && up + d == current) {
				word2 = word2.substring(0, n) + "-" + word2.substring(n, word2.length());
				m--;
			} else if (n != 0 && left + d == current) {
				word1 = word1.substring(0, m) + "-" + word1.substring(m, word1.length());
				n--;
			} else {
				m--;
				n--;
			}
		}
		File outFile = new File(filename);
		FileWriter fWriter = new FileWriter(outFile);
		PrintWriter pWriter = new PrintWriter(fWriter);
			
		pWriter.println(word1);
		pWriter.println(word2);
		pWriter.print(M[k][t]);

		pWriter.close();
	}

	private int[][] optSequenceAlignment(int m, int n, String[] x, String[] y, int d, String[][] a) {
		
	
		int[][] M = new int[x.length + 1][y.length + 1];
		for (int i = 0; i <= x.length; i++) {
			M[i][0] = i * d;
		}
		for (int i = 0; i <= y.length; i++) {
			M[0][i] = i * d;
		}
		for (int i = 1; i < x.length + 1; i++) {
			for (int j = 1; j < y.length + 1; j++) {
				int c = Integer.parseInt(a[charValues.get(x[i - 1])][charValues.get(y[j - 1])]);
				int opt1 = Math.max(c + M[i - 1][j - 1], d + M[i - 1][j]);
				M[i][j] = Math.max(opt1, d + M[i][j - 1]);
			}
		}
//		for (int i = 0; i <= x.length; i++) {
//			for (int j = 0; j <= y.length; j++) {
//				System.out.print(M[i][j] + " ");
//			}
//			System.out.println();
//		}
//		System.out.println(M[x.length][y.length]);
		return M;
	}

	public String[][] getBlosum(String path) throws FileNotFoundException {
		Scanner scan = new Scanner(new File(path));
		String[][] matrix = new String[25][25];
		for (int k = 0; k < 6; k++) {
			scan.nextLine();
		}
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				if (j == 0 && i == 0) {
					matrix[i][j] = " ";
				} else {
					String k = scan.next();
					matrix[i][j] = k;
				}
			}
		}
		scan.close();
		return matrix;
	}

	private Map<String, Integer> getValues(String[][] M) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		for (int i = 0; i < M.length; i++) {
			if (i != 0)
				map.put(M[0][i], i);
		}
		return map;
	}

	public static void main(String[] args) throws IOException {
		new SequenceAlignment("KQRIKAAKABK", "KQRK","Out");
	}
}
