package com.openapi.auth.exception;


import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class InternalException extends Exception {

    private static final long serialVersionUID = 1L;

    private int outcomeCode;
    private String outcomeMessage;
    private String internalMessage;

    public InternalException(Integer outcomeCode, String outcomeMessage, String internalMessage){
        this.outcomeCode = outcomeCode;
        this.outcomeMessage = outcomeMessage;
        this.internalMessage = internalMessage;
    }

}
