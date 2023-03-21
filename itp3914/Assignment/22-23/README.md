# ITP3914 Programming Assignment (PTE)

## Instructions
1. This assignment is an individual assignment and should be done by you only. Plagiarism will be treated seriously. Any submitted assignment is found that involved wholly or partly in plagiarism (no matter the assignments are from the original authors or from the plagiarists) will be scored Zero mark and the students involved will be received discipline penalty set by the institute accordingly.
2. Grading of your programs will be based on correctness, quality, style and efficiency.
3. You are required to hand in
   - softcopy of the program source codes upload to Moodle. (named: BattleShip.java)
   - test report (sample screen of your program) (named: TestReport.docx)
4. Late submission will NOT be accepted.
5. Each student may be arranged to conduct an interview with your lecturer to explain some parts of your program code and answer some questions. Marks will be deducted if you cannot explain your code well.

## Assignment Specification
You are asked to write a Battleship game using Java.
### Setting up the game
1. When the program runs, there is a 10 x 10 battlefield created. Computer will set 3 battleship on the field randomly.
2. Each battleship's occupies consecutively 3 cells, and can be placed horizontally or vertically, the chance should be 50:50.
3. The following is an example of two battleships set horizontally while one set vertically.
4. The three battleships should not overlap.

```
    0 1 2 3 4 5 6 7 8 9
--+--------------------
0 | . . . S S S . . . .
1 | . . . . . . . . . .
2 | . . . . . . . . . .
3 | . . . . . S . . . .
4 | . . . . . S . . . .
5 | . . . . . S . . . .
6 | . . . . . . . . . .
7 | . . . . . . . . . .
8 | S S S . . . . . . .
9 | . . . . . . . . . .
```

#### Game play
1. Show the greeting message and battlefield to player as below. Hide the battleship (of course!)
```
---- Battleship Game----
    0 1 2 3 4 5 6 7 8 9
--+--------------------
0 | . . . . . . . . . .
1 | . . . . . . . . . .
2 | . . . . . . . . . .
3 | . . . . . . . . . .
4 | . . . . . . . . . .
5 | . . . . . . . . . .
6 | . . . . . . . . . .
7 | . . . . . . . . . .
8 | . . . . . . . . . .
9 | . . . . . . . . . .
```

2. Prompt player to input the place where to he/she wants to hit.
3. The player will enter two characters range from 0-9, where the first character is the nth column, and the second character is the mth row. E.g. [45] means the player wants to hit column 4, row 5 of the battlefield.

```
Set your missile [XY] (x to exit) :45
Missed.
Launched:1, Hit: 0
```

4. If player hit the battleship, the cell will shows [#].
5. If player missed the battleship, the cell will shows [o].
```
Set your missile [XY] (x to exit) :45
Missed.
Launched:1, Hit: 0

    0 1 2 3 4 5 6 7 8 9
--+--------------------
0 | . . . . . . . . . .
1 | . . . . . . . . . .
2 | . . . . . . . . . .
3 | . . . . . . . . . .
4 | . . . . . . . . . .
5 | . . . . o . . . . .
6 | . . . . . . . . . .
7 | . . . . . . . . . .
8 | . . . . . . . . . .
9 | . . . . . . . . . .
```
6. Cumulated and show the number of missile launched and the number of successful hit.
7. If the player chose a cell which have been hit before, show [You have already fired this area.] and do NOT cumulate the number of missile launched.

#### Extra feature
1. Add a cheating function: whenever the player enter "c", shows the battleships with mark "S".
```
Set your missile [XY] (x to exit) :c
    0 1 2 3 4 5 6 7 8 9
--+--------------------
0 | . . . . . . . . . .
1 | . . . o . . . . . .
2 | . . o . . . . . o .
3 | . . . . . . . . . .
4 | . . . . . . . . . .
5 | . . . . o S S S . .
6 | . . S S S . . S S S
7 | . . . . . . o . . .
8 | . . . . o . . . . .
9 | . . . . . . . . . .
```

2. Whenever the player enter "x", end the game
```
    0 1 2 3 4 5 6 7 8 9
--+--------------------
0 | . . . . . . . . . .
1 | . . . o . . . . . .
2 | . . o . . . . . o .
3 | . . . . . . . . . .
4 | . . . . . . . . . .
5 | . . . . o . . . . .
6 | . . . # . . . . . .
7 | . . . . . . o . . .
8 | . . . . o . . . . .
9 | . . . . . . . . . .
Set your missile [XY] (x to exit) :x
Bye!
```

3. When the player hit all battleships, show " You have hit all battleships!" and end the game.
```
Set your missile [XY] (x to exit) :96
It's a hit!!
Launched:19, Hit: 9
Set your missile [XY] (x to exit) :96
It's a hit!!
Launched:19, Hit: 9

    0 1 2 3 4 5 6 7 8 9
--+--------------------
0 | . . . . . . . . . .
1 | . . . o . . . . . .
2 | . . o . . . . . o .
3 | . . . . . . . . . .
4 | . . . . . . . . . .
5 | . . . . o # # # o .
6 | . o # # # o o # # #
7 | . . . . . . o . . .
8 | . . . . o . . . . .
9 | . . . . . . . . . .
Launched:19, Hit: 9
You have hit all battleships!
```

## Remark
1. No data validation is needed, you may assume the user will always input correctly. The user will only input a two-character number such as [04] or [99], [c] (to cheat) or [x] (to terminate).
2. You should put all your program code in ONE single JAVA file named BattleShip.java.
3. Total number of code should be around 100-200 lines.

## Deliverables
1. Program source code - Battleship.java
2. Test report - at least two sample running output (in doc / docx / pdf / txt format)