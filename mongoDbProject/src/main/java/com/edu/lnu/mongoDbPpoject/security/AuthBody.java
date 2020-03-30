package com.edu.lnu.mongoDbPpoject.security;
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

