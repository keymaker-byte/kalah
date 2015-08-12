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

/**
 *
 * Different modes for the game that changes the ammount of stones used per pit.
 *
 * @author Juan Francisco Rodríguez
 */
public enum KalahMode {

    /**
     * 3-stone Kalah
     */
    STONES_3(3),
    /**
     * 4-stone Kalah
     */
    STONES_4(4),
    /**
     * 6-stone Kalah
     */
    STONES_6(6);

    private final Integer stones;

    KalahMode(Integer stones) {
	this.stones = stones;
    }

    /**
     * Gets the number of stones to add to each pit under this mode
     *
     * @return the number of stones
     */
    public Integer stones() {
	return this.stones;
    }

}
