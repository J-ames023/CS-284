package main;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import java.io.IOException;

import org.junit.jupiter.api.Test;

class AnagramsTest {

	@Test
	void ManualTextInsertion() {
		Anagrams a =  new Anagrams();
		
		a.addWord("least");
		a.addWord("setal");
		a.addWord("james");
		a.addWord("slate");
		a.addWord("stale");
		a.addWord("steal");
		a.addWord("stela");
		a.addWord("taels");
		a.addWord("tales");
		a.addWord("teals");
		a.addWord("tesla");
		a.addWord("damagetesting");
		a.addWord("testingdamage");
		
		assertEquals(a.getMaxEntries().toString(), "[3872198=[least, setal, slate, stale, steal, stela, taels, tales, teals, tesla]]");

	}
	
	@Test
	void TxtTextInsertion() {
		Anagrams a =  new Anagrams();
		
		try {
			a.processFile("words_alpha.txt");
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		assertEquals(a.getMaxEntries().toString(), "[236204078=[alerts, alters, artels, estral, laster, lastre, rastle, ratels, relast, resalt, salter, slater, staler, stelar, talers]]");
	}
	
	@Test
	void myHashCodeTest() {
		Anagrams a =  new Anagrams();
		
//		a.myHashCode("test");
//		a.myHashCode("testing");
		
		assertEquals(a.myHashCode("alters"), 236204078);
//		assertEquals(testing, 62463943421);
		
	}
	
	@Test
	void DuplicateTest() {
		Anagrams a =  new Anagrams();
		
		a.addWord("test");
		
		assertThrows(IllegalArgumentException.class, () -> { a.addWord("test"); });
	}
	
	@Test
	void NonAlphabetWordTest() {
		Anagrams a =  new Anagrams();
		
		assertThrows(IllegalArgumentException.class, () -> { a.addWord("test!"); });
	}
	
	
}

