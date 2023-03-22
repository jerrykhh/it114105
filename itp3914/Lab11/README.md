# Lab 11

## Exercise 1
You are given the Java classes `Circle`, `Rectangle` and `Test` below.
```java
public class Circle {
    private double radius;

    public Circle(double r) {
        radius = r;
    }

    public double area() {
        return radius * radius * Math.PI;
    }
}

public class Rectangle {
    private double length;
    private double width;

    public Rectangle(double l, double w) {
        length = l;
        width = w;
    }

    public double area() {
        return length * width;
    }
}

public class Test {

    public static void main(String[] args) {
        Rectangle r = new Rectangle(30.1, 10.2);
        Circle c = new Circle(5.3);
        System.out.println("r=" + r);
        System.out.println("c=" + c);
    }
}
```

Execute `Test` and write down the output.

```
r=Rectangle@19e1023e
c=Circle@3a4afd8d
# RAM address
```

## Exercise 2
You are given the Java classes `Test2` below. Execute the program together with the `Rectangle` class in Exercise 1.

```java
public class Test2 {

    public static void main(String[] args) {
        Rectangle r = new Rectangle(1, 2);
        System.out.println("r=" + r);
        System.out.println("area=" + r.area());
        r = new Rectangle(3, 4);
        System.out.println("r=" + r);
        System.out.println("area=" + r.area());
        r = null;
        System.out.println("r=" + r);
        System.out.println("area=" + r.area());

    }
}
```

Execute `Test2` and write down the output. Can you explain the output?

```
r=Rectangle@27ddd392
area=2.0
r=Rectangle@2a098129
area=12.0
r=null
Exception in thread "main" java.lang.NullPointerException
```

## Exercise 3
You are given the Java classes `Triangle` below.
```java
public class Triangle {
    private int base;
    private int height;

    public Triangle() {
        base = 0;
        height = 0;
    }

    public Triangle(int base, int height) {
        this.base = base;
        this.height = height;
    }

    public void printThis() {
        System.out.println(this);
    }

    public static void main(String[] args) {
        Triangle t = new Triangle(1, 2);
        System.out.println(t);
        t.printThis();
    }

}
```

Execute `Triangle` and write down the output. Can you explain the output? Discuss what `this` is in an object instance.

```
Triangle@6504e3b2
Triangle@6504e3b2
# RAM Address
```

## Exercise 4
You are given the Java classes Date and DateUser below.
```java
public class Date {

    private int day;
    private int month;
    private int year;
    public Date() {
        this(1, 1, 1970);
    }

    public Date(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;

    }

    public String toString() {
        return "[" + day + "/" + month + "/" + year + "]";
    }

}
public class DateUser {

    public static void main(String[] args) {
        Date d1 = new Date();
        System.out.println(d1);
    }

}
```

(a) Execute `DateUser` and observer the output. Can you explain the purpose of the program statement “`this(1, 1, 1970)`” in the no-argument constructor of `Date`?

(b) Remove the `toString()` method from the `Date` class. Compile and execute the program again. What is the difference?


```
# (a)
[1/1/1970]

# (b)
Date@28a418fc
# RAM Address
```

## Exercise 5
Consider the following class.

```java
public class QuestionOne {
    public final int A = 345;
    public int b;
    private float c;

    private void methodOne(int a) {
        b = a;
    }

    public float methodTwo() {
        return 23;
    }

}
```

Identify the invalid statements in the program below. For each invalid statement, state why it is invalid.

```java
public class Q1Main {

    public static void main(String[] args) {
        QuestionOne q1 = new QuestionOne(); //line1
        q1.A = 12; //line2
        q1.b = 12; //line3
        q1.c = 12; //line4
        q1.methodOne(12); //line5
        q1.methodOne(); //line6
        System.out.println(q1.methodTwo()); //line7
        q1.b = q1.methodTwo(); //line8
    }
}
```

```
Q1Main.java:17: cannot assign a value to final variable A
			q1.A = 12;
                    ^
Q1Main.java:19: c has private access in QuestionOne
			q1.c = 12;
                    ^
Q1Main.java:21: methodOne(int) has private access in QuestionOne
			q1.methodOne(12);
                    ^
Q1Main.java:22: methodOne(int) in QuestionOne cannot be applied to ()
			q1.methodOne();
                    ^
Q1Main.java:24: possible loss of precision
found   : float
required: int
			q1.b = q1.methodTwo();
                                     ^
5 errors

Tool completed with exit code 1

```

## Exercise 6
Suppose that the class `Foo` is defined as below.
```java
public class Foo {
    public int i;
    public static String s;
    public void imethod() { }
    public static void smethod() { }
}
```

Let f be an instance of `Foo`. Determine if each of the following Java statements is valid or invalid.

| Code | valid | invalid |
| ---- | ----- | ------- |
| System.out.println(f.s); | [x] | [] |
| f.smethod(); | [x] | [] |
| System.out.println(Foo.i); | [] | [x] |
| System.out.println(Foo.s); | [x] | [] |
| Foo.imethod(); | [] | [x] |
| Foo.smethod(); | [x] | [] |

## Exercise 7
A `Point` object instance represents a Cartesian coordinate and thus has two member variables, x and y. The Java program `Distance` below instantiates two `Point` object instances to represent the coordinates (4, 5) and (11, 4). The program then displays the distance between the two coordinates by invoking the `distance()` method in the `distance() `method.

Expression to get the distance of two Points (x1, y1) (x2, y2) is `distance = Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2));`

```
C:\> java Ex7
Distance = 7.0710678118654755
```

```java
public class Ex7 {
    public static void main(String[] args) {
        Point p1 = new Point(4, 5);
        Point p2 = new Point(11, 4);
        System.out.println("Distance = " + p1.distance(p2));
    }
}
```

Complete the `Point` class.

```java
public class Point {
    private int x;
    private int y;

    public Point() {
        this(0, 0);
    }

    public Point(int x, int y) {
        setPoint(x, y);
    }

    public void setPoint(int x, int y) {
        if (x >= 0 && y >= 0) {
            this.x = x;
            this.y = y;
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public double distance(Point p) {
        // COMPLETE THIS METHOD
    }

}
```

## Exercise 8
Study the validity of the programs below. Correct those invalid statements.
```java
public class Student {
    public static int numberOfStudent = 0; //line 1
    public String name; //line 2

    public void Student(String name) { //line 3
        numberOfStudent++; //line 4
        setName(name); //line 5
    }

    public int getNumberOfStudent() { //line 6
        return; //line 7
    }

    public String setName(String name) { //line 8
        name = name; //line 9
    }

}

public class Ex8 {
    public static void main(String args[]) {
        Student stu1 = new Student("John Chan"); //line 10
        System.out.println(numberOfStudent); //line 11
        System.out.println(name); //line 12
    }
}
```

```
C:\> java Ex8
1
John Chan
```