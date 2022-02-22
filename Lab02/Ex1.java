/*
   Filename:	 	Ex1.java
   Programmer:	<YOUR NAME>
   Description:	Determine the check digit of HKID numbers.
*/

import java.util.*;

public class Ex1 { // Validate HKID
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        System.out.print("? ";
        String str = kb.next();
        char[] letter;
        int sum;
        int code;
        letter = str.toCharArray();
        letter[0] = Character.toUpperCase(letter[0]);
        sum = ((int) letter[0] - 64) * 8;
        sum = sum + ((int) letter[1] - 48) * 7;
        sum = sum + ((int) letter[2] - 48) * 6;
        sum = sum + ((int) letter[3] - 48) * 5;
        sum = sum + ((int) letter[4] - 48) * 4;
        sum = sum + ((int) letter[5] - 48) * 3;
        sum = sum + ((int) letter[6] - 48) * 2;
        code = 11 - (sum % 11);
        System.out.print("The HKID is: " + letter[0] + str.substring(1, 7));
        if (code == 11) {
            System.out.println("(0)");
        } else if (code == 10) {
            System.out.println("(A)");
        } else {
            System.out.println("(" + code + ")");
        }
    }
}
