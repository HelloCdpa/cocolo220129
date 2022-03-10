package com.phl.cocolo.repository;

import com.phl.cocolo.dto.MemberUpdateDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import java.util.Map;

@Mapper
public interface MemberMapperRepository {
    //회원 포인트 충전
    @Update("update member_table set member_point = member_point + #{member_point} where member_id = #{member_id}")
    void pointCharge(Map<String, Object> memberPointUpdate);

    @Update("update member_table set member_phone = #{memberPhone}, member_profile_name = #{memberProfileName},member_level = #{memberLevel},member_interesting = #{memberInteresting} where member_id = #{memberId}")
    void memberUpdate(MemberUpdateDTO memberUpdateDTO);


//    //회원목록 출력
//    List<MemberDetailDTO> memberList();
//    //회원가입
//    void save(MemberSaveDTO memberSaveDTO);
//
//    //mapper를 호출하지 않고 여기서 쿼리까지 수행하는 방식
//    @Select("select * from member_table")
//    List<MemberDetailDTO> memberList2();
//
//    @Insert("insert into member_table(member_email,member_password,member_name) values (#{member_email},#{member_password},#{member_name})")
//    void save2(MemberSaveDTO memberSaveDTO);
}
