## Q1
(a) (4 marks) Name the most suitable primitive data types in Java that can store the
following data:

(1) the price of a notebook computer (e.g. 6888.5)

(2) the number of visits to Facebook in 2019 

```
(1) double
(2) int
```

(b) (5 marks) Identify whether the statments below are valid in Java.
| code | valid | invalid |
| ---- | ----- | ------- |
| byte b = 12.2; | [] | [v] |
| double x = (int) 28.05; | [v] | [] |
| float f = 26.0; | [] | [v] |
| char c = "c"; | [] | [v] |
| int[3] arr = {1, 2, 3}; | [] | [v] |

(c) (4 marks)

(1) What is the size (number of bytes) of a float variable?

(2) What is the size (number of bytes) of a long variable? 

```
(1) 32(bits)/8 = 4 bytes 
(2) 64(bits)/8 = 8 bytes
```

(d) (4 marks) Identify whether the identifier names below are valid in Java.

| code | valid | invalid |
| ---- | ----- | ------- |
| $12_3 | [] | [v] |
| a:b | [] | [v] |
| 4x | [] | [v] |
| Java | [v] | [] |

## Q2

(9 marks) Given x, y and z are integers and x = 2, y = 3 and z = 8, write down the output of
the following statements:

(a) System.out.println( "\\\""+x+"\"\\" );
```
Answer: 
\""\
```
(b)
System.out.println( x+y+"\t"+z+"\n"+y+z+"\t"+x );
```
Answer: 
5   8
38  2  
```
(c) 
z += (x+z)/y - (x+z)%y;
System.out.println(z);
```
Answer:
10
```

## Q3
(9 marks) What will be printed by the program fragment below?
```
int x=3, y=7, z=4;
z = --x + y++;
System.out.println("x=" + x);
z = x-- + y++ + z;
System.out.println("y=" + y);
System.out.println("z=" + z);
```
```
Answer:
x=2
y=9
z=19
```

## Q4
(a) (6 marks) Write a Java program statement to declare and initialize a variable/constant
for each of the cases below. The identifier names MUST be meaningful and follow Java
convention.

(1) A variable to store the average mark of a student in a semester, with initial value 0.0.
```
Answer: double avgMark = 0.0;
```
(2) A constant to represent the maximum number (value 10) of items a user can borrow.
```
Answer: final int maxBorrowNum = 10;
```

(b) (4 marks) Rewrite the following Mathematical equation in Java. Assume that all variables
in the equation are of double type and initialized properly.
```
Answer: double x = (x + (y/6)) / (3 * (x * x - 1))
```

## Q5
(a) (4 marks) What is the output of the following statements:
```
int x = 2, y = 5;
if ( !(x>y && x<=y) && x>y)
System.out.println("AAA");
else
System.out.println("BBB");
System.out.println( (x<=3 || !(y!=7)) ? "XXX" : "YYY");
```

```
Answer: 
BBB
XXX
```

(b) (2 marks) Consider the following statements.
```
if (i==20 || i==50 || i==60)
j=1;
else if (i==10 || i==30)
j=2;
else
j=3;
System.out.println("j=" + j);
```
(1) What is the output of the above statements if i has the value 30.
(2) What is the output of the above statements if i has the value 33.

```
Answer: 
(1) j=2
(2) j=3
```

(c) (6 marks) Rewrite the above if-else statement using a switch structure.
```
Answer:

switch(i){
    case 20:
    case 50:
    case 60:
        j = 1;
        break;
    case 10:
    case 30:
        j=2;
        break
    default:
        j=3;
}
```
## Q6
(14 marks)

(a) (1) What is the output of the following program segment?
```
int a=10, x=0;
do {
    a-=1;
    x+=2;
    if (a<=6)
    break; //(*)
} while (a>4);
System.out.println("x=" + x);
```
(2) What is the output if the word break at the line marked with (*) is changed to
continue?

```
Answer:
(1) x=8
(2) x=12
```

(b)
Complete the following program fragment which prints a pattern as below. The number
of lines in the pattern is determined by the int variable numLine.
```
Output:
// numLine is 3
531
 31
  1
// numLine is 5
97531
 7531
  531
   31
    1

Answer:
int numLine;
// code skipped - read the value of numLine
for (int i= numLine; i>=1; ____i--_____) {
    // print space
    for (int j=1; j<=____numLine - i____; ___j++____)
    System.out.print (____" "____);
    // print numbers
    for (int j=i; j>=___1___; ___j--____)
    System.out.print (____j*2-1_____);
    // advance a line
    System.out.____println_____();
}
```

## Q7
(a) (6 marks) Write a for-loop to find and print the integer answer of the mathematical
expression: 2×5 - 3×7 + 4×9 - 5×11 + 6×13 - 7×15
You only need to write statements for variable declaration(s), the loop and the output
statement. You are NOT required to write a complete Java program.

```
Answer:
int answer = 0;
for(int i = 2, j = 5; i <=7 && j <=15; i++, j+=2)
    if (i % 2 == 0)
        answer += i*j;
    else
        answer -= i*j;
System.out.println(answer);
```

(b) (9 marks)
(1) Write ONE statement to declare an array with the following settings:
```
Array variable: quotas 
Data type: int
Size: 5 
elements Values: 1, 2, 3, 4, 5
```
```
int[] quotas = {1,2,3,4,5};
```

(2) What is the value of quotas.length? 
```
5
```

(3) Write a for loop to find the average value in the array quotas and store the result in
mean.
```
int sum = 0;
double mean;
for(int i = 0; i < quoatas.length; i++)
    sum += quotas[i];
mean /= quotas.length;
```

(4) Write a for loop to add 2 to all the elements in the array quotas.
```
for(int i = 0; i < quotas.length; i++)
    quotas[i] += 2;
```

## Q8
(14 marks) Complete the program below which calculates the shipping cost of a courier
service. The cost is calculated by weight×rate, where rate is based on weight as shown in
the table below.

| weight (in kg) | rate ($ per kg) |
| -------------- | --------------- |
| weight >= 50 | 18 |
| 50 > weight >= 40 | 20 |
| 40> weight >=20 | 25 |
| weight < 20 | 30 |

Shown below is a sample execution of the program. Those in bold underline are user inputs.
Your program MUST check whether the weight inputted is greater than or equal to zero, or
otherwise print an error message and ask the user to input again. You may assume user will
always input an integer for the variable weight. The program will repeat running until the
user inputs zero (0).

```
C:\> java ShippingCost
Enter weight: -12
Invalid input! Please input again.
Enter weight: 55
The shipping cost is 990
Enter weight: -6
Invalid input! Please input again.
Enter weight: 30
The shipping cost is 750
Enter weight: 0
Thank you for using this program. Bye!
```
```
import java.util.Scanner;
public class ShippingCost {
    public static void main( String[] args ) {
        int weight; // parcel weight - inputted by user
        int rate=0; // shipping rate - determined by the value of weight
        Scanner input = new Scanner( System.in );
        while (true) {

            // Answer:

            weight = input.nextInt();
            if (weight == 0)
                break;
            
            if (weight < 0)
                System.out.println("Invalid input! Please input again.");
            else{

                if (weight >= 50)
                    rate = 18;
                else if (weight >= 40)
                    rate = 20;
                else if (weight >= 20)
                    rate = 25;
                else
                    rate = 30;
                
                System.out.println("The shipping cost is " + (weight * rate));

            }


        } // while (true) - end
        System.out.println("Thank you for using this program. Bye!");
    }
}
```