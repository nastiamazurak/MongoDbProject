package com.edu.lnu.mongoDbPpoject.model;


import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString()
@EqualsAndHashCode()
@Document("posts")
public class Post {

    @Id
    private String id;
    private String text;
    private Date date;
    @Field("author_nickname")
    private String authorNickName;
    private List<Comment> comments;

}
