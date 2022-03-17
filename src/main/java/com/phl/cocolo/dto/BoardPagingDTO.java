package com.phl.cocolo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardPagingDTO {
    private Long boardId;
    private String boardWriter;
    private String boardTitle;



    private int boardHits;
    private int likeCount;

    private LocalDateTime createBoardDate;

    private String cateName;


}
