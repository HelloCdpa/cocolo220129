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
        IntStream.rangeClosed(1, 3).forEach(i->{
            StudySaveDTO studySaveDTO = new StudySaveDTO((long)2, 4, 3, "스프링 같이 공부해요!", "얼른 모집"+i,"서울","자바");
            ss.save(studySaveDTO);
        });
    }








}
