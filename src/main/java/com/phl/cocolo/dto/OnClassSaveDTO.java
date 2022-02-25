package com.phl.cocolo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OnClassSaveDTO {

    private String onClassTeacher;
    private String onClassTitle;
    private String onClassContents;
    private String onClassCate;
    private String onClassIntro;
    private int onClassPrice;
    private String onClassFileName;
    private MultipartFile onClassFile;

    public OnClassSaveDTO(String onClassTeacher, String onClassTitle, String onClassContents, String onClassCate, String onClassIntro, int onClassPrice, String onClassFileName) {
        this.onClassTeacher = onClassTeacher;
        this.onClassTitle = onClassTitle;
        this.onClassContents = onClassContents;
        this.onClassCate = onClassCate;
        this.onClassIntro = onClassIntro;
        this.onClassPrice = onClassPrice;
        this.onClassFileName = onClassFileName;
    }
}
