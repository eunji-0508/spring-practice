package org.example.spring.repository;

import org.example.spring.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

// Member 클래스고, id가 Long이라서 <Member, Long>
public interface MemberRepository extends JpaRepository<Member, Long> {
}
