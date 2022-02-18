package com.phl.cocolo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseUpdateDTO {
    private Long courseId;
    private Long onClassId;


    private String courseSection;
    private String courseTitle;
    private String courseContents;
    private String courseFileName;
    private MultipartFile courseFile;


}
