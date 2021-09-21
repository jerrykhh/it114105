
public class Ex5b {
    public static void main(String [ ] args) {
        int times = 0;
		int count=1;
		System.out.print(2);  // 2 is the first prime number
		int num=2;
		while (count < 20) {
			num++;			// try next number
			boolean isPrime=true;
			for (int i=2; i<num; i++) {
                times++;
				if (num%i == 0 ){   	// divisible by i
					isPrime = false;    // not a prime
                    break;
                }
            }
			if (isPrime) {
				count++;	// one more prime is found
				System.out.print(", " + num);
			}
		}
		System.out.println("\nDone");
        System.out.println("Total calculation = " + times);
	}
}
