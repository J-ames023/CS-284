package main;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.HashMap;

public class Anagrams {

	final Integer[] primes = { 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83,
			89, 97, 101 };

	Map<Character, Integer> letterTable;
	Map<Long, ArrayList<String>> anagramTable;

	public Anagrams() {
		letterTable = new HashMap<Character, Integer>();
		anagramTable = new HashMap<Long, ArrayList<String>>();
		buildLetterTable();
	}

	/**
	 * This method buildLetterTable should be invoked by the constructor for the
	 * class Anagrams and should build the hash table letterTable which consists of
	 * the first 26 prime numbers.
	 */
	public void buildLetterTable() {

		char[] alphabet = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r',
				's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };

		for (int i = 0; i < 26; i++) {
			letterTable.put(alphabet[i], primes[i]);
		}
	}

	/**
	 * Method addWord should compute the hash code of the string s passed as
	 * argument, and should add this word to the hash table anagramTable.
	 * 
	 * @param s
	 */
	public void addWord(String s) {
		if (s == null || s == "") { //s cannot be empty
			throw new IllegalArgumentException("Word doesn't exist.");
		}

		
		if (!Pattern.matches("[a-z]+", s)) { //checks characters if only in Alphabet.
			throw new IllegalArgumentException("Illegal character used.");
		}
		
		long hash = myHashCode(s);

		if (!anagramTable.containsKey(hash)) { //if anagram does not contain existing hash
			ArrayList<String> CurrString = new ArrayList<String>();
			CurrString.add(s);
			anagramTable.put(hash, CurrString);
		}
		else {
			ArrayList<String> CurrAnagram = anagramTable.get(hash); // Temporary value for current anagram
			
			if (CurrAnagram.contains(s)) {
				throw new IllegalArgumentException("This word already exists in list.");
			}

			CurrAnagram.add(s); // now most up to date value
			anagramTable.replace(hash, CurrAnagram); // update anagram table with latest curr anagram value
		}
	}

	/**
	 * This method, given a string s, should compute its hash code. Should throw
	 * IllegalArgumentException if string s is empty.
	 * 
	 * @param s
	 * @return
	 */
	public long myHashCode(String s) {
		if (s == null || s == "")
			throw new IllegalArgumentException("String s is empty.");

		int hash = 1;
		for (int i = 0; i < s.length(); i++) { //checks for string length
			hash *= letterTable.get(s.charAt(i)); //multiplies each letter in string together
		}
		return hash;
	}

	/**
	 * The main method is processFile which receives the name of a text file
	 * containing words, one per line, and builds the hash table anagramTable. For
	 * that it uses the addWord method.
	 * 
	 * @param s
	 * @throws IOException
	 */
	public void processFile(String s) throws IOException {
		FileInputStream fstream = new FileInputStream(s);
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
		String strLine;

		while ((strLine = br.readLine()) != null) {
			this.addWord(strLine);
		}
		br.close();
	}

	// toString method

	/**
	 * This method should return the entries in the anagramTable that have the
	 * largest number of anagrams. It returns a list of them since there may be more
	 * than one list of anagrams with a maximal size.
	 * 
	 * @return
	 */
	public ArrayList<Map.Entry<Long, ArrayList<String>>> getMaxEntries() {
		// First, initialize a list for making max entry
		ArrayList<Map.Entry<Long, ArrayList<String>>> MaxEntry = new ArrayList<Map.Entry<Long, ArrayList<String>>>();
		int CurrMax = 0; // Current Max Entry

		for (Map.Entry<Long, ArrayList<String>> CurrEntry : anagramTable.entrySet()) { // Iteration over every entry in
																						// anagramTable
			if (CurrEntry.getValue().size() > CurrMax) { // if there are more entries of the current anagram than
															// current highest max entries, remove current max and
				MaxEntry.clear();
				MaxEntry.add(CurrEntry); // Adds new entry as the current
				CurrMax = CurrEntry.getValue().size(); // Using the new entry, new tally begins for total max entries
			}

			else if (CurrEntry.getValue().size() == CurrMax) { // CurrEntry is the same size as the Current Max entry
				MaxEntry.add(CurrEntry); // Adds another entry
			}
		}
		return MaxEntry;

	}

	/**
	 * The main method will read all the strings in a file, place them in the hash
	 * table of anagrams and then iterate over the hash table to report which words
	 * have the largest number of anagrams.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Anagrams a = new Anagrams();
		final long startTime = System.nanoTime();
		try {
			a.processFile("words_alpha.txt");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		ArrayList<Map.Entry<Long, ArrayList<String>>> maxEntries = a.getMaxEntries();
		final long estimatedTime = System.nanoTime() - startTime;
		final double seconds = ((double) estimatedTime / 1000000000);
		System.out.println("Time: " + seconds);
		System.out.println("List of max anagrams: " + maxEntries);
	}

}
