package com.example.personbook.core.rest;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ServerResponse {

    private LocalDateTime timeStamp;
    private String message;
}
