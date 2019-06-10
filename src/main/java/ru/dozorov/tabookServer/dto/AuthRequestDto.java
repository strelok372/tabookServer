package ru.dozorov.tabookServer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AuthRequestDto {
    private String username;
    private String password;
    private String email;
}
