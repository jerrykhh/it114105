## Exercise 1
(a)
```
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

# Output:
2
```

(b)
```
public static void main( String[] args ) {
   int j = 2;
   String s = "";
   while ( j <= 8 ) {
      s += "*";
      j += 2;
   }
   System.out.println( j + " " + s );
}

# Output:
10 ****
```
(c)
```
public static void main( String[] args ) {
   int i;
   for ( i=-9; i<=-1; i+=3 )
      System.out.print( i + " " );
}

# Output:
-9 -6 -3
```

## Exercise 2
(a)
```
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
```
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
```
For (int x=100, x >= 1, x++);
	System.out.println(x);
```
Syntax error: for (int x=100; x >= 1; x++)  
Logic error: x will always be greater than 1 and the loop will never terminate.
Having a syntactically correct program doesn&#39;t mean that your program is producing the
correct result.

## Exercise 3
```
int x=1, y=9;
for (int i=0; i<y; i+=3)
    x++;

# x is 4
```

## Exercise 4

(a)
```
int x=12;
do {
	System.out.print(x + " ");
	x--;
} while (x > 7);

# Output:
12 11 10 9 8
```

(b)
```
for (int i=10; i<=25; i+=5)
	System.out.print( (i/5) + " ")

# Output:
2 3 4 5
```

## Exercise 5
```
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

# Output:
1
4
9
16
25
Total is 55
```
