public class Reverse {
    public static void main(String [ ] args) throws StackFullException, StackEmptyException {
		String [ ] arr = {"Abby", "Boris", "Cat", "Dino", "Elvis"};
		Stack stack = new ArrayStack();
                for (int i=0; i<arr.length; i++) {
			stack.push(arr[i]);
		}
		for (int i=0; i<arr.length; i++) {
			arr[i] = (String) stack.pop();
		}
		for (int i=0; i<arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}
}
