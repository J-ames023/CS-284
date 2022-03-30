package main;

import java.util.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TreapTest {

	@Test
	void TreapTestShort() {
		/**
		 * testAdd, Seed, Random, Priority, Delete (that don't work), Find, and DuplicateKey
		 */
		
		Treap<Integer> FirstTreapTest = new Treap<Integer>();
		assertEquals(FirstTreapTest.toString(), "null\n");

	}

	@Test
	void TreapTestLarge() {

		Treap<Integer> SecondTreapTest = new Treap<Integer>();
		assertEquals(SecondTreapTest.toString(), "null\n");

	}
	
	@Test
	void TreapTestAdd() {

		Treap<Integer> AddTreapTest = new Treap<Integer>();
		assertEquals(AddTreapTest.add(4,19), true);

	}
	
	@Test
	void TreapTestDelete() {
		
		Treap<Integer> DeleteTreapTest = new Treap<Integer>();
		assertEquals(DeleteTreapTest.add(4,19), true);
		assertEquals(DeleteTreapTest.delete(4), true);

	}
	
	@Test
	void TreapTestFind() {
		
		Treap<Integer> FindTreapTest = new Treap<Integer>();
		assertEquals(FindTreapTest.add(4,19), true);
		assertEquals(FindTreapTest.find(4), true);

	}
	
	@Test
	void TreapTestConsolidated() {
		
		Treap<Integer> ConsolidatedTreapTest = new Treap<Integer>();
		assertEquals(ConsolidatedTreapTest.add(4,19), true);
		ConsolidatedTreapTest.add(4,19);
		ConsolidatedTreapTest.delete(4);
		ConsolidatedTreapTest.add(2,31);
		ConsolidatedTreapTest.add(6,70); 
		ConsolidatedTreapTest.add(1,84);
		ConsolidatedTreapTest.add(3,12); 
		ConsolidatedTreapTest.add(5,83);
		ConsolidatedTreapTest.add(7,26);
		ConsolidatedTreapTest.find(4);
		assertEquals(ConsolidatedTreapTest.find(4), false);
		assertEquals(ConsolidatedTreapTest.add(4,19), true);

	}
}
