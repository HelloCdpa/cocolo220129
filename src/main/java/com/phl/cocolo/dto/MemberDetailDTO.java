package com.phl.cocolo.dto;

import com.phl.cocolo.entity.MemberEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberDetailDTO {

    private Long memberId;
    private String memberEmail;
    private String memberPassword;
    private String memberName;
    private String memberNickName;
    private String memberPhone;
    private String memberProfileName;
    private String memberInteresting;
    private String memberLevel;
    private int memberPoint;

    private LocalDateTime memberCreateTime;
    private LocalDateTime memberUpdateTime;
    


    public static MemberDetailDTO toMemberDetailDTO(MemberEntity memberEntity){
        MemberDetailDTO memberDetailDTO = new MemberDetailDTO();
        memberDetailDTO.setMemberId(memberEntity.getId());
        memberDetailDTO.setMemberName(memberEntity.getMemberName());
        memberDetailDTO.setMemberNickName(memberEntity.getMemberNickName());
        memberDetailDTO.setMemberPhone(memberEntity.getMemberPhone());
        memberDetailDTO.setMemberEmail(memberEntity.getMemberEmail());
        memberDetailDTO.setMemberPassword(memberEntity.getMemberPassword());
        memberDetailDTO.setMemberProfileName(memberEntity.getMemberProfileName());
        memberDetailDTO.setMemberPoint(memberEntity.getMemberPoint());
        memberDetailDTO.setMemberInteresting(memberEntity.getMemberInteresting());
        memberDetailDTO.setMemberLevel(memberEntity.getMemberLevel());

        return memberDetailDTO;

    }



}
