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
import java.util.LinkedList;

/**
 *
 * Represents the pits of the board.
 *
 * @author Juan Francisco Rodríguez
 */
public class KalahPit {

    private final ArrayList<KalahStone> stones;

    /**
     * Constructor
     *
     * @param mode the mode determines how many stones put initialy in the pit
     */
    public KalahPit(KalahMode mode) {
	this.stones = new ArrayList<>();
	for (int i = 0; i < mode.stones(); i++) {
	    KalahStone stone = new KalahStone();
	    this.stones.add(stone);
	}
    }

    /**
     * Gets the amount of stones remaining in this pit
     *
     * @return the number of stones
     */
    public Integer size() {
	return stones.size();
    }

    /**
     * Check if there are no remaining stones in the pit
     *
     * @return true if there is no stones, false otherwise
     */
    public Boolean isEmpty() {
	return stones.isEmpty();
    }

    /**
     * Take and removes the stones from the pit
     *
     * @return all the stones as a LinkedList
     */
    protected LinkedList<KalahStone> takeStones() {
	LinkedList<KalahStone> takedStones = new LinkedList<>(stones);
	stones.clear();
	return takedStones;
    }

    /**
     * Adds a stone to the pit
     *
     * @param stone the stone to add
     */
    protected void addStone(KalahStone stone) {
	stones.add(stone);
    }

}
