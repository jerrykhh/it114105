public class QuickSort {

    public static void main(String [ ] args) {
            int [ ] arr = {21, 13, 8, 42, 19, 5, 34, 61};
            quicksort(arr, 0, arr.length-1);
            for (int i=0; i<arr.length; i++)
                System.out.print(arr[i] + " ");
            System.out.println();
    }
 
 
    public static void quicksort (int array[], int first, int last) {
           int pivot;
           int i;
 
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
 }
 
 