package com.phl.cocolo.service;

import com.phl.cocolo.dto.OnClassDetailDTO;
import com.phl.cocolo.dto.OnClassSaveDTO;
import com.phl.cocolo.dto.OnClassUpdateDTO;
import com.phl.cocolo.entity.OnClassEntity;
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
    public void update(OnClassUpdateDTO onClassUpdateDTO) {
        or.save(OnClassEntity.toOnClassUpdateEntity(onClassUpdateDTO));
    }
}
