# ITP4501-Assignment
Programming Techniques for Mobile Systems

In this assignment, you are required to develop an Android Application to play a "15,20" game by selecting a player on a server. You can use the following website to get the detail of this game:
[https://zh.wikipedia.org/wiki/%E6%95%B8%E5%AD%97%E6%8B%B3](https://zh.wikipedia.org/wiki/%E6%95%B8%E5%AD%97%E6%8B%B3)
[https://www.youtube.com/watch?v=IrYzM-NCIEs](https://www.youtube.com/watch?v=IrYzM-NCIEs)

## Functional Requirements
1. An activity performs a player’s personal information registration screen which can store player’s name, date of birth, phone number and email by using shared preferences in your phone. You should let player to update his personal information in your app.

2. An activity which contains a button “Start”. When a player touches this button, your app will find a player from a server and then show the information of this player on the screen. 
    1. And then your game will show (0,0),(0,5),(5,0),(5,5) to represent your two hands to let you to choose. Next screen will show 0,5,10,15,20 on the screen for you to guess the number. You should try to make your choice by touching the image on the screen. 
    2. Once you make your guess and hands, your app will get the hands and guess (useless at this round) of your opponent from the server. On the screen, your app should show your name and the name of your opponent, the hands of yours and corresponding opponent. The game will show who win the game if the number on the screen is same as your guess. Otherwise, the game will go on next round but this time you only select your hands, i.e. (0,0),(0,5),(5,0),(5,5) and then your app will get the hands and guess of your opponent from the server again and go on. The game will go on until the number on the screen will be same as your guess or opponent's guess.
    3. If the game gets the winner, you can touch a Continue button to play again or Quit button to leave it. 
    4. To simplify your work, you are always the first one to start the guess. 
    5. You are required to use image to represent your and opponents' hands in this app.
3. An activity which uses a bar chart to show how many games you win and how many games you lose.
4. A database GamesLog which contains a table to store the game history for yours which store the date and time, the name of your opponents and the result of the game (Win or Lost).
5. An activity for GamesLog properly show the data stored in the local database. Your list
should show the result of a game.

### Local Database
The database scheme described here is an extremely simple one. Many fields are intended not to be included in order to reduce the complexity of this assignment. You are free to add columns and tables to the database to fit for your own needs.
```
GamesLog (gameDate, gameTime, opponentName, winOrLost)
```

### Opponents JSON Server
You can find your opponent from the server by using following url:
[https://4qm49vppc3.execute-api.us-east-1.amazonaws.com/Prod/itp4501_api/opponent/0](https://4qm49vppc3.execute-api.us-east-1.amazonaws.com/Prod/itp4501_api/opponent/0)
and the data returned is in JSON format.
The sample JSON string returned is shown below:
```
{"id": 2, "name": "May", "country": "UK"}
```
Be careful the last digit 0 on the url is going to find an online opponent from the server.

The attribute "id" is the userid of corresponding opponent.

You can get the choice and hands from that user (here is May with id 2) by using following url:
[https://4qm49vppc3.execute-api.us-east-1.amazonaws.com/Prod/itp4501_api/opponent/2](https://4qm49vppc3.execute-api.us-east-1.amazonaws.com/Prod/itp4501_api/opponent/2)
The last digit 2 is the id of May.

The sample JSON string returned is shown below:
```
{"name": "May", "left": 5, "right": 5, "guess": 15}
```

For the turn which the number is not guessed by opponent, you can ignore the value in the attribute "guess".

### Marking Guidelines
You project will be assessed according to the items below.
➢ Database initialisation
➢ Level of completion
➢ Correctness
➢ UI design
➢ Program design and implementation
➢ Program style and comments