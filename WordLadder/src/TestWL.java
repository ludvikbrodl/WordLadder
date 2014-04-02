
import org.junit.After;
import org.junit.Before;

import static org.junit.Assert.*;

import org.junit.Test;

import java.io.*;


public class TestWL {
	private final static String TESTDATA_DIR = "/h/d8/l/dat12jkr/git/WordLadder/WordLadder/lab2";
	private final static char SC = File.separatorChar;
	
	/**
	 * Method to run an actual test case.
	 * 
	 * @param testname
	 *            Name of test data to be used, e.g. "stable-marriage-friends".
	 * @throws IOException 
	 */
	private void runTestCase(String testname) throws IOException {
		
		System.out.println("Running test: " + testname);
		String infile = TESTDATA_DIR + SC + testname + ".in";
		String datafile = TESTDATA_DIR + SC + testname.split("-test")[0] + ".dat"; 
		String outfile = TESTDATA_DIR + SC + testname + ".out";

		/*
		 * Divert stdout to buffer
		 */
		long start = System.currentTimeMillis();
//		System.out.println(datafile);
//		System.out.println(outfile);

		
		PrintStream oldOut = System.out;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(baos);
		System.setOut(ps);

		
		String[] args = new String[2];
		args[1] = infile;
		args[0] = datafile;
		WordLadder.main(args); // FIXME: Change GS to your own class!
	

		/*
		 * Restore stdout 
		 */
		System.setOut(oldOut);

		/*
		 * Compare program output with .out file
		 */
		try {
			StringBuilder sb = new StringBuilder();
			FileInputStream is = new FileInputStream(new File(outfile));
			byte buffer[] = new byte[1024];

			while (is.available() != 0) {
				int i = is.read(buffer);
				sb.append(new String(buffer, 0, i));
			}

			assertEquals(sb.toString(), baos.toString());
		} catch (FileNotFoundException e) {
			fail("File " + outfile + " not found.");
		} catch (IOException e) {
			fail("Error reading " + outfile);
		}
		long done = System.currentTimeMillis();
		System.out.println("Elapsed time (ms): " + (done - start));
	}
	
	/**
	 * Simple test case for the 'friends' test data.
	 * @throws IOException 
	 */
//	@Test
//	public void testFriends() throws IOException {
//		runTestCase("sm-friends");
//	}

	/**
	 * Test case that will use all test data it can find in TESTDATA_DIR.
	 * 
	 * You may want to comment this out until you think your program works, as
	 * this test can take some time to execute.
	 * @throws IOException 
	 */
	@Test
	public void testAll() throws IOException {
		File dir = new File(TESTDATA_DIR);

		
		for (File f : dir.listFiles()) {
			if (f.isFile() && f.toString().endsWith(".out")) {
				String s = f.toString();
				s = s.substring(s.lastIndexOf(SC) + 1);
				s = s.substring(0, s.lastIndexOf(".out"));

				runTestCase(s);
			}
			
		}
		
	}



}