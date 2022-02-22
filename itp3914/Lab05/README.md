## Exercise 1
```
# Output:
7
```

## Exercise 2

| Code | valid | invalid |
| ---- | ----- | ------- |
| char [] charArray = new charArray[26]; | [] | [x] |
| char charArray[] = new charArray[26]; | [] | [x] |
| int [] words = new words[100]; | [] | [x] |
| int [100] words = new int []; | [] | [x] |
| char [] name = "Peter"; | [] | [x] |
| char [] name = {'P', 'e', 't', 'e', 'r'}; | [x] | [] |
| char [] name = {"P", "e", "t", "e", "r"}; | [] | [x] |
| double [] nums = [10.5, 25.1, 30.05]; | [] | [x] |
| double [] nums = {-3.5, 0, 3, 20.5}; | [x] | [] |


## Exercise 3

```
double[] scores = {5, 66, 2, 19, 6, 0};
// or
int[] scores = {5, 66, 2, 19, 6, 0};
```

## Exercise 4
| Code | valid | invalid |
| int [3][4] matrix; | [] | [x] |
| double [3][4] matrix = new double[][]; | [] | [x] |
| int [][] matrix = new int[3][4]; | [x] | [] |
| int [][] matrix = new double[3][4]; | [] | [x] |
| double [][] matrix = new double [][4]; | [] | [x] |
| int [][] matrix = {(1, 2), (3, 4), (5, 6)}; | [] | [x] |
| int [][] matrix = {1, 2}, {3, 4}, {5, 6}; | [] | [x] |
| int [][] matrix = {{1, 2}, {3, 4}, {5, 6}}; | [x] | [] |

## Exercise 5
```
int [][] m = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}, {10, 11, 12}};
```
| Code | Output |
| ---- | ------ |
| System.out.print( m[0][0] ); | 1 |
| System.out.print( m[1][2] ); | 6 |
| System.out.print( m[3][1] ); | 11 |
| System.out.print( m[0][2] + m[3][1] ); | 14 |
| System.out.print( m.length ); | 4 |
| System.out.print( m[2].length ); | 3 |
| System.out.print( m[2][m[0][1]] ); | 9 |
| System.out.print( m[m[1].length][1]*2 ); | 22 |

## Exercise 6
```
char [][] board = new char[10][10];
``` 
a) 3
b) 2
c) 3
d) 3
e) 3
f) 4
g) 4
h) 4
i) 5
j) 4

## Exercise 7
```
for (int i = 0; i < list.length; i ++>)
    System.out.println( list[i] );
```

