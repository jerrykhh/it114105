public class QuickVsBubble {
	public static void main(String [] args) {
		int n = Integer.parseInt(args[0]);
		int [] arrayQS = new int[n];
		int [] arrayBS = new int[n];

		for (int i=0; i<n; i++) {
			int x = (int) (Math.random() * 10000);
			arrayQS[i] = x;
			arrayBS[i] = x;
		}

		long startTime, endTime;

		startTime = System.nanoTime();
		quicksort(arrayQS, 0, arrayQS.length-1);
		endTime = System.nanoTime();
		System.out.println("Quick Sort = " + (endTime-startTime));

		startTime = System.nanoTime();
		bubbleSort(arrayBS);
		endTime = System.nanoTime();
		System.out.println("Bubble Sort = " + (endTime-startTime));
	}

	 public static void quicksort (int array[], int first, int last) {
	   int pivot;

	   if (first >= last)
		  return;

	   pivot = partition(array, first, last);

	   quicksort(array, first, pivot - 1);    /* sort left side   */
	   quicksort(array, pivot + 1, last);     /* sort right side  */
	}

	public static int partition(int array[], int left, int right){
	 	int i = left;

		while (true) {
			while (array[i] <= array[right] && i != right)
				--right;

				if (i == right)
				    return i ;

				if (array[i] > array[right]) {
				    swap(array, i, right);
				    i = right;
				}
			while (array[left] <= array[i] && left != i )
				++left;

				if (i == left)
					return i;

				if (array[left] > array[i]) {
					swap(array, i, left);
					i = left;
				}
		}
	}

	 public static void swap(int [ ] arr, int x, int y) {
		int temp=arr[x];
		arr[x] = arr[y];
		arr[y] = temp;
	}


	public static void bubbleSort(int [] array) {
		for (int pass=0; pass < array.length-1; pass++)
			for (int i=0; i < array.length-pass-1; i++)
				if (array [ i ] > array [ i+1 ])
					swap(array, i, i+1);
	}
}


