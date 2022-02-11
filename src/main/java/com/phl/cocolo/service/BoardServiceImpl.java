package com.phl.cocolo.service;

import com.phl.cocolo.common.PagingConst;
import com.phl.cocolo.dto.BoardDetailDTO;
import com.phl.cocolo.dto.BoardPagingDTO;
import com.phl.cocolo.dto.BoardSaveDTO;
import com.phl.cocolo.dto.BoardUpdateDTO;
import com.phl.cocolo.entity.BoardEntity;
import com.phl.cocolo.entity.CategoryEntity;
import com.phl.cocolo.entity.MemberEntity;
import com.phl.cocolo.repository.BoardRepository;
import com.phl.cocolo.repository.CategoryRepository;
import com.phl.cocolo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
    private final BoardRepository br;
    private final MemberRepository mr;
    private  final CategoryRepository ctr;

    @Override
    public Long save(BoardSaveDTO boardSaveDTO) throws IllegalStateException, IOException {

        MultipartFile b_file = boardSaveDTO.getBoardFile();
        String b_filename = b_file.getOriginalFilename();
        b_filename = System.currentTimeMillis() + "-" + b_filename;
        // 파일 저장하기
        String savePath = "D:\\development_Phl\\source\\springboot\\MemberBoardProject\\src\\main\\resources\\board_uploadfile\\" + b_filename;
        if (!b_file.isEmpty()) {
            b_file.transferTo(new File(savePath));
        }
        boardSaveDTO.setBoardFileName(b_filename);

        MemberEntity memberEntity = mr.findById(boardSaveDTO.getMemberId()).get();
        CategoryEntity categoryEntity = ctr.findById(boardSaveDTO.getCateId()).get();
        BoardEntity boardEntity = BoardEntity.toBoardEntitySave(boardSaveDTO, memberEntity,categoryEntity);

        return br.save(boardEntity).getId();
    }
    @Override
    public Long saveTest(BoardSaveDTO boardSaveDTO){
        MemberEntity memberEntity = mr.findById(boardSaveDTO.getMemberId()).get();
        CategoryEntity categoryEntity = ctr.findById(boardSaveDTO.getCateId()).get();
        BoardEntity boardEntity = BoardEntity.toBoardEntitySave(boardSaveDTO, memberEntity,categoryEntity);

        return br.save(boardEntity).getId();
    }


    @Override
    @Transactional
    public BoardDetailDTO findById(Long boardId) {
        Optional<BoardEntity> optionalBoard = br.findById(boardId);
        BoardDetailDTO boardDetailDTO = BoardDetailDTO.toBoardDetailDTO(optionalBoard.get());
        br.boardHits(boardId);
        return boardDetailDTO;
    }

    @Override
    public Page<BoardPagingDTO> paging(Pageable pageable) {
        int page = pageable.getPageNumber();
        page = (page == 1) ? 0 : (page - 1);

        Page<BoardEntity> boardEntities = br.findAll(PageRequest.of(page, PagingConst.PAGE_LIMIT, Sort.by(Sort.Direction.DESC, "id")));

        Page<BoardPagingDTO> boardList = boardEntities.map(
                board -> new BoardPagingDTO(board.getId(),
                        board.getBoardWriter(),
                        board.getBoardTitle(),
                        board.getBoardHits())
        );

        return boardList;
    }

    @Override
    public Long update(BoardUpdateDTO boardUpdateDTO) throws IllegalStateException, IOException {
        MultipartFile b_file = boardUpdateDTO.getBoardFile();
        String b_filename = b_file.getOriginalFilename();
        b_filename = System.currentTimeMillis() + "-" + b_filename;
        // 파일 저장하기
        String savePath = "D:\\development_Phl\\source\\springboot\\MemberBoardProject\\src\\main\\resources\\board_uploadfile\\" + b_filename;
        if (!b_file.isEmpty()) {
            b_file.transferTo(new File(savePath));
        }
        boardUpdateDTO.setBoardFileName(b_filename);

        MemberEntity memberEntity = mr.findById(boardUpdateDTO.getMemberId()).get();
        CategoryEntity categoryEntity = ctr.findById(boardUpdateDTO.getCateId()).get();
        BoardEntity boardEntity = BoardEntity.toBoardUpdateEntity(boardUpdateDTO, memberEntity,categoryEntity);

        return br.save(boardEntity).getId();

    }

    @Override
    public void deleteById(Long boardId) {
        br.deleteById(boardId);
    }

    @Override
    public Page<BoardPagingDTO> search(String type, String keyword, Pageable pageable) {
        int page = pageable.getPageNumber();
        page = (page == 1) ? 0 : (page - 1);

        Page<BoardEntity> searchEntity = null;
        br.findAll(PageRequest.of(page, PagingConst.PAGE_LIMIT, Sort.by(Sort.Direction.DESC, "id")));


        if (type.equals("boardTitle")){
            searchEntity = br.findByBoardTitleContainingIgnoreCase(keyword,PageRequest.of(page, PagingConst.PAGE_LIMIT, Sort.by(Sort.Direction.DESC, "id")));
        } else {
            searchEntity = br.findByBoardWriterContainingIgnoreCase(keyword,PageRequest.of(page, PagingConst.PAGE_LIMIT, Sort.by(Sort.Direction.DESC, "id")));
        }

        Page<BoardPagingDTO> boardList = searchEntity.map(
                board -> new BoardPagingDTO(board.getId(),
                        board.getBoardWriter(),
                        board.getBoardTitle(),
                        board.getBoardHits())
        );

        return boardList;
    }



}
