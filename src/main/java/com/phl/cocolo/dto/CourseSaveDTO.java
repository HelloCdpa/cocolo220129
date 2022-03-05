package com.phl.cocolo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseSaveDTO {
    private Long onClassId;

    private String courseSection;
    private String courseTitle;
    private String courseContents;
    private String courseFileName;
    private MultipartFile courseFile;

    public CourseSaveDTO(Long onClassId, String courseSection, String courseTitle, String courseContents, String courseFileName) {
        this.onClassId = onClassId;
        this.courseSection = courseSection;
        this.courseTitle = courseTitle;
        this.courseContents = courseContents;
        this.courseFileName = courseFileName;
    }

}
