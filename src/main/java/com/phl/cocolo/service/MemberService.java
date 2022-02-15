package com.phl.cocolo.service;

import com.phl.cocolo.dto.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public interface MemberService {
    Long save(MemberSaveDTO memberSaveDTO) throws IllegalStateException, IOException;

    boolean findByEmail(MemberLoginDTO memberLoginDTO);

    List<MemberDetailDTO> findAll();

    MemberDetailDTO findById(Long memberId);

    Long findByMemberId(String memberEmail);

    String emailDuplication(String memberEmail);

    void deleteById(Long memberId);


    void update(MemberUpdateDTO memberUpdateDTO) throws IllegalStateException, IOException;

    Long saveTest(MemberSaveDTO memberSaveDTO);

    String getKaKaoAccessToken(String code);

    String getUserInfo(String access_token);

    String NickNameDuplication(String memberNickName);

    void pointCharge(PointSaveDTO pointSaveDTO);
}

