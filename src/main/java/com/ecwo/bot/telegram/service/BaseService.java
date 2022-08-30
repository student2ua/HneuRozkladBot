package com.ecwo.bot.telegram.service;

import javax.ws.rs.NotFoundException;
import java.util.List;

public class BaseService {
    public <T> T wrapResult(T result) {
        if (result == null) throw new NotFoundException();
        return result;
    }

    public <T> List<T> wrapResults(List<T> list) {
        if (list == null || list.size() == 0) throw new NotFoundException();
        return list;
    }
}
