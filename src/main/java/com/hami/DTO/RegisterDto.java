package com.hami.DTO;

import lombok.Data;

@Data
public class RegisterDto {
    private String name;
    private String email;
    private String phoneNumber;
    private String password;
}
