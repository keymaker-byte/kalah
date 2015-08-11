#Kalah Java
A Java library that models the Kalah game (https://en.wikipedia.org/wiki/Kalah).

## How to use it
The game can be instantiated using one of the available modes (3-seed, 4-seed or 6-seed):
```
KalahGame game = new KalahGame(KalahMode.STONES_3);
```
Then start the game when ready:
```
game.start();
```
At every step the current player must decide the index of the pit to use, apply that desition to the game:
```
game.move(selectedIndex);
```
Check the game status aftert every move, they can be:
* INIT: instantiated but not started yet
* PLAYINGA: player A turn
* PLAYINGB: player B turn
* FINISHED: game was finished

When finished retrieve the winner of the game:
```
KalahPlayer winer = game.getWinner();
```
