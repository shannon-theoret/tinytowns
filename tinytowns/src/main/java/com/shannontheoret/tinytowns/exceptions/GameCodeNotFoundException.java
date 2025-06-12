package com.shannontheoret.tinytowns.exceptions;

public class GameCodeNotFoundException extends Exception {

    public GameCodeNotFoundException(String gameCode) {
        super("Invalid game code: " + gameCode);
    }
}
