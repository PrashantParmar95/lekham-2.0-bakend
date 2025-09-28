package com.lekham.dto;
import com.lekham.entities.Lekh;
import com.lekham.enums.LekhAccess;
import com.lekham.enums.LekhPriority;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LekhDto {
    private long id;
    private String title;
    private String contentId;
    private long categoryId;
    private boolean published;
    private boolean deleted;
    private LekhAccess access;
    private LekhPriority priority;
    private String bgc;
    private String txtc;
    private long parentId;
    private Date createdTime;
    private Date updatedTime;
    private long createdBy;
    private long updatedBy;
    private List<LekhDto> childlekhs = new ArrayList<>();


    public static LekhDto fromEntity(Lekh lekh) {
        return new LekhDto(
                lekh.getId(),
                lekh.getTitle(),
                lekh.getContentId(),
                lekh.getCategoryId(),
                lekh.isPublished(),
                lekh.isDeleted(),
                lekh.getAccess(),
                lekh.getPriority(),
                lekh.getBgc(),
                lekh.getTxtc(),
                lekh.getParentId(),
                lekh.getCreatedTime(),
                lekh.getUpdatedTime(),
                lekh.getCreatedBy(),
                lekh.getUpdatedBy(),
                new ArrayList<>() // children initialized empty
        );
    }

    // --- Builder: build hierarchy from List<Lekh> ---
    public static List<LekhDto> buildHierarchy(List<Lekh> entities) {
        // 1. Convert entities to DTOs and map by ID
        Map<Long, LekhDto> dtoMap = entities.stream()
                .map(LekhDto::fromEntity)
                .collect(Collectors.toMap(LekhDto::getId, dto -> dto));

        List<LekhDto> roots = new ArrayList<>();

        // 2. Link children to parents
        for (Lekh entity : entities) {
            LekhDto dto = dtoMap.get(entity.getId());
            if (entity.getParentId() == 0) {
                // Root node
                roots.add(dto);
            } else {
                LekhDto parent = dtoMap.get(entity.getParentId());
                if (parent != null) {
                    parent.getChildlekhs().add(dto);
                } else {
                    // Orphan, treat as root if no parent found
                    roots.add(dto);
                }
            }
        }

        return roots;
    }
}
