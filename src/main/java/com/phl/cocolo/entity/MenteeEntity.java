package com.phl.cocolo.entity;

import com.phl.cocolo.dto.MenteeUpdateDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Getter
@Setter
@Table(name = "mentee_table")
public class MenteeEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mentee_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity memberEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mentoring_id")
    private MentoringEntity mentoringEntity;


    @Column(columnDefinition = "integer default 0")
    private int menteeCount;



    public static MenteeEntity toMenteeSaveEntity(MemberEntity memberEntity,MentoringEntity mentoringEntity){
        MenteeEntity menteeEntity = new MenteeEntity();

        menteeEntity.setMemberEntity(memberEntity);
        menteeEntity.setMentoringEntity(mentoringEntity);

        return menteeEntity;
    }

    public static MenteeEntity toMenteeUpdateEntity(MenteeUpdateDTO menteeUpdateDTO, MemberEntity memberEntity, MentoringEntity mentoringEntity){
        MenteeEntity menteeEntity = new MenteeEntity();

        menteeEntity.setMemberEntity(memberEntity);
        menteeEntity.setMentoringEntity(mentoringEntity);
        menteeEntity.setMenteeCount(menteeUpdateDTO.getMenteeCount()+1);

        return menteeEntity;
    }







}
