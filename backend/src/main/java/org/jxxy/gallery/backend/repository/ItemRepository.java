package org.jxxy.gallery.backend.repository;

import org.jxxy.gallery.backend.domain.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<ItemEntity, Integer> {
}
