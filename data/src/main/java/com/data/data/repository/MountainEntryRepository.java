package com.data.data.repository;

import com.data.data.entity.MountainEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MountainEntryRepository extends JpaRepository<MountainEntry, Long> {
}
