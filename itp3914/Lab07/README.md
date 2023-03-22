# Lab 7 Repetition Part 1
## Topic 3: Basic Program Structures

### Intended Learning Outcomes
Upon completion of this tutorial/lab, you should be able to:
- Use loops to perform searching on a text string.
- Use different types of loops to calculate sum of series.

## Exercise 1
Determine the output of the following Java programs.

(a)
```java
public static void main( String[] args ) {
   double balance = 1000;      // initialize to $1000
   double interestRate = 0.1;
   int years = 0;               // number of years
   do {
      balance = ( 1 + interestRate ) * balance;
      years += 1;
   } while ( balance <= 1200 );
   System.out.println( years );
}

// Output:
2
```

(b)
```java
public static void main( String[] args ) {
   int j = 2;
   String s = "";
   while ( j <= 8 ) {
      s += "*";
      j += 2;
   }
   System.out.println( j + " " + s );
}

// Output:
10 ****
```
(c)
```java
public static void main( String[] args ) {
   int i;
   for ( i=-9; i<=-1; i+=3 )
      System.out.print( i + " " );
}

// Output:
-9 -6 -3
```

## Exercise 2

Identify and correct the error(s) in the program fragment below.

(a)
```java
int c=0, product=1;
while (c <= 5) {
	product *= c;
	++c;

```
Logic error:
The variable product is always 0 after each loop, thus the loop is having no use.
Having a syntactically correct program doesn't mean that your program is producing the
correct result.

(b)
```java
int x=1, total;
While (x <= 10) {
	total += x;
	x++;
}
```
Syntax error: While should be while.  
Better: Although Java initializes numerical variables to 0 automatically if an initial value is
not specified during variable declaration, it is better to write explicitly total=0 for better
program readability.

(c)
```java
For (int x=100, x >= 1, x++);
	System.out.println(x);
```
Syntax error: for (int x=100; x >= 1; x++)  
Logic error: x will always be greater than 1 and the loop will never terminate.
Having a syntactically correct program doesn&#39;t mean that your program is producing the
correct result.

## Exercise 3
What will be the value of variable $x$ after executing the following program fragment?

```java
int x=1, y=9;
for (int i=0; i<y; i+=3)
    x++;

# x is 4
```

## Exercise 4
What will be printed by the program fragments below?

(a)
```java
int x=12;
do {
	System.out.print(x + " ");
	x--;
} while (x > 7);

// Output:
12 11 10 9 8
```

(b)
```java
for (int i=10; i<=25; i+=5)
	System.out.print( (i/5) + " ")

// Output:
2 3 4 5
```

## Exercise 5
What will be printed by the program below?

```java
public class Question {
	public static void main(String [] args) {
		int y, x=1, total=0;
		while (x <= 5) {
			y = x * x;
			System.out.println(y);
			total += y;
			x++;
		}
		System.out.println("Total is " + total);
	}
}

// Output:
1
4
9
16
25
Total is 55
```

## Exercise 6
Rewrite the following programs with while loops.

(a)
```java
public static void main( String[] args ) {
   int i; 
   for ( i=-9; i<=-1; i+=3 )
      System.out.println( i ); 
   System.out.println( "After loop, i=" + i );
}
```

(b)
```java
public static void main( String[] args ) { 
   int i = 5; 
   do { 
      System.out.println(i--); 
   } while ( i >= 0 ); 
   System.out.println( "After loop, i=" + i ); }
```

## Exercise 7
Write a program to ask the user to input an int value. The program then prints the corresponding number of ‘*’ on screen.

Shown below are two sample executions of the program. Those in underline are user inputs.

```
e:\> java Ex7
Number of stars? _5_
****
```

```
e:\> java Ex7
Number of stars? _9_
*********
```

## Exercise 8
Write a program to calculate n! where n is an integer value entered by the user.

NOTE: $n! = n  (n-1) \times (n-2) \times (n-3) \times …\times 1$

For example, $5!=5\times 5\times 4\times 3\times 2\times 1$

Shown below are two sample executions of the program. Those in __ are user inputs.

```
e:\> java Ex8
n? _3_
3! = 6
```

```
e:\> java Ex8
n? _6_
6! = 720
```

## Exercise 9
Rewrite your program in Exercise 8 so that the output becomes:

```
e:\> java Ex9
n? _3_
3 x 2 x 1 = 6
```

```
e:\> java Ex9
n? _6_
6 x 5 x 4 x 3 x 2 x 1 = 720
```

## Exercise 10
Write a program to perform the followings:
- Ask the user the number of values to enter
- Read the values from the user
- Calculate and print the average of the values.
  
Shown below is a sample execution of the program. Those in __ are user inputs.

```
e:\> java Ex10
How many values to enter? _4_
Value? _10_
Value? _24_
Value? _20_
Value? _9_
Average = 15.75
```