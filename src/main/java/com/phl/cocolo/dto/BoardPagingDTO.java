package com.phl.cocolo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardPagingDTO {
    private Long boardId;
    private String BoardWriter;
    private String BoardTitle;
    private int likeCount;
    private int boardHits;


}
