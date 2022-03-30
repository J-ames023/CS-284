package main;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class IDLListTest {

	@Test
	public void testadd() {
		
		IDLList<Integer> list = new IDLList<Integer>();
		
		list.add(0, 123);
		list.add(0, 54);
		list.add(1, 23);
		list.add(1, 16);
		list.add(2, 26);
		list.add(3, 5);
		
		assertEquals(list.size(), 6);
	}
	
	@Test
	public void TestPrint() {
		IDLList<Integer> list = new IDLList<Integer>();
		
		list.add(0, 1);
		list.add(0, 2);
		list.add(1, 3);
		list.add(2, 4);
		list.add(3, 5);
		
		assertEquals(list.getHead(), 2);
		assertEquals(list.getLast(), 1);
		assertEquals(list.toString(), "[ 2, 3, 4, 5, 1 ]");
		
	}
	
	@Test
	public void TestRemove() {
		
		IDLList<Integer> list = new IDLList<Integer>();
		
		list.add(0, 1);
		list.add(0, 2);
		list.add(1, 3);
		list.add(2, 4);
		list.add(3, 5);
		
		assertEquals(list.removeAt(3), 5);
		assertEquals(list.removeLast(), 1);
		assertEquals(list.getHead(), 2);
		assertEquals(list.toString(), "[ 2, 3, 4 ]");
		
	}
	
	@Test
	void testEmptyList() {
		IDLList<Integer> list = new IDLList<Integer>();
		
		assertThrows(IllegalStateException.class, () -> { list.getHead(); } );
		assertThrows(IllegalStateException.class, () -> { list.getLast(); } );
		assertThrows(IllegalStateException.class, () -> { list.remove(); } );
		assertThrows(IllegalStateException.class, () -> { list.removeLast(); } );
		assertThrows(IllegalStateException.class, () -> { list.removeAt(0); } );

	}

}
