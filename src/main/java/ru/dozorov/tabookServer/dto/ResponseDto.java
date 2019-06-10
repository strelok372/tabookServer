package ru.dozorov.tabookServer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseDto {
    private Boolean status;
    private String body;
}
