package com.phl.cocolo.dto;

import com.phl.cocolo.entity.BaseEntity;
import com.phl.cocolo.entity.BoardEntity;
import com.phl.cocolo.entity.LikeEntity;
import com.phl.cocolo.entity.MemberEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LikeDetailDTO {
    private Long likeId;
    private Long boardId;
    private Long memberId;


    public static LikeDetailDTO toLikeDetailDTO(LikeEntity likeEntity, BoardEntity boardEntity, MemberEntity memberEntity){
        LikeDetailDTO likeDetailDTO = new LikeDetailDTO();

        likeDetailDTO.setLikeId(likeEntity.getId());
        likeDetailDTO.setBoardId(boardEntity.getId());
        likeDetailDTO.setMemberId(memberEntity.getId());


        return likeDetailDTO;

    }

}
