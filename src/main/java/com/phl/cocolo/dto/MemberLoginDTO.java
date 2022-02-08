package com.phl.cocolo.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class MemberLoginDTO {

    @NotBlank(message = "이메일 필수입력")
    private String memberEmail;
    @NotBlank(message = "비밀번호 필수입력")
    private String memberPassword;
}
