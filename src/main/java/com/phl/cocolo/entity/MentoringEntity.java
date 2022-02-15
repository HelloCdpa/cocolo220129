package com.phl.cocolo.entity;

import com.phl.cocolo.dto.MentoringSaveDTO;
import com.phl.cocolo.dto.MentoringUpdateDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "mentoring_table")
public class MentoringEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mentoring_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity memberEntity;

    @Column
    private String mentoringCate;

    @Column
    private String mentoringTitle;

    @Column
    private String mentoringContents;

    @Column(columnDefinition = "integer default 0")
    private int mentoringCount;

    @Column(columnDefinition = "integer default 0")
    private int mentoringPrice;

    @Column
    private String mentoringCareer;

    @OneToMany(mappedBy = "mentoringEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MenteeEntity> menteeEntityList = new ArrayList<>();


    public static MentoringEntity toMentoringSaveEntity(MentoringSaveDTO mentoringSaveDTO, MemberEntity memberEntity){
        MentoringEntity mentoringEntity = new MentoringEntity();

        mentoringEntity.setMemberEntity(memberEntity);

        mentoringEntity.setMentoringCate(mentoringSaveDTO.getMentoringCate());
        mentoringEntity.setMentoringTitle(mentoringSaveDTO.getMentoringTitle());
        mentoringEntity.setMentoringContents(mentoringSaveDTO.getMentoringContents());
        mentoringEntity.setMentoringCount(mentoringSaveDTO.getMentoringCount());
        mentoringEntity.setMentoringPrice(mentoringSaveDTO.getMentoringPrice());
        mentoringEntity.setMentoringCareer(mentoringSaveDTO.getMentoringCareer());

        return mentoringEntity;
    }
    public static MentoringEntity toMentoringUpdateEntity(MentoringUpdateDTO mentoringUpdateDTO, MemberEntity memberEntity){
        MentoringEntity mentoringEntity = new MentoringEntity();

        mentoringEntity.setId(mentoringUpdateDTO.getMentoringId());

        mentoringEntity.setMemberEntity(memberEntity);

        mentoringEntity.setMentoringCate(mentoringUpdateDTO.getMentoringCate());
        mentoringEntity.setMentoringTitle(mentoringUpdateDTO.getMentoringTitle());
        mentoringEntity.setMentoringContents(mentoringUpdateDTO.getMentoringContents());
        mentoringEntity.setMentoringCount(mentoringUpdateDTO.getMentoringCount());
        mentoringEntity.setMentoringPrice(mentoringUpdateDTO.getMentoringPrice());
        mentoringEntity.setMentoringCareer(mentoringUpdateDTO.getMentoringCareer());

        return mentoringEntity;
    }


}
