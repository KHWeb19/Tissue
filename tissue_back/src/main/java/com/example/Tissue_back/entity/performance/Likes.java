package com.example.Tissue_back.entity.performance;

import com.example.Tissue_back.entity.member.Member;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
public class Likes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Likes_no;

    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn(name="member_no")
    private Member member;

    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn (name="performance_no")
    private Performance performance;


}
