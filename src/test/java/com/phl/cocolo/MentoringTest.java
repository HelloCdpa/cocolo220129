package com.phl.cocolo;

import com.phl.cocolo.dto.MentoringSaveDTO;
import com.phl.cocolo.dto.OnClassSaveDTO;
import com.phl.cocolo.repository.MentoringRepository;
import com.phl.cocolo.service.MemberService;
import com.phl.cocolo.service.MentoringService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
public class MentoringTest {
    @Autowired
    private MemberService ms;
    @Autowired
    private MentoringService mts;

    @Autowired
    private MentoringRepository mtr;

    @Test
    @DisplayName("테스트멘토링30개 등록")
    public void testMentoringSave(){
        IntStream.rangeClosed(1, 5).forEach(i->{
            MentoringSaveDTO mentoringSaveDTO = new MentoringSaveDTO((long)2, "혜린 멘토 입니다!"+i, "멘토내용"+i, 2000, "10년 경력"+i,"취업/면접",5);
            mts.save(mentoringSaveDTO);
        });
    }







}
