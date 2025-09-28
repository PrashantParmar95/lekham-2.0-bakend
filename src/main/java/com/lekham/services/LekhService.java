package com.lekham.services;

import com.lekham.api.RestApiResponse;
import com.lekham.dto.LekhDto;
import com.lekham.dto.request.CategoryWithLekhs;
import com.lekham.dto.request.LekhRequestDto;
import com.lekham.dto.response.LekhResponse;
import com.lekham.entities.Category;
import com.lekham.entities.Lekh;
import com.lekham.entities.Users;
import com.lekham.entities.mongo.Content;
import com.lekham.enums.LekhAccess;
import com.lekham.enums.LekhPriority;
import com.lekham.repositories.ContentRepository;
import com.lekham.repositories.LekhRepository;
import com.lekham.util.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class LekhService {
    
    @Autowired
    private UserService userService;
    @Autowired
    private ContentRepository contentRepository;
    @Autowired
    private LekhRepository lekhRepository;
    @Autowired
    private MessageUtil messageUtil;
    @Autowired
    private CategoryService categoryService;

    public RestApiResponse<Lekh> addLekh(LekhRequestDto lekhDto) {
        Users currentUser = userService.getCurrentUser();
        if(currentUser == null){
            return RestApiResponse.notAuthorized();
        }
        Content content = new Content();
        content.setContent(lekhDto.getContent());
        content.setCreatedBy(currentUser.getId());
        content.setCreatedTime(new Date());
        Content savedContent = contentRepository.save(content);
        Lekh lekh = new Lekh();
        lekh.setContentId(savedContent.getId());
        lekh.setCreatedBy(currentUser.getId());
        lekh.setCreatedTime(new Date());
        lekh.setTitle(lekhDto.getTitle());
        lekh.setAccess(LekhAccess.valueOf(lekhDto.getAccess()));
        lekh.setPriority(LekhPriority.valueOf(lekhDto.getPriority()));
        lekh.setParentId(lekhDto.getParent());
        lekh.setCategoryId(lekhDto.getCategory());
        Lekh savedLekh = lekhRepository.save(lekh);

        return RestApiResponse.successResponseBuilder(messageUtil.get("lekh.save.success"),savedLekh);
    }

    public RestApiResponse<LekhResponse> getLekhDetails(long lekhId) {
        Optional<Lekh> lekh = lekhRepository.findById(lekhId);
        LekhResponse lekhResponse = new LekhResponse();
        if(lekh.isPresent()){
            Optional<Content> content = contentRepository.findById(lekh.get().getContentId());
            if(content.isPresent()){
                lekhResponse.setContent(content.get());
                lekhResponse.setTitle(lekh.get().getTitle());
                lekhResponse.setCreatedTime(lekh.get().getCreatedTime());
                lekhResponse.setUpdatedTime(lekh.get().getUpdatedTime());
                return RestApiResponse.successResponseBuilder(messageUtil.get("lekh.get.success"),lekhResponse);
            }

        }
        return RestApiResponse.badResponseBuilder(messageUtil.get("lekh.get.error"));
    }

    public ResponseEntity<RestApiResponse<CategoryWithLekhs>>  getLekhListForCategory(long categoryId) {

        Category category = categoryService.getCategoryById(categoryId);
        if(category == null){
            return new ResponseEntity<>(RestApiResponse.successResponseBuilder("Category not found",null), HttpStatus.BAD_REQUEST);
        }
        Users user = userService.getCurrentUser();
        if(user == null){
            return new ResponseEntity<>(RestApiResponse.notAuthorized(), HttpStatus.UNAUTHORIZED);
        }
        List<Lekh> lekhs = lekhRepository.findByUserIdandCategoryId(user.getId(),categoryId);

        List<LekhDto> lekhDtos = new ArrayList<>();

        lekhDtos = LekhDto.buildHierarchy(lekhs);
        return new ResponseEntity<>(RestApiResponse.successResponseBuilder("",new CategoryWithLekhs(category,lekhDtos)),HttpStatus.OK);
    }
}
