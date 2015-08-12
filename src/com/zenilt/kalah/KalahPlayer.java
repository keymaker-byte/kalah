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

import java.util.ArrayList;

/**
 * Represents one of the players and their side of the board.
 *
 * @author Juan Francisco Rodríguez
 */
public class KalahPlayer {

    private final String name;
    private final ArrayList<KalahPit> pits;
    private final KalahHouse house;

    /**
     * Constructor
     *
     * @param name the name of the player
     * @param mode game mode determines how many stones start at each pit
     * @param pitsNumber The number of pits the player has
     */
    public KalahPlayer(String name, KalahMode mode, Integer pitsNumber) {
	this.name = name;
	pits = new ArrayList<>();
	house = new KalahHouse();
	for (int i = 0; i < pitsNumber; i++) {
	    KalahPit pit = new KalahPit(mode);
	    pits.add(pit);
	}
    }

    /**
     * Player's name
     *
     * @return the name of the player
     */
    public String getName() {
	return name;
    }

    /**
     * Gets a pit of the player by index
     *
     * @param pitIndex the corresponding index of the pit, a value between 0 and KalahGame.PITS
     * @return
     */
    public KalahPit getPit(Integer pitIndex) {
	if (pitIndex < 0 || pitIndex >= pits.size()) {
	    return null;
	}
	return pits.get(pitIndex);
    }

    /**
     * The house of the player
     *
     * @return the player's house
     */
    public KalahHouse getHouse() {
	return house;
    }

    /**
     * Check if the plater has any remaining stones
     *
     * @return true if the player has stones in any of the pits, false otherwise
     */
    protected Boolean hasStones() {
	for (KalahPit pit : pits) {
	    if (pit.size() > 0) {
		return true;
	    }
	}
	return false;
    }

    /**
     * Returns all the pits from the player in the indexed order
     *
     * @return an array of pits
     */
    public ArrayList<KalahPit> getPits() {
	return pits;
    }

}
