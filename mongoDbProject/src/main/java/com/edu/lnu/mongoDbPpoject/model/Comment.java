package com.edu.lnu.mongoDbPpoject.model;

import lombok.*;

import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString()
@EqualsAndHashCode()
public class Comment {

    private String text;
    private Date date;
    private String author_nickname;
}
