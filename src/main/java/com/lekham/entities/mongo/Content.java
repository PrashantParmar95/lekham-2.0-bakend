package com.lekham.entities.mongo;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Document(collection = "contents")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Content {

    @Id
    private String id;

    private String content;

    @Field("created_time")
    private Date createdTime;

    @Field("updated_time")
    private Date updatedTime;

    @Field("created_by")
    private Long createdBy;

    @Field("updated_by")
    private Long updatedBy;
}
