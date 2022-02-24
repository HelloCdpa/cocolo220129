package com.phl.cocolo.dto;

import com.phl.cocolo.entity.KakaoEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KakaoDetailDTO {
    private String kakaoEmail;

    public static KakaoDetailDTO toKakaoDetailDTO(KakaoEntity kakaoEntity){
        KakaoDetailDTO kakaoDetailDTO = new KakaoDetailDTO();
        kakaoDetailDTO.setKakaoEmail(kakaoEntity.getMemberEmail());
        return kakaoDetailDTO;
    }
}
