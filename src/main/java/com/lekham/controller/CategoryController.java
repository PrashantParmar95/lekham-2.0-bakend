package com.lekham.controller;


import com.lekham.api.RestApiResponse;
import com.lekham.dto.request.CategoryRequestDto;
import com.lekham.entities.Category;
import com.lekham.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {


    @Autowired
    private CategoryService categoryService;

    @PostMapping("/add")
    public RestApiResponse<Category> getCategory(@RequestBody CategoryRequestDto categoryRequestDto) {

        return categoryService.addCategory(categoryRequestDto);
    }

    @GetMapping("/list")
    public ResponseEntity<RestApiResponse<List<Category>>> getCategoryList() {

      //  return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        return categoryService.GetListOfCategoryForUser();
    }
}
