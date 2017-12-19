
public class Pair {
	private Point p1, p2;

	public Pair(Point p1, Point p2) {
		this.p1 = p1;
		this.p2 = p2;
	}

	public double distance() {
		return p1.distance(p2);
	}

	@Override
	public String toString() {
		return p1.toString() + p2.toString();
	}
	public Point getPoint1(){
		return p1;
	}
	public Point getPoint2(){
		return p2;
	}
}
