import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Set;

public class main {
	public static void main(String[] arg) {
		System.out.println("smb");
		ArrayList<Word> words = new ArrayList<Word>();
		words.add(new Word("there"));
		words.add(new Word("which"));
		words.add(new Word("their"));
		words.add(new Word("about"));
		words.add(new Word("these"));
		words.add(new Word("words"));
		words.add(new Word("would"));
		words.add(new Word("other"));
		words.add(new Word("write"));
		words.add(new Word("could"));
		Word tempWord = null;
		HashMap<Word, LinkedList<Word>> map = new HashMap<Word, LinkedList<Word>>();

		// bygg hashmappen med de reducerade nycklarna.
		for (int i = 0; i < words.size(); i++) {
			tempWord = words.get(i);
			map.put(tempWord, new LinkedList<Word>());
		}
	
		//lägg till alla 4:a bokstäver långa ord till bucketsen.
		Set<Word> keySet = map.keySet();
		System.out.println(keySet.size());
		for (Word key : keySet) {
			for (int i = 0; i < words.size(); i++) {
				Word word = words.get(i);
				if (word.checkMatch(key.getKey()) && !word.toString().equals(key.toString())) {
					map.get(key).add(word);
				}
			}
		}

		//		ArrayList<LinkedList<Word>> ord = new ArrayList<LinkedList<Word>>();
//		
//		for (Word w : words) {
//			ord.add(map.get(w.getKey()));
//		}
//		
//		System.out.println(ord.toString());
		System.out.println(map.toString());
	}

	public static class Word {
		private String string, sortedString;

		public Word(String string) {
			this.string = string;

			sortedString = string.substring(1);
			char[] unsortedChars = sortedString.toCharArray();

			Arrays.sort(unsortedChars);
			sortedString = new String(unsortedChars);
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
		public int hashCode(){
			return sortedString.hashCode();
		}
		public String getKey() {
			return sortedString;
		}
		
		public String toString(){
			return string;
		}
	}
}
