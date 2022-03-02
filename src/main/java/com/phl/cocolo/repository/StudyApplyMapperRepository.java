package com.phl.cocolo.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import java.util.Map;

@Mapper
public interface StudyApplyMapperRepository {
    //applyStates update
    @Update("update study_apply_table set apply_state = 1 where apply_id = #{apply_id}")
    void update(Long apply_id);

}
