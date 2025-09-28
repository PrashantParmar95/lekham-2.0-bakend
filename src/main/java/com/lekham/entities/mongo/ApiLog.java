package com.lekham.entities.mongo;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Document(collection = "api_logs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ApiLog {

    @Id
    private String id;
    private String request;
    private String response;
    @Field("request_time")
    private Date requestTime;
    @Field("response_time")
    private Date responseTime;
    @Field("created_by")
    private Long createdBy;
}
