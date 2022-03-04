package com.phl.cocolo.dto;

import com.phl.cocolo.entity.ReviewEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDetailDTO {
    private Long reviewId;
    private Long memberId;
    private Long onClassId;

    private int reviewStar;

    private String reviewContents;

    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    //리뷰에서 보여줄 것
    //작성자 닉네임, 별점, 내용, 작성날짜, 평균별점
    private String reviewWriter;
    private String writerProfileName;

    public static ReviewDetailDTO toReviewDetail(ReviewEntity reviewEntity) {
        ReviewDetailDTO reviewDetailDTO = new ReviewDetailDTO();

        reviewDetailDTO.setReviewId(reviewEntity.getId());
        reviewDetailDTO.setMemberId(reviewEntity.getMemberEntity().getId());
        reviewDetailDTO.setOnClassId(reviewEntity.getOnClassEntity().getId());

        reviewDetailDTO.setReviewWriter(reviewEntity.getMemberEntity().getMemberNickName());

        reviewDetailDTO.setReviewContents(reviewEntity.getReviewContents());
        reviewDetailDTO.setReviewStar(reviewEntity.getReviewStar());
        reviewDetailDTO.setCreateTime(reviewEntity.getCreatTime());
        reviewDetailDTO.setUpdateTime(reviewEntity.getUpdateTime());
        reviewDetailDTO.setWriterProfileName(reviewEntity.getMemberEntity().getMemberProfileName());

        return reviewDetailDTO;
    }
}
