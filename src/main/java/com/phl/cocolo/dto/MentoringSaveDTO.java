package com.phl.cocolo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MentoringSaveDTO {
    private Long memberId;
    private Long cateId;

    @NotBlank(message = "제목을 입력해주세요.")
    private String mentoringTitle;
    @NotBlank(message = "내용을 입력해주세요.")
    private String mentoringContents;

    private int mentoringPrice;
    private String mentoringCareer;
    private String mentoringCate;
    private int mentoringCount;

}
