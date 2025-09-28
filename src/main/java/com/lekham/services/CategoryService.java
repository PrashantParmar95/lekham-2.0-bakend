package com.lekham.services;

import com.lekham.api.RestApiResponse;
import com.lekham.dto.request.CategoryRequestDto;
import com.lekham.entities.Category;
import com.lekham.entities.Users;
import com.lekham.repositories.CategoryRepository;
import com.lekham.util.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

@Autowired
private CategoryRepository categoryRepository;

@Autowired
private UserService userService;

@Autowired
private MessageUtil messageUtil;

    public RestApiResponse<Category> addCategory(CategoryRequestDto categoryRequestDto) {
        Users user = userService.getCurrentUser();
        if(user == null) {
            return null;
        }
        //check category already exist for same user

        int count = categoryRepository.getCountByNameAndUserId(categoryRequestDto.getName(), user.getId());

        if(count > 0) {
            return RestApiResponse.badResponseBuilder(messageUtil.get("category.already.exist"));
        }
        Category savedCategory = categoryRepository.save(CategoryRequestDto.buildCategory(categoryRequestDto, user));

        return RestApiResponse.successResponseBuilder(messageUtil.get("category.added.success"), savedCategory);
    }

    public ResponseEntity<RestApiResponse<List<Category>>> GetListOfCategoryForUser() {
        Users user = userService.getCurrentUser();
        if(user == null) {
            return null;
        }
        List<Category> categories = categoryRepository.findByCreatedBy(user.getId());

        if(categories.isEmpty()) {
            return new ResponseEntity<>(RestApiResponse.badResponseBuilder(messageUtil.get("category.not.found")), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(RestApiResponse.successResponseBuilder(messageUtil.get("category.all.fetch.success"), categories), HttpStatus.OK);
    }

    public Category getCategoryById(long categoryId) {
        Optional<Category> categoryOpt = categoryRepository.findById(categoryId);

        if(categoryOpt.isPresent()) {
            return categoryOpt.get();
        }

        return null;
    }
}
