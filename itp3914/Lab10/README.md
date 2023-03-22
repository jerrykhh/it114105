# Lab 10 Objects and Classes
## Topic 4.1 to 4.4 Part (a) Objects and Classes

## Exercise 1

(a) Create a class `Employee` that has

Variables:
| Instance Variable name | Data type |
| - | - |
| name | String | 
| salary | int |

Methods:`getName()`, `setName()`, `getSalary()` and `setSalary()`

(b) Complete the following test program that creates two `Employee` object instances named `emp1` and `emp2`. Then perform the followings:
1. Set the name and salary of `emp1` to *"Chan Tai Man"* and 12000, respectively.
2. Set the name and salary of `emp2` to *"Tam Ping Shing"* and 13500, respectively.
3. Print the current details of `emp1` and ``emp2``.
4. Increase the salary of *"Chan Tai Man"* by 10% and the salary of *"Tam Ping Shing"* by 5%.
5. Print the new details of `emp1` and `emp2`.

```java
public class Ex1b {

  public static void main(String[] args) {

    Employee emp1 = new Employee();
    Employee emp2 = new Employee();
    int oldSalary;

    // Part 1-2 here
    // Part 3 below
    System.out.println("Before-");
    System.out.println("Employee 1: name=" + emp1.getName() +" salary=" + emp1.getSalary());
    System.out.println("Employee 2: name=" + emp2.getName() +" salary=" + emp2.getSalary());

    // Part 4-5 here

  }
}
```

The output of the program is shown below.
```
C:\> java Ex1b

Before-
Employee 1: name=Chan Tai Man salary=12000
Employee 2: name=Tam Ping Shing salary=13500
After-
Employee 1: name=Chan Tai Man salary=13200
Employee 2: name=Tam Ping Shing salary=14175
```

(c) Redo Part (b) by adding a Constructor to set the name and salary.

(d) Add a method `displayDetails()` to display the name and salary in Employee class, and modify Ex1b to use `displayDetails()`to print the details of `emp1` and `emp2`.

(e) Add method `raiseSalary()` that accepts a percentage increase in salary (`double`) as argument and increases the salary by the corresponding percentage. Create a new Test program `Ex1cde.java` to raise Chan’s salary by 10% and that of Tam by 5%. Print the details of `emp1` and `emp2` after the increase of salaries.

```
C:\> java Ex1cde

Before-
Employee: name=Chan Tai Man salary=12000
Employee: name=Tam Ping Shing salary=13500
After-
Employee: name=Chan Tai Man salary=13200
Employee: name=Tam Ping Shing salary=14175
```

## Exercise 2
(a) Create a class `Student` that has

Variables:
| Attribute name | Data type |
| - | - |
| name | String | 
| id | int |
| score | double |

Methods: *the getter and setter methods for each of the above attributes.*


(b) Write a test program Ex2b.java that creates three Student object instances named `stud1`, `stud2` and `stud3`. Then perform the followings:
1. Set the name, id and score of stud1 to *"Cheung Siu Ming"*, 310567 and 87.1.
2. Set the name, id and score of stud2 to *"Ng Wai Man"*, 451267 and 77.5.
3. Set the name, id and score of stud3 to *"Wong Sui Kai"*, 789014 and 83.4.
4. Print the details of `stud1`, `stud2` and `stud3`.
5. Find and print the average score among the three students.

The output of the program is shown below.
```
C:\> java Ex2b
Student : name=Cheung Siu Ming id=310567 score=87.1
Student : name=Ng Wai Man id=451267 score=77.5
Student : name=Wong Sui Kai id=789014 score=83.4

Average Score = 82.66666666666667
```

(c) Redo Part (b) by adding a Constructor to set the name, id and score and then create a new test program `Ex2c.java` to test it.

```
C:\> java Ex2c
Student : name=Cheung Siu Ming id=310567 score=87.1
Student : name=Ng Wai Man id=451267 score=77.5
Student : name=Wong Sui Kai id=789014 score=83.4

Average Score = 82.66666666666667
```

## Exercise 2
Consider the following classes.

```java
class AStudent {
  private String name;
  public int age;

  public void setName(String inName) {
    name = inName;
  }

  public String getName() {
    return name;
  }

}

public class Ex3 {

  public static void main(String s[]) {
    AStudent stud1 = new AStudent();
    AStudent stud2 = new AStudent();
    stud1.setName("Chan Tai Man");
    stud1.age = 19;
    stud2.setName("Ng Hing");
    stud2.age = -23;
    System.out.println("Student: name=" + stud1.getName() +", age=" + stud1.age);

    System.out.println("Student: name=" + stud2.getName() +", age=" + stud2.age);

  }
}
```

For the object `stud2`, age is -23 which is a negative value, this is not allowed in human being.

Enhance the class `AStudent` by enforcing data encapsulation on the attribute `age`. If the inputted `age` is invalid, print an error message and set the age to 18.

```
C:\> java Ex3
-23 is not allowed! Age must be greater than or equal to 18!
Student: name=Chan Tai Man, age=19
Student: name=Ng Hing, age=18
```

## Exercise 4
Given the following CurrencyConverter class which handles the exchange between a foreign currency and US dollars.
```java
public class CurrencyConverter {
  private double exchangeRate;
  private double commissionRate;
  private int largeAmount;

  public CurrencyConverter(double er, double cr) {
    exchangeRate = er;
    commissionRate = cr;
  }

  public double fromUSDollar(double dollar) {

    if (dollar >= largeAmount)
      return (dollar * exchangeRate * (1 - commissionRate * 0.5));
    else
      return (dollar * exchangeRate * (1 - commissionRate));

  }

  public double toUSDollar(double foreignMoney) {

    if (foreignMoney / exchangeRate >= largeAmount)
      return (foreignMoney / exchangeRate * (1 - commissionRate * 0.5));
    else
      return (foreignMoney / exchangeRate * (1 - commissionRate));

  }

  public void setExchangeRate(double rate) {
    exchangeRate = rate;
  }

  public double getExchangeRate() {
    return exchangeRate;
  }

  public void setCommissionRate(double rate) {
    commissionRate = rate;
  }

  public double getCommissionRate() {
    return commissionRate;
  }

  public void setLargeAmount(int amount) {
    largeAmount = amount;
  }

  public int getLargeAmount() {
    return largeAmount;
  }
}
```

Write a test program `Ex4.java` to test the CurrencyConverter class by performing the following operations:

1. Instantiate two `CurrencyConverter` object with the following attributes:
 
|  | exchangeRate | comissionRate |
| - | - | - |
| yenConverter | 115.7 | 0.0005 |
| euroCOnverter | 0.9881 | 0.0003 |

2. Set `largeAmount` to 50000.
3. Use the `yenConverter` to convert 1500000 yens to US dollars.
4. Use the `yenConverter` to convert from US$2000 to yens.
5. Set the exchange rate of euro to 0.9881.
6. Use the `euroConverter` to convert 170000 euros to US$.
7. Use the `euroConverter` to convert from US$20000 to euros.

Sample output
```
C:\> java Ex4
1500000 yens = US$ 12958.0812445981
US$ 2000 = 231284.30000000002 yens

170000 euros = US$ 172021.55652261918
US$ 20000 = 19756.0714 euros
```