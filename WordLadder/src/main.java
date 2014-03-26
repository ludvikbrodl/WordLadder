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
	static ArrayList<Node> words = new ArrayList<Node>();
	static ArrayList<Node> combinations = new ArrayList<Node>();

	public static void main(String[] arg) throws IOException {
		
		// words.add(new Node("there"));
		// words.add(new Node("which"));
		// words.add(new Node("their"));
		// words.add(new Node("about"));
		// words.add(new Node("these"));
		// words.add(new Node("words"));
		// words.add(new Node("would"));
		// words.add(new Node("other"));
		// words.add(new Node("write"));
		// words.add(new Node("could"));
		// words.add(new Node("bheir"));
		// words.add(new Node("ciehr"));
		parse(arg);
		Node tempWord = null;
		HashMap<Node, LinkedList<Node>> map = new HashMap<Node, LinkedList<Node>>();

		// bygg hashmappen med de reducerade nycklarna.
		for (int i = 0; i < words.size(); i++) {
			tempWord = words.get(i);
			map.put(tempWord, new LinkedList<Node>());
		}

		// lägg till alla 4:a bokstäver långa ord till bucketsen.
		Set<Node> keySet = map.keySet();
		for (Node key : keySet) {
			for (int i = 0; i < words.size(); i++) {
				Node word = words.get(i);
				if (word.checkMatch(key.getKey())
						&& !word.toString().equals(key.toString())) {
					map.get(key).add(word);
				}
			}
		}
		 System.out.println(map.toString());
		Iterator itr = combinations.iterator();
		
		while (itr.hasNext()) {
			distanceBetween(map, new Node(itr.next().toString()), new Node(itr.next().toString()));
			clear();
		}

	}

	private static void clear() {
		for (Node n : words) {
			n.setVisited(false);
		}
	}

	public static void distanceBetween(HashMap<Node, LinkedList<Node>> graph,
			Node from, Node to) {

		Queue<Node> queue = new LinkedList();

		queue.add(from);
		from.setVisited(true);
		// System.out.println(queue.peek());
		int currentDepth = 0, elementsToDepthIncrease = 1, nextElementsToDepthIncrease = 0;
		while (!queue.isEmpty()) {
			Node word = queue.remove();
			Node child = null;

			try {
				while (true) {
					LinkedList<Node> w = graph.get(word);
					child = w.pop();
					nextElementsToDepthIncrease += w.size();
					if (elementsToDepthIncrease == 0) {

						++currentDepth;
						elementsToDepthIncrease = nextElementsToDepthIncrease;
						nextElementsToDepthIncrease = 0;
					}
					elementsToDepthIncrease--;
					if (!child.getVisited()) {
						if (child.toString().equals(to.toString())) {
							System.out.println(currentDepth);
							return;
						}
						queue.add(child);
						child.setVisited(true);
					}
				}
			} catch (NoSuchElementException e) {

			}

		}
		System.out.println("-1");
	}

	public static class Node {
		private String string, sortedString;
		private boolean visited = false;

		public Node(String string) {
			this.string = string;

			sortedString = string.substring(1);
			char[] unsortedChars = sortedString.toCharArray();

			Arrays.sort(unsortedChars);
			sortedString = new String(unsortedChars);
		}

		public boolean getVisited() {
			return visited;
		}

		public void setVisited(boolean b) {
			visited = b;
		}

		/**
		 * check if otherString is contained inside this Word.
		 * 
		 * @param otherString
		 * @return
		 */
		public boolean checkMatch(String otherString) {
			String tempString = string;
			for (int i = 0; i < otherString.length(); i++) {
				int indexOfChar = tempString.indexOf(otherString.charAt(i));
				if (indexOfChar == -1) {
					return false;
				}
				tempString = tempString.substring(0, indexOfChar)
						+ tempString.substring(1 + indexOfChar);
			}

			return true;
		}

		public int hashCode() {
			return sortedString.hashCode();
		}

		public String getKey() {
			return sortedString;
		}

		public String toString() {
			return string;
		}

		public boolean equals(Object o) {

			return sortedString.equals(((Node) o).getKey());
		}

	}

	private static void parse(String[] args) throws IOException {

		FileReader fr = new FileReader(new File(args[0]));
		BufferedReader br = new BufferedReader(fr);
		while (br.ready()) {
			String line = br.readLine();

			words.add(new Node(line));

		}
		br.close();
		fr = new FileReader(new File(args[1]));
		br = new BufferedReader(fr);
		while (br.ready()) {
			String line = br.readLine();
			String[] split = line.split(" ");
			combinations.add(new Node(split[0]));
			combinations.add(new Node(split[1]));
		}
		br.close();
		System.out.println(words);
		System.out.println(combinations);
	}
}
