package com.phl.cocolo.dto;

import com.phl.cocolo.entity.BoardEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardDetailDTO {
    private Long boardId;
    private Long memberId;
    private Long cateId;


    private String categoryName;
    private String boardWriter;
    private String boardTitle;
    private String boardContents;
    private String boardFileName;
    private LocalDateTime CreateBoardDate;
    private LocalDateTime UpdateBoardDate;
    private int boardHits;
    private int likeCount;


    public static BoardDetailDTO toBoardDetailDTO(BoardEntity boardEntity){
        BoardDetailDTO boardDetailDTO = new BoardDetailDTO();
        boardDetailDTO.setBoardId(boardEntity.getId());
        boardDetailDTO.setMemberId(boardEntity.getMemberEntity().getId());
        boardDetailDTO.setCateId(boardEntity.getCategoryEntity().getId());

        boardDetailDTO.setCategoryName(boardEntity.getCategoryEntity().getCateName());

        boardDetailDTO.setBoardWriter(boardEntity.getBoardWriter());
        boardDetailDTO.setBoardTitle(boardEntity.getBoardTitle());
        boardDetailDTO.setBoardContents(boardEntity.getBoardContents());
        boardDetailDTO.setBoardFileName(boardEntity.getBoardFileName());
        boardDetailDTO.setCreateBoardDate(boardEntity.getCreatTime());
        boardDetailDTO.setBoardHits(boardEntity.getBoardHits());
        boardDetailDTO.setLikeCount(boardEntity.getLikeCount());

        return boardDetailDTO;

    }

}
