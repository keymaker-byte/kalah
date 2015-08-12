/*
 * The MIT License
 *
 * Copyright 2015 Juan Francisco Rodríguez.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.zenilt.kalah;

import com.zenilt.kalah.exception.KalahException;
import com.zenilt.kalah.exception.KalahIlegalMoveException;
import java.util.LinkedList;

/**
 * This is the main class used to instantiate the game.
 *
 * Call start() when ready to play.
 *
 * Use move(Integer pitIndex) to input the players' movements.
 *
 * Checkout getStatus() to retrieve the game status.
 *
 * Checkout getPlayerA() and getPlayerB() to get a representation of each side of the board.
 *
 * Use getWinner() to retrieve the game winner.
 *
 * @author Juan Francisco Rodríguez
 */
public class KalahGame {

    private static final Integer PITS = 6;
    private static final String NAMEA = "Player A";
    private static final String NAMEB = "Player B";

    private final KalahPlayer playerA;
    private final KalahPlayer playerB;
    private final KalahMode mode;
    private KalahStatus status;

    /**
     * Constructor with game mode
     *
     * @param mode the mode to construct the game
     */
    public KalahGame(KalahMode mode) {
	this.mode = mode;
	this.playerA = new KalahPlayer(NAMEA, this.mode, PITS);
	this.playerB = new KalahPlayer(NAMEB, this.mode, PITS);
	this.status = KalahStatus.INIT;
    }

    /**
     * Gets the current game status
     *
     * @return the game status
     */
    public KalahStatus getStatus() {
	return status;
    }

    /**
     * Gets the first player object
     *
     * @return the first player
     */
    public KalahPlayer getPlayerA() {
	return playerA;
    }

    /**
     * Gets the second player object
     *
     * @return the second player
     */
    public KalahPlayer getPlayerB() {
	return playerB;
    }

    /**
     * Starts the game
     *
     * @throws KalahException if the game was already started
     */
    public void start() throws KalahException {
	if (status != KalahStatus.INIT) {
	    throw new KalahException();
	}
	status = KalahStatus.PLAYINGA;
    }

    /**
     * Generates a move for the current player with all the stones at the choosen pit.
     *
     * @param pitIndex the index of the pit
     * @throws KalahException if the game has not been started or is already finished
     * @throws KalahIlegalMoveException if the move is illegal, such as there are no stones in the selected pit, or the index is out of range
     */
    public void move(Integer pitIndex) throws KalahException, KalahIlegalMoveException {
	if (status != KalahStatus.PLAYINGA && status != KalahStatus.PLAYINGB) {
	    throw new KalahException();
	}
	KalahMove move = processMove(pitIndex);
	switch (move) {
	    case ILEGAL: {
		throw new KalahIlegalMoveException();
	    }
	    case CONTINUE:
	    case STEAL: {
		changePlayerTurn();
		break;
	    }
	    case PLAYAGAIN: {
		break;
	    }
	}
	if (gameFinished()) {
	    status = KalahStatus.FINISHED;
	}
    }

    /**
     * Get the player that won the match
     *
     * @return the player that won the match, or null if there is a tie
     * @throws KalahException in case the game has not finished yet
     */
    public KalahPlayer getWinner() throws KalahException {
	if (status != KalahStatus.FINISHED) {
	    throw new KalahException();
	}
	if (playerA.getHouse().size() > playerB.getHouse().size()) {
	    return playerA;
	}
	if (playerB.getHouse().size() > playerA.getHouse().size()) {
	    return playerB;
	}
	return null;
    }

    private KalahMove processMove(Integer pitIndex) {
	if (!validMove(pitIndex)) {
	    return KalahMove.ILEGAL;
	}
	KalahPlayer currentPlayer = movingPlayer();
	KalahPit pit = currentPlayer.getPit(pitIndex);
	LinkedList<KalahStone> stones = pit.takeStones();
	KalahMove move = KalahMove.CONTINUE;
	Boolean isOponentArea = false;
	while (!stones.isEmpty()) {
	    KalahStone stone = stones.remove();
	    pitIndex++;
	    move = KalahMove.CONTINUE;
	    pit = currentPlayer.getPit(pitIndex);
	    if (pit != null) {
		if (!isOponentArea && pit.isEmpty()) {
		    move = KalahMove.STEAL;
		}
		pit.addStone(stone);
	    } else {
		if (!isOponentArea) {
		    KalahHouse housePit = currentPlayer.getHouse();
		    housePit.addStone(stone);
		    move = KalahMove.PLAYAGAIN;
		} else {
		    //add the stone back to play at opponet's area in next iteration
		    stones.add(stone);
		}
		currentPlayer = opponentPlayer(currentPlayer);
		isOponentArea = !isOponentArea;
		pitIndex = -1;
	    }
	}
	if (move == KalahMove.STEAL) {
	    steal(pitIndex);
	}
	return move;
    }

    public Boolean validMove(Integer pitIndex) {
	if (pitIndex < 0 || pitIndex >= KalahGame.PITS) {
	    return false;
	}
	return !movingPlayer().getPit(pitIndex).isEmpty();
    }

    private void steal(Integer pitIndex) {
	KalahPlayer currentPlayer = movingPlayer();
	KalahHouse currentPlayerHouse = currentPlayer.getHouse();
	LinkedList<KalahStone> stones = currentPlayer.getPit(pitIndex).takeStones();
	for (KalahStone stone : stones) {
	    currentPlayerHouse.addStone(stone);
	}
	KalahPlayer oponentPlayer = opponentPlayer(currentPlayer);
	stones = oponentPlayer.getPit((PITS - 1) - pitIndex).takeStones();
	for (KalahStone stone : stones) {
	    currentPlayerHouse.addStone(stone);
	}
    }

    private KalahPlayer movingPlayer() {
	switch (status) {
	    case PLAYINGA: {
		return playerA;
	    }
	    case PLAYINGB: {
		return playerB;
	    }
	}
	return null;
    }

    private KalahPlayer opponentPlayer(KalahPlayer player) {
	if (player.getName().equals(NAMEA)) {
	    return playerB;
	}
	return playerA;
    }

    private void changePlayerTurn() {
	switch (status) {
	    case PLAYINGA: {
		status = KalahStatus.PLAYINGB;
		break;
	    }
	    case PLAYINGB: {
		status = KalahStatus.PLAYINGA;
		break;
	    }
	}
    }

    private boolean gameFinished() {
	return !(this.playerA.hasStones() && this.playerB.hasStones());
    }

}
