import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.swing.plaf.synth.SynthSplitPaneUI;

public class ClosestPoints {
	private List<Point> point;
	private Map<Point, String> vertices;
	private Pair closest;

	public ClosestPoints(String path) throws FileNotFoundException {
		reader(path);
		closest = new Pair(new Point(0, 0), new Point(0, 1000));
		findClosestPair(sortByX(point.toArray(new Point[point.size()])),
				sortByY(point.toArray(new Point[point.size()])));
		System.out.print(vertices.get(closest.getPoint1()) + " " +
		 closest.getPoint1());
		 System.out.print(vertices.get(closest.getPoint2()) + " " +
		 closest.getPoint2());
		 System.out.println(closest.distance());
	}

	public void reader(String path) throws FileNotFoundException {
		point = new ArrayList<Point>();
		vertices = new HashMap<Point, String>();
		Scanner scan = new Scanner(new File(path));
		while (scan.hasNext()) {
			String t = scan.nextLine().trim();
			String[] s = t.split("\\s+");
				try {
					double x = Double.parseDouble(s[1]);
					double y = Double.parseDouble(s[2]);
					Point p = new Point(x, y);
					point.add(p);
					vertices.put(p, s[0]);
				} catch (Exception e) {

				
			}
		}
		scan.close();
	}

	public Pair findClosestPair(Point[] xList, Point[] yList) {
		
		if (xList.length < 4) {
			for (int i = 0; i < xList.length; i++) {
				for (int j = 0; j < xList.length; j++) {
					if (i != j) {
						Pair pair = new Pair(xList[i], xList[j]);
						if (pair.distance() < closest.distance()) {
							closest = pair;
						}
					}
				}
			}
			return closest;
		}

		Point[] qX = leftHalf((xList));
		Point[] rX = rightHalf((xList));
		Point[] qY = leftHalf((yList));
		Point[] rY = rightHalf((yList));
		Pair right = findClosestPair(qX, qY);
		Pair left = findClosestPair(rX, rY);
		closest = right.distance() < left.distance() ? right : left;
		double s = rightHalf(xList)[xList.length / 2 - 1].getX();
		Point[] sortedByY = yList;
		List<Point> subset = new ArrayList<Point>();
		double delta = closest.distance();
		for (int i = 0; i < xList.length; i++) {
			double t = Math.abs(sortedByY[i].getX() - s);
			if (t <= delta) {
				subset.add(sortedByY[i]);
			}
		}
		for (int i = 0; i < subset.size(); i++) {
			for (int j = 0; j < 11; j++) {
				if (i != j && j < subset.size() && i < subset.size()) {
					Pair p = new Pair(subset.get(i), subset.get(j));
					if (p.distance() < closest.distance()) {
						closest = p;
					}
				}
			}
		}

		return closest;
	}

	public Point[] leftHalf(Point[] list) {
		Point[] leftHalf = new Point[list.length / 2];
		for (int i = 0; i < leftHalf.length; i++) {
			leftHalf[i] = list[i];
		}
		return leftHalf;
	}

	public Point[] rightHalf(Point[] list) {
		Point[] rightHalf = new Point[list.length / 2];
		if (list.length % 2 == 0) {
			for (int i = (list.length / 2); i < list.length; i++) {
				rightHalf[i - (list.length / 2)] = list[i];
			}
		} else {
			rightHalf = new Point[list.length / 2];
			for (int i = (list.length / 2 + 1); i < list.length; i++) {
				rightHalf[i - (list.length / 2) - 1] = list[i];
			}
		}
		return rightHalf;
	}

	public Point[] sortByX(Point[] points) {
		Point p = new Point(0, 0);
		p.sortByX();
		Arrays.sort(points, p);
		return points;
	}

	public Point[] sortByY(Point[] points) {
		Arrays.sort(points, new Point(0, 0));
		return points;
	}
//	public static void main(String[]args) throws FileNotFoundException{
//		new ClosestPoints("hej");
//	}

}
