import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class WordLadderTest {

	@Test
	public void test10() throws IOException {
		String [] tenIn = {"words-10.txt","words-10-in.txt"};
		new WordLadder(tenIn);
		Scanner realOutput = new Scanner(new File("words-10-out.txt"));
		Scanner myOutput = new Scanner(new File("words-10myOutput.txt"));
		while(realOutput.hasNextLine()&& myOutput.hasNextLine()){
			assertEquals(realOutput.nextLine(),myOutput.nextLine());
		}
	}
	@Test
	public void test250() throws IOException {
		String [] twoFiftyIn = {"words-250.txt","words-250-in.txt"};
		new WordLadder(twoFiftyIn);
		Scanner realOutput = new Scanner(new File("words-250-out.txt"));
		Scanner myOutput = new Scanner(new File("words-250myOutput.txt"));
		while(realOutput.hasNextLine()&& myOutput.hasNextLine()){
			assertEquals(realOutput.nextLine(),myOutput.nextLine());
		}
	}
	@Test
	public void test5757() throws IOException {
		String [] fiveSevenFiveSevenIn = {"words-5757.txt","words-5757-in.txt"};
		new WordLadder(fiveSevenFiveSevenIn);
		Scanner realOutput = new Scanner(new File("words-5757-out.txt"));
		Scanner myOutput = new Scanner(new File("words-5757myOutput.txt"));
		while(realOutput.hasNextLine()&& myOutput.hasNextLine()){
			assertEquals(realOutput.nextLine(),myOutput.nextLine());
		}
	}

}
