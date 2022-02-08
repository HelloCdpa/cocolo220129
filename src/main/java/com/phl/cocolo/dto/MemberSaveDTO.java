package com.phl.cocolo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberSaveDTO {
    @NotBlank(message = "이메일은 필수입니다.")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[@])[a-z@]{5,20}$",
            message = "이메일 @포함 5~20자 이내")
    private String memberEmail;

    @NotBlank(message = "필수입력")
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,20}",
            message = "비밀번호는 영문 대,소문자와 숫자, 특수기호가 적어도 1개 이상씩 포함된 8자 ~ 20자의 비밀번호여야 합니다.")
    private String memberPassword;

    @NotBlank(message = "필수입력")
    private String memberName;

//    @Pattern(regexp="/^\\d{3}-\\d{4}-\\d{4}$/", (오류난 부분 : / 요부분을 없앤다.)
//            message = "휴대폰 번호는 - 포함 3-4-4자리로 입력해주세요.")

    @Pattern(regexp = "^\\d{3}-\\d{3,4}-\\d{4}$", message = "휴대폰 번호는 - 포함 3-3(4)-4자리로 입력해주세요.")
    private String memberPhone;

    private String memberProfileName;
    private MultipartFile memberProfile;

    public MemberSaveDTO(String memberEmail, String memberPassword, String memberName, String memberPhone, String memberProfileName) {
        this.memberEmail = memberEmail;
        this.memberPassword = memberPassword;
        this.memberName = memberName;
        this.memberPhone = memberPhone;
        this.memberProfileName = memberProfileName;
    }
}