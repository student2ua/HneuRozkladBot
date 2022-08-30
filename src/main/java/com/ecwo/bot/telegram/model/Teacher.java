package com.ecwo.bot.telegram.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Teacher {
    @JsonProperty("id")
    private final int id;
    @JsonProperty("fio")
    private final String fio;
}
