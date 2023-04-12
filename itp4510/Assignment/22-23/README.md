# ITP4510-Assignment
ITP4510 Data Structures &amp; Algorithms: Concepts &amp; Implementation

## Scenarios
The Yummy Restaurant Group Limited is a catering company. It has grown into one of the largest catering company in Hong Kong.  The Group is operating diversified services including Chinese Restaurants, Western Restaurants, Japanese Restaurants, Conveyor-belt Sushi Restaurants, Fast Food Restaurants and etc. It has over 10 brands and around 100 restaurants in 2021. 

The Novel House, which located in the Clover Hotel, is belonged to Yummy Restaurant Group Limited. For the Novel House, customer can place food order by phone. The operator will enter the order information in the system. For each of the food order, a priority indicator will be assigned to it. The least priority indicator of the food order will be first delivered, however, if the food orders with the same priority occurred, the food will be delivered according to their ordering in the queue.

Now, one IT staff is assigned to develop a prototype program to simulate the ordering of the food. 

## Membership
List of Priority Indicator 
| Membership | Priority Indicator |
| -- | -- |
| VIP Member | 1 |
| Registered Member | 2 |
| Guest | 3 |

Member ID is a four digit number starting with “8”. Member ID of VIP members should be started from 8001 to 8199. The Member ID of registered members should be in the range of 8200 to 8999. The Member ID for a guest would be an increment number started from 9000.

## Food Menu 

| Set | Food |
| -- | -- |
| A	| Chicken Salad |
| B | Grilled Ribeye Steak |
| C | Angel Hair Pasta with Shrimp |
| D | Grilled Fish and Potatoes |

## Execution Sample

```
_Number_ <- present the user input
```

### 1. Process Ordering
```
Please input your member ID [input 0 for guest]:_0_  ← Order by guest
----------------- Food Menu ----------------
Set A : Chicken Salad
Set B : Grilled Ribeye Steak
Set C : Angel Hair Pasta with Shrimp
Set D : Grilled Fish and Potatoes
--------------------------------------------
Select food:_a_
Please input your member ID [input 0 for guest]:_8101_ ← Order by VIP
----------------- Food Menu ----------------
Set A : Chicken Salad
Set B : Grilled Ribeye Steak
Set C : Angel Hair Pasta with Shrimp
Set D : Grilled Fish and Potatoes
--------------------------------------------
Select food:_b_
Please input your member ID [input 0 for guest]:_8103_ ← Order by VIP
----------------- Food Menu ----------------
Set A : Chicken Salad
Set B : Grilled Ribeye Steak
Set C : Angel Hair Pasta with Shrimp
Set D : Grilled Fish and Potatoes
--------------------------------------------
Select food:A
Please input your member ID [input 0 for guest]:_8299_ ← Order by registered member
----------------- Food Menu ----------------
Set A : Chicken Salad
Set B : Grilled Ribeye Steak
Set C : Angel Hair Pasta with Shrimp
Set D : Grilled Fish and Potatoes
--------------------------------------------
Select food:_d_
Please input your member ID [input 0 for guest]:_0_ ← Order by guest
----------------- Food Menu ----------------
Set A : Chicken Salad
Set B : Grilled Ribeye Steak
Set C : Angel Hair Pasta with Shrimp
Set D : Grilled Fish and Potatoes
--------------------------------------------
Select food:_a_
Please input your member ID [input 0 for guest]:_8233_ ← Order by registered member
----------------- Food Menu ----------------
Set A : Chicken Salad
Set B : Grilled Ribeye Steak
Set C : Angel Hair Pasta with Shrimp
Set D : Grilled Fish and Potatoes
--------------------------------------------
Select food:_B_

```


### 2.	Print out Order List
```
Please input your member ID [input 0 for guest]: _9999_
----------------- Admin Function ----------------
1 : Print order list
2 : Remove order
>_1_
--------------------------------------
 [ MemberID: 8101 ordered Set B with priority 1 ] 
 [ MemberID: 8103 ordered Set A with priority 1 ] 
 [ MemberID: 8299 ordered Set D with priority 2 ] 
 [ MemberID: 8233 ordered Set B with priority 2 ] 
 [ MemberID: 9000 ordered Set A with priority 3 ] 
 [ MemberID: 9001 ordered Set A with priority 3 ] 
--------------------------------------
Total outstanding order:6
```

### 3.	Delete Order
```
Please input your member ID [input 0 for guest]:_9999_
----------------- Admin Function ----------------
1 : Print order list
2 : Remove order
>_2_
Enter Member ID:8299
Please input your member ID [input 0 for guest]:_9999_
----------------- Admin Function ----------------
1 : Print order list
2 : Remove order
>_1_
--------------------------------------
 [ MemberID: 8101 ordered Set B with priority 1 ] 
 [ MemberID: 8103 ordered Set A with priority 1 ] 
 [ MemberID: 8233 ordered Set B with priority 2 ] 
 [ MemberID: 9000 ordered Set A with priority 3 ] 
 [ MemberID: 9001 ordered Set A with priority 3 ] 
--------------------------------------
Total outstanding order:5

```

