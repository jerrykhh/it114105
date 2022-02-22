## Exercise 1
(a)
```
int x, a=0;
for (x=9; x>=1; x--) {
	a++;
	if (a == 4)
		break;
}
# x is 6
```
(b)
```
int a=9, x=0;
while (a > 0) {
	a--;
	x+=2;
	if (a < 3)
		continue;
	x+=3;
}

# x is 36
```

## Exercise 2
(a)
```
int balance = 100;
while ( true ) {
   if ( balance < 12 )
      break;
   balance = balance – 12;
}
System.out.println( "Balance is " + balance );

# Output:
Balance is 4
```
(b)
```
int balance = 100;
while ( true ) {
   if ( balance < 12 )
      continue;
   balance = balance – 12;
}
System.out.println( "Balance is " + balance );

#  loop
```

## Exercise 3
(a)
```
for (i=0; i<5; i++)
	for (j=0; j<7; j++)
		System.out.println("loop");   // statement in question

# 5*7 = 35 times
```
(b)
```
for (i=0; i<5; i++)
	for (j=0; j<7; j++)
		for (k=0; k<6; k++)
			System.out.println("another loop");
			System.out.println("loop");   // statement in question

# 1 times, due to for loop not {}. Therefore, the loop will only handle next line only.
```

## Exercise 4
(a)
```
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

# Output:
0 0 1 0 1 2 0 1 2 3
```

(b)
```
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

# Output:
*    
 *   
  *  
   * 
    *
```
(c)
```
for (int i=0; i<2; i++) {
	System.out.println("outer " + i);
	for (int j=0; j<3; j++) {
		System.out.println("Inner " + i + " " + j);
	}
	for (int k=2; k>0; k--) {
		System.out.println("Inner " + i + " " + k);
	}
}

# Output:
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