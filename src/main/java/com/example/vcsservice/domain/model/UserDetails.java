package com.example.vcsservice.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class UserDetails {
    private String firstName;
    private String lastName;
    private String email;
}
