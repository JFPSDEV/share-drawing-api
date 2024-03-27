package com.jfteam.sharedrawing.exception;

public class ServerSideException extends RuntimeException {

    private static final long serialVersionUID = -5038193676107089487L;

    public ServerSideException(String message, Exception e) {
        super(message, e);
    }

    public ServerSideException(String message) {
        super(message);
    }

}