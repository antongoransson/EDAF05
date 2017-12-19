import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class TestSequenceAlignment {
	String one, two, three;

	@Before
	public void setup() {
		one = "KQRIKAAKABK";
		two = "KQRK";
		three = "KAK";
	}

	@Test
	public void test1() throws IOException {
		new SequenceAlignment(one, two,"Out1");
	}

	@Test
	public void test2() throws IOException {
		new SequenceAlignment(two, three,"Out2");

	}

	@Test
	public void test3() throws IOException {
		new SequenceAlignment(one, three,"Out3");
	}
}
