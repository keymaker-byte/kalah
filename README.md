# kalah
A Java library that models the Kahala game (https://en.wikipedia.org/wiki/Kalah).

## How to use it
The game can be instantiated using one of the available modes (3-seed, 4-seed or 6-seed)
```
KalahGame game = new KalahGame(KalahMode.STONES_3);
```
to start the game
```
game.start();
```
At every step the current player must decide the index of the pit to use
```
game.move(selectedIndex);
```
The game status are:
* INIT: instantiated but not started yet
* PLAYINGA: player A turn
* PLAYINGB: player B turn
* FINISHED: game was finished
To retreive the winner:
```
KalahPlayer winer = game.getWinner();
```
