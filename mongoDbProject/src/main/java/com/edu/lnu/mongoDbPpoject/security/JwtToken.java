package com.edu.lnu.mongoDbPpoject.security;

import com.edu.lnu.mongoDbPpoject.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JwtToken {
    private int id;
    private String username;
    private String role;
}
