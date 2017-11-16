package projet00;

import java.io.IOException;
import java.util.Arrays;

/**
 * @author adiel
 * @since 01.11.17
 * @see: https://github.com/ramswaroop/Algorithms-and-Data-Structures-in-Java/blob/master/src/me/ramswaroop/common/MaxHeap.java
 */
public class MaxHeap {

	private double[] heap;
	private int size;

	public MaxHeap() {
		this.size = 0;
		this.heap = null;
	}

	public MaxHeap(double[] heap) {
		if (heap == null) {
			this.size = 0;
			this.heap = null;
		}else {
			this.size = heap.length;
			this.heap = Arrays.copyOf(heap, size);
		}
	}

	/**
	 * Build heap
	 */
	public void maxHeapify(int index) throws IOException{
		try {
			if (heap == null) return;
			int largest = index;
			int leftIndex = 2 * index + 1;
			int rightIndex = 2 * index + 2;

			if (leftIndex < size && heap[index] < heap[leftIndex]) {
				largest = leftIndex;
			}
			if (rightIndex < size && heap[largest] < heap[rightIndex]) {
				largest = rightIndex;
			}

			if (largest != index) {
				swap(index, largest);
				maxHeapify(largest);
			}
		}catch(IOException e) {
			System.err.println(e.getMessage());
		}
	}

	/**
	 * Covert Heap to MaxHeap
	 */
	public void buildMaxHeap() throws IOException {
		try {
			if (heap == null)return;
			for (int i = size / 2 - 1; i >= 0; i--) {
				maxHeapify(i);
			}
		}catch(IOException e){
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Insert into the Heap
	 */
	public void insert(double elem) throws IOException{
		if (heap == null) {
			heap = new double[] {elem};
		}else {
			// increase heap size
			heap = Arrays.copyOf(heap, size + 1);
			int i = size;
			int parentIndex = (int) Math.floor((i - 1) / 2);
			// move up through the heap till you find the right position
			while (i > 0 && elem > heap[parentIndex]) {
				heap[i] = heap[parentIndex];
				i = parentIndex;
				parentIndex = (int) Math.floor((i - 1) / 2);
			}
			heap[i] = elem;
		}
		size++;
	}

	/**
	 * @return The Maxheap biggest value   
	 */

	public double findMax() {
		if (size == 0) {
			return -1;
		} else {
			return heap[0];
		}
	}

	/**
	 * 
	 * @return Extract the max value from the Maxheap
	 */
	public double extractMax() throws IOException {
		if (size == 0) return -1;

		double min = heap[0];
		heap[0] = heap[size - 1];
		size--;
		maxHeapify(0);
		return min;
	}


	public int getSize() {
		return size;
	}

	public double[] getHeap() {
		return heap;
	}

	public void printHeap() throws IOException{
		if (heap == null) {
			System.out.println("null");
		}else {
			int iMax = size - 1, i;
			if (iMax == -1)
				System.out.print("[]");

			StringBuilder b = new StringBuilder();
			b.append('[');
			for (i = 0; i < iMax; i++) {
				b.append(heap[i]);
				b.append(", ");
			}
			System.out.println(b.append(heap[i]).append(']').toString());
		}
	}

	private void swap(int firstIndex, int secondIndex) {
		double temp = heap[firstIndex];
		heap[firstIndex] = heap[secondIndex];
		heap[secondIndex] = temp;
	}

	public void dropHeap() {
		this.heap = null;
		this.size = 0;
	}

	// test cases
	public static void main(String[] args) throws IOException {
		double[] a = new double[] {-8,-1,-2,-5,-9,0};
		MaxHeap maxHeap = new MaxHeap(a);
		maxHeap.printHeap();
		maxHeap.buildMaxHeap();
		maxHeap.printHeap();
		maxHeap.extractMax();
		maxHeap.printHeap();
		maxHeap.insert(12);
		maxHeap.printHeap();
		maxHeap.dropHeap();
		maxHeap.printHeap();
	}

}