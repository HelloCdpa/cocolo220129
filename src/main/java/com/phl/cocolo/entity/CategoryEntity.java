package com.phl.cocolo.entity;

import com.phl.cocolo.dto.CategorySaveDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "category_table")
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;

    @Column
    private String cateName;

    public static CategoryEntity toCategorySaveEntity(CategorySaveDTO categorySaveDTO){
        CategoryEntity categoryEntity = new CategoryEntity();

        categoryEntity.setCateName(categorySaveDTO.getCateName());

        return categoryEntity;
    }

}
