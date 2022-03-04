package com.phl.cocolo.dto;

import com.phl.cocolo.entity.CommentEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDetailDTO {
    private Long commentId;
    private Long boardId;
    private Long memberId;
    private String commentWriter;
    private String commentContents;
    private String writerProfileName;

    private LocalDateTime commentCreateDate;
    private LocalDateTime commentUpdateDate;

    public static CommentDetailDTO toCommentDetail(CommentEntity commentEntity){
        CommentDetailDTO commentDetailDTO = new CommentDetailDTO();
        commentDetailDTO.setCommentWriter(commentEntity.getCommentWriter());
        commentDetailDTO.setCommentContents(commentEntity.getCommentContents());
        commentDetailDTO.setCommentCreateDate(commentEntity.getCreatTime());
        commentDetailDTO.setCommentUpdateDate(commentEntity.getUpdateTime());

        commentDetailDTO.setCommentId(commentEntity.getId());
        commentDetailDTO.setBoardId(commentEntity.getBoardEntity().getId());
        commentDetailDTO.setMemberId(commentEntity.getMemberEntity().getId());
        commentDetailDTO.setWriterProfileName(commentEntity.getMemberEntity().getMemberProfileName());

        return commentDetailDTO;
    }





}
