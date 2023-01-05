# ITP4501 Project
In this assignment, you are required to develop an Android Application to play a Tic-Tac-Toe Game. This app will also record the result and corresponding time required to complete a game and use charts to show the history records. 

You can use following link to know how to play a Tic-Tac-Teo game:
[https://en.wikipedia.org/wiki/Tic-tac-toe](https://en.wikipedia.org/wiki/Tic-tac-toe)


## Functional Requirements
Listed below are the basic requirements of your application. You need to refer to the Local Database section for the database schema.

1.	A main activity which contains a main menu for players to choose. The four main functions are: Play, Game Ranking, Your Records and Close.
2. When players touch the "Play" button on the main menu, they start to play the game with CPU. Players will mark an 'O' on an available button which does not contain any mark ('O' or 'X') by touching it. After players touch on an available button, CPU will RANDOMLY to mark a 'X' on an available button. To simplify your workload, you are not required to write an AI for CPU to win the game. 
   When players or CPU who succeed in placing three of their marks in a horizontal, vertical, or diagonal row is the winner. Your app will save the record of this game to the GamesLog table in a local database and then your app will show a "Continue" for players to restart the game.

3. When players touch the "Game Ranking" button on the main menu, your app will download a JSON from your own api server. You MUST use a ListView to show all the records in the JSON string.
   
4. When players touch the "Your Records" button on the main menu, Your app will load the records in the GamesLog table from your local database. You MUST use a ListView to show all these records. Your app will provide a button "Show in Pie Chart" which let players to show winning status (Win, Lose, Draw) by using a Pie Chart.

Note: You are encouraged to design and implement extra features. 10% of the total mark will be allocated on such additional functions. Refer to section 7 Marking Guidelines for more details.

## Local Database
The database scheme described here is an extremely simple one. Many fields are intended not to be included in order to reduce the complexity of this assignment. You are free to add columns and tables to the database to fit for your own needs. 
```
GamesLog (gameID, playDate, playTime, duration, winningStatus)
```

## Ranking JSON Server
You can obtain a ranking list from your own api server and the data returned is in JSON format.
The JSON string returned is shown below:
```
[{"Name":"Kenny Lam","Duration":104},
{"Name":"Peter Kwong","Duration":25},
{"Name":"John Chan","Duration":38},
{"Name":"Johnny Kwong","Duration":117},
{"Name":"Mary Lam","Duration":23},
{"Name":"David Wong","Duration":49},
{"Name":"Alan Ma","Duration":18},
{"Name":"Carrie Lam","Duration":68},
{"Name":"Chris Lam","Duration":93},
{"Name":"Mary Cheung","Duration":78}]
```
You need to sort the JSON data by using duration in ascending order to obtain the ranking.

### Marking Guidelines
You project will be assessed according to the items below. 
➢	Database initialisation.
➢	Level of completion.
➢	Correctness.
➢	UI design (no mark will be given if you are using the same design in this document).
➢	Program design and implementation.
➢	Program style and comments.
➢	Driving Question: How can an organization get benefit from a central computerized management system by using a mobile app?
	Briefly discuss how ITP4510, ITP4522 and ITP4915M modules help you to finish this assignment.

