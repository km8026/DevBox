package com.o2b2.devbox_server.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.o2b2.devbox_server.gatherMate.entity.GatherMate;
import com.o2b2.devbox_server.gatherMate.like.entity.Like;
import com.o2b2.devbox_server.greeting.entity.Greeting;
import com.o2b2.devbox_server.message.model.MsgReciverEntity;
import com.o2b2.devbox_server.message.model.MsgSenderEntity;
import com.o2b2.devbox_server.project.model.ProEntity;
import com.o2b2.devbox_server.project.model.ProLike;
import com.o2b2.devbox_server.reference.model.Reference;
import com.o2b2.devbox_server.reservation.model.Reservation;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;



@Entity
@Data
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    private String name;
    private String nickname;
    private String password;

    // oAuth2에서 추가 되는 정보
    private String provider; // google, naver, kakao 인지 어느 소셜 로그인했는지
    private String providerId;// sub=101926511570168785716

    // @ManyToOne(fetch = FetchType.EAGER)
    // @JoinColumn(name = "role_id", nullable = false)
    // private Role role;
    private String role;

    private String field;

    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    List<ProEntity> proEntitys = new ArrayList<>();


    // @OneToMany(mappedBy = "userEntity", cascade = CascadeType.REMOVE)
    // List<EduEntity> eduEntitys = new ArrayList<>();
    
    @OneToMany(mappedBy = "receiver")
    @JsonIgnore
    List<MsgReciverEntity> MsgEntitys = new ArrayList<>();
    
    @OneToMany(mappedBy = "sender")
    @JsonIgnore
    List<MsgSenderEntity> MsgSenderEntitys = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    List<ProLike> proLikes = new ArrayList<>();

    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    List<Reference> ReferenceLists = new ArrayList<>();

    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    List<Reservation> ReservationLists = new ArrayList<>();

    // 모여라메이트 좋아요
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Like> likes = new ArrayList<>();

    // 모여라메이트
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<GatherMate> gatherMates = new ArrayList<>();

    // 모여라메이트
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Greeting> greetings = new ArrayList<>();
}