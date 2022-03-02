package com.phl.cocolo;

import com.phl.cocolo.dto.OnClassSaveDTO;
import com.phl.cocolo.dto.StudySaveDTO;
import com.phl.cocolo.service.MemberService;
import com.phl.cocolo.service.StudyService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
public class StudyTest {
    @Autowired
    private StudyService ss;

    @Test
    @DisplayName("테스트스터디30개 등록")
    public void testStudySave(){
        IntStream.rangeClosed(1, 30).forEach(i->{
            StudySaveDTO studySaveDTO = new StudySaveDTO((long)1, 5, 0, "StudyTitle"+i, "StudyContents"+i,"서울","취업/면접");
            ss.save(studySaveDTO);
        });
    }








}
