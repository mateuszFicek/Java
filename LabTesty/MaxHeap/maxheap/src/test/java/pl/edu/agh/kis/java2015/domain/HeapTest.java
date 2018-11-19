package pl.edu.agh.kis.java2015.domain;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.ArrayList;

public class HeapTest {
	
	@Test
	public void insert0intoNewHeap_topIs0() {
		
		Heap heap = new Heap();
		heap.insert(0);
		
		assertEquals("size should be 1",1,heap.size());
		assertEquals(0,(Integer) heap.top(),0.001);
		assertEquals(1,heap.size());
	}

	@Test
	public void insert0AndThen2intoNewHeap_topIs2() {
		
		Heap heap = new Heap();
		heap.insert(0);
		heap.insert(2);
		
		assertEquals("size should be 2",2,heap.size());
		assertEquals(2,(Integer) heap.top(),0.001);
	}
	
	@Test
	public void insert0And2And3And5And6intoNewHeap_topIs6() {
		
		Heap heap = new Heap();
		heap.insert(0);
		heap.insert(2);
		heap.insert(3);
		heap.insert(5);
		heap.insert(6);
		
		assertEquals(6,(Integer)heap.top(),0.001);
	}

	@Test
    public void mergeTwoHeaps(){
	    Heap first = new Heap();
	    first.insert(1);
	    first.insert(9);
	    first.insert(5);
	    first.insert(14);
	    first.insert(2);

	    Heap sec = new Heap();
	    sec.insert(7);
	    sec.insert(3);
	    sec.insert(13);
	    sec.insert(19);
	    sec.insert(6);

	    Heap merged = first.merge(sec);
	    assertEquals("First element should be 14", 14, (Integer)merged.top(),0.001);
	    assertEquals("Size should be 10",10,merged.size(),0.001);
    }

    @Test
    public void meldTwoHeaps(){
        Heap first = new Heap();
        first.insert(1);
        first.insert(9);
        first.insert(5);
        first.insert(14);
        first.insert(2);

        Heap sec = new Heap();
        sec.insert(7);
        sec.insert(3);
        sec.insert(13);
        sec.insert(19);
        sec.insert(6);

        Heap merged = first.meld(sec);
        assertEquals("Biggest element should be 14", 19, (Integer)merged.top(),0.001);
        assertEquals("Size should be 10",10,merged.size(),0.001);
    }

    @Test
    public void extractMaxValueFromHeap(){
        Heap first = new Heap();
        first.insert(1);
        first.insert(9);
        first.insert(5);
        first.insert(14);
        first.insert(2);

        assertEquals("Extracted value should be 14",14, first.extractMax());
    }

    @Test
    public void heapifyHeapFromList(){
        Heap b = new Heap();
        b.heapify(new Double[] {1.2,1.3,1.4,1.5});

        assertEquals("Heap should contain 4 elements",4,b.size());
    }

    @Test
    public void deleteMaxValueFromHeap(){
        Heap first = new Heap();
        first.insert(1);
        first.insert(9);
        first.insert(5);
        first.insert(14);
        first.insert(2);
        assertEquals("Biggest value should be 14",14, first.extractMax());
        first.deleteMax();
        assertEquals("Biggest value should be 9",9, first.extractMax());
    }

    @Test
    public void replaceBiggestValueWithOther(){
        Heap first = new Heap();
        first.insert(1);
        first.insert(9);
        first.insert(5);
        first.insert(14);
        first.insert(2);

        first.replace(19);

        assertEquals("Biggest value should be 19",19, first.top());
    }
}
