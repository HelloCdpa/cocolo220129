package com.phl.cocolo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OnClassUpdateDTO {
    private Long onClassId;

    private String onClassTeacher;
    private String onClassTitle;
    private String onClassContents;
    private String onClassCate;
    private String onClassIntro;

    private int onClassPrice;
    private String onClassFileName;
    private int onClassWishListCount;
}
