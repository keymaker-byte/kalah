package com.zenilt.kalah.example;

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
import com.zenilt.kalah.KalahGame;
import static com.zenilt.kalah.KalahGame.PITS;
import com.zenilt.kalah.KalahMode;
import com.zenilt.kalah.KalahPit;
import com.zenilt.kalah.KalahPlayer;
import com.zenilt.kalah.KalahStatus;
import com.zenilt.kalah.exception.KalahException;
import com.zenilt.kalah.exception.KalahIlegalMoveException;
import java.io.PrintStream;
import java.util.Scanner;

/**
 *
 * @author Juan Francisco Rodríguez
 */
public class KalahConsole {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
	try (Scanner in = new Scanner(System.in)) {
	    try {
		KalahGame game = new KalahGame(KalahMode.STONES_3);
		print(game, System.out);
		in.next();
		game.start();
		print(game, System.out);
		while (true) {
		    int n = in.nextInt();
		    try {
			game.move(n);
		    } catch (KalahIlegalMoveException kalahIllegalMoveException) {
			System.out.println("Illegal Move");
			continue;
		    }
		    print(game, System.out);
		    if (game.getStatus() == KalahStatus.FINISHED) {
			break;
		    }
		}
	    } catch (KalahException ex) {
		ex.printStackTrace();
	    }
	}
    }

    private static void print(KalahGame game, PrintStream stream) throws KalahException {
	switch (game.getStatus()) {
	    case INIT: {
		stream.println("Enter a number to start...");
		break;
	    }
	    case PLAYINGA: {
		printKalahGame(game, stream, true);
		stream.println("Player A Moves");
		break;
	    }
	    case PLAYINGB: {
		printKalahGame(game, stream, true);
		stream.println("Player B Moves");
		break;
	    }
	    case FINISHED: {
		printKalahGame(game, stream, false);
		KalahPlayer winer = game.getWinner();
		if (winer != null) {
		    stream.println(String.format("Finished. %s wins.", winer.getName()));
		} else {
		    stream.println(String.format("Finished with a tie."));
		}
		break;
	    }
	}
    }

    private static void printKalahGame(KalahGame game, PrintStream stream, Boolean withNumbers) {
	String houseA = String.format("[%s]", String.format("%02d", game.getPlayerA().getHouse().size()));
	String pitsA = "";
	for (KalahPit pit : game.getPlayerA().getPits()) {
	    pitsA = pitsA + String.format("(%d)", pit.size());
	}
	String houseB = String.format("[%s]", String.format("%02d", game.getPlayerB().getHouse().size()));
	String pitsB = "";
	for (KalahPit pit : game.getPlayerB().getPits()) {
	    pitsB = String.format("(%d)", pit.size()) + pitsB;
	}
	if (!withNumbers) {
	    stream.println(String.format("%s %s %s", game.getPlayerB().getName(), houseB, pitsB));
	    stream.println(String.format("%s     %s %s", game.getPlayerA().getName(), pitsA, houseA));
	} else {
	    String pitsnumbersA = "";
	    String pitsnumbersB = "";
	    for (int i = 0; i < PITS; i++) {
		pitsnumbersA = pitsnumbersA + String.format("(%d)", i);
		pitsnumbersB = String.format("(%d)", i) + pitsnumbersB;
	    }
	    stream.println(String.format("              %s", pitsnumbersB));
	    stream.println(String.format("%s %s %s", game.getPlayerB().getName(), houseB, pitsB));
	    stream.println(String.format("%s      %s %s", game.getPlayerA().getName(), pitsA, houseA));
	    stream.println(String.format("              %s", pitsnumbersA));
	}

    }

}
