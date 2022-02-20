package com.phl.cocolo.repository;

import com.phl.cocolo.dto.LikeSaveDTO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;



@Mapper
public interface LikeRepository{
    @Select("select * from like_table where board_id=#{boardId} and member_id=#{memberId}")
    LikeSaveDTO LikeCheck(Long boardId, Long memberId);

    @Delete("delete from like_table where board_id=#{boardId} and member_id=#{memberId}")
    void deleteLike(LikeSaveDTO likeSaveDTO);

    @Insert("insert into like_table (board_id,member_id) values(#{boardId},#{memberId})")
    void save(LikeSaveDTO likeSaveDTO);
}
