package com.phl.cocolo;

import com.phl.cocolo.dto.BoardSaveDTO;
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

import java.util.stream.IntStream;

@SpringBootTest
public class BoardTest {
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
    @Transactional
    @Rollback(value = false)
    @DisplayName("회원게시물작성테스트")
    public void BoardSaveTest() {
        IntStream.rangeClosed(1, 10).forEach(i -> {
            BoardSaveDTO boardSaveDTO = new BoardSaveDTO((long) 1, (long) 2, "제목" + i, "내용" + i, "파일이름" + i);
            bs.saveTest(boardSaveDTO);
        });
    }
}