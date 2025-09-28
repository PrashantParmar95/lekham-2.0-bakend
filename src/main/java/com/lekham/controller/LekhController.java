package com.lekham.controller;


import com.lekham.api.RestApiResponse;
import com.lekham.dto.request.CategoryWithLekhs;
import com.lekham.dto.request.LekhRequestDto;
import com.lekham.dto.response.LekhResponse;
import com.lekham.entities.Lekh;
import com.lekham.services.LekhService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lekh")
public class LekhController {

    @Autowired
    private LekhService lekhService;

    @PostMapping("/add")
    public RestApiResponse<Lekh> addLekh(@RequestBody LekhRequestDto lekhDto){

        return lekhService.addLekh(lekhDto);
    }

    @GetMapping("/{lekhId}")
    public RestApiResponse<LekhResponse> addLekh(@PathVariable("lekhId") long lekhId){

        return lekhService.getLekhDetails(lekhId);
    }


    @GetMapping("/list/{categoryId}")
    public ResponseEntity<RestApiResponse<CategoryWithLekhs>> lekhListForCategory(@PathVariable("categoryId") long categoryId){

       // return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
      return lekhService.getLekhListForCategory(categoryId);
    }
    
}

