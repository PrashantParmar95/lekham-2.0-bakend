package com.lekham.api;

import com.lekham.entities.Users;
import com.lekham.util.MessageUtil;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.util.Date;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RestApiResponse<T> {

    private boolean success;
    private String message;
    private String error;
    private Date timestamp;
    private HttpStatus status;
    private T data;




    public static RestApiResponse badResponseBuilder(String message) {
        return new RestApiResponse(false,null,message,new Date(),HttpStatus.BAD_REQUEST,null);
    }

    public static <T> RestApiResponse<T> notAuthorized() {
        return new RestApiResponse<>(false,"Not Authorized",null,new Date(),HttpStatus.UNAUTHORIZED,null);
    }

    public static <T> RestApiResponse<T> successResponseBuilder(String message, T obj) {
        return new RestApiResponse<>(true, message, null, new Date(), HttpStatus.OK, obj);
    }

}
