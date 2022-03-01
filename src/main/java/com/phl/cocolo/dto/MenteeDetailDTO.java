package com.phl.cocolo.dto;

import com.phl.cocolo.entity.MenteeEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenteeDetailDTO {
    //신청한 멘토링 정보 조회
    private Long menteeId;
    //멘티 회원 아이디
    private Long memberId;
    private int menteeCount;

    //멘토 닉네임, 멘토링 남은 횟수, 신청 날짜, 멘토링 1회당 금액
    private String mentorNickName;
    private int mentoringPrice;
    private LocalDateTime createTime;
    //멘토링 하면 > 멘토가 버튼을 눌러 멘토링 횟수 차감
    //멘토에게 보여줄 화면 > 멘티 닉네임, 남은 횟수, 날짜, 멘토링 1회당 번 포인트
    //멘토 환불 하면 > 남은 멘토링 금액 * 멘토링 남은 횟수 = 환불

    public static MenteeDetailDTO toMenteeDetailDTO(MenteeEntity menteeEntity){
        MenteeDetailDTO menteeDetailDTO = new MenteeDetailDTO();

        menteeDetailDTO.setMenteeId(menteeEntity.getId());
        menteeDetailDTO.setMemberId(menteeEntity.getMemberEntity().getId());
        menteeDetailDTO.setMenteeCount(menteeEntity.getMenteeCount());
        menteeDetailDTO.setMentorNickName(menteeEntity.getMentoringEntity().getMemberEntity().getMemberNickName());
        menteeDetailDTO.setMentoringPrice(menteeEntity.getMentoringEntity().getMentoringPrice());
        menteeDetailDTO.setCreateTime(menteeEntity.getCreatTime());

        return menteeDetailDTO;



    }




}
