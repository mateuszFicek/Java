package pl.edu.agh.kis.java2015.domain;

import java.util.ArrayList;

public class Heap<T extends  Comparable<T>> {

	private int heapSize = 0;
	private ArrayList<T> tab = new ArrayList<>();

	public void insert(T value) {
		int currentIndex = heapSize;
		int parentIndex = parentIndex(currentIndex);
		tab.add(value);
		while( isChildGreaterThanParent(currentIndex, parentIndex)>0 ) {
			swapElements(currentIndex, parentIndex);
			currentIndex = parentIndex;
			parentIndex = parentIndex(currentIndex);
		}
		heapSize++;
	}

	public int isChildGreaterThanParent(int currentIndex, int parentIndex) {
		return tab.get(currentIndex).compareTo(tab.get(parentIndex));
	}

	public void swapElements(int currentIndex, int parentIndex) {
        T parentValue = parentValue(currentIndex);
        T currentValue = tab.get(currentIndex);
        tab.set(parentIndex, currentValue);
        tab.set(currentIndex, parentValue);
	}

	public T parentValue(int currentIndex) {
        T parentValue = tab.get(parentIndex(currentIndex));
        return parentValue;
	}

	public int parentIndex(int currentIndex) { return (currentIndex - 1 )/2;
	}
	public int size() {
		return heapSize ;
	}

	public T top() {
		return tab.get(0);
	}

    public T extractMax(){
	    T last = tab.get(0);
	    return last;
    }

    public void deleteMax(){
	    T last = tab.get(heapSize-1);
	    tab.remove(last);
	    tab.set(0,last);
	    heapSize--;
	    resortHeap();
    }

    public void replace(T newValue){
	    tab.set(0,newValue);
	    resortHeap();
    }

    public void resortHeap(){
        int currentIndex = 1;
        int parentIndex = parentIndex(currentIndex);
        while( currentIndex<heapSize ) {
            if(currentIndex+1<heapSize && tab.get(currentIndex+1).compareTo(tab.get(currentIndex))>0){
                currentIndex = currentIndex+1;
            }
            if(isChildGreaterThanParent(currentIndex,parentIndex)>0)
            {
                swapElements(currentIndex,parentIndex);
            }
            parentIndex = currentIndex;
            currentIndex = 2*currentIndex+1;

        }
    }

    public void print(){
	    for(int i = 0; i <heapSize; i++){
	        System.out.println(tab.get(i));
        }
    }
    public void heapify(T[] elements){
        for(T value: elements){
            insert(value);
        }
    }

    public Heap merge(Heap toMerge){
        Heap newMerged = new Heap();

        newMerged.tab.addAll(tab);
        newMerged.tab.addAll(toMerge.tab);
        newMerged.heapSize = newMerged.tab.size();
        return newMerged;
    }

    public Heap meld(Heap toMeld){
        Heap melded = new Heap();
        ArrayList<T> toAdd = new ArrayList<>();
        toAdd.addAll(toMeld.tab);
        toAdd.addAll(tab);
        for(T element: toAdd){
            melded.insert(element);
        }

        return melded;
    }
}
