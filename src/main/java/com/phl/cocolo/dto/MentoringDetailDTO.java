package com.phl.cocolo.dto;

import com.phl.cocolo.entity.MentoringEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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
    private String memberProfileName;

    private LocalDateTime createTime;


//    멘토링 상세조회
    public static MentoringDetailDTO toMentoringDetailDTO(MentoringEntity mentoringEntity){
        MentoringDetailDTO mentoringDetailDTO = new MentoringDetailDTO();

        mentoringDetailDTO.setMentoringId(mentoringEntity.getId());
        mentoringDetailDTO.setMemberId(mentoringEntity.getMemberEntity().getId());
        mentoringDetailDTO.setMemberProfileName(mentoringEntity.getMemberEntity().getMemberProfileName());

        mentoringDetailDTO.setMentoringTitle(mentoringEntity.getMentoringTitle());
        mentoringDetailDTO.setMentoringContents(mentoringEntity.getMentoringContents());
        mentoringDetailDTO.setMentoringWriter(mentoringEntity.getMemberEntity().getMemberNickName());
        mentoringDetailDTO.setMentoringCount(mentoringEntity.getMentoringCount());
        mentoringDetailDTO.setMentoringPrice(mentoringEntity.getMentoringPrice());
        mentoringDetailDTO.setMentoringCate(mentoringEntity.getMentoringCate());
        mentoringDetailDTO.setMentoringCareer(mentoringEntity.getMentoringCareer());
        mentoringDetailDTO.setCreateTime(mentoringEntity.getCreatTime());

        return mentoringDetailDTO;
    }





}
