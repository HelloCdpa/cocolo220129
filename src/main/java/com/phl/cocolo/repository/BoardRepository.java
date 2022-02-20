package com.phl.cocolo.repository;

import com.phl.cocolo.entity.BoardEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BoardRepository extends JpaRepository<BoardEntity,Long> {
    Page<BoardEntity> findByBoardTitleContainingIgnoreCase(String keyword, Pageable paging);
    Page<BoardEntity> findByBoardWriterContainingIgnoreCase(String keyword,Pageable paging);
    Page<BoardEntity> findByCategoryEntity_Id(Long cateId,Pageable paging);


    //조회수를 올렸는데 보드업데이트 시간이 수정되는 기이한 현상을 보완해줌
    //native query
    // jpql(java persistence query language) 네이티브 쿼리를 쓸 수 있도록 도와줌
    // jpa 를 보조해주는 것
    // @Query(value = ~ : 반드시 테이블에 대한 약칭을 써야 함 (BoardEntity as b) entity 기준
    //query dsl

    @Modifying
    @Query(value = "update BoardEntity b set b.boardHits = b.boardHits+1 where b.id = :boardId")
    int boardHits(@Param("boardId") Long boardId);

    @Modifying
    @Query(value = "update BoardEntity b set b.likeCount = b.likeCount+1 where b.id = :boardId")
    int plusLike(@Param("boardId") Long boardId);

    @Modifying
    @Query(value = "update BoardEntity b set b.likeCount = b.likeCount-1 where b.id = :boardId")
    int minusLike(@Param("boardId") Long boardId);

}

