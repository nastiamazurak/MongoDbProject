package com.edu.lnu.mongoDbPpoject.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString()
@EqualsAndHashCode()

@Document("role")
public class Role {
    @Id
    private String _id;
    private String role;
}
