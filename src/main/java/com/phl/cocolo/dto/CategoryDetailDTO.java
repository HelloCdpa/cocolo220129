package com.phl.cocolo.dto;

import com.phl.cocolo.entity.CategoryEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDetailDTO {
    private Long cateId;
    private String cateName;

    public static CategoryDetailDTO toCategoryDetailDTO(CategoryEntity categoryEntity){
        CategoryDetailDTO categoryDetailDTO = new CategoryDetailDTO();

        categoryDetailDTO.setCateId(categoryEntity.getId());
        categoryDetailDTO.setCateName(categoryEntity.getCateName());

        return categoryDetailDTO;
    }
}
