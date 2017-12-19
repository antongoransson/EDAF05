import java.util.Comparator;

public class Point implements Comparator<Point> {
	private double x, y;
	private boolean sort;

	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public void sortByX() {
		sort = true;
	}

	public void sortByY() {
		sort = false;
	}

	public double distance(Point other) {
//		return Math.pow(x - other.x, 2) + Math.pow(y - other.y, 2);
		return Math.hypot(x-other.x, y-other.y);
	}

	@Override
	public int compare(Point o1, Point o2) {
		if (sort) {
			return  ((int)o1.x - (int)o2.x);
		} else {
			return  ((int)o1.y - (int)o2.y);
		}
	}

	@Override
	public String toString() {
		return "X: " + x + " Y: " + y + "\n";
	}
}