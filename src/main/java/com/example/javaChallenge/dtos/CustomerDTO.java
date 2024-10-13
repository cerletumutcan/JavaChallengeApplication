package com.example.javaChallenge.dtos;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class CustomerDTO implements Serializable {
    private int id;
    private String email;
    private String password;
    private String phoneNumber;
}
