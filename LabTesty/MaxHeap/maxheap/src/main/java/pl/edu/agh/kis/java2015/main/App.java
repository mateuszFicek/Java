package pl.edu.agh.kis.java2015.main;

import pl.edu.agh.kis.java2015.domain.Heap;

public class App {
        public static void main(String[]args){
            Heap a = new Heap();
            a.insert(1);
            a.insert(3);
            a.insert(10);
            a.insert(15);
            a.insert(2);
            a.insert(7);

            a.resortHeap();
            a.print();
            System.out.println("\n" + a.extractMax()+" \n");
            a.deleteMax();
            a.print();
        }
    }