package com.shannontheoret.tinytowns;
public class InternalGameException extends Exception {
    public InternalGameException(String message) {
        super("Internal game error:" + message);
    }
}
