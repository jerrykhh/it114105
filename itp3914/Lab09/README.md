# Lab 9 Methods and Parameter Passing
## Topic 3: Basic Program Structures

## Exercise 1
Show the output of the following programs.

(a)
```java
public class Q1a {
   public static void main( String[] args ) {
      question();
      answer();
   }

   public static void answer() {
      System.out.println("javac.exe");
   }

   public static void question() {
      System.out.println("What is the command to compile a Java program?");
   }
}

// Output:
What is the command to compile a Java program?
javac.exe
```

(b)
```java
public class Q1b {
   public static void main( String[] args ) {
      firstName("Peter");
   }

   public static void firstName( String Name ) {
      System.out.println("Call me " + Name + "." );
   }
}

// Output:
Call me Peter.
```

(c)
```java
public class Q1c {
   public static void main( String[] args ) {
      potato( 1 );
      potato( 2 );
      potato( 3 );
   }

   public static void potato( int Quantity ) {
      System.out.println(Quantity + " potato");
   }
}

// Output:
1 potato
2 potato
3 potato
```

(d)
```java
public class Q1d {
   public static void main( String[] args ) {
      characterType( 'A' );
      characterType( 'z' );
      characterType( '5' );
   }

   public static void characterType( char ch ) {
      System.out.print( "'" + ch + "' is a " );
      if ( ( ch >= 'A' ) &&  ( ch <= 'Z' ) )
         System.out.println( "upper-case letter." );
      else if ( ( ch >= 'a' ) &&  ( ch <= 'z' ) )
         System.out.println( "lower-case letter." );
      else if ( ( ch >= '0' ) &&  ( ch <= '9' ) )
         System.out.println( "digit." );
   }
}

// Output:
'A' is a upper-case letter.
'z' is a lower-case letter.
'5' is a digit.
```

## Exercise 2
Show the output of the following programs.

(a)
```java
public class Q2a {
   public static void main( String[] args ) {
      double acres = 5;
      System.out.println( "You can park about " + cars(acres)+ " cars." );
   }

   public static double cars( double x ) {
      return 100 * x;
   }
}

// Output:
You can park about 500 cars.
```

(b)
```java
public class Q2b {
   public static void main( String[] args ) {
      double r = 1; // Radius of the base of a cylinder
      double h = 2; // Height of a cylinder
      displayVolume( r, h );
      r = 3;
      h = 4;
      displayVolume( r, h );
   }
   public static double getArea( double r ) {
      return 3.14159 * r * r;
   }
   public static void displayVolume( double r, double h ) {
	  double area = getArea(r);
      System.out.println("Volume of cylinder having base area " + area +
                      " and height " + h + " is " + ( h * area ) + "." );
   }
}

// Output:
Volume of cylinder having base area 3.14159 and height 2.0 is 6.28318.
Volume of cylinder having base area 28.274309999999996 and height 4.0 is 113.09723999999999.
```

(c)
```java
public class Q2c {
   public static void main( String[] args ) {
	  double maxResult = max4( 2.3, 4.9, -5.1, 0.0 );
      System.out.println( "The maximum is " + maxResult);
   }

   public static double max2( double a, double b ) {
      return ( a > b ) ? a : b;
   }

   public static double max4( double a, double b, double c, double d ) {
      return max2( max2( a, b ), max2( c, d ) );
   }
}

// Output:
The maximum is 4.9
```

(d)
```java
import java.util.*;
 
public class Q2d {
	public static void main(String [] args) {
		Scanner kb = new Scanner(System.in);
		int street;
 
		System.out.print("Street number? ");
		street = kb.nextInt();
 
		if (isEven(street)) 
			System.out.println("East-bound");
		else
			System.out.println("West-bound");
	}
 
	public static boolean isEven( int num ) {
      return num%2 == 0;
   	}
}

// Output:
Street number? 1
West-bound
```


## Exercise 3
Complete the method `countDown()` in the program below. The program allows the users to enter an integer value and prints the counting down of the integer. Example executions are shown below. User inputs are underline.

```
c:\> java Ex3
? _3_
3 2 1
```

```
c:\> java Ex3
? _5_
5 4 3 2 1
```

```java
import java.util.*;

public class Ex3 {

  public static void main(String[] args) {
    Scanner kb = new Scanner(System.in);
    System.out.print("? ");
    int num = kb.nextInt();
    countDown(num);

  }

  public static ________ countDown(_____________) {
    ___ missing codes ____
  }

}
```

## Exercise 4 
**Ideal Age of Wife**: According to Plato, a man should marry a woman whose age is half his age plus seven years. Write a Java program that requests a man’s age as input. The `main()` method then calls a method `idealAge()`, passing the man’s age as argument. The method then returns to the ideal age of his wife for the `main()` method to print on the screen. The program skeleton is shown below.

```java
import java.util.Scanner;
public class Ex4 {
  public static void main(String[] args) {
    Scanner kb = new Scanner(System.in);
    System.out.print("Your age? ");
    int manAge = kb.nextInt();
    int wifeAge;

    // call idealAge() with manAge as argument

    System.out.println("Ideal age of wife = " + wifeAge);

  }

  public static _______ idealAge(____________) {
    ___ missing codes ____
  }
}
```

```
c:\> java Ex4
Your age? _18_
Ideal age of wife = 16
```

## Exercise 5
Write a Java program to let users enter an integer. The integer is then passed to a `boolean` method as argument. The method checks if the integer is divisible by 7. Return `true` if so, and `false` otherwise. Test you method by developing a complete Java program and calling the method from `main()`.

```java
import java.util.Scanner;

public class Ex5 {
  public static void main(String[] args) {
    Scanner kb = new Scanner(System.in);
    System.out.print("? ");
    int num = kb.nextInt();
    if (________________) // call isDivisibleBy7() with num as argument
      System.out.println(num + " is divisible by 7");
    else
      System.out.println(num + " is not divisible by 7");

  }

  public static __________ isDivisibleBy7(___________) {
    ___ missing codes ____
  }
}
```

```
c:\> java Ex5
? _21_
21 is divisible by 7
```

## Exercise 6
Write a Java program to let users enter the three sides of a triangle. The three sides are then passed to a `boolean` method as argument. The method checks if it the triangle is right-angled by using the Pythagorean Theorem $a^2 + b^2 = c^2$. Test you method by developing a complete Java program and calling the method from `main()`.

```
c:\> java Ex6
a ? _3_
b ? _4_
c ? _5_

It is a right-angled triangle
```

```
c:\> java Ex6
a ? _3_
b ? _3_
c ? _4_

It is not a right-angled triangle
```

## Exercise 7
In Lab 8 Exercise 5 (b), you have developed a Java program to print 20 prime numbers on screen. You are now required to enhance the functionality of the program.
- Move the program logic for checking whether a number is prime to a `boolean` method `isPrime()`. The method receives the integer to be checked as a parameter.
- Modify the program so that users are allowed to enter the integer to be checked during runtime.
- NOTE: The new program does not need to find 20 prime numbers, thus the program logic is much more simple.

Example executions are shown below. User inputs are highlighted in underline.

```
c:\> java Ex7
? _6_
6 is not a prime number
```

```
c:\> java Ex7
? _11_
11 is a prime number
```