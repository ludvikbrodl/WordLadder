import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Stack;

public class main {
	static ArrayList<String> words = new ArrayList<String>();
	static ArrayList<String> combinations = new ArrayList<String>();
	static HashMap<String, LinkedList<String>> graph = new HashMap<String, LinkedList<String>>();

	public static void main(String[] arg) throws IOException {
		long time = System.currentTimeMillis();
		parse(arg);
		System.out.println("parsing took in ms: " + (System.currentTimeMillis() - time));
		time = System.currentTimeMillis();
//		System.out.println(words);
//		System.out.println(combinations);
		
		// build the graph. WORKS 10:42 28/3
		buildGraph();
		System.out.println("building the graph took in ms: " + (System.currentTimeMillis() - time));
		time = System.currentTimeMillis();
//		System.out.println(graph);
		
		//bfs on all combinations
		for (int i = 0; i < combinations.size(); i = i + 2) {
			int depth = bfs(graph, combinations.get(i), combinations.get(i + 1));
			System.out.println(depth);
		}
		System.out.println("bfs took ms to find: " + (System.currentTimeMillis() - time));
		time = System.currentTimeMillis();
		for (int i = 0; i < combinations.size(); i = i + 2) {
			dfs(graph, combinations.get(i), combinations.get(i + 1));
		}
		System.out.println("dfs took ms to find: " + (System.currentTimeMillis() - time));
		
		
	}

	private static int bfs(HashMap<String, LinkedList<String>> graph, String wordFrom,
			String wordTo) {
		HashMap<String, Boolean> visitedGraph = new HashMap<String, Boolean>();
		for (String word : words) {
			visitedGraph.put(word, false);
		}
		LinkedList<String> queue = new LinkedList<String>();
		queue.add(wordFrom);
		visitedGraph.put(wordFrom, true);
		int depth = 0;
		int childrenCount = 1;
		
		while (!queue.isEmpty()) {
			String nextWord = queue.poll();
			if (nextWord.equals(wordTo)) {
				return depth;
			}
			LinkedList<String> children = graph.get(nextWord);
//			System.out.println(children);
			for (String child : children) {
				if (visitedGraph.get(child) == false) {
					visitedGraph.put(child, true);
					queue.add(child);
				}
			}
			if (--childrenCount == 0) {
				depth++;
				childrenCount = queue.size();
			}
		}
		return -1;
	}
	
	private static int dfs(HashMap<String, LinkedList<String>> graph, String wordFrom,
			String wordTo) {
		HashMap<String, Boolean> visitedGraph = new HashMap<String, Boolean>();
		for (String word : words) {
			visitedGraph.put(word, false);
		}
		Stack<String> queue = new Stack<String>();
		queue.add(wordFrom);
		visitedGraph.put(wordFrom, true);
		int depth = 0;
		int childrenCount = 1;
		
		while (!queue.isEmpty()) {
			String nextWord = queue.pop();
			if (nextWord.equals(wordTo)) {
				return depth;
			}
			LinkedList<String> children = graph.get(nextWord);
//			System.out.println(children);
			for (String child : children) {
				if (visitedGraph.get(child) == false) {
					visitedGraph.put(child, true);
					queue.add(child);
				}
			}
			if (--childrenCount == 0) {
				depth++;
				childrenCount = queue.size();
			}
		}
		return -1;
	}

	/**
	 * builds our graph from the words ArrayList(dictionary)
	 */
	private static void buildGraph() {
		for (int i = 0; i < words.size(); i++) {
			String word = words.get(i);
			LinkedList<String> linkedList = new LinkedList<String>();
			graph.put(word, linkedList);
			for (int j = 0; j < words.size(); j++) {
				String nextWord = words.get(j);
				if (i != j && checkMatch(word, nextWord)) {
					linkedList.add(nextWord);
				}
			}
		}
	}

	/**
	 * check if word should have a link to nextWord. (4 last letters in word has
	 * to be contained in nextWord)
	 * 
	 * @param nextWord
	 *            a link to this string?
	 * @param word
	 *            a link from this string?
	 * @return
	 */
	public static boolean checkMatch(String word, String nextWord) {
		word = word.substring(1);
		for (int i = 0; i < word.length(); i++) {
			int indexOfChar = nextWord.indexOf(word.charAt(i));
			if (indexOfChar == -1) {
				return false;
			}
			nextWord = nextWord.substring(0, indexOfChar)
					+ nextWord.substring(1 + indexOfChar);
		}
		return true;
	}

	/**
	 * parses 2 files to our words and combinations arrays
	 * 
	 * @param args
	 *            file1 = dictionary, file2 = matches to check depth of.
	 * @throws IOException
	 */
	private static void parse(String[] args) throws IOException {
		// first file
		FileReader fr = new FileReader(new File(args[0]));
		BufferedReader br = new BufferedReader(fr);
		while (br.ready()) {
			String line = br.readLine();
			words.add(line);
		}
		br.close();
		// second file
		fr = new FileReader(new File(args[1]));
		br = new BufferedReader(fr);
		while (br.ready()) {
			String line = br.readLine();
			String[] split = line.split(" ");
			combinations.add(split[0]);
			combinations.add(split[1]);
		}
		br.close();
	}
}
