package com.phl.cocolo.service;

import com.phl.cocolo.dto.OnClassDetailDTO;
import com.phl.cocolo.dto.OnClassSaveDTO;
import com.phl.cocolo.dto.OnClassUpdateDTO;
import com.phl.cocolo.entity.OnClassEntity;
import com.phl.cocolo.repository.OnClassRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OnClassServiceImpl implements OnClassService{
    private final OnClassRepository or;

    @Override
    public Long save(OnClassSaveDTO onClassSaveDTO) {
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
