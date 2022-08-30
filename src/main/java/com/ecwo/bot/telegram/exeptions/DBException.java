package com.ecwo.bot.telegram.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class DBException extends RuntimeException {
    public DBException(String msg, Throwable throwable) {
        super(msg, throwable);
    }

    public DBException(Throwable throwable) {
        super("Ошибка БД", throwable);
    }

    public DBException(String msg) {
        super(msg);
    }
}
