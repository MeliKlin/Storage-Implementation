package com.meli.storageimplementation.repository;

import com.meli.storageimplementation.models.Jewel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface JewelRepository extends JpaRepository<Jewel, UUID> {
}
