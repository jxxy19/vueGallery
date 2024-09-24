package org.jxxy.gallery.backend.repository;

import org.jxxy.gallery.backend.domain.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<CartEntity, Integer> {

    List<CartEntity> findByMemberId(int memberId);

    CartEntity findByMemberIdAndItemId(int memberId, int itemId);

}
