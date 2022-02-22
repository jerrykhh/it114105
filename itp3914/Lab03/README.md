## Exercise 1
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
(a)  
```
"a + b" = c;
```
* only a variable is allowed on the left hand side of an assignment statement
```
System.out.println( 5"*"((a + b) / (c + "d")"))";
```
* \* is expected between 5(
* d was not declared
* two ) were missed
* 
(b)
```
balance = 1","200.3;
```
* comma is not allowed

```
deposit = $140;
```
* $ is not allowed

(c)
```
double "6n";
```
* a variable name must start with a letter (not a digit)

## Exercise 
```
String var;
var = "Programming";
System.out.print( var );
var = "Networking";
System.out.println( var );

# Output:
ProgrammingNetworking
```

```
System.out.println( "act" + "ion" );

# Output: action
```

```
double var1 = 3;
System.out.println( var1 + 5 );

# Output: 8.0
```

```
System.out.println("The price is " + 30 + " dollars and " + 60 + " cents." );

# Output:
The price is 30 dollars and 60 cents.
```

## Exercise 6

```
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

# Output:
11
10
12
12
```
## Exercise 7
```
char c = 'A';
int i = int (c);

# not allowed
```

```
double d = 1000.74;
int i = (int) d;
# allowed, 1000
```

```
boolean b = true;
int i = (int) b;

# not allowed
```

