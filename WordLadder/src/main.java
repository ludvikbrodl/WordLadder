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
		
//		Node tempWord = null;
		
		// bygg hashmappen med de reducerade nycklarna.
		for (int i = 0; i < words.size(); i++) {
			String key = words.get(i);
			for (int j = 0; j < words.size(); j++) {
				String reducedKey = words.get(j);
				if (i != j && checkMatch(key, reducedKey)) {
					LinkedList<String> linkedList = graph.get(key);
					if (linkedList == null) {
						graph.put(key, new LinkedList<String>());
					} else {
						linkedList.add(key);
					}
				}
			}
		}
		System.out.println(graph);
	}
	 //f�r inte detta skitet att funka
	public static boolean checkMatch(String word, String reducedKey) {
		for (int i = 0; i < word.length(); i++) {
			int indexOfChar = reducedKey.indexOf(word.charAt(i));
			if (indexOfChar == -1) {
				return false;
			}
			reducedKey = reducedKey.substring(0, indexOfChar)
					+ reducedKey.substring(1 + indexOfChar);
			System.out.println(reducedKey);
		}
		return true;
	}

//		// lägg till alla 4:a bokstäver långa ord till bucketsen.
//		Set<Node> keySet = map.keySet();
//		for (Node key : keySet) {
//			for (int i = 0; i < words.size(); i++) {
//				Node word = words.get(i);
//				if (word.checkMatch(key.getKey())
//						&& !word.toString().equals(key.toString())) {
//					map.get(key).add(word);
//				}
//			}
//		}
//		 System.out.println(map.toString());
//		Iterator itr = combinations.iterator();
//		
//		while (itr.hasNext()) {
//			distanceBetween(map, new Node(itr.next().toString()), new Node(itr.next().toString()));
//			clear();
//		}
//
//	}
//
//	private static void clear() {
//		for (Node n : words) {
//			n.setVisited(false);
//		}
//	}
//
//	public static void distanceBetween(HashMap<Node, LinkedList<Node>> graph,
//			Node from, Node to) {
//
//		Queue<Node> queue = new LinkedList();
//
//		queue.add(from);
//		from.setVisited(true);
//		// System.out.println(queue.peek());
//		int currentDepth = 0, elementsToDepthIncrease = 1, nextElementsToDepthIncrease = 0;
//		while (!queue.isEmpty()) {
//			Node word = queue.remove();
//			Node child = null;
//
//			try {
//				while (true) {
//					LinkedList<Node> w = graph.get(word);
//					child = w.pop();
//					nextElementsToDepthIncrease += w.size();
//					if (elementsToDepthIncrease == 0) {
//
//						++currentDepth;
//						elementsToDepthIncrease = nextElementsToDepthIncrease;
//						nextElementsToDepthIncrease = 0;
//					}
//					elementsToDepthIncrease--;
//					if (!child.getVisited()) {
//						if (child.toString().equals(to.toString())) {
//							System.out.println(currentDepth);
//							return;
//						}
//						queue.add(child);
//						child.setVisited(true);
//					}
//				}
//			} catch (NoSuchElementException e) {
//
//			}
//
//		}
//		System.out.println("-1");
//	}
//
//	public static class Node {
//		private String string;
//		private String[] sortedStrings;
//		private boolean visited = false;
//
//		public Node(String string) {
//			this.string = string;
//
//			sortedStrings = string.substring(1);
//			char[] unsortedChars = sortedStrings.toCharArray();
//
//			Arrays.sort(unsortedChars);
//			sortedStrings = new String(unsortedChars);
//		}
//
//		public String[] getKeys() {
//			Stri
//			return null;
//		}
//
//		public boolean getVisited() {
//			return visited;
//		}
//
//		public void setVisited(boolean b) {
//			visited = b;
//		}
//
//		/**
//		 * check if otherString is contained inside this Word.
//		 * 
//		 * @param otherString
//		 * @return
//		 */
//		public boolean checkMatch(String otherString) {
//			String tempString = string;
//			for (int i = 0; i < otherString.length(); i++) {
//				int indexOfChar = tempString.indexOf(otherString.charAt(i));
//				if (indexOfChar == -1) {
//					return false;
//				}
//				tempString = tempString.substring(0, indexOfChar)
//						+ tempString.substring(1 + indexOfChar);
//			}
//
//			return true;
//		}
//
//		public int hashCode() {
//			return sortedStrings.hashCode();
//		}
//
//		public String getKey() {
//			return sortedStrings;
//		}
//
//		public String toString() {
//			return string;
//		}
//
//		public boolean equals(Object o) {
//			return sortedStrings.equals(((Node) o).sortedStrings);
//		}
//
//	}

	private static void parse(String[] args) throws IOException {

		FileReader fr = new FileReader(new File(args[0]));
		BufferedReader br = new BufferedReader(fr);
		while (br.ready()) {
			String line = br.readLine();

			words.add(line);

		}
		br.close();
		fr = new FileReader(new File(args[1]));
		br = new BufferedReader(fr);
		while (br.ready()) {
			String line = br.readLine();
			String[] split = line.split(" ");
			combinations.add(split[0]);
			combinations.add(split[1]);
		}
		br.close();
		System.out.println(words);
		System.out.println(combinations);
	}
}
