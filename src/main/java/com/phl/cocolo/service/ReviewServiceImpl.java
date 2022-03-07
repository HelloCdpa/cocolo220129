package com.phl.cocolo.service;

import com.phl.cocolo.dto.ReviewDetailDTO;
import com.phl.cocolo.dto.PointSaveDTO;
import com.phl.cocolo.dto.ReviewDetailDTO;
import com.phl.cocolo.dto.ReviewSaveDTO;
import com.phl.cocolo.entity.OnClassEntity;
import com.phl.cocolo.entity.ReviewEntity;
import com.phl.cocolo.entity.MemberEntity;
import com.phl.cocolo.repository.OnClassRepository;
import com.phl.cocolo.repository.ReviewRepository;
import com.phl.cocolo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService{
    private final ReviewRepository rr;
    private final OnClassRepository or;
    private final MemberRepository mr;
    private final MemberService ms;

    @Override
    public Long save(ReviewSaveDTO ReviewSaveDTO) {
        OnClassEntity onClassEntity = or.findById(ReviewSaveDTO.getOnClassId()).get();
        MemberEntity memberEntity = mr.findById(ReviewSaveDTO.getMemberId()).get();
        ReviewEntity reviewEntity = ReviewEntity.toReviewSaveEntity(ReviewSaveDTO, memberEntity,onClassEntity);
        // 댓글 포인트 적립
        PointSaveDTO pointSaveDTO = new PointSaveDTO(ReviewSaveDTO.getMemberId(), 5000,"리뷰 작성 적립");
        ms.pointCharge(pointSaveDTO);

        return rr.save(reviewEntity).getId();
    }

    @Override
    public List<ReviewDetailDTO> findAll(Long onClassId) {
        OnClassEntity OnClassEntity = or.findById(onClassId).get();
        List<ReviewEntity> ReviewEntityList = OnClassEntity.getReviewEntityList();
        List<ReviewDetailDTO> ReviewList = new ArrayList<>();
        for(ReviewEntity c: ReviewEntityList){
            ReviewList.add(ReviewDetailDTO.toReviewDetail(c));
        }

        return ReviewList;

    }

    @Override
    public void deleteById(Long reviewId) {
        rr.deleteById(reviewId);
    }

    @Override
    public boolean reviewCheck(Long memberId, Long onClassId) {
        if(rr.findByMemberEntity_IdAndOnClassEntity_Id(memberId,onClassId) == null){
            return false;
        }else {
            return true;
        }

    }
}
