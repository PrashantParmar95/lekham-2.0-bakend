package com.lekham.dto.request;

import com.lekham.dto.LekhDto;
import com.lekham.entities.Category;
import java.util.List;

public record CategoryWithLekhs (Category category,List<LekhDto> lekhList){}
