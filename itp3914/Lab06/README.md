# Lab 6 Selection Structures
## Topic 3 Basic Program Structures

## Exercise 1
Assuming that the variable $x$ is storing $1$, determine the result of the boolean expressions below.
```
int x = 1;
```
| Boolean expressions | Result |
| ------------------- | ------ |
| (true) && (4 < 3) | false|
| !(x < 0) && (x < 0) | false |
| (x < 0) || (x > 0) | true |
| (x != 0) || (x == 0)	 | true |
| (x != 0) || (x == 0)	 | true |
| (x != 0) == !(x == 0) | true |

## Exercise 2
Assuming that $x$ and $y$ are `int` type, which of the followings are legal Java expressions?
| Expressions | legal | illegal |
| ----------- | ----- | ------- |
| x > y > 0	 | [] | [x] |
| x = y || y	| [] | [x] |
| x /= y | [x] | [x] |
| x or y | [] | [x] |
| x and y | [] | [x] |
| (x != 7) || (x = 7) | [] | [x] |

## Exercise 3
What will be the value of y after the program fragment below has been executed?
```
int x = 4;
int y = 4;
switch (x + 4) {
	case 8: y=1;
	default: y++;
}
# y is 2
```

## Exercise 4
Write a boolean expression that evaluates to true if the number x is in the range 21 to 30 inclusive, or x is negative.
```
(x >= 21 and x <= 30) || x < 0
```

## Exercise 5
Rewrite the `if-else` statements below to use the `switch` statement.

```java
import java.util.Scanner;

public class Ex5 {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        int a;
        int x = 0;

        System.out.print("a: ");
        a = scan.nextInt();

        if (a == 1)
            x += 3;
        else if (a == 2)
            x += 5;
        else if (a == 3)
            x += 7;
        else
            x += 10;

        System.out.println("x = " + x);

    }
}
```

## Exercise 6
In New York City (NYC), even numbered streets are east-bound, odd numbered streets are west-bound. Write a Java program to accept a street number from the user and print a message on screen to tell the user the direction.
A sample execution of the program is shown below. Those in __ are user inputs.
```
e:\> java Ex6
Street number? __213__
West-bound
```

## Exercise 7
Write a Java program to calculate the parking fee based on the vehicle type and the hours a vehicle spent in a car park. The parking fee is charged according to the table below.

| Vehicle Type | Hourly Rate ($ per hour) |
| - | - |
| Private Car | 15 |
| Bus | 35 |
| Truck | 50 |

Sample executions of the program are shown below. Those in __ are user inputs. All inputs (including the vehicle type) have the data type `int`.
```
e:\> java Ex7
Vehicle Type [1=private, 2=bus, 3=truck]? _1_
Number of hours? _3_
Parking fee = 45
```

```
e:\> java Ex7
Vehicle Type [1=private, 2=bus, 3=truck]? _2_
Number of hours? _2_
Parking fee = 70
```

## Exercise 8
Write a Java program to read an examination mark and determine the grade obtained according to the table below. Note that an examination mark may be a fractional number such as 62.5.

| Mark | 0-39 | 39.1-49 | 49.1-59 | 59.1-69 | otherwise |
| - | - | - | - | - | - |
| Grade | F | D | C | B | A |

Sample executions of the program are shown below. Those in __ are user inputs.

```
e:\> java Ex8
Exam mark? _72.5_
Grade = A
```

```
e:\> java Ex8
Exam mark? _43.5_
Grade = D
```