package com.phl.cocolo.dto;

import com.phl.cocolo.entity.OnClassEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OnClassDetailDTO {
    private Long onClassId;

    private String onClassTeacher;
    private String onClassTitle;
    private String onClassContents;
    private String onClassCate;
    private String onClassIntro;

    private int onClassPrice;
    private String onClassFileName;
    private int onClassWishListCount;

    public static OnClassDetailDTO toOnClassDetailDTO(OnClassEntity onClassEntity){
        OnClassDetailDTO onClassDetailDTO = new OnClassDetailDTO();

        onClassDetailDTO.setOnClassId(onClassEntity.getId());
        onClassDetailDTO.setOnClassCate(onClassEntity.getOnClassCate());
        onClassDetailDTO.setOnClassContents(onClassEntity.getOnClassContents());
        onClassDetailDTO.setOnClassIntro(onClassEntity.getOnClassIntro());
        onClassDetailDTO.setOnClassTitle(onClassEntity.getOnClassTitle());
        onClassDetailDTO.setOnClassTeacher(onClassEntity.getOnClassTeacher());
        onClassDetailDTO.setOnClassPrice(onClassEntity.getOnClassPrice());
        onClassDetailDTO.setOnClassFileName(onClassEntity.getOnClassFileName());
        onClassDetailDTO.setOnClassWishListCount(onClassEntity.getOnClassWishListCount());

        return onClassDetailDTO;

    }




}
