package com.jfteam.sharedrawing.exception;

public class IllegalEntityException extends IllegalArgumentException {

    private static final long serialVersionUID = -2516172526799685709L;

    public IllegalEntityException(String message, Exception e) {
        super(message, e);
    }

    public IllegalEntityException(String message) {
        super(message);
    }
}
