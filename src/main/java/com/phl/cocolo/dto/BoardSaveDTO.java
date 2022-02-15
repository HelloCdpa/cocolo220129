package com.phl.cocolo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardSaveDTO {

    private Long memberId;
    private Long cateId;

    private String boardWriter;
    @NotBlank(message = "제목을 입력해주세요.")
    private String boardTitle;
    @NotBlank(message = "내용을 입력해주세요.")
    private String boardContents;
    private String boardFileName;
    private MultipartFile boardFile;


    public BoardSaveDTO(Long memberId, String boardWriter, String boardPassword, String boardTitle, String boardContents, String boardFileName) {
        this.memberId = memberId;
        this.boardWriter = boardWriter;
        this.boardTitle = boardTitle;
        this.boardContents = boardContents;
        this.boardFileName = boardFileName;
    }
}
