package com.jfteam.sharedrawing.exception;

public class NoSuchEntityException extends RuntimeException {

    private static final long serialVersionUID = -1682071042347346617L;

    public NoSuchEntityException(String message, Exception e) {
        super(message, e);
    }

    public NoSuchEntityException(String message) {
        super(message);
    }


}
