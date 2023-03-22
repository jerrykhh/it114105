
# Lab 2 Programming Practices 
## Topic 2.1-2.3: Variables, Data Types, Operators

### Exercise 1

(a) Type the Java program ValidateHKID below with proper format (using indentations and blank lines appropriately, merge or break lines in a meaningful way, etc.).

```java
import java.util.*;

public class Ex1{ //Validate HKID

public static void main(String[] args) {

Scanner kb=new Scanner(System.in);

System.out.print("? ");

String str=kb.next();

char[] letter;

int sum; int code;

letter = str.toCharArray();

letter[0] = Character.toUpperCase(letter[0]);

sum = (

(int)letter[0] -64 )

*8;

sum = sum + ( (int)letter[1] -48 )*7;

sum = sum + ( (int)letter[2] -48 )*6;

sum = sum + ( (int)letter[3] -48 )*5;

sum = sum + ( (int)letter[4] -48 )*4;

sum = sum + ( (int)letter[5] -48 )*3;

sum = sum + ( (int)letter[6] -48 )*2;

code = 11 - (sum % 11);

System.out.print("The HKID is: "

+ letter[0] +

str.substring(1,7));

if(code == 11) {System.out.println("(0)");} else if(code == 10) {

System.out.println("(A)");}

else { System.out.println("(" + code +

")"); }

}}
```

(b) Compile and execute the program. State the purpose of the program.

(c) At the top of your program, insert the following multi-comment to document who the writer of the program is. (Writing comments alongside program statements is called inline documentation.)
```
/*
    Filename: Ex1.java
    Programmer: <YOUR NAME>
    Description: Determine the check digit of HKID numbers.
*/
```
```
c:\> java Ex1
? A123456
The HKID is: A123456(3)
```

### Exercise 2 Programming Exercise
Total Cholesterol (TC, 總膽固醇), HDL cholesterol (HDLC, 高密度膽固醇), and triglyceride (TG, 甘油三酯) levels are measured directly from a blood sample. LDL cholesterol (LDLC, 低密度膽固醇) is calculated by using the formula:

```math
LDLC = TC - HDLC - \frac{TG}{5}
```

Write a Java program that prompts user to input TC, HDLC, and TG as double values and then calculate and display the LDLC as shown below.

```
c:\> java Ex2
Enter TC : 234
Enter HDLC : 66
Enter TG : 104
LDLC = 147.2
```
Study the program below for how keyboard input can be performed.
```java
import java.util.Scanner;

public class Ex2 {

    public static void main( String[] args ) {

        // Create a Scanner object for console input
        Scanner input = new Scanner(System.in);
        // Declare variables
        double n, j, result;
        System.out.println( "This is a template program for console I/O." );
        System.out.print( "Enter n: " );
        n = input.nextDouble(); // input a double from keyboard
        System.out.print( "Enter j: " );
        j = input.nextDouble(); // input a double from keyboard
        result = n + j;
        System.out.println( "result = " + result );

    }

}
```