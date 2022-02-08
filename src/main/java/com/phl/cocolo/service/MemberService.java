package com.phl.cocolo.service;

import com.phl.cocolo.dto.MemberDetailDTO;
import com.phl.cocolo.dto.MemberLoginDTO;
import com.phl.cocolo.dto.MemberSaveDTO;
import com.phl.cocolo.dto.MemberUpdateDTO;

import java.io.IOException;
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

    public Long saveTest(MemberSaveDTO memberSaveDTO);
}

