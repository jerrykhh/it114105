# Lab 8 Repetition Part 2 (Nested Loops)
## Topic 3: Basic Program Structures

### Intended Learning Outcomes

Upon completion of this lab, you should be able to:
- Trace loops to determine their actions, such as the number of iterations.
- Use loops to process elements in an array.
- Use nested loops to generate two-dimensional tables.

## Exercise 1
What will be the value of x after executing the program fragments below?

(a)
```java
int x, a=0;
for (x=9; x>=1; x--) {
	a++;
	if (a == 4)
		break;
}
// x is 6
```
(b)
```java
int a=9, x=0;
while (a > 0) {
	a--;
	x+=2;
	if (a < 3)
		continue;
	x+=3;
}

// x is 36
```

## Exercise 2
Will the programs below terminate? If so, give the output.
(a)
```java
int balance = 100;
while ( true ) {
   if ( balance < 12 )
      break;
   balance = balance – 12;
}
System.out.println( "Balance is " + balance );

// Output:
Balance is 4
```
(b)
```java
int balance = 100;
while ( true ) {
   if ( balance < 12 )
      continue;
   balance = balance – 12;
}
System.out.println( "Balance is " + balance );

//  loop
```

## Exercise 3
What will be the number of times the statement marked with a comment executes?

(a)
```java
for (i=0; i<5; i++)
	for (j=0; j<7; j++)
		System.out.println("loop");   // statement in question

// 5*7 = 35 times
```
(b)
```java
for (i=0; i<5; i++)
	for (j=0; j<7; j++)
		for (k=0; k<6; k++)
			System.out.println("another loop");
			System.out.println("loop");   // statement in question

// 1 times, due to for loop not {}. Therefore, the loop will only handle next line only.
```

## Exercise 4
Determine the output of the program below.

(a)
```java
public class Test {
   public static void main ( String[] args ) {
      for ( int i = 1; i < 5; i++ ) {
         int j = 0;
         while ( j < i ) {
            System.out.print( j + " " );
            j++;
         }
      }
      System.out.println();
   }
}

// Output:
0 0 1 0 1 2 0 1 2 3
```

(b)
```java
public static void main( String[] args ) {
   String s1;
   for ( int row=1; row<=5; row++ ) {
      s1 = "";
      for ( int column=1; column<=5; column++ ) {
         s1 += ( row == column ) ? "*" : " ";
      }
      System.out.println( s1 );
   } 
}

// Output:
*    
 *   
  *  
   * 
    *
```

(c)
```java
for (int i=0; i<2; i++) {
	System.out.println("outer " + i);
	for (int j=0; j<3; j++) {
		System.out.println("Inner " + i + " " + j);
	}
	for (int k=2; k>0; k--) {
		System.out.println("Inner " + i + " " + k);
	}
}

// Output:
outer 0
Inner 0 0
Inner 0 1
Inner 0 2
Inner 0 2
Inner 0 1
outer 1
Inner 1 0
Inner 1 1
Inner 1 2
Inner 1 2
Inner 1 1
```

## Exercise 5
In lecture notes 3.2 (Repetition Structures – Part 2), the Java program FindPrime is used to illustrate nested-loops.

```java
public class FindPrime {

    public static void main(String[] args) {
        int count = 1;

        System.out.print(2); // 2 is the first prime number
        int num = 2;

        while (count < 20) {
            num++; // try next number
            boolean isPrime = true;
            for (int i = 2; i < num; i++) {
                if (num % i == 0) // divisible by i
                    isPrime = false; // not a prime
            }
            if (isPrime) {
                count++; // one more prime is found
                System.out.print(", " + num);
            }
        }
        System.out.println("\nDone");
    }
}
```

(a) Add some program statements to count the number of times the inner-loop has been executed, and verify that it agrees with the value 2415 given in the lecture notes.
```
c:\> java FindPrime
2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71
Done
Total calculation = 2415
```

(b) Modify the program so that the number of times the inner-loop is executed can be reduced without sacrificing the correctness of the program. Report the number of times the inner-loop has been executed in your improved program.
```
c:\> java FindPrime2
2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71
Done
Total calculation = 677
```

## Exercise 6
Write a program that asks the user to input an integer. The program then prints a pattern with the number of lines controlled by the user input. You must use a nested-for loop.

(a)
```
c:\exercises> java Pattern
Number? 4
1
12
123
1234
```

(b)
```
c:\exercises> java Pattern2
Number? 5
    1
   12
  123
 1234
12345
```

## Exercise 7
Generate a Multiplication Table:
Write a Java program to fill values to a two-dimensional array, mTable[10][10], for a multiplication table. Print the multiplication table as follows.

```
      0  1  2  3  4  5  6  7  8  9
   +------------------------------
  0|  0  0  0  0  0  0  0  0  0  0
  1|  0  1  2  3  4  5  6  7  8  9
  2|  0  2  4  6  8 10 12 14 16 18
  3|  0  3  6  9 12 15 18 21 24 27
  4|  0  4  8 12 16 20 24 28 32 36
  5|  0  5 10 15 20 25 30 35 40 45
  6|  0  6 12 18 24 30 36 42 48 54
  7|  0  7 14 21 28 35 42 49 56 63
  8|  0  8 16 24 32 40 48 56 64 72
  9|  0  9 18 27 36 45 54 63 72 81
```

## Exercise 8
Write a Java program to prompt a user to input some positive real numbers and store them in an array. The user can enter no more than 10 numbers. The program should stop prompting input when the user has entered the 10th number or input a negative value, e.g. -1. Then, the program starts to calculate the following statistics.
1. Sum = $\sum_{i=1}^{n}x_i$
2. Mean, $\overline{x} = \frac{Sum}{n}$
3. Maximum
4. Minimum
5. Standard Deviation, $\sigma = \sqrt{\frac{\sum_{i=1}^{n}(x_i-\overline{x})^2}{n-1}}$ (for $n>1$ only)

Test your program with the following five numbers, 1.23, 2.05, 4.0, 0.01, 0.12. Their sum=7.41, mean=1.48, maximum=4.0, minimum=0.01, and standard deviation=1.64.

## Exercise 9
Approximate sin x by a sum of series:
Write a Java program to calculate the following series ($x$ is in radians, $360° = 2\pi$ radians):

```math
\sin(x)=x-\frac{x^3}{3!}+\frac{x^5}{5!}-\frac{x^7}{7!}+\frac{x^9}{9!}-\frac{x^{11}}{11!}...+\frac{x^{25}}{25!}-\frac{x^{27}}{27!'}, -3.1416 < x < 3.1416
```

**Sample Output**
```
C:\>java SinX
Enter x (in radians): _2_
Term 1 = 2.0 
Term 2 = -1.3333333333333333 
Term 3 = 0.26666666666666666 
Term 4 = -0.025396825396825393 
Term 5 = 0.0014109347442680773 
Term 6 = -5.130671797338463E-5 
Term 7 = 1.3155568711124264E-6 
Term 8 = -2.5058226116427168E-8 
Term 9 = 3.6850332524157597E-10 
Term 10 = -4.309980412182175E-12 
Term 11 = 4.1047432496973094E-14 
Term 12 = -3.2448563238713907E-16 
Term 13 = 2.163237549247594E-18 
Term 14 = -1.2326139881752673E-20 
Sin(2.0)=0.9092974268256817
```