package com.phl.cocolo.repository;

import com.phl.cocolo.dto.MemberDetailDTO;
import com.phl.cocolo.dto.MemberSaveDTO;
import com.phl.cocolo.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface MemberRepository extends JpaRepository<MemberEntity,Long> {
    MemberEntity findByMemberEmail(String MemberEmail);

    MemberEntity findByMemberNickName(String memberNickName);

    //회원목록 출력
    List<MemberDetailDTO> memberList();
    //회원가입
    void save(MemberSaveDTO memberSaveDTO);

    //mapper를 호출하지 않고 여기서 쿼리까지 수행하는 방식
    @Select("select * from member_table")
    List<MemberDetailDTO> memberList2();

    @Insert("insert into member_table(member_email,member_password,member_name) values (#{member_email},#{member_password},#{member_name})")
    void save2(MemberSaveDTO memberSaveDTO);
}
