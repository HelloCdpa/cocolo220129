package com.phl.cocolo.entity;

import com.phl.cocolo.dto.BoardSaveDTO;
import com.phl.cocolo.dto.BoardUpdateDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "board_table")
public class BoardEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity memberEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private CategoryEntity categoryEntity;

    @Column
    private String boardWriter;

    @Column
    private String boardTitle;

    @Column(length=1000)
    private String boardContents;

    @Column(columnDefinition = "integer default 0")
    private int boardHits;

    @Column(columnDefinition = "integer default 0")
    private int likeCount;

    @Column
    private String boardFileName;

    @OneToMany(mappedBy = "boardEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CommentEntity> commentEntityList = new ArrayList<>();
    @OneToMany(mappedBy = "boardEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LikeEntity> likeEntityList = new ArrayList<>();

    public static BoardEntity toBoardEntitySave(BoardSaveDTO boardSaveDTO, MemberEntity memberEntity, CategoryEntity categoryEntity){
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setMemberEntity(memberEntity);
        boardEntity.setCategoryEntity(categoryEntity);

        boardEntity.setBoardWriter(memberEntity.getMemberNickName());
        boardEntity.setBoardTitle(boardSaveDTO.getBoardTitle());
        boardEntity.setBoardContents(boardSaveDTO.getBoardContents());
        boardEntity.setBoardFileName(boardSaveDTO.getBoardFileName());

        return boardEntity;


    }

    public static BoardEntity toBoardUpdateEntity(BoardUpdateDTO boardUpdateDTO, MemberEntity memberEntity,CategoryEntity categoryEntity){
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setId(boardUpdateDTO.getBoardId());
        boardEntity.setMemberEntity(memberEntity);
        boardEntity.setCategoryEntity(categoryEntity);

        boardEntity.setBoardWriter(memberEntity.getMemberNickName());
        boardEntity.setBoardTitle(boardUpdateDTO.getBoardTitle());
        boardEntity.setBoardContents(boardUpdateDTO.getBoardContents());
        boardEntity.setBoardFileName(boardUpdateDTO.getBoardFileName());
        boardEntity.setLikeCount(boardUpdateDTO.getLikeCount());

        return boardEntity;
    }



}
