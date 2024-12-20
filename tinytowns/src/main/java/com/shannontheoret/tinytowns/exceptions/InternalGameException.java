package com.shannontheoret.tinytowns.exceptions;
public class InternalGameException extends Exception {
    public InternalGameException(String message) {
        super("Internal game error:" + message);
    }
}
