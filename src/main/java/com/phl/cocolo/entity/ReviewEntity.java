package com.phl.cocolo.entity;

import com.phl.cocolo.dto.ReviewSaveDTO;
import com.phl.cocolo.dto.ReviewUpdateDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Getter
@Setter
@Table(name = "review_table")
public class ReviewEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity memberEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "onClass_id")
    private OnClassEntity onClassEntity;

    @Column
    private String reviewContents;

    @Column
    private int reviewStar;

    public static ReviewEntity toReviewSaveEntity(ReviewSaveDTO reviewSaveDTO,MemberEntity memberEntity,OnClassEntity onClassEntity){
        ReviewEntity reviewEntity = new ReviewEntity();

        reviewEntity.setMemberEntity(memberEntity);
        reviewEntity.setOnClassEntity(onClassEntity);

        reviewEntity.setReviewStar(reviewSaveDTO.getReviewStar());
        reviewEntity.setReviewContents(reviewSaveDTO.getReviewContents());

        return reviewEntity;
    }
    public static ReviewEntity toReviewUpdateEntity(ReviewUpdateDTO reviewUpdateDTO, MemberEntity memberEntity, OnClassEntity onClassEntity){
        ReviewEntity reviewEntity = new ReviewEntity();

        reviewEntity.setId(reviewUpdateDTO.getReviewId());

        reviewEntity.setMemberEntity(memberEntity);
        reviewEntity.setOnClassEntity(onClassEntity);

        reviewEntity.setReviewStar(reviewUpdateDTO.getReviewStar());
        reviewEntity.setReviewContents(reviewUpdateDTO.getReviewContents());

        return reviewEntity;
    }

}
