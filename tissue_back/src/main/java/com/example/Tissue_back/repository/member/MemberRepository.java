package com.example.Tissue_back.repository.member;

import com.example.Tissue_back.entity.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    @Query("select m from Member m where m.memberId = :id")
    Optional<Member> findByMemberId(@Param("id") String memberId);

    @Query("select m from Member m where m.memberName = :name")
    Optional<Member> findByMemberName(@Param("name") String memberName);

    Optional<Member> findByRefreshToken (String token);

    //임지훈
    @Transactional
    @Modifying
    @Query("update Member m set m.memberMileage = m.memberMileage - :usedMileage where m.memberId = :memberId")
    void updateMileage(@Param("memberId") String memberId, @Param("usedMileage")Integer usedMileage);

}