### 4.	Quit Program
```
Please input your member ID [input 0 for guest]:_-1_
Have a nice day!!!
```

Type any negative number in the main menu to quit the program.

## Validation for Input 
You should create an exceptional class `InvalidInputException` for the program. During the ordering stage, the program should do the validation for the following cases:
1. Checking valid range of Member ID
2. Checking selection of food
3. Deleting of order
```
Please input your member ID [input 0 for guest]:_523_
Invalid input! Please input again.
Please input your member ID [input 0 for guest]:_7569_
Invalid input! Please input again.
Please input your member ID [input 0 for guest]:_9856_
Invalid input! Please input again.
Please input your member ID [input 0 for guest]:_0_
----------------- Food Menu ----------------
Set A : Chicken Salad
Set B : Grilled Ribeye Steak
Set C : Angel Hair Pasta with Shrimp
Set D : Grilled Fish and Potatoes
--------------------------------------------
Select food:_f_
Invalid input! Please input again.
Please input your member ID [input 0 for guest]:_9999_
----------------- Admin Function ----------------
1 : Print order list
2 : Remove order
>_2_
Enter Member ID:_8231_ ← This order is not exist in the LinkedList
None of order
```
Program would be stopped when entered non-number in the main menu.
```
Please input your member ID [input 0 for guest]:_Abcd_
Input Error
```

## Given Files 
1.	`OrderSystem.java` – *main program which should be completed*
2.	`LinkedList.java` – *data structure which should be amended*
3.	`FoodOrder.java` – *data type for the data object stored in ListNode*

## Task Specification 
1.	You should use the classes provided in the given files. 
2.	Implement the program using Java. Submit listings of all programs.
3.	Handle exceptional/abnormal cases. You should create your own Exception class. Submit a brief description of all such cases (e.g. invalid input ) handled by the program. A class InvalidInputException should be implemented.
4.	Program structure and in-program comments. 
5.	Evidence of testing. Test the program and submit the logged listing of run samples.

## Mark Allocation
<table>
 <tbody><tr>
  <td>
  <p>0. Compilation</p>
  <p>-- success compilation and execute the program</p>
  </td>
  <td>
  <p>10%</p>
  </td>
 </tr>
 <tr>
  <td>
  <p>1. Design</p>
  <p>-- correct using class <span >FoodOrder</span>, LinkedList,
  <span >ListNode</span></p>
  <p>-- suitable data
  structure</p>
  </td>
  <td >
  <p>10%</p>
  </td>
 </tr>
 <tr style="mso-yfti-irow:2">
  <td>
  <p>2. Implementation</p>
  <p>-- input parameter</p>
  <p>-- program simulation</p>
  <p>-- result print out</p>
  </td>
  <td >
  <p>45%</p>
  </td>
 </tr>
 <tr>
  <td>
  <p>3. Selection of menu</p>
  <p>-- Correct menu design</p>
  </td>
  <td>
  <p>10%</p>
  </td>
 </tr>
 <tr>
  <td>
  <p>4. Error handling</p>
  <p>-- create exception class InvalidRangeInputException</p>
  <p>-- handle exception (e.g.InputMismatchException>)</p>
  </td>
  <td>
  <p>10%</p>
  </td>
 </tr>
 <tr>
  <td>
  <p>5. Report</p>
  <p>-- executable results (screen dumps)</p>
  </td>
  <td>
  <p>5%</p>
  </td>
 </tr>
 <tr>
  <td>
  <p>6. Coding standard</p>
  <p>-- proper indentation</p>
  <p>-- proper naming</p>
  <p>-- consistency coding style</p>
  <p>-- appropriate comments</p>
  </td>
  <td>
  <p>10%</p>
  </td>
 </tr>
 <tr>
  <td>
  <p><b>Total</b></p>
  </td>
  <td>
  <p>100%<o:p></b></p>
  </td>
 </tr>
</tbody></table>

## Instructions to Students
This assignment is an individual assignment. Each student has to submit his/her own work. Plagiarism will be treated seriously. All assignments that have been found involved wholly or partly in plagiarism (no matter these assignments are from the original authors or from the plagiarists) will score ZERO marks. Further, disciplinary action will be followed.

Adequate in-program comments should be placed as appropriate. All user-defined names should be descriptive as much as possible. Marks are given based on correctness, programming quality, and style.

You are required to submit:
- Well-documented program listings and the executable results (screen dumps).
- Upload your files to Moodle including all your programs and report. It is required that your programs can be successfully compile.
