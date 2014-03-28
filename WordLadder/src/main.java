import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Set;

import javax.print.attribute.standard.MediaSize.Other;

public class main {
	static ArrayList<String> words = new ArrayList<String>();
	static ArrayList<String> combinations = new ArrayList<String>();
	static HashMap<String, LinkedList<String>> graph = new HashMap<String, LinkedList<String>>();

	public static void main(String[] arg) throws IOException {
		parse(arg);
		System.out.println(words);
		System.out.println(combinations);
		
		// build the graph. WORKS 10:42 28/3
		buildGraph();
		System.out.println(graph);
		
		//bfs on all combinations
		for (int i = 0; i < combinations.size(); i = i + 2) {
			int depth = bfs(graph, combinations.get(i), combinations.get(i + 1));
		}
	}

	private static int bfs(HashMap<String, LinkedList<String>> graph, String wordFrom,
			String wordTo) {
		HashMap<String, Boolean> visitedGraph = new HashMap<String, Boolean>();
		for (String word : words) {
			visitedGraph.put(word, false);
		}
		Queue queue = new LinkedList<String>();
		queue.add(wordFrom);
		while (!queue.isEmpty()) {
			//GOGOGO
		}
		return 0;
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

	// // lägg till alla 4:a bokstäver långa ord till bucketsen.
	// Set<Node> keySet = map.keySet();
	// for (Node key : keySet) {
	// for (int i = 0; i < words.size(); i++) {
	// Node word = words.get(i);
	// if (word.checkMatch(key.getKey())
	// && !word.toString().equals(key.toString())) {
	// map.get(key).add(word);
	// }
	// }
	// }
	// System.out.println(map.toString());
	// Iterator itr = combinations.iterator();
	//
	// while (itr.hasNext()) {
	// distanceBetween(map, new Node(itr.next().toString()), new
	// Node(itr.next().toString()));
	// clear();
	// }
	//
	// }
	//
	// private static void clear() {
	// for (Node n : words) {
	// n.setVisited(false);
	// }
	// }
	//
	// public static void distanceBetween(HashMap<Node, LinkedList<Node>> graph,
	// Node from, Node to) {
	//
	// Queue<Node> queue = new LinkedList();
	//
	// queue.add(from);
	// from.setVisited(true);
	// // System.out.println(queue.peek());
	// int currentDepth = 0, elementsToDepthIncrease = 1,
	// nextElementsToDepthIncrease = 0;
	// while (!queue.isEmpty()) {
	// Node word = queue.remove();
	// Node child = null;
	//
	// try {
	// while (true) {
	// LinkedList<Node> w = graph.get(word);
	// child = w.pop();
	// nextElementsToDepthIncrease += w.size();
	// if (elementsToDepthIncrease == 0) {
	//
	// ++currentDepth;
	// elementsToDepthIncrease = nextElementsToDepthIncrease;
	// nextElementsToDepthIncrease = 0;
	// }
	// elementsToDepthIncrease--;
	// if (!child.getVisited()) {
	// if (child.toString().equals(to.toString())) {
	// System.out.println(currentDepth);
	// return;
	// }
	// queue.add(child);
	// child.setVisited(true);
	// }
	// }
	// } catch (NoSuchElementException e) {
	//
	// }
	//
	// }
	// System.out.println("-1");
	// }
	//
	// public static class Node {
	// private String string;
	// private String[] sortedStrings;
	// private boolean visited = false;
	//
	// public Node(String string) {
	// this.string = string;
	//
	// sortedStrings = string.substring(1);
	// char[] unsortedChars = sortedStrings.toCharArray();
	//
	// Arrays.sort(unsortedChars);
	// sortedStrings = new String(unsortedChars);
	// }
	//
	// public String[] getKeys() {
	// Stri
	// return null;
	// }
	//
	// public boolean getVisited() {
	// return visited;
	// }
	//
	// public void setVisited(boolean b) {
	// visited = b;
	// }
	//
	// /**
	// * check if otherString is contained inside this Word.
	// *
	// * @param otherString
	// * @return
	// */
	// public boolean checkMatch(String otherString) {
	// String tempString = string;
	// for (int i = 0; i < otherString.length(); i++) {
	// int indexOfChar = tempString.indexOf(otherString.charAt(i));
	// if (indexOfChar == -1) {
	// return false;
	// }
	// tempString = tempString.substring(0, indexOfChar)
	// + tempString.substring(1 + indexOfChar);
	// }
	//
	// return true;
	// }
	//
	// public int hashCode() {
	// return sortedStrings.hashCode();
	// }
	//
	// public String getKey() {
	// return sortedStrings;
	// }
	//
	// public String toString() {
	// return string;
	// }
	//
	// public boolean equals(Object o) {
	// return sortedStrings.equals(((Node) o).sortedStrings);
	// }
	//
	// }

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
