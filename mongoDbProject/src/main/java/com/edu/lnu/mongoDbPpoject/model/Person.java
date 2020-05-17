package com.edu.lnu.mongoDbPpoject.model;

import lombok.*;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString()
@EqualsAndHashCode()

@NodeEntity
public class Person {

    private String nickName;
    private String firstName;
    private String lastName;
    @Relationship(type="Follows", direction = Relationship.OUTGOING)
    private List<Person> followers;
}
