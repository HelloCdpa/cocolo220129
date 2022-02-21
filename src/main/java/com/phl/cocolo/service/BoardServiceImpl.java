package com.phl.cocolo.service;

import com.phl.cocolo.common.PagingConst;
import com.phl.cocolo.dto.*;
import com.phl.cocolo.entity.BoardEntity;
import com.phl.cocolo.entity.CategoryEntity;
import com.phl.cocolo.entity.LikeEntity;
import com.phl.cocolo.entity.MemberEntity;
import com.phl.cocolo.repository.BoardRepository;
import com.phl.cocolo.repository.CategoryRepository;
import com.phl.cocolo.repository.LikeRepository;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
    private final BoardRepository br;
    private final MemberRepository mr;
    private  final CategoryRepository ctr;
    private  final LikeRepository lr;


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
    //관리자 카테고리 저장
    @Override
    public Long cateSave(CategorySaveDTO categorySaveDTO) {
        CategoryEntity categoryEntity = CategoryEntity.toCategorySaveEntity(categorySaveDTO);
        return ctr.save(categoryEntity).getId();
    }
    // 카테고리별로 보기 기능
    @Override
    public Page<BoardPagingDTO> findCate(Long cateId, Pageable pageable) {
        int page = pageable.getPageNumber();
        page = (page == 1) ? 0 : (page - 1);
        //비워놓기
        Page<BoardEntity> cateEntity = null;
        br.findAll(PageRequest.of(page, PagingConst.PAGE_LIMIT, Sort.by(Sort.Direction.DESC, "id")));

        // 카테고리 아이디로 게시물 찾아 넣기
        cateEntity = br.findByCategoryEntity_Id(cateId,PageRequest.of(page, PagingConst.PAGE_LIMIT, Sort.by(Sort.Direction.DESC, "id")));

        // BoardPaging DTO 화 시키기
        Page<BoardPagingDTO> boardList = cateEntity.map(
                board -> new BoardPagingDTO(board.getId(),
                        board.getBoardWriter(),
                        board.getBoardTitle(),
                        board.getBoardHits(),
                        board.getLikeCount())
        );

        return boardList;
    }

    @Override
    public List<CategoryDetailDTO> cateFindAll() {
        List<CategoryEntity> categoryEntityList = ctr.findAll();
        List<CategoryDetailDTO> categoryList = new ArrayList<>();
        for(CategoryEntity c : categoryEntityList){
            categoryList.add(CategoryDetailDTO.toCategoryDetailDTO(c));
        }

        return categoryList;
    }

    @Override
    public int findLike(Long boardId, Long memberId) {
        // 저장된 DTO 가 없다면 0, 있다면 1

        Optional<LikeEntity> findLike = lr.findByBoardEntity_IdAndMemberEntity_Id(boardId, memberId);


        if (findLike.isEmpty()){
            return 0;
        }else {

            return 1;
        }
    }

    @Transactional
    @Override
    public int saveLike(Long boardId, Long memberId) {
        Optional<LikeEntity> findLike = lr.findByBoardEntity_IdAndMemberEntity_Id(boardId, memberId);

        System.out.println(findLike.isEmpty());

        if (findLike.isEmpty()){
            MemberEntity memberEntity = mr.findById(memberId).get();
            BoardEntity boardEntity = br.findById(boardId).get();

            LikeEntity likeEntity = LikeEntity.toLikeEntity(memberEntity, boardEntity);
            lr.save(likeEntity);
            br.plusLike(boardId);
            return 1;
        }else {
            lr.deleteByBoardEntity_IdAndMemberEntity_Id(boardId, memberId);
            br.minusLike(boardId);
            return 0;

        }

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
                        board.getBoardHits(),
                        board.getLikeCount())
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

    //검색 작성자 제목 검색
    @Override
    public Page<BoardPagingDTO> search(String type, String keyword, Pageable pageable) {
        int page = pageable.getPageNumber();
        page = (page == 1) ? 0 : (page - 1);

        // Page<BoardEntity>에 검색한 리스트들 찾아 넣기
        Page<BoardEntity> searchEntity = null;
        br.findAll(PageRequest.of(page, PagingConst.PAGE_LIMIT, Sort.by(Sort.Direction.DESC, "id")));

        //검색 타입이 제목이라면
        if (type.equals("boardTitle")){
            searchEntity = br.findByBoardTitleContainingIgnoreCase(keyword,PageRequest.of(page, PagingConst.PAGE_LIMIT, Sort.by(Sort.Direction.DESC, "id")));
        } else {
            //검색 타입이 작성자라면
            searchEntity = br.findByBoardWriterContainingIgnoreCase(keyword,PageRequest.of(page, PagingConst.PAGE_LIMIT, Sort.by(Sort.Direction.DESC, "id")));
        }
        //가져온 내용 DTO 리스트에 담기
        Page<BoardPagingDTO> boardList = searchEntity.map(
                board -> new BoardPagingDTO(board.getId(),
                        board.getBoardWriter(),
                        board.getBoardTitle(),
                        board.getBoardHits(),
                        board.getLikeCount())
        );

        return boardList;
    }



}
