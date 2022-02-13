package com.phl.cocolo;

import com.phl.cocolo.dto.MemberDetailDTO;
import com.phl.cocolo.dto.MemberSaveDTO;
import com.phl.cocolo.repository.BoardRepository;
import com.phl.cocolo.repository.CommentRepository;
import com.phl.cocolo.repository.MemberRepository;
import com.phl.cocolo.service.BoardService;
import com.phl.cocolo.service.CommentService;
import com.phl.cocolo.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class MemberTest {
    @Autowired
    private MemberService ms;
    @Autowired
    private CommentService cs;
    @Autowired
    private BoardService bs;

    @Autowired
    private MemberRepository mr;
    @Autowired
    private CommentRepository cr;
    @Autowired
    private BoardRepository br;

    @Test
    @DisplayName("테스트 회원 30명 등록")
    public void testMemberSave(){

        IntStream.rangeClosed(1, 30).forEach(i->{
            MemberSaveDTO memberSaveDTO = new MemberSaveDTO("이메일"+i, "비번"+i, "이름"+i, "전화번호"+i, "프로필"+i);
            ms.saveTest(memberSaveDTO);
        });
    }

    @Test
    @Transactional
    @Rollback(value = false)
    @DisplayName("회원전체보기테스트")
    public void MemberFindAllTest(){
        List<MemberDetailDTO> memberList = ms.findAll();

        assertThat(memberList.size()).isEqualTo(30);
    }

    @Test
    @Transactional
    @Rollback
    @DisplayName("회원상세보기테스트")
    public void MemberFindById(){
        String email = "상세보기 이메일";
        String password = "상세보기 비번";
        String name = "상세보기 이름";
        String phone = "상세보기 전화번호";
        String file = "상세보기 프로필사진";

        MemberSaveDTO memberSaveDTO = new MemberSaveDTO(email,password,name,phone,file);
        Long memberSaveId = ms.saveTest(memberSaveDTO);

        MemberDetailDTO memberDetailDTO = ms.findById(memberSaveId);

        assertThat(memberSaveId).isEqualTo(memberDetailDTO.getMemberId());

    }










}
