import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Set;

public class main {
	public static void main(String[] arg) {
		System.out.println("smb");
		ArrayList<Word> words = new ArrayList<Word>();
		words.add(new Word("there",0));
		words.add(new Word("which",1));
		words.add(new Word("their",2));
		words.add(new Word("about",3));
		words.add(new Word("these",4));
		words.add(new Word("words",5));
		words.add(new Word("would",6));
		words.add(new Word("other",7));
		words.add(new Word("write",8));
		words.add(new Word("could",9));
		Word tempWord = null;
		HashMap<String, LinkedList<Word>> map = new HashMap<String, LinkedList<Word>>();

		// bygg hashmappen med de reducerade nycklarna.
		for (int i = 0; i < words.size(); i++) {
			tempWord = words.get(i);
			map.put(tempWord.getKey(), new LinkedList<Word>());
		}
		
		//lägg till alla 4:a bokstäver långa ord till bucketsen.
		Set<String> keySet = map.keySet();
		for (String key : keySet) {
			for (int i = 0; i < words.size(); i++) {
				Word word = words.get(i);
				if (word.checkMatch(key)) {
					map.get(key).add(word);
				}
			}
		}
		
		System.out.println(map.toString());
	}

	public static class Word {
		private String string, sortedString;
		private int id;

		public Word(String string, int id) {
			this.string = string;
			this.id = id;

			sortedString = string.substring(1);
			char[] unsortedChars = sortedString.toCharArray();

			Arrays.sort(unsortedChars);
			sortedString = new String(unsortedChars);
		}

		public int getID() {
			return id;
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
				tempString = tempString.substring(0, indexOfChar) + tempString.substring(1+indexOfChar);
			}
			return true;
		}

		public String getKey() {
			return sortedString;
		}
		
		public String toString(){
			return string;
		}
	}
}
