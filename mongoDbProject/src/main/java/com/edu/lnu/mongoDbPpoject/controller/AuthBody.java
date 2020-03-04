package com.edu.lnu.mongoDbPpoject.controller;

import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString()

public class AuthBody {

    private String nickName;
    private String password;

}
