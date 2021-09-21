## Exercise 1
(a)
```
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

# Output:
What is the command to compile a Java program?
javac.exe
```
(b)
```
public class Q1b {
   public static void main( String[] args ) {
      firstName("Peter");
   }

   public static void firstName( String Name ) {
      System.out.println("Call me " + Name + "." );
   }
}

# Output:
Call me Peter.
```

(c)
```
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

# Output:
1 potato
2 potato
3 potato
```

(d)
```
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

# Output:
'A' is a upper-case letter.
'z' is a lower-case letter.
'5' is a digit.
```

## Exercise 2

(a)
```
public class Q2a {
   public static void main( String[] args ) {
      double acres = 5;
      System.out.println( "You can park about " + cars(acres)+ " cars." );
   }

   public static double cars( double x ) {
      return 100 * x;
   }
}

# Output:
You can park about 500 cars.
```

(b)
```
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

# Output:
Volume of cylinder having base area 3.14159 and height 2.0 is 6.28318.
Volume of cylinder having base area 28.274309999999996 and height 4.0 is 113.09723999999999.
```

(c)
```
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

# Output:
The maximum is 4.9
```

(d)
```
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

# Output:
Street number? 1
West-bound
```