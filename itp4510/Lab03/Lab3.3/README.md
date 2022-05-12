### Q3

In topic “3.1 Linked Lists”, we have discussed the importance of using a tail for referencing the last node in a linked list. The purpose of this exercise is to have an empirical study on the impact of using the tail reference on a linked list.

Execute the program multiple times with different value for <num>. Record the execution
time as reported by the program in a spreadsheet.

(b) Copy your programs in Q3(a) to a new folder. Q3(b). Modify the Java program LinkedList.java so that the linked list does not contain the tail reference. Needless to say, when the last node in the linked list is wanted, you need to traverse the list from the head towards its end until the last node is reached. Execute LinkedQueueTiming again using the same set of <num> values as in Q3(a). Record the execution time as reported by the program in the spreadsheet.

| n | num | with tail Q3(a) | average time(with tail) | waithout tail Q3(b) | average time(without tail) |
| --- | --- | --- | --- | --- | --- |
| 1 | 10 | 266800 | 276500 | 272300 | 294200 |
| 2 | 100 | 281600 | 288650 | 325500 | 345600 |
| 3 | 1000| 669900 | 671020 | 1767600 | 1678600 |
| 4 | 10000 | 1575200 | 1595300 | 90549600 | 101132100 |
| 5 | 100000 | 5840900 | 6020100 | 9229402000 | 9103203100 |
| 6 | 1000000 | 24357800 | 26236800 | 1500203112900 | 1612141023950 |