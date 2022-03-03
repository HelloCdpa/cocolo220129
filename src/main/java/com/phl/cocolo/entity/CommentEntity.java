package com.phl.cocolo.entity;

import com.phl.cocolo.dto.CommentSaveDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "comment_table")
public class CommentEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private BoardEntity boardEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity memberEntity;

    @Column
    private String commentWriter;

    @Column
    private String commentContents;


    public static CommentEntity toCommentEntity(CommentSaveDTO commentSaveDTO, BoardEntity boardEntity, MemberEntity memberEntity){
        CommentEntity commentEntity = new CommentEntity();

        commentEntity.setBoardEntity(boardEntity);
        commentEntity.setMemberEntity(memberEntity);
        commentEntity.setCommentWriter(memberEntity.getMemberNickName());
        commentEntity.setCommentContents(commentSaveDTO.getCommentContents());

        return commentEntity;
    }
}
