public class BubbleSort {
    public static void main(String [] args) {
		int [] arr = {21, 13, 8, 42, 19, 5, 34, 61};
		bubbleSort(arr);

		for (int i=0; i<arr.length; i++)
			System.out.print(arr[i] + " ");
		System.out.println();
	}

	public static void bubbleSort(int [] array) {
		for (int pass=0; pass < array.length-1; pass++) 
			for (int i=0; i < array.length-pass-1; i++) 
				if (array [ i ] > array [ i+1 ])
					swap(array, i, i+1);	
	}

	public static void swap (int [ ] array, int first, int second) {
		int temp = array [ first ];
		array [ first ] = array [ second ];
		array [ second ] = temp;
	}
}
