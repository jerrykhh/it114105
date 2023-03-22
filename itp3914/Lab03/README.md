# Lab 2 Variables and Arithmetic Operations

### Intended Learning Outcomes

Upon completion of this tutorial/lab, you should be able to:
- Define variables with meaningful names.
- Use arithmetic expressions and assignment statements to perform calculations.
- Identify and fix syntax errors in Java programs.

## Exercise 1
Identify whether the followings are valid or invalid identifiers.

| variable name | Valid | Invalid |
| ------------- | ----- | ------- |
| R2D2 | [x] | [] |
| Watchamacallit | [x] | [] |
| howAboutThis? | [] | [x] |
| Java | [x] | [] |
| GoodChoice | [x] | [] |
| 12345 | [] | [x] |
| 3CPO | [] | [x] |
| This is okay | [] | [x] |
| thisIsReallyokay | [x] | [] |
| aPPlet | [x] | [] |
| Bad-Choice | [] | [x] |
| A12345 | [x] | []|

## Exercise 2
Write down the output of the program fragments below.
```
double amount;
amount = 20;
System.out.println(amount – 8);

# Output: 12.0
```

```
int n=9;
n+=1;
System.out.println(n);
System.out.println(n+1);

# Output: 
10
11
```

```
int num=7;
num = 9%num;
System.out.println(num);

# Output: 2
```

```
int u, v;
u = 5;
v = u * u;
System.out.println(u*v);

# Output: 125
```

```
double x=8;
x += 5;
System.out.println(x + 3 * x);

# Output: 52
```

```
double p=5.1, q=2.3;
p += p;
System.out.println(p + q);
System.out.println(p – q);

# output: 
12.5
7.9
```

### Exercise 3
Identify the errors in the following Java program fragments.

(a)
```java
// Question
double a, b, c;
a = 2;
b = 3;
a + b = c;
System.out.println( 5((a + b) / (c + d);
``` 
```
// Answer
"a + b" = c;
```
* only a variable is allowed on the left hand side of an assignment statement
```
System.out.println( 5"*"((a + b) / (c + "d")"))";
```
* \* is expected between 5(
* d was not declared
* two ) were missed
  

(b)
```java
// Question
double balance, deposit;
balance = 1,200.3;
deposit = $140;
System.out.println( balance + deposit );
```

```
// Answer
balance = 1","200.3;
```
* comma is not allowed

```
deposit = $140;
```
* $ is not allowed

(c)
```java
// Question
double 6n; 
6n = 2 * 6n;
```
```
// Answer
double "6n";
```
* a variable name must start with a letter (not a digit)

## Exercise 4
Write down the output of the program fragments below.
```java
String var;
var = "Programming";
System.out.print( var );
var = "Networking";
System.out.println( var );

// Output:
ProgrammingNetworking
```

```java
System.out.println( "act" + "ion" );

// Output: action
```

```java
double var1 = 3;
System.out.println( var1 + 5 );

// Output: 8.0
```

```java
System.out.println("The price is " + 30 + " dollars and " + 60 + " cents." );

// Output:
The price is 30 dollars and 60 cents.
```

## Exercise 5
```java
public class StringConcatenate {

    public static void main(String[] args) {

        int number1 = 200;

        int number2 = 8;

        int number3 = 18;

        System.out.println("Next year is " + number1 + number2);

        System.out.println("My age is " + (number2 + number3));

    }

}

// Output:
Next year is 2008
My age is 26
```

## Exercise 6

```java
public class IncrementDecrement {
   public static void main( String[] args ) {
      int a, x;
      a = 10;
      x = a++;
      System.out.println( "a=" + a );
      System.out.println( "x=" + x );
      x = ++a;
      System.out.println( "a=" + a );
      System.out.println( "x=" + x );
   }
}

// Output:
11
10
12
12
```
## Exercise 7
Can the following conversions involving casting be allowed? If so, find the converted result, that is, the value stored in variable $i$.

```java
char c = 'A';
int i = int (c);

// not allowed
```

```java
double d = 1000.74;
int i = (int) d;
// allowed, 1000
```

```java
boolean b = true;
int i = (int) b;

// not allowed
```

## Exercise 8
PROBLEM: Calculate the volume of a cylinder (with console I/O)

Write a Java program that computes the volume ($v$) of a cylinder. The program should use Scanner class to read the radius ($r$) and the length ($l$), and compute the volume ($v$) with the formula: $v = r^2\pi l$.

Test Case
|  | Case 1 | Case 2 | Case 3 |
| - | - | - | - |
| $r$ | 4 | 6 | 8 |
| $l$ | 5 | 7 | 9 |
| $v=$ | 251/3274 | 791.6813 | 1809.5574 |

```
c:\> java Ex8
Enter r : 8
Enter l : 9
v = 1809.5574
```