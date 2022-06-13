package com.example.Tissue_back.repository.member;

import com.example.Tissue_back.entity.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    @Query("select m from Member m where m.memberId = :id")
    Optional<Member> findByMemberId(@Param("id") String memberId);

    @Query("select m from Member m where m.memberName = :name")
    Optional<Member> findByMemberName(@Param("name") String memberName);

}
