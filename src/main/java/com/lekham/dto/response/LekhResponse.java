package com.lekham.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lekham.entities.Comment;
import com.lekham.entities.Lekh;
import com.lekham.entities.Like;
import com.lekham.entities.mongo.Content;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class LekhResponse {

    private String title;
    private Content content;
    @JsonProperty("created_time")
    private Date createdTime;
    @JsonProperty("updated_time")
    private Date updatedTime;
    List<Comment> comments;
    List<Like> likes;


}
