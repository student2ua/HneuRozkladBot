package com.ecwo.bot.telegram.exeptions;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BaseException extends RuntimeException {
    public BaseException(String msg, Throwable throwable) {
        super(msg, throwable);
        log.error(msg, throwable);
    }

    public BaseException(String msg) {
        super(msg);
        log.error(msg);
    }
}
