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

    private String mentoringTitle;

    private String mentoringContents;

    private int mentoringPrice;
    private String mentoringCareer;
    private String mentoringCate;
    private int mentoringCount;

}
