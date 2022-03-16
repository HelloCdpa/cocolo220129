package com.phl.cocolo.service;

import com.phl.cocolo.dto.*;
import com.phl.cocolo.entity.MyClassEntity;
import com.phl.cocolo.entity.OnClassEntity;
import com.phl.cocolo.repository.CartRepository;
import com.phl.cocolo.repository.MemberRepository;
import com.phl.cocolo.repository.MyClassRepository;
import com.phl.cocolo.repository.OnClassRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OnClassServiceImpl implements OnClassService{
    private final OnClassRepository or;
    private final MemberRepository mr;
    private final MyClassRepository mcr;
    private final CartRepository cr;
    private final CartService cs;

    @Override
    public Long save(OnClassSaveDTO onClassSaveDTO) throws IllegalStateException, IOException {

        MultipartFile o_file = onClassSaveDTO.getOnClassFile();
        String o_filename = o_file.getOriginalFilename();
        o_filename = System.currentTimeMillis() + "-" + o_filename;
        // 파일 저장하기
        String savePath = "D:\\development_Phl\\source\\springboot\\CocoloProject\\src\\main\\resources\\static\\class_upload\\" + o_filename;
        if (!o_file.isEmpty()) {
            o_file.transferTo(new File(savePath));
        }
        onClassSaveDTO.setOnClassFileName(o_filename);

        return or.save(OnClassEntity.toOnClassSaveEntity(onClassSaveDTO)).getId();
    }

    @Override
    public Long saveTest(OnClassSaveDTO onClassSaveDTO){
        return or.save(OnClassEntity.toOnClassSaveEntity(onClassSaveDTO)).getId();
    }


    @Override
    public OnClassDetailDTO findById(Long onClassId) {
        Optional<OnClassEntity> optionalOnClassEntity = or.findById(onClassId);
        OnClassDetailDTO onClassDetailDTO = OnClassDetailDTO.toOnClassDetailDTO(optionalOnClassEntity.get());
        return onClassDetailDTO;
    }

    @Override
    public void deleteById(Long onClassId) {
        or.deleteById(onClassId);
    }

    @Override
    public List<OnClassDetailDTO> findAll() {
        List<OnClassEntity> onClassEntityList = or.findAll();
        List<OnClassDetailDTO> onClassList = new ArrayList<>();

        for(OnClassEntity o : onClassEntityList){
            onClassList.add(OnClassDetailDTO.toOnClassDetailDTO(o));
        }


        return onClassList;
    }


    @Override
    public void payment(Long onClassId,Long memberId) {
        // 회원의 온라인 클래스 : : my_class_table 여기에 onClassId 와 memberId를 넣음
        //    member_id
        //    onClass_id
        //    CartDetailDTO 에서 쓸 것
        //    Long memberId;
        //    Long onClassId;
        // 나의 강의에 저장(구매한 회원, 구매한강의)
        // Entity 로 변환시켜서 DB에 저장시키기
        for(int i=0; i<cs.findByMemberId(memberId).size(); i++){
            mcr.save(MyClassEntity.toMyClassSaveEntity(mr.findById(memberId).get(),
                    or.findById(cs.findByMemberId(memberId).get(i).getOnClassId()).get() ));
        }





        //장바구니 비우기
        cr.deleteAllByMemberEntity_Id(memberId);

    }

    @Override
    public List<MyClassDetailDTO> myClassList(Long memberId) {
        //my course 에서 memberID 를 찾아 회원의 온라인클래스 조회
        List<MyClassEntity> myClassEntityList =mcr.findAllByMemberEntity_Id(memberId);

        List<MyClassDetailDTO> myClassList = new ArrayList<>();

        for (MyClassEntity m : myClassEntityList){
            myClassList.add(MyClassDetailDTO.toMyClassDetailDTO(m));
        }




        return myClassList;
    }

    @Override
    public boolean myClassCheck(Long memberId, Long onClassId) {
        if(mcr.findByMemberEntity_IdAndOnClassEntity_Id(memberId,onClassId).isEmpty()){
            return false;
        }else {
            return true;
        }

    }
}
