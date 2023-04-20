package com.data.data.repository;

import com.data.data.entity.MountainPeak;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MountainPeakRepository extends JpaRepository<MountainPeak, Long> {
}
