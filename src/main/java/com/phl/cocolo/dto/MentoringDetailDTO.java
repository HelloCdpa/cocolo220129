package com.phl.cocolo.dto;

import com.phl.cocolo.entity.MentoringEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MentoringDetailDTO {
    private Long mentoringId;
    private Long memberId;

    private String mentoringTitle;
    private String mentoringContents;
    private String mentoringWriter;

    private int mentoringCount;
    private int mentoringPrice;

    private String mentoringCareer;
    private String mentoringCate;


//    멘토링 상세조회
    public static MentoringDetailDTO toMentoringDetailDTO(MentoringEntity mentoringEntity){
        MentoringDetailDTO mentoringDetailDTO = new MentoringDetailDTO();

        mentoringDetailDTO.setMentoringId(mentoringEntity.getId());
        mentoringDetailDTO.setMemberId(mentoringEntity.getMemberEntity().getId());

        mentoringDetailDTO.setMentoringTitle(mentoringEntity.getMentoringTitle());
        mentoringDetailDTO.setMentoringContents(mentoringEntity.getMentoringContents());
        mentoringDetailDTO.setMentoringWriter(mentoringEntity.getMemberEntity().getMemberNickName());
        mentoringDetailDTO.setMentoringCount(mentoringDetailDTO.getMentoringCount());
        mentoringDetailDTO.setMentoringPrice(mentoringDetailDTO.getMentoringPrice());
        mentoringDetailDTO.setMentoringCate(mentoringEntity.getMentoringCate());
        mentoringDetailDTO.setMentoringCareer(mentoringEntity.getMentoringCareer());

        return mentoringDetailDTO;
    }





}
