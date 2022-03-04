package com.phl.cocolo.entity;

import com.phl.cocolo.dto.OnClassSaveDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "onClass_table")
public class OnClassEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "onClass_id")
    private Long id;

    @Column
    private String onClassTeacher;

    @Column
    private String onClassTitle;

    @Column(length=1000)
    private String onClassContents;

    @Column
    private String onClassCate;

    @Column
    private String onClassIntro;

    @Column
    private int onClassPrice;

    @Column
    private String onClassFileName;

    @Column(columnDefinition = "integer default 0")
    private int onClassWishListCount;





    @OneToMany(mappedBy = "onClassEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReviewEntity> reviewEntityList = new ArrayList<>();
    @OneToMany(mappedBy = "onClassEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CourseEntity> courseEntityList = new ArrayList<>();
    @OneToMany(mappedBy = "onClassEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MyClassEntity> myClassEntityList = new ArrayList<>();
    @OneToMany(mappedBy = "onClassEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartEntity> cartEntityList = new ArrayList<>();
    @OneToMany(mappedBy = "onClassEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WishListEntity> wishListEntityList = new ArrayList<>();


    public static OnClassEntity toOnClassSaveEntity(OnClassSaveDTO onClassSaveDTO){
        OnClassEntity onClassEntity = new OnClassEntity();

        onClassEntity.setOnClassTeacher(onClassSaveDTO.getOnClassTeacher());
        onClassEntity.setOnClassTitle(onClassSaveDTO.getOnClassTitle());
        onClassEntity.setOnClassContents(onClassSaveDTO.getOnClassContents());
        onClassEntity.setOnClassCate(onClassSaveDTO.getOnClassCate());
        onClassEntity.setOnClassIntro(onClassSaveDTO.getOnClassIntro());
        onClassEntity.setOnClassPrice(onClassSaveDTO.getOnClassPrice());
        onClassEntity.setOnClassFileName(onClassSaveDTO.getOnClassFileName());

        return onClassEntity;
    }


}
