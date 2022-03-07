package com.phl.cocolo.dto;

import com.phl.cocolo.entity.MyClassEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyClassDetailDTO {
    private Long MyClassId;
    private Long memberId;

    private Long onClassId;

    //구매한 온라인클래스 정보
    private String onClassTeacher;
    private String onClassTitle;
    private String onClassContents;
    private String onClassCate;
    private String onClassIntro;

    private int onClassPrice;
    private String onClassFileName;
    private int onClassWishListCount;

    public static MyClassDetailDTO toMyClassDetailDTO(MyClassEntity myClassEntity){
        MyClassDetailDTO myClassDetailDTO = new MyClassDetailDTO();

        myClassDetailDTO.setMyClassId(myClassEntity.getId());
        myClassDetailDTO.setOnClassId(myClassEntity.getOnClassEntity().getId());
        myClassDetailDTO.setMemberId(myClassEntity.getMemberEntity().getId());

        myClassDetailDTO.setOnClassTeacher(myClassEntity.getOnClassEntity().getOnClassTeacher());
        myClassDetailDTO.setOnClassTitle(myClassEntity.getOnClassEntity().getOnClassTeacher());
        myClassDetailDTO.setOnClassContents(myClassEntity.getOnClassEntity().getOnClassContents());
        myClassDetailDTO.setOnClassCate(myClassEntity.getOnClassEntity().getOnClassCate());
        myClassDetailDTO.setOnClassIntro(myClassEntity.getOnClassEntity().getOnClassIntro());
        myClassDetailDTO.setOnClassPrice(myClassEntity.getOnClassEntity().getOnClassPrice());
        myClassDetailDTO.setOnClassWishListCount(myClassEntity.getOnClassEntity().getOnClassWishListCount());

        return myClassDetailDTO;
    }

}
