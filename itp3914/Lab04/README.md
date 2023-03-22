# Lab 4 Simple I/O
## Topic 2.1-2.3: Variables, Data Types, Operators

### Intended Learning Outcomes
Upon completion of this tutorial/lab, you should be able to:
- Use console screen to input/output data.
- Use dialog box and message box to input/output data.

#### Exercise 1 – Programming Exercise
PROBLEM: Calculate the volume of a cylinder (with console I/O)

Write a Java program that computes the volume ($v$) of a cylinder. The program should use Scanner class to read the radius ($r$) and the length ($l$), and compute the volume ($v$) with the formula:$ v = r^2\pi l$.

#### Exercise 2 – Programming Exercise
PROBLEM: Calculate the volume of a cylinder (with dialog and message boxes)

Rewrite the program in Exercise 1 using dialog and message boxes in the `JOptionPane` class as the input/output method.

#### Exercise 3 – Programming Exercise
PROBLEM: Find the root(s) of a quadratic equation

Write a Java program to calculate the two roots of a quadratic equation (ax2 + bx + c = 0) by using the formulae:

```math
x1 = \frac{-b+\sqrt{b^2-4ac}}{2a}, x2=\frac{-b-\sqrt{b^2-4ac}}{2a}
```

You can use `Math.sqrt(x)` method to calcuate $\sqrt{x}$ For example, the statement

```java
System.out.println(Math.sqrt(10));
```
prints the square root of 10. You are free to use either console I/O or `JOptionPane` classes.