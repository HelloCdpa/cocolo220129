package com.phl.cocolo;

import com.phl.cocolo.dto.MemberDetailDTO;
import com.phl.cocolo.dto.MemberSaveDTO;
import com.phl.cocolo.dto.OnClassDetailDTO;
import com.phl.cocolo.dto.OnClassSaveDTO;
import com.phl.cocolo.repository.MemberRepository;
import com.phl.cocolo.repository.OnClassRepository;
import com.phl.cocolo.service.MemberService;
import com.phl.cocolo.service.OnClassService;
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
public class OnClassTest {
    @Autowired
    private MemberService ms;

    @Autowired
    private OnClassService os;

    @Autowired
    private MemberRepository mr;

    @Autowired
    private OnClassRepository or;

    @Test
    @DisplayName("테스트강의소개30개 등록")
    public void testMemberSave(){
        IntStream.rangeClosed(1, 30).forEach(i->{
            OnClassSaveDTO onClassSaveDTO = new OnClassSaveDTO("onClassTeacher"+i, "onClassTitle"+i, "onClassContents"+i, "onClassCate"+i, "onClassIntro"+i,20000,"기본.png");
            os.saveTest(onClassSaveDTO);
        });
    }

    @Test
    @Transactional
    @Rollback(value = false)
    @DisplayName("강의전체보기테스트")
    public void onClassFindAllTest(){
        List<OnClassDetailDTO> onClassDetailDTOS = os.findAll();

        assertThat(onClassDetailDTOS.size()).isEqualTo(28);
    }

    @Test
    @Transactional
    @Rollback
    @DisplayName("강의상세보기테스트")
    public void MemberFindById(){
        String onClassTeacher = "상세보기 선생님";
        String onClassTitle = "상세보기 제목";
        String onClassContents = "상세보기 내용";
        String onClassCate = "상세보기 카테고리";
        String onClassIntro = "상세보기 소개";
        int onClassPrice = 10000;
        String onClassFileName = "기본.png";

        OnClassSaveDTO onClassSaveDTO = new OnClassSaveDTO(onClassTeacher,onClassTitle,onClassContents,onClassCate,onClassIntro,onClassPrice,onClassFileName);
        Long onClasSaveId = os.saveTest(onClassSaveDTO);

        OnClassDetailDTO onClassDetailDTO = os.findById(onClasSaveId);

        assertThat(onClasSaveId).isEqualTo(onClassDetailDTO.getOnClassId());

    }




}
