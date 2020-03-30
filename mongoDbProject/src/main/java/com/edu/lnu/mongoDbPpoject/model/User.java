package com.edu.lnu.mongoDbPpoject.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import lombok.*;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import java.util.Date;
import java.util.Set;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString()
@EqualsAndHashCode()

@Document("users")
public class User  {
    @Id
    private String _id;
    private String name;
    private String surname;
    @Field("nickname")
    @Indexed(unique = true, direction = IndexDirection.DESCENDING, dropDups = true)
    private String nickName;
    private String password;
    @Field("birth_date")
    private Date birthDate;
    private String city;
    private String country;
    @DBRef
    private Role role;
    private boolean enabled;
}
