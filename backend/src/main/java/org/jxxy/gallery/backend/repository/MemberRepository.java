package org.jxxy.gallery.backend.repository;

import org.jxxy.gallery.backend.domain.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<MemberEntity, Integer> {
    MemberEntity findByEmailAndPassword(String email, String password);
}
