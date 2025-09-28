package com.lekham.dto.request;

import com.lekham.entities.Category;
import com.lekham.entities.Users;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class CategoryRequestDto {

    private String name;
    private String description;


    public static Category buildCategory(CategoryRequestDto categoryRequestDto, Users user) {
        Category category = new Category();
        category.setName(categoryRequestDto.getName());
        category.setDescription(categoryRequestDto.getDescription());
        category.setActive(true);
        category.setCreatedBy(user.getId());
        category.setCreatedDate(new Date());

        return category;
    }

}
