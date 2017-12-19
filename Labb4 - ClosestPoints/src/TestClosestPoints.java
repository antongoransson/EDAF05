import java.io.File;

public class TestClosestPoints {
	public static void main(String[] args) {
		File folder = null;
		if(args.length == 0)
			folder = new File("files");
		else
			folder = new File(args[0]);
		File[] files = folder.listFiles();
		for(int i = 0; i < files.length; i++) {
			String name = files[i].getName();
			try {
				new ClosestPoints(folder + "/" + name);
			} catch (Exception e) {
				System.out.println("ERROR: " + e.getMessage()+ " " + name + " "+i);
			}
		}
	}
}

