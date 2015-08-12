#Kalah Java
A Java library that models the Kalah game (https://en.wikipedia.org/wiki/Kalah).

## How to use it
The game can be instantiated using one of the available modes (3-stones, 4-stones or 6-stones):
```
KalahGame game = new KalahGame(KalahMode.STONES_3);
```
Then start the game when ready:
```
game.start();
```
At every step the active player must decide the index of one of the 6 available pits to use (0-5). The pit must contain stones to be used. Apply that desition to the game:
```
game.move(selectedIndex);
```
You can get the current representation of each side of the board using the players' objects:
```
KalahPlayer playerA = game.getPlayerA();
KalahPlayer playerB = game.getPlayerB();
```
Check the game status after every move, they can be:
* INIT: instantiated but not started yet
* PLAYINGA: player A turn
* PLAYINGB: player B turn
* FINISHED: game was finished

When finished you can retrieve the winner of the game:
```
KalahPlayer winer = game.getWinner();
```
## Example
A full example is available to play the game in a terminal: https://github.com/zenilt/kalah/blob/master/src/com/zenilt/kalah/example/KalahConsole.java

There is a simple Web application using this library here: https://github.com/zenilt/kalah-web
